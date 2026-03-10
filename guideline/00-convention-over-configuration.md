# Convention Over Configuration Guideline

## Goal

このプロダクトでは Ruby on Rails 的な Convention over Configuration を目指す。つまり、毎回設計選択を議論しない。まず既定形に従い、既定形で解けない時だけ例外を導入する。

repository / module / package / file の理想終着点は
`18-target-state-architecture-and-package-topology.md`
を第一参照先にする。
OSS governance と docs/knowledge の運用標準は
`19-oss-governance-and-maintainer-experience.md` と
`20-documentation-adr-and-knowledge-ssot.md`
を参照する。

## Default Decision Rule

- 新規実装は「どの形でもよい」ではなく、まず既定形を使う。
- 既定形から外れる場合は、`AGENTS.md` の例外規約を満たす。
- 命名、配置、責務分離、テスト場所、workflow 名は、既定形を優先する。

## Canonical Layout

### Repository Root

- 理想的な root directory は次を既定にする。
  - `build-logic/`
  - `gradle/`
  - `modules/`
  - `samples/`
  - `benchmarks/`
  - `docs/`
  - `guideline/`
  - `scripts/`
  - `.github/`
- product code は `modules/` に集約し、sample host と混在させない。
- build logic は `build-logic/` の convention plugin に集約し、module ごとの重複 build script を増やさない。
- benchmark と sample は library と別 directory に分離し、consumer path を汚さない。

### Screen

- Screen 追加時の既定ファイル:
  - `XxxScreen.kt`
  - `XxxStateHolder.kt`
  - `XxxState.kt` または `XxxModel.kt`
  - `XxxCatalog.kt` が必要な場合のみ追加
- `XxxScreen.kt` は画面構成と event wiring だけを持つ。
- reducer は `reduceXxxState` という top-level pure function にする。
- `rememberXxxStateHolder()` を用意し、screen-level mutable state はそこへ集約する。

### Example / Demo Content

- デモデータは `*Catalog` または `*Data` に集約する。
- preview/demo renderer は `*PreviewRenderers.kt` へ置く。
- component animation のサンプルはカテゴリごとに分け、1 ファイル 500 行未満に保つ。

### Preset

- preset 追加時の既定:
  - 1 preset 1 file
  - ファイル名は `XxxPreset.kt`
  - registry への接続は `PresetRegistryDefaultsPart*.kt`
- preset 本体に UI 知識を入れない。
- reduced-motion 代替 spec を同時に定義する。

### Platform Adapter

- platform capability 追加時の既定:
  - common に契約
  - target ごとに `FeatureName.platform.kt` か `FeatureName.<target>.kt`
  - domain/UI からは common 契約だけを見る
- adapter 名は `*Adapter`、data source 名は `*DataSource`、fallback 名は `*Fallback` を基本にする。

### Docs

- 設計原則は `guideline/`
- プロダクト判断・計画・ADR は `docs/`
- release policy は release 系 docs に置く
- 「規約」と「事情説明」を同じファイルに混ぜない

### GitHub Actions

- workflow 名の既定:
  - `ci.yml`: push / pull_request の基本検証
  - `release.yml`: release と publish
  - `pages.yml`: documentation/site deploy
  - `schedule.yml`: 定期点検
- reusable 化できるセットアップは `.github/actions/` か reusable workflow に切り出す。
- job 名は `build`, `test`, `lint`, `publish`, `deploy`, `audit` など動詞ベースで統一する。

## Naming Conventions

### Type and File Pairing

- `FooStateHolder` は `FooStateHolder.kt`
- `FooUiState` は `FooState.kt` か `FooStateHolder.kt` に置く
- `FooEvent` は `FooStateHolder.kt` に置くか、肥大化したら `FooState.kt` に分離する
- `FooCatalog` は表示や選択肢の SSoT
- `FooAdapter` は port 実装
- `FooPolicy` は切替可能ロジック
- `FooRoute` は navigation contract

### Route Naming

- route key は `ROUTE_KEY_FOO`
- route object は `FooRoute`
- `String.contains()` ベースの route 判定を増やさない。既存 helper を使う。

### Test Naming

- test class は対象名 + `Test`
- test 名は behavior ベースにする
- reducer test は `event -> state` の変化が読める名前にする

## Default Implementation Shapes

### Screen Shape

1. `UiState`
2. `Event`
3. `reduceXxxState`
4. `XxxStateHolder`
5. `rememberXxxStateHolder`
6. `XxxScreen`

この順を既定とする。

### Diagnostics Shape

1. metrics collection
2. pure metrics computation
3. overlay state holder
4. overlay rendering

計測と描画を直結しない。

### Settings / Form Shape

- staged local input がある場合も、screen-level state と混在させない。
- bottom sheet / dialog 単位で state holder を分けてよい。
- form value の変換・正規化は reducer か state holder 側で行う。

## File Growth Rules

- 200 行を超えたら責務を点検する。
- 300 行を超えたら分割案を必ず検討する。
- 500 行超は原則禁止。
- ファイル分割時は `Part2` を乱用せず、できるだけ責務名で切る。
  - ただし既存構造上の互換維持中は一時的 `Part2` を許容する。

## Default Anti-Drift Rules

- 同じ kind のものは同じ場所に置く。
- 似た責務なのに命名や配置だけ違う実装を増やさない。
- 新規コードが既存規約を更新するなら、先に `guideline/` を更新する。
- 「今回だけ別」は禁止。別扱いにするなら例外記録が必要。

## Sources

- Ruby on Rails Guides, Getting Started with Rails
  - https://guides.rubyonrails.org/getting_started.html
- Ruby on Rails Guides, Rails Command Line
  - https://guides.rubyonrails.org/command_line.html
- Kotlin Docs, Coding conventions
  - https://kotlinlang.org/docs/coding-conventions.html
