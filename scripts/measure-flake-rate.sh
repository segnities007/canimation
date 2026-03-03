#!/usr/bin/env bash
set -euo pipefail

TOTAL_RUNS=5
FAIL_RUNS=0
PASS_RUNS=0
FLAKE_THRESHOLD="0.02"

GRADLE_CMD="./gradlew :canimation-core:jvmTest :canimation-presets:jvmTest :canimation-a11y:jvmTest :canimation-diagnostics:jvmTest :canimation-test:jvmTest"

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

mkdir -p docs/quality

cat > docs/quality/flake-rate.json <<EOF
{
  "totalRuns": $TOTAL_RUNS,
  "passRuns": $PASS_RUNS,
  "failRuns": $FAIL_RUNS,
  "flakeRate": $FLAKE_RATE
}
EOF

echo "Results saved to docs/quality/flake-rate.json"

EXCEEDED=$(awk "BEGIN {print ($FLAKE_RATE >= $FLAKE_THRESHOLD) ? 1 : 0}")
if [ "$EXCEEDED" -eq 1 ]; then
  echo "ERROR: Flake rate $FLAKE_RATE >= threshold $FLAKE_THRESHOLD"
  exit 1
fi

echo "Flake rate $FLAKE_RATE is below threshold $FLAKE_THRESHOLD"
