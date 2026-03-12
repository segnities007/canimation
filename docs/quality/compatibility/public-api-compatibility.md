# Public API Compatibility Evidence

## Purpose

stable public API の互換性確認結果をまとめる。

## Scope

- ABI/source compatibility gate
- validated modules
- migration note linkage

## Required Inputs

- API dump / ABI validation result
- affected stable modules
- changelog or migration note

## Result Summary

- current compatibility tracking is centered on the publishable modules declared in `settings.gradle.kts` and the ABI tasks wired through `releaseReadiness`
- semantics-first runtime entry points are the intended stable path:
  - `CanimationProvider`
  - `Modifier.canimation(recipe = ...)`
  - `CanimationVisibility(visible, recipe = ...)`
  - `CanimationTransition(visible, recipe = ...)`
  - `CanimationRecipeRegistry`
- target-state wrapper modules already exist, but the deeper implementation extraction from `canimation-core` is still in progress
- effect-first and preset-first APIs remain present for migration compatibility
- refresh ABI and API evidence with `./gradlew updateLibraryAbi --max-workers=2 --no-daemon` when an intentional public API change is accepted

## Known Limitations

- compatibility evidence is strongest for the current publishable module map, not for a fully completed target-state internals split

## Last Updated Trigger

- refresh when a publishable module changes public API, a migration stage changes, or ABI validation coverage changes
