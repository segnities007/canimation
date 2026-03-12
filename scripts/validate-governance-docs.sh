#!/usr/bin/env bash
set -euo pipefail

source "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/lib/common.sh"
enter_project_root

if ! SEARCH_CMD="$(resolve_search_cmd)"; then
  echo "ERROR: either rg or grep is required for this script."
  exit 1
fi

ERRORS=0
REPORT_FILE="docs/quality/workflow/governance-docs-validation-report.json"
FINDINGS_FILE="$(mktemp)"

ensure_quality_dir "workflow"

check_file_exists() {
  local file="$1"
  if [ ! -f "$file" ]; then
    record_error "ERROR: required governance file is missing ($file)" "$FINDINGS_FILE" ERRORS
  fi
}

check_literal() {
  local file="$1"
  local literal="$2"
  local message="$3"
  check_literal_in_file "$file" "$literal" "$message" "$SEARCH_CMD" "$FINDINGS_FILE" ERRORS
}

check_support_tooling_baseline() {
  local output
  if ! output="$(python3 - <<'PY'
from pathlib import Path
import re
import sys
import tomllib

root = Path('.')
support_text = (root / 'SUPPORT.md').read_text()
versions = tomllib.loads((root / 'gradle/libs.versions.toml').read_text())['versions']
wrapper_text = (root / 'gradle/wrapper/gradle-wrapper.properties').read_text()
setup_build_text = (root / '.github/actions/setup-build/action.yml').read_text()

gradle_version = re.search(r'gradle-([0-9.]+)-', wrapper_text)
jdk_version = re.search(r"default:\s*'([^']+)'", setup_build_text)

expected = {
    'Kotlin': versions['kotlin'],
    'Compose Multiplatform': versions['composeMultiplatform'],
    'Gradle': gradle_version.group(1) if gradle_version else None,
    'JDK': jdk_version.group(1) if jdk_version else None,
}

missing = []
for label, version in expected.items():
    if version is None:
        missing.append(f'source version lookup failed for {label}')
        continue
    token = f'{label} `{version}`'
    if token not in support_text:
        missing.append(token)

if missing:
    print('\n'.join(missing))
    sys.exit(1)
PY
)"; then
    while IFS= read -r finding; do
      [ -n "$finding" ] || continue
      record_error "ERROR: SUPPORT.md tooling baseline drift detected: $finding" "$FINDINGS_FILE" ERRORS
    done <<< "$output"
  fi
}

write_report() {
  local payload
  payload='{"validatedDocs":["README.md","CONTRIBUTING.md","SUPPORT.md","SECURITY.md","MAINTAINERS.md","CODE_OF_CONDUCT.md",".github/labels.yml",".github/CODEOWNERS",".github/PULL_REQUEST_TEMPLATE.md",".github/ISSUE_TEMPLATE/architecture-proposal.yml","docs/reference/governance/triage-and-label-taxonomy.md","docs/explanation/architecture/implementation-overview.md"],"checks":["required governance files exist","validation commands stay aligned across README, CONTRIBUTING, and implementation overview","SUPPORT.md tooling baseline matches repository sources","CODEOWNERS covers required architecture and governance paths","issue templates cover architecture intake and code of conduct acknowledgements","label taxonomy stays aligned across labels, issue forms, and labeler automation","pull request template includes regression, ADR, and contract review prompts"]}'
  write_json_report "$REPORT_FILE" "$FINDINGS_FILE" "$ERRORS" "$payload"
}

echo "== Governance docs validation =="

for file in \
  "README.md" \
  "CONTRIBUTING.md" \
  "SUPPORT.md" \
  "SECURITY.md" \
  "MAINTAINERS.md" \
  "CODE_OF_CONDUCT.md" \
  ".github/labels.yml" \
  ".github/CODEOWNERS" \
  ".github/PULL_REQUEST_TEMPLATE.md" \
  ".github/ISSUE_TEMPLATE/architecture-proposal.yml" \
  "docs/reference/governance/triage-and-label-taxonomy.md"; do
  check_file_exists "$file"
done

for file in \
  "README.md" \
  "CONTRIBUTING.md" \
  "docs/explanation/architecture/implementation-overview.md"; do
  check_literal "$file" "allTests --max-workers=2 --no-daemon" \
    "allTests validation command must be documented"
  check_literal "$file" ":koverHtmlReport :koverXmlReport" \
    "Kover coverage validation command must be documented"
  check_literal "$file" "compileLibraryAndroid compileLibraryJvm :androidApp:assembleDebug :composeApp:compileKotlinJvm" \
    "library Android/JVM and host validation command must be documented"
  check_literal "$file" "compileLibraryApple :composeApp:packageDistributionForCurrentOS :composeApp:linkDebugFrameworkIosSimulatorArm64" \
    "library Apple validation command must be documented"
  check_literal "$file" "compileLibraryWeb :composeApp:compileKotlinWasmJs" \
    "library Web validation command must be documented"
  check_literal "$file" "releaseReadiness" \
    "release readiness validation command must be documented"
  check_literal "$file" "bash scripts/validate-governance-docs.sh" \
    "governance docs validation command must be documented"
done

check_support_tooling_baseline

for required_path in \
  "/modules/canimation-runtime/" \
  "/modules/canimation-semantics/" \
  "/modules/canimation-recipes/" \
  "/modules/canimation-a11y/" \
  "/guideline/" \
  "/.github/workflows/" \
  "/build-logic/" \
  "/SECURITY.md" \
  "/MAINTAINERS.md" \
  "/SUPPORT.md"; do
  check_literal ".github/CODEOWNERS" "$required_path" \
    "CODEOWNERS must cover required governance or architecture path $required_path"
done

check_literal ".github/PULL_REQUEST_TEMPLATE.md" "regression test added for bug fixes" \
  "pull request template must prompt for regression tests"
check_literal ".github/PULL_REQUEST_TEMPLATE.md" "ADR updated when stable architecture, workflow security posture, or public API tiering changed" \
  "pull request template must prompt for ADR updates"
check_literal ".github/PULL_REQUEST_TEMPLATE.md" "support / security contract impact reviewed" \
  "pull request template must prompt for support/security contract review"
check_literal "CONTRIBUTING.md" ".github/CODEOWNERS" \
  "contributing guide must point to CODEOWNERS review ownership"
check_literal "CONTRIBUTING.md" "architecture-proposal.yml" \
  "contributing guide must point to the architecture proposal issue form"
check_literal "README.md" "[CODEOWNERS](.github/CODEOWNERS)" \
  "README docs map must link to CODEOWNERS"
check_literal "README.md" "[Maintainers](MAINTAINERS.md)" \
  "README docs map must link to MAINTAINERS.md"
check_literal "README.md" "triage-and-label-taxonomy.md" \
  "README docs map must link to triage and label taxonomy"
check_literal "SUPPORT.md" "MAINTAINERS.md" \
  "support policy must link to MAINTAINERS.md"
check_literal "MAINTAINERS.md" ".github/CODEOWNERS" \
  "maintainer doc must reference CODEOWNERS"
check_literal "CONTRIBUTING.md" ".github/labels.yml" \
  "contributing guide must link to label taxonomy SSoT"

for label in \
  "type:bug" \
  "type:feature" \
  "type:docs" \
  "type:question" \
  "type:security" \
  "status:needs-info" \
  "status:blocked" \
  "status:accepted" \
  "priority:high" \
  "good first issue" \
  "documentation" \
  "ci" \
  "architecture"; do
  check_literal ".github/labels.yml" "name: $label" \
    "labels.yml must define required label $label"
done

for label in \
  "type:bug" \
  "type:feature" \
  "type:docs" \
  "type:question"; do
  check_literal "docs/reference/governance/triage-and-label-taxonomy.md" "$label" \
    "triage reference must mention label $label"
done

check_literal ".github/labeler.yml" "documentation:" \
  "labeler config must define documentation label routing"
check_literal ".github/labeler.yml" "ci:" \
  "labeler config must define ci label routing"
check_literal ".github/labeler.yml" "architecture:" \
  "labeler config must define architecture label routing"

for file in \
  ".github/ISSUE_TEMPLATE/bug-report.yml" \
  ".github/ISSUE_TEMPLATE/feature-request.yml" \
  ".github/ISSUE_TEMPLATE/docs-request.yml" \
  ".github/ISSUE_TEMPLATE/question.yml" \
  ".github/ISSUE_TEMPLATE/architecture-proposal.yml"; do
  check_literal "$file" "Code of Conduct" \
    "issue forms must include a Code of Conduct acknowledgement"
done

check_literal ".github/ISSUE_TEMPLATE/architecture-proposal.yml" "Compatibility and migration impact" \
  "architecture proposal form must ask for compatibility and migration impact"
check_literal ".github/ISSUE_TEMPLATE/architecture-proposal.yml" "Required evidence" \
  "architecture proposal form must ask for evidence requirements"

write_report
rm -f "$FINDINGS_FILE"

if [ "$ERRORS" -gt 0 ]; then
  echo ""
  echo "Governance docs validation failed with $ERRORS error(s)."
  exit 1
fi

echo "Governance docs validation passed."
