#!/usr/bin/env bash

readonly COMMON_LIB_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

record_error() {
  local message="$1"
  local findings_file="$2"
  local error_count_var="$3"

  echo "$message"
  printf '%s\n' "$message" >> "$findings_file"
  printf -v "$error_count_var" '%s' "$(( ${!error_count_var} + 1 ))"
}

check_literal_in_file() {
  local file="$1"
  local literal="$2"
  local message="$3"
  local search_cmd="$4"
  local findings_file="$5"
  local error_count_var="$6"

  if [ ! -f "$file" ]; then
    record_error "ERROR: required file is missing ($file)" "$findings_file" "$error_count_var"
    return
  fi

  if [ "$search_cmd" = "rg" ]; then
    if ! rg -q --fixed-strings -- "$literal" "$file"; then
      record_error "ERROR: $message ($file)" "$findings_file" "$error_count_var"
    fi
  else
    if ! grep -qF -- "$literal" "$file"; then
      record_error "ERROR: $message ($file)" "$findings_file" "$error_count_var"
    fi
  fi
}

write_json_report() {
  local report_file="$1"
  local findings_file="$2"
  local error_count="$3"
  local payload="$4"

  python3 - "$report_file" "$findings_file" "$error_count" "$payload" <<'PY'
from pathlib import Path
import json
import sys

report_path = Path(sys.argv[1])
findings_path = Path(sys.argv[2])
error_count = int(sys.argv[3])
payload = json.loads(sys.argv[4])
findings = [line.strip() for line in findings_path.read_text().splitlines() if line.strip()]
report = {
    'result': 'passed' if error_count == 0 else 'failed',
    'errorCount': error_count,
    **payload,
    'findings': findings,
}
report_path.write_text(json.dumps(report, indent=2) + '\n')
PY
}

project_root_dir() {
  printf '%s\n' "$(cd "$COMMON_LIB_DIR/../.." && pwd)"
}

enter_project_root() {
  cd "$(project_root_dir)"
}

ensure_quality_dir() {
  local relative_dir="$1"
  mkdir -p "$(project_root_dir)/docs/quality/${relative_dir}"
}

utc_timestamp() {
  date -u '+%Y-%m-%dT%H:%M:%SZ'
}

resolve_search_cmd() {
  if command -v rg >/dev/null 2>&1; then
    printf 'rg\n'
    return 0
  fi

  if command -v grep >/dev/null 2>&1; then
    printf 'grep\n'
    return 0
  fi

  return 1
}
