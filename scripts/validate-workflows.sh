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

# Validate ci.yml
CI_FILE=".github/workflows/ci.yml"
if [ ! -f "$CI_FILE" ]; then
  echo "ERROR: $CI_FILE does not exist"
  ERRORS=$((ERRORS + 1))
else
  for job in lint test build security-audit; do
    check_job "$CI_FILE" "$job"
  done
  check_runner "$CI_FILE" "ubuntu-24.04"
fi

# Validate release.yml
RELEASE_FILE=".github/workflows/release.yml"
if [ ! -f "$RELEASE_FILE" ]; then
  echo "ERROR: $RELEASE_FILE does not exist"
  ERRORS=$((ERRORS + 1))
else
  for job in check publish; do
    check_job "$RELEASE_FILE" "$job"
  done
  check_runner "$RELEASE_FILE" "ubuntu-24.04"
fi

# Validate schedule.yml
SCHEDULE_FILE=".github/workflows/schedule.yml"
if [ ! -f "$SCHEDULE_FILE" ]; then
  echo "ERROR: $SCHEDULE_FILE does not exist"
  ERRORS=$((ERRORS + 1))
else
  for job in flake-scan; do
    check_job "$SCHEDULE_FILE" "$job"
  done
  check_runner "$SCHEDULE_FILE" "ubuntu-24.04"
fi

# Validate pages.yml
PAGES_FILE=".github/workflows/pages.yml"
if [ ! -f "$PAGES_FILE" ]; then
  echo "ERROR: $PAGES_FILE does not exist"
  ERRORS=$((ERRORS + 1))
else
  for job in build deploy; do
    check_job "$PAGES_FILE" "$job"
  done
  check_runner "$PAGES_FILE" "ubuntu-24.04"
fi

if [ "$ERRORS" -gt 0 ]; then
  echo ""
  echo "Validation failed with $ERRORS error(s)."
  exit 1
fi

echo "All workflow validations passed."
