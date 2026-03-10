# Benchmark Summary

## Purpose

performance と regression の summary を stable path に残す。

## Scope

- benchmark targets
- environment
- threshold
- regression status

## Source Inputs Or Commands

```bash
bash scripts/measure-benchmark-baseline.sh
```

## Required Inputs

- benchmark command or script
- target scenarios
- environment note

## Result Summary

- current benchmark scaffold uses a Gradle task-wall-clock smoke baseline until a dedicated benchmark module is introduced
- benchmark target:
  - `:canimation-core:jvmTest`
  - `:canimation-presets:jvmTest`
  - `:canimation-diagnostics:jvmTest`
- environment:
  - local and scheduled Ubuntu runners via the existing Gradle toolchain setup
- threshold:
  - average run time must stay at or below `180s` unless intentionally adjusted with rationale
- regression status:
  - use the generated benchmark artifact to compare the current smoke baseline against the recorded threshold
- machine-readable artifact:
  - `docs/quality/performance/benchmark-baseline.json`

This document describes the currently maintained performance evidence path. It does not claim dedicated runtime animation-frame benchmarking coverage yet.

## Known Limitations

- this is build-task performance evidence, not runtime animation-frame benchmarking
- no dedicated `benchmarks/` Gradle project is registered yet, so the current scaffold is intentionally isolated from production modules

## Last Updated Trigger

- update when benchmark targets, thresholds, or the benchmark automation path changes
