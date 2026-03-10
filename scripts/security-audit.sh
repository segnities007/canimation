#!/usr/bin/env bash
set -euo pipefail

source "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/lib/common.sh"
enter_project_root

if ! SEARCH_CMD="$(resolve_search_cmd)"; then
  echo "ERROR: either rg or grep is required for this script."
  exit 1
fi

ERRORS=0
ANDROID_MANIFEST="androidApp/src/main/AndroidManifest.xml"
REPORT_FILE="docs/quality/security/security-audit-report.json"
FINDINGS_FILE="$(mktemp)"

ensure_quality_dir "security"

record_error() {
  local message="$1"
  echo "$message"
  printf '%s\n' "$message" >> "$FINDINGS_FILE"
  ERRORS=$((ERRORS + 1))
}

check_literal() {
  local file="$1"
  local literal="$2"
  local message="$3"
  if [ ! -f "$file" ]; then
    record_error "ERROR: required file is missing ($file)"
    return
  fi
  if [ "$SEARCH_CMD" = "rg" ]; then
    if ! rg -q --fixed-strings -- "$literal" "$file"; then
      record_error "ERROR: $message ($file)"
    fi
  else
    if ! grep -qF -- "$literal" "$file"; then
      record_error "ERROR: $message ($file)"
    fi
  fi
}

search_regex() {
  local pattern="$1"
  shift
  if [ "$SEARCH_CMD" = "rg" ]; then
    rg -n "$pattern" "$@" || true
  else
    grep -REn "$pattern" "$@" || true
  fi
}

search_http_hits() {
  if [ "$SEARCH_CMD" = "rg" ]; then
    rg -n "http://" composeApp/src modules | rg -v "schemas.android.com|www.w3.org|www.apple.com/DTDs" || true
  else
    grep -REn "http://" composeApp/src modules | grep -Ev "schemas.android.com|www.w3.org|www.apple.com/DTDs" || true
  fi
}

search_project_repo_hits() {
  if [ "$SEARCH_CMD" = "rg" ]; then
    rg -n "^repositories[[:space:]]*\\{" --glob '**/build.gradle.kts' . || true
  else
    grep -REn "^repositories[[:space:]]*\\{" --include='*build.gradle.kts' . || true
  fi
}

search_secret_hits() {
  search_regex "(AKIA[0-9A-Z]{16}|ghp_[A-Za-z0-9]{36}|AIza[0-9A-Za-z\\-_]{35}|-----BEGIN [A-Z ]+ PRIVATE KEY-----)" \
    composeApp modules gradle .github scripts build.gradle.kts settings.gradle.kts gradle.properties
}

search_unpinned_action_hits() {
  python3 - <<'PY'
from pathlib import Path
import re

paths = list(Path('.github/workflows').glob('*.yml')) + list(Path('.github/actions').glob('**/*.yml'))
for path in paths:
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
    "auditedScopes": [
        "dependency repositories",
        "dependabot coverage",
        "workflow security posture",
        "Gradle verification metadata",
        "Android manifest hardening",
        "web security headers",
        "secret pattern scan",
    ],
    "highRiskFindings": findings,
}
report_path.write_text(json.dumps(report, indent=2) + "\n")
PY
}

echo "== Security audit =="

check_literal "settings.gradle.kts" "mavenCentral()" \
  "mavenCentral must be defined in settings.gradle.kts"
check_literal "settings.gradle.kts" "exclusiveContent {" \
  "Node.js distribution repository must be explicitly declared in settings.gradle.kts"
check_literal ".github/dependabot.yml" "package-ecosystem: \"gradle\"" \
  "Dependabot must track Gradle dependencies"
check_literal ".github/dependabot.yml" "package-ecosystem: \"github-actions\"" \
  "Dependabot must track GitHub Actions dependencies"
check_literal ".github/workflows/dependency-review.yml" "actions/dependency-review-action@" \
  "Dependency review workflow must be configured"
check_literal ".github/workflows/codeql.yml" "github/codeql-action/init@" \
  "CodeQL init action must be configured"
check_literal ".github/workflows/codeql.yml" "github/codeql-action/analyze@" \
  "CodeQL analyze action must be configured"
check_literal ".github/workflows/labeler.yml" "actions/labeler@" \
  "Pull request labeler workflow must be configured"
check_literal ".github/workflows/stale.yml" "actions/stale@" \
  "Stale management workflow must be configured"
check_literal ".github/dependency-review-config.yml" "fail-on-severity: moderate" \
  "Dependency review must fail on at least moderate severity"
check_literal "gradle/verification-metadata.xml" "<verification-metadata" \
  "Gradle dependency verification metadata must be committed"

check_literal "$ANDROID_MANIFEST" "android:allowBackup=\"false\"" \
  "Android backup must be disabled"
check_literal "$ANDROID_MANIFEST" "android:fullBackupContent=\"false\"" \
  "Android full backup must be disabled"
check_literal "$ANDROID_MANIFEST" "android:usesCleartextTraffic=\"false\"" \
  "Cleartext traffic must be disabled"

check_literal "composeApp/src/webMain/resources/index.html" "Content-Security-Policy" \
  "Web entrypoint must define CSP"
check_literal "composeApp/src/webMain/resources/index.html" "Permissions-Policy" \
  "Web entrypoint must define Permissions-Policy"
check_literal "composeApp/src/webMain/resources/index.html" "name=\"referrer\"" \
  "Web entrypoint must define referrer policy"

SECRET_HITS="$(search_secret_hits)"
if [ -n "$SECRET_HITS" ]; then
  record_error "ERROR: potential secret material detected"
  while IFS= read -r hit; do
    [ -n "$hit" ] || continue
    record_error "$hit"
  done <<< "$SECRET_HITS"
fi

HTTP_HITS="$(search_http_hits)"
if [ -n "$HTTP_HITS" ]; then
  record_error "ERROR: insecure http:// usage detected"
  while IFS= read -r hit; do
    [ -n "$hit" ] || continue
    record_error "$hit"
  done <<< "$HTTP_HITS"
fi

PROJECT_REPO_HITS="$(search_project_repo_hits)"
if [ -n "$PROJECT_REPO_HITS" ]; then
  record_error "ERROR: project-level repositories are not allowed. Declare repositories only in settings.gradle.kts"
  while IFS= read -r hit; do
    [ -n "$hit" ] || continue
    record_error "$hit"
  done <<< "$PROJECT_REPO_HITS"
fi

UNPINNED_ACTION_HITS="$(search_unpinned_action_hits)"
if [ -n "$UNPINNED_ACTION_HITS" ]; then
  record_error "ERROR: third-party GitHub Actions must be pinned to full SHAs"
  while IFS= read -r hit; do
    [ -n "$hit" ] || continue
    record_error "$hit"
  done <<< "$UNPINNED_ACTION_HITS"
fi

write_report
rm -f "$FINDINGS_FILE"

if [ "$ERRORS" -gt 0 ]; then
  echo ""
  echo "Security audit failed with $ERRORS error(s)."
  exit 1
fi

echo "Security audit passed."
