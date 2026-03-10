#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

if command -v rg >/dev/null 2>&1; then
  SEARCH_CMD="rg"
else
  SEARCH_CMD="grep"
fi

OUTPUT_DIR="docs/quality"
mkdir -p "$OUTPUT_DIR"

echo "Running script..."

echo "{}" > "$OUTPUT_DIR/result.json"

echo "Script completed successfully."
