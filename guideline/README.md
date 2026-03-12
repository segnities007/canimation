# Guideline Index

このディレクトリは、`Canimation` に関わる技術領域ごとの強制ガイドラインをまとめた SSoT である。設計・実装・レビュー・CI・リリースのすべてで、該当領域の文書を必ず参照する。

## Scope

- `00-convention-over-configuration.md`
  - 既定構成、命名、配置、実装形の標準
- `01-kotlin.md`
  - Kotlin の書き方、公開 API、型設計、可視性、例外・状態の扱い
- `02-kotlin-multiplatform.md`
  - Kotlin Multiplatform の source set、`expect/actual`、共有ロジック、target 差分
- `03-jetpack-compose.md`
  - Jetpack Compose の UDF、state hoisting、再 compose、semantics、performance
- `04-compose-multiplatform.md`
  - Compose Multiplatform の resource、共通 UI、Android Compose との差分
- `05-animation.md`
  - animation API 選定、motion token、timing/easing、draw/layout 負荷
- `06-accessibility-i18n.md`
  - accessibility、reduced-motion、keyboard/focus、localization、pseudolocale
- `07-testing-quality.md`
  - unit/reducer/platform/UI test、flake 対策、coverage、回帰防止
- `08-gradle-build.md`
  - Gradle Kotlin DSL、configuration cache、dependency verification、build performance
- `09-github-actions-ci.md`
  - GitHub Actions の security、permissions、reusable workflows、concurrency、cache、attestation
- `10-release-api-compatibility.md`
  - backward compatibility、SemVer、deprecation、changelog、public API review
- `11-platform-adapters.md`
  - adapter/port、platform API 隔離、clipboard/system motion/frame metrics などの boundary
- `12-module-build-conventions.md`
  - module `build.gradle.kts` の標準骨格、dependency、resource setup
- `13-workflow-and-script-conventions.md`
  - workflow/job/script 名、shell script 定型、artifact 出力先
- `14-docs-navigation-and-resource-conventions.md`
  - docs 配置、route naming、resource naming、test placement
- `15-symbol-and-file-micro-conventions.md`
  - シンボル名、ファイル内順序、reducer/state holder の標準形
- `16-compose-micro-conventions.md`
  - `remember`、`LaunchedEffect`、`Modifier`、Compose 細部の標準形
- `17-test-and-workflow-micro-conventions.md`
  - test 1 ケース粒度、workflow step 名、artifact 名の標準形
- `18-target-state-architecture-and-package-topology.md`
  - 理想 OSS library の repository/module/source set/package/file 構造、依存方向、pattern selection
- `19-oss-governance-and-maintainer-experience.md`
  - README/CONTRIBUTING/SUPPORT/SECURITY/CODEOWNERS、triage、review、support policy、maintainer role
- `20-documentation-adr-and-knowledge-ssot.md`
  - docs IA、ADR、knowledge lifecycle、SSoT 境界、quality artifact と doc versioning
- `21-semantic-taxonomy-and-stability-tiering.md`
  - semantic intent/surface/intensity/cost、stable/experimental 境界、命名と分類規約
- `22-recipe-descriptor-registry-and-extension-model.md`
  - descriptor schema、registry merge policy、extension metadata、validation と SPI 規約
- `23-public-api-compatibility-and-migration.md`
  - stable entry、tier 分離、互換層、deprecation/migration、docs 導線の規約
- `24-showcase-catalog-and-consumer-app-conventions.md`
  - showcase/gallery/detail/pattern/lab、consumer app 構成、catalog/data/state holder 規約
- `25-quality-evidence-and-audit-artifacts.md`
  - `docs/quality/` の report 種別、命名、必須項目、更新条件、evidence lifecycle

## Enforcement

- 変更者は、触るファイルが属する領域を先に特定する。
- 1つの変更が複数領域にまたがる場合、関係するすべてのガイドラインを確認する。
- 迷った場合は、まず `00-convention-over-configuration.md` の既定形を使う。
- target-state の repository 再設計や新規 OSS 運用ルールを決める場合は `18` から `20` を確認する。
- motion semantics、descriptor、registry、public API migration、showcase、quality artifact を決める場合は `21` から `25` を確認する。
- 実装がガイドラインと競合する場合は、`AGENTS.md` の例外規約を満たす必要がある。
- 領域ガイドラインを更新したら、必要に応じて `AGENTS.md` と関連ドキュメントも同時更新する。

## Source Policy

- ベストプラクティスの根拠は原則として公式ドキュメントまたは仕様を使う。
- このディレクトリの各ファイル末尾に参照元を明示する。
- 最新性が重要な領域は、更新時点の upstream guidance を再確認する。

## Templates

- 実装テンプレートは `guideline/templates/` に置く。
- CoC を文章だけでなく雛形として再利用できる状態を保つ。
