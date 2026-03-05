#!/usr/bin/env bash
set -euo pipefail

ERRORS=0

check_job() {
  local file="$1"
  local job="$2"
  if ! grep -qE "^\s+${job}:" "$file" 2>/dev/null; then
    echo "ERROR: Missing job '$job' in $file"
    ERRORS=$((ERRORS + 1))
  fi
}

check_runner() {
  local file="$1"
  local runner="$2"
  if ! grep -q "$runner" "$file" 2>/dev/null; then
    echo "ERROR: Runner '$runner' not found in $file"
    ERRORS=$((ERRORS + 1))
  fi
}

# Validate pr.yml
PR_FILE=".github/workflows/pr.yml"
if [ ! -f "$PR_FILE" ]; then
  echo "ERROR: $PR_FILE does not exist"
  ERRORS=$((ERRORS + 1))
else
  for job in linux-check security-audit format-check lint-detekt unit-test integration-test artifact-build coverage-verify flake-scan; do
    check_job "$PR_FILE" "$job"
  done
  check_runner "$PR_FILE" "ubuntu-24.04"
fi

# Validate release.yml
RELEASE_FILE=".github/workflows/release.yml"
if [ ! -f "$RELEASE_FILE" ]; then
  echo "ERROR: $RELEASE_FILE does not exist"
  ERRORS=$((ERRORS + 1))
else
  for job in check sign publish docs-deploy release-secrets-check; do
    check_job "$RELEASE_FILE" "$job"
  done
  check_runner "$RELEASE_FILE" "ubuntu-24.04"
fi

if [ "$ERRORS" -gt 0 ]; then
  echo ""
  echo "Validation failed with $ERRORS error(s)."
  exit 1
fi

echo "All workflow validations passed."
