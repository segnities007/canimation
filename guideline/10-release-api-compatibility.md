# Release and API Compatibility Guideline

## Scope

- public API
- semver
- deprecation
- changelog
- migration

## Core Rules

- breaking change は事前告知なしで入れない。
- deprecation は message で migration path を示す。
- changelog は user impact ベースで書く。
- release note は implementation detail ではなく contract 変化を中心に書く。

## Versioning

- backward compatible bug fix は patch。
- backward compatible feature addition は minor。
- incompatible public API change は major。
- multiplatform library では Android だけでなく target 全体の互換性で判定する。

## Compatibility Practice

- accidental API exposure を避ける。
- inferred public type 変更による binary/source break を避ける。
- public symbol rename は deprecate -> migrate -> remove で行う。
- docs / sample / demo app も migration path の一部として更新する。

## Sources

- Kotlin Docs, Backward compatibility guidelines for library authors
  - https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html
- Kotlin Docs, Introduction to library authors' guidelines
  - https://kotlinlang.org/docs/api-guidelines-introduction.html
- Semantic Versioning 2.0.0
  - https://semver.org/
