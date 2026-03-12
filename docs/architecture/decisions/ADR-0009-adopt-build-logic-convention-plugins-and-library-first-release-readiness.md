# ADR-0009: Adopt Build Logic Convention Plugins and Library-First Release Readiness

## Status

Accepted

## Context

- `guideline/00-convention-over-configuration.md` and `guideline/12-module-build-conventions.md` define `build-logic/` as the SSoT for shared Gradle conventions.
- module `build.gradle.kts` files had repeated Kotlin Multiplatform target declarations, Android namespace setup, iOS framework naming, and Compose plugin wiring.
- release validation and CI compile coverage were biased toward sample hosts (`composeApp`, `androidApp`) even though the repository is primarily a publishable library workspace.
- release, ABI, and publishing checks already existed at the root, but the default validation story for published modules was not expressed as a library-first convention.

## Decision

- introduce `build-logic/` as a composite build that owns repository convention plugins for module build shapes.
- treat module type declaration as the default entrypoint:
  - `canimation.multiplatform.library`
  - `canimation.compose.multiplatform.library`
  - `canimation.platform.<target>.library`
- move shared target configuration, namespace derivation, and framework base-name rules into convention plugins instead of repeating them per module.
- standardize root-level library verification tasks so published modules are compiled independently of sample hosts:
  - `compileLibraryAndroid`
  - `compileLibraryJvm`
  - `compileLibraryApple`
  - `compileLibraryWeb`
- keep `releaseReadiness` as the stable release gate for ABI, Dokka, and publish-to-local verification, and make CI invoke the library compile aggregators alongside app-host checks.

## Consequences

- positive:
  - build CoC is executable instead of only documented
  - new modules can declare their shape with one plugin id rather than copying large target blocks
  - CI and release automation verify publishable library surfaces explicitly
  - module type and verification intent are easier to read from the build files
- negative:
  - `build-logic/` introduces another maintained Gradle boundary and requires dependency-verification updates when plugin dependencies change
  - convention changes now affect many modules at once and therefore need stronger review discipline
- migration impact:
  - existing module scripts move from repeated target declarations to convention plugin application
  - docs, CI, and quality evidence must reference the new library compile aggregators and the `build-logic/` boundary

## Alternatives Considered

- keep repeated target and plugin configuration in each module build script
- use `subprojects {}` or `allprojects {}` cross-project configuration instead of convention plugins
- use `buildSrc` instead of a dedicated `build-logic/` composite build

## Supersedes / Superseded by

- supersedes ad hoc repeated module target configuration spread across module build scripts
- superseded by: none
