#!/usr/bin/env bash
set -euo pipefail

source "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/lib/common.sh"
enter_project_root

ERRORS=0
REPORT_FILE="docs/quality/workflow/workflow-validation-report.json"
FINDINGS_FILE="$(mktemp)"

ensure_quality_dir "workflow"

record_error() {
  local message="$1"
  echo "$message"
  printf '%s\n' "$message" >> "$FINDINGS_FILE"
  ERRORS=$((ERRORS + 1))
}

check_job() {
  local file="$1"
  local job="$2"
  if ! grep -qE "^\s+${job}:" "$file" 2>/dev/null; then
    record_error "ERROR: Missing job '$job' in $file"
  fi
}

check_runner() {
  local file="$1"
  local runner="$2"
  if ! grep -q "$runner" "$file" 2>/dev/null; then
    record_error "ERROR: Runner '$runner' not found in $file"
  fi
}

check_root_permissions() {
  local file="$1"
  if ! grep -qE '^permissions:\s*$' "$file" 2>/dev/null; then
    record_error "ERROR: Missing top-level permissions block in $file"
  fi
}

check_root_concurrency() {
  local file="$1"
  if ! grep -qE '^concurrency:\s*$' "$file" 2>/dev/null; then
    record_error "ERROR: Missing top-level concurrency block in $file"
  fi
}

check_checkout_persist_credentials() {
  local file="$1"
  if grep -q "actions/checkout@" "$file" 2>/dev/null && ! grep -q "persist-credentials: false" "$file" 2>/dev/null; then
    record_error "ERROR: Checkout must set persist-credentials: false in $file"
  fi
}

collect_unpinned_actions() {
  local file="$1"
  python3 - "$file" <<'PY'
from pathlib import Path
import re
import sys

path = Path(sys.argv[1])
for line_number, line in enumerate(path.read_text().splitlines(), start=1):
    match = re.match(r'^\s*-?\s*uses:\s*([^\s#]+)', line)
    if not match:
        continue
    action = match.group(1)
    if action.startswith('./') or action.startswith('docker://'):
        continue
    if re.search(r'@[0-9a-f]{40}$', action):
        continue
    print(f"{path}:{line_number}: {action}")
PY
}

check_sha_pins() {
  local file="$1"
  local hits
  hits="$(collect_unpinned_actions "$file")"
  if [ -n "$hits" ]; then
    while IFS= read -r finding; do
      [ -n "$finding" ] || continue
      record_error "ERROR: Third-party action must be pinned to a full SHA: $finding"
    done <<< "$hits"
  fi
}

check_job_permissions() {
  local file="$1"
  local job="$2"
  if ! python3 - "$file" "$job" <<'PY'
from pathlib import Path
import re
import sys

path = Path(sys.argv[1])
job = sys.argv[2]
lines = path.read_text().splitlines()
in_jobs = False
current_job = None
has_permissions = False

for line in lines:
    if not in_jobs:
        if re.match(r'^jobs:\s*$', line):
            in_jobs = True
        continue

    if re.match(r'^[^ ]', line):
        break

    match = re.match(r'^  ([A-Za-z0-9_-]+):\s*$', line)
    if match:
        if current_job == job:
            break
        current_job = match.group(1)
        continue

    if current_job == job and re.match(r'^    permissions:\s*$', line):
        has_permissions = True
        break

sys.exit(0 if has_permissions else 1)
PY
  then
    record_error "ERROR: Missing job-level permissions for '$job' in $file"
  fi
}

collect_workflow_job_names() {
  python3 - <<'PY'
from pathlib import Path
import re

for path in sorted(Path('.github/workflows').glob('*.yml')):
    lines = path.read_text().splitlines()
    in_jobs = False
    current_job = None
    current_name = None

    def flush_current():
        if current_job is not None:
            effective_name = current_name if current_name is not None else current_job
            explicit = 'yes' if current_name is not None else 'no'
            print(f"{path}\t{current_job}\t{effective_name}\t{explicit}")

    for line in lines:
        if not in_jobs:
            if re.match(r'^jobs:\s*$', line):
                in_jobs = True
            continue

        if re.match(r'^[^ ]', line):
            break

        match = re.match(r'^  ([A-Za-z0-9_-]+):\s*$', line)
        if match:
            flush_current()
            current_job = match.group(1)
            current_name = None
            continue

        if current_job is not None:
            name_match = re.match(r'^    name:\s*(.+?)\s*$', line)
            if name_match:
                value = name_match.group(1).strip()
                if (value.startswith('"') and value.endswith('"')) or (value.startswith("'") and value.endswith("'")):
                    value = value[1:-1]
                current_name = value

    flush_current()
PY
}

check_unique_explicit_job_names() {
  local job_names
  local duplicate_file
  job_names="$(collect_workflow_job_names)"
  duplicate_file="${FINDINGS_FILE}.dup"

  if [ -z "$job_names" ]; then
    record_error "ERROR: no workflow jobs were collected for unique-name validation"
    return
  fi

  while IFS=$'\t' read -r file job_name effective_name explicit_flag; do
    [ -n "$file" ] || continue
    if [ "$explicit_flag" != "yes" ]; then
      record_error "ERROR: Workflow job '$job_name' must declare an explicit unique name in $file"
    fi
  done <<< "$job_names"

  python3 - "$job_names" <<'PY' > "$duplicate_file"
import collections
import sys

entries = [line for line in sys.argv[1].splitlines() if line.strip()]
by_name = collections.defaultdict(list)

for entry in entries:
    file, job, effective_name, explicit = entry.split('\t')
    by_name[effective_name].append((file, job))

for effective_name, locations in sorted(by_name.items()):
    if len(locations) <= 1:
        continue
    joined = ', '.join(f"{file}:{job}" for file, job in locations)
    print(f"{effective_name} -> {joined}")
PY

  if [ -s "$duplicate_file" ]; then
    while IFS= read -r finding; do
      [ -n "$finding" ] || continue
      record_error "ERROR: Workflow job names must be unique across workflows: $finding"
    done < "$duplicate_file"
  fi

  rm -f "$duplicate_file"
}

write_report() {
  python3 - "$REPORT_FILE" "$FINDINGS_FILE" "$ERRORS" <<'PY'
from pathlib import Path
import json
import sys

report_path = Path(sys.argv[1])
findings_path = Path(sys.argv[2])
error_count = int(sys.argv[3])
findings = [line.strip() for line in findings_path.read_text().splitlines() if line.strip()]
report = {
    "result": "passed" if error_count == 0 else "failed",
    "errorCount": error_count,
    "validatedWorkflows": [
        "ci.yml",
        "release.yml",
        "schedule.yml",
        "dependency-review.yml",
        "codeql.yml",
        "labeler.yml",
        "stale.yml",
        "pages.yml",
        "setup-build/action.yml",
    ],
    "checks": [
        "top-level permissions",
        "top-level concurrency",
        "expected jobs",
        "job-level permissions",
        "expected runner family",
        "checkout persist-credentials=false",
        "third-party action full SHA pinning",
        "explicit unique workflow job names",
    ],
    "findings": findings,
}
report_path.write_text(json.dumps(report, indent=2) + "\n")
PY
}

# Validate ci.yml
CI_FILE=".github/workflows/ci.yml"
if [ ! -f "$CI_FILE" ]; then
  record_error "ERROR: $CI_FILE does not exist"
else
  check_root_permissions "$CI_FILE"
  check_root_concurrency "$CI_FILE"
  check_checkout_persist_credentials "$CI_FILE"
  check_sha_pins "$CI_FILE"
  for job in lint test build build-apple wasm-build check audit; do
    check_job "$CI_FILE" "$job"
    check_job_permissions "$CI_FILE" "$job"
  done
  check_runner "$CI_FILE" "ubuntu-24.04"
  check_runner "$CI_FILE" "macos-15"
fi

# Validate release.yml
RELEASE_FILE=".github/workflows/release.yml"
if [ ! -f "$RELEASE_FILE" ]; then
  record_error "ERROR: $RELEASE_FILE does not exist"
else
  check_root_permissions "$RELEASE_FILE"
  check_root_concurrency "$RELEASE_FILE"
  check_checkout_persist_credentials "$RELEASE_FILE"
  check_sha_pins "$RELEASE_FILE"
  for job in check publish; do
    check_job "$RELEASE_FILE" "$job"
    check_job_permissions "$RELEASE_FILE" "$job"
  done
  check_runner "$RELEASE_FILE" "ubuntu-24.04"
fi

# Validate schedule.yml
SCHEDULE_FILE=".github/workflows/schedule.yml"
if [ ! -f "$SCHEDULE_FILE" ]; then
  record_error "ERROR: $SCHEDULE_FILE does not exist"
else
  check_root_permissions "$SCHEDULE_FILE"
  check_root_concurrency "$SCHEDULE_FILE"
  check_checkout_persist_credentials "$SCHEDULE_FILE"
  check_sha_pins "$SCHEDULE_FILE"
  for job in flake-scan benchmark-scan; do
    check_job "$SCHEDULE_FILE" "$job"
    check_job_permissions "$SCHEDULE_FILE" "$job"
  done
  check_runner "$SCHEDULE_FILE" "ubuntu-24.04"
fi

# Validate dependency-review.yml
DEPENDENCY_REVIEW_FILE=".github/workflows/dependency-review.yml"
if [ ! -f "$DEPENDENCY_REVIEW_FILE" ]; then
  record_error "ERROR: $DEPENDENCY_REVIEW_FILE does not exist"
else
  check_root_permissions "$DEPENDENCY_REVIEW_FILE"
  check_root_concurrency "$DEPENDENCY_REVIEW_FILE"
  check_checkout_persist_credentials "$DEPENDENCY_REVIEW_FILE"
  check_sha_pins "$DEPENDENCY_REVIEW_FILE"
  for job in dependency-review; do
    check_job "$DEPENDENCY_REVIEW_FILE" "$job"
    check_job_permissions "$DEPENDENCY_REVIEW_FILE" "$job"
  done
  check_runner "$DEPENDENCY_REVIEW_FILE" "ubuntu-24.04"
fi

# Validate codeql.yml
CODEQL_FILE=".github/workflows/codeql.yml"
if [ ! -f "$CODEQL_FILE" ]; then
  record_error "ERROR: $CODEQL_FILE does not exist"
else
  check_root_permissions "$CODEQL_FILE"
  check_root_concurrency "$CODEQL_FILE"
  check_checkout_persist_credentials "$CODEQL_FILE"
  check_sha_pins "$CODEQL_FILE"
  for job in analyze; do
    check_job "$CODEQL_FILE" "$job"
    check_job_permissions "$CODEQL_FILE" "$job"
  done
  check_runner "$CODEQL_FILE" "ubuntu-24.04"
fi

# Validate labeler.yml
LABELER_FILE=".github/workflows/labeler.yml"
if [ ! -f "$LABELER_FILE" ]; then
  record_error "ERROR: $LABELER_FILE does not exist"
else
  check_root_permissions "$LABELER_FILE"
  check_root_concurrency "$LABELER_FILE"
  check_sha_pins "$LABELER_FILE"
  for job in labeler; do
    check_job "$LABELER_FILE" "$job"
    check_job_permissions "$LABELER_FILE" "$job"
  done
  check_runner "$LABELER_FILE" "ubuntu-24.04"
fi

# Validate stale.yml
STALE_FILE=".github/workflows/stale.yml"
if [ ! -f "$STALE_FILE" ]; then
  record_error "ERROR: $STALE_FILE does not exist"
else
  check_root_permissions "$STALE_FILE"
  check_root_concurrency "$STALE_FILE"
  check_sha_pins "$STALE_FILE"
  for job in stale; do
    check_job "$STALE_FILE" "$job"
    check_job_permissions "$STALE_FILE" "$job"
  done
  check_runner "$STALE_FILE" "ubuntu-24.04"
fi

# Validate pages.yml
PAGES_FILE=".github/workflows/pages.yml"
if [ ! -f "$PAGES_FILE" ]; then
  record_error "ERROR: $PAGES_FILE does not exist"
else
  check_root_permissions "$PAGES_FILE"
  check_root_concurrency "$PAGES_FILE"
  check_checkout_persist_credentials "$PAGES_FILE"
  check_sha_pins "$PAGES_FILE"
  for job in build deploy; do
    check_job "$PAGES_FILE" "$job"
    check_job_permissions "$PAGES_FILE" "$job"
  done
  check_runner "$PAGES_FILE" "ubuntu-24.04"
fi

SETUP_BUILD_FILE=".github/actions/setup-build/action.yml"
if [ ! -f "$SETUP_BUILD_FILE" ]; then
  record_error "ERROR: $SETUP_BUILD_FILE does not exist"
else
  check_sha_pins "$SETUP_BUILD_FILE"
fi

check_unique_explicit_job_names

write_report
rm -f "$FINDINGS_FILE"

if [ "$ERRORS" -gt 0 ]; then
  echo ""
  echo "Validation failed with $ERRORS error(s)."
  exit 1
fi

echo "All workflow validations passed."
