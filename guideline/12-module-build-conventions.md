# Module Build Convention Guideline

## Why This Exists

このリポジトリの module `build.gradle.kts` はかなり似た骨格を持っている。ここを CoC 化すると、新規 module 追加時の判断をほぼなくせる。

理想的な target-state での repository topology は
`18-target-state-architecture-and-package-topology.md`
を第一参照先にする。

## Default Module Shapes

### Library Module

- 既定 plugin 順:
  - `kotlinMultiplatform`
  - Android library plugin
  - `composeMultiplatform`
  - `composeCompiler`
- `kotlin {}` の target 順:
  - `androidLibrary`
  - iOS targets
  - `jvm()`
  - `js { browser() }`
  - `wasmJs { browser() }`
- `commonMain.dependencies` に shared API / implementation を集める。

### Platform Module

- 名前は `canimation-platform-<target>`
- 役割は adapter 提供に限定する。
- domain rule や public product API は持たない。
- common contract は他 module に置き、platform module は actual/adapter 実装に集中する。

### Test Helper Module

- shared test clock、test host、fixture を置く。
- production dependency graph を汚さない。

## Dependency Convention

- project dependency は内側から外側へ逆流させない。
- `api` は本当に surface に出すものだけ。
- `implementation` を既定とし、`api` は例外扱いにする。
- repository 宣言は root/settings に限定し、module 側に持たない。
- plugin version と shared Gradle logic は `build-logic/` と version catalog に集約し、module build script へ重複定義しない。

## Resource Convention

- Compose resources を使う module は `generateComposeResClass` 前提で整える。
- resource を持たない module に不要な resource setup を増やさない。

## Sources

- Gradle Docs, Structuring and Organizing Projects
  - https://docs.gradle.org/current/userguide/organizing_gradle_projects.html
- Gradle Docs, Best Practices for Dependencies
  - https://docs.gradle.org/current/userguide/best_practices_dependencies.html
