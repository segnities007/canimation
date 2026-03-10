#!/usr/bin/env bash
set -euo pipefail

source "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/lib/common.sh"
enter_project_root

BENCHMARK_RUNS="${BENCHMARK_RUNS:-3}"
BENCHMARK_AVG_THRESHOLD_SECONDS="${BENCHMARK_AVG_THRESHOLD_SECONDS:-180}"
BENCHMARK_OUTPUT_FILE="docs/quality/performance/benchmark-baseline.json"
BENCHMARK_GRADLE_CMD="${BENCHMARK_GRADLE_CMD:-./gradlew :canimation-core:jvmTest :canimation-presets:jvmTest :canimation-diagnostics:jvmTest}"
STARTED_AT="$(utc_timestamp)"
TOTAL_SECONDS=0
MAX_SECONDS=0
MIN_SECONDS=0
RUN_INDEX=0
RUN_SECONDS=()

ensure_quality_dir "performance"

for i in $(seq 1 "$BENCHMARK_RUNS"); do
  echo "=== Benchmark run $i / $BENCHMARK_RUNS ==="
  START_SECONDS=$(date +%s)
  $BENCHMARK_GRADLE_CMD --no-daemon --quiet
  END_SECONDS=$(date +%s)
  ELAPSED_SECONDS=$((END_SECONDS - START_SECONDS))
  RUN_SECONDS+=("$ELAPSED_SECONDS")
  TOTAL_SECONDS=$((TOTAL_SECONDS + ELAPSED_SECONDS))
  if [ "$RUN_INDEX" -eq 0 ] || [ "$ELAPSED_SECONDS" -gt "$MAX_SECONDS" ]; then
    MAX_SECONDS="$ELAPSED_SECONDS"
  fi
  if [ "$RUN_INDEX" -eq 0 ] || [ "$ELAPSED_SECONDS" -lt "$MIN_SECONDS" ]; then
    MIN_SECONDS="$ELAPSED_SECONDS"
  fi
  RUN_INDEX=$((RUN_INDEX + 1))
  echo "  -> ${ELAPSED_SECONDS}s"
done

AVERAGE_SECONDS=$(awk "BEGIN {printf \"%.2f\", $TOTAL_SECONDS / $BENCHMARK_RUNS}")

COMPLETED_AT="$(utc_timestamp)"
RUN_SECONDS_CSV="$(IFS=,; printf '%s' "${RUN_SECONDS[*]}")"

STARTED_AT="$STARTED_AT" \
COMPLETED_AT="$COMPLETED_AT" \
BENCHMARK_RUNS="$BENCHMARK_RUNS" \
AVERAGE_SECONDS="$AVERAGE_SECONDS" \
MIN_SECONDS="$MIN_SECONDS" \
MAX_SECONDS="$MAX_SECONDS" \
BENCHMARK_AVG_THRESHOLD_SECONDS="$BENCHMARK_AVG_THRESHOLD_SECONDS" \
BENCHMARK_GRADLE_CMD="$BENCHMARK_GRADLE_CMD" \
RUN_SECONDS_CSV="$RUN_SECONDS_CSV" \
python3 - "$BENCHMARK_OUTPUT_FILE" <<'PY'
from pathlib import Path
import json
import os
import sys

output_path = Path(sys.argv[1])
run_seconds = [int(value) for value in os.environ["RUN_SECONDS_CSV"].split(",") if value]
report = {
    "startedAtUtc": os.environ["STARTED_AT"],
    "completedAtUtc": os.environ["COMPLETED_AT"],
    "benchmarkRuns": int(os.environ["BENCHMARK_RUNS"]),
    "averageSeconds": float(os.environ["AVERAGE_SECONDS"]),
    "minSeconds": int(os.environ["MIN_SECONDS"]),
    "maxSeconds": int(os.environ["MAX_SECONDS"]),
    "averageThresholdSeconds": float(os.environ["BENCHMARK_AVG_THRESHOLD_SECONDS"]),
    "gradleCommand": os.environ["BENCHMARK_GRADLE_CMD"],
    "runSeconds": run_seconds,
}
output_path.write_text(json.dumps(report, indent=2) + "\n")
PY

echo "Average duration: ${AVERAGE_SECONDS}s"
echo "Results saved to $BENCHMARK_OUTPUT_FILE"

EXCEEDED=$(awk "BEGIN {print ($AVERAGE_SECONDS > $BENCHMARK_AVG_THRESHOLD_SECONDS) ? 1 : 0}")
if [ "$EXCEEDED" -eq 1 ]; then
  echo "ERROR: Benchmark baseline average ${AVERAGE_SECONDS}s exceeds threshold ${BENCHMARK_AVG_THRESHOLD_SECONDS}s"
  exit 1
fi

echo "Benchmark baseline check passed."
