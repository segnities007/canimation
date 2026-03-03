# Release Versioning Policy

## Overview

canimation follows [Semantic Versioning 2.0.0](https://semver.org/spec/v2.0.0.html).
Every published artifact uses the format:

```
MAJOR.MINOR.PATCH[-PRE_RELEASE]
```

## Version Increment Rules

### Patch (`x.y.Z`)

Increment the **patch** version for changes that are fully
backward-compatible and contain no new API surface:

- Bug fixes in animation behavior.
- Performance improvements with no API change.
- Documentation corrections published alongside a code fix.

**Example:** `0.1.0` → `0.1.1`

### Minor (`x.Y.0`)

Increment the **minor** version when new functionality is added in a
backward-compatible manner:

- New animation presets (e.g., a `RotateIn` preset).
- New configuration options on existing APIs.
- New utility composables or test helpers.
- Deprecation of existing APIs (with replacement).

**Example:** `0.1.1` → `0.2.0`

### Major (`X.0.0`)

Increment the **major** version for changes that break backward
compatibility:

- Removal of deprecated APIs.
- Signature changes on public functions or composables.
- Behavioral changes that require consumer code updates.
- Minimum Kotlin / Compose version bumps that drop older support.

**Example:** `0.2.0` → `1.0.0`

> **Note:** While the library is in the `0.x` series, minor version bumps
> may include breaking changes. Consumers should pin to an exact minor
> version until `1.0.0` is released.

## Pre-Release Tags

Pre-release versions are published for early feedback before a stable
release:

| Tag       | Purpose                                  | Example       |
|-----------|------------------------------------------|---------------|
| `alpha`   | API is experimental, may change freely.  | `0.2.0-alpha1`|
| `beta`    | API is feature-complete, may have bugs.  | `0.2.0-beta1` |
| `rc`      | Release candidate, final validation.     | `0.2.0-rc1`   |

### Progression

```
0.2.0-alpha1 → 0.2.0-alpha2 → 0.2.0-beta1 → 0.2.0-rc1 → 0.2.0
```

Pre-release artifacts are published to the snapshots repository. Only
stable releases (`MAJOR.MINOR.PATCH` with no suffix) are promoted to
the release repository.

## Branch and Tag Strategy

| Branch / Tag     | Purpose                               |
|------------------|---------------------------------------|
| `main`           | Latest development, may be unstable.  |
| `release/x.y`   | Stabilization branch for a release.   |
| `vX.Y.Z`        | Immutable tag for a published version.|

## Deprecation Policy

- Deprecated APIs are annotated with `@Deprecated` and include a
  `ReplaceWith` migration hint.
- Deprecated APIs remain available for at least **one minor version**
  before removal.
- Removal happens only in a **major** version bump (or a minor bump
  during the `0.x` series).
