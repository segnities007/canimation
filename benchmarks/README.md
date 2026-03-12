# Benchmark Scaffold

This directory is reserved for dedicated benchmark projects.

## Current State

- The repository does not yet include a standalone Gradle benchmark module.
- Phase 4 uses `scripts/measure-benchmark-baseline.sh` as an interim benchmark smoke check.
- The generated machine-readable artifact is `docs/quality/performance/benchmark-baseline.json`.

## Why It Is Isolated

- No new project is registered in `settings.gradle.kts`.
- No library module or app source is coupled to this directory.
- Future benchmark hosts can be added here once they compile as independent projects.
