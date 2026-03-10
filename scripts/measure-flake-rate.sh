#!/usr/bin/env bash
set -euo pipefail

source "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/lib/common.sh"
enter_project_root

TOTAL_RUNS="${TOTAL_RUNS:-5}"
FAIL_RUNS=0
PASS_RUNS=0
FLAKE_THRESHOLD="${FLAKE_THRESHOLD:-0.02}"
OUTPUT_FILE="docs/quality/workflow/flake-rate.json"
STARTED_AT="$(utc_timestamp)"

GRADLE_CMD="${GRADLE_CMD:-./gradlew :canimation-core:jvmTest :canimation-presets:jvmTest :canimation-a11y:jvmTest :canimation-diagnostics:jvmTest :canimation-test:jvmTest}"

ensure_quality_dir "workflow"

for i in $(seq 1 "$TOTAL_RUNS"); do
  echo "=== Run $i / $TOTAL_RUNS ==="
  if $GRADLE_CMD --no-daemon --quiet; then
    PASS_RUNS=$((PASS_RUNS + 1))
    echo "  -> PASS"
  else
    FAIL_RUNS=$((FAIL_RUNS + 1))
    echo "  -> FAIL"
  fi
done

FLAKE_RATE=$(awk "BEGIN {printf \"%.4f\", $FAIL_RUNS / $TOTAL_RUNS}")

echo ""
echo "Results: $PASS_RUNS passed, $FAIL_RUNS failed out of $TOTAL_RUNS runs"
echo "Flake rate: $FLAKE_RATE"

COMPLETED_AT="$(utc_timestamp)"

STARTED_AT="$STARTED_AT" \
COMPLETED_AT="$COMPLETED_AT" \
TOTAL_RUNS="$TOTAL_RUNS" \
PASS_RUNS="$PASS_RUNS" \
FAIL_RUNS="$FAIL_RUNS" \
FLAKE_RATE="$FLAKE_RATE" \
FLAKE_THRESHOLD="$FLAKE_THRESHOLD" \
GRADLE_CMD="$GRADLE_CMD" \
python3 - "$OUTPUT_FILE" <<'PY'
from pathlib import Path
import json
import os
import sys

output_path = Path(sys.argv[1])
report = {
    "startedAtUtc": os.environ["STARTED_AT"],
    "completedAtUtc": os.environ["COMPLETED_AT"],
    "totalRuns": int(os.environ["TOTAL_RUNS"]),
    "passRuns": int(os.environ["PASS_RUNS"]),
    "failRuns": int(os.environ["FAIL_RUNS"]),
    "flakeRate": float(os.environ["FLAKE_RATE"]),
    "flakeThreshold": float(os.environ["FLAKE_THRESHOLD"]),
    "gradleCommand": os.environ["GRADLE_CMD"],
}
output_path.write_text(json.dumps(report, indent=2) + "\n")
PY

echo "Results saved to $OUTPUT_FILE"

EXCEEDED=$(awk "BEGIN {print ($FLAKE_RATE >= $FLAKE_THRESHOLD) ? 1 : 0}")
if [ "$EXCEEDED" -eq 1 ]; then
  echo "ERROR: Flake rate $FLAKE_RATE >= threshold $FLAKE_THRESHOLD"
  exit 1
fi

echo "Flake rate check passed."
