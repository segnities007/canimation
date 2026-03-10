#!/usr/bin/env bash

readonly COMMON_LIB_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

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
