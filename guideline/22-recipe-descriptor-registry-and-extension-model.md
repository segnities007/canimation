# Recipe Descriptor, Registry, and Extension Model Guideline

## Scope

- recipe descriptor schema
- registry merge and lookup policy
- extension metadata and SPI
- descriptor validation
- compatibility bridge rule

## Goal

recipe の意味、spec、a11y、performance、docs metadata は descriptor に集約する。
registry は immutable に保ち、extension は explicit registration を既定にする。

## Descriptor SSoT Rule

descriptor は次を同時に保持する唯一の recipe SSoT とする。

- semantic identity
- accessibility contract
- performance contract
- docs metadata
- runtime spec

禁止事項:

- spec と docs metadata の二重管理
- gallery 用だけの別 catalog
- reduced spec を別 registry に持つこと

## Canonical Descriptor Shape

```text
CanimationRecipeDescriptor
  - id
  - semantic
  - accessibility
  - performance
  - docs
  - specs
```

### Required Stable Fields

- `id`
- `semantic.intent`
- `semantic.surfaceRole`
- `semantic.intensity`
- `semantic.family`
- `semantic.tags`
- `accessibility.reducedStrategy`
- `accessibility.supportsOff`
- `performance.cost`
- `performance.listSafe`
- `performance.benchmarkRequired`
- `docs.title`
- `docs.summary`
- `docs.usageGuidance`
- `specs.full`
- `specs.reduced`
- `specs.off`

Rules:

- stable recipe は `supportsOff = true` 必須
- `cost == Layout` なら `benchmarkRequired = true` 必須
- `usageGuidance` は stable recipe で必須
- `off` を runtime で自動生成しない

## Built-In File Shape

```text
XxxRecipe.kt
  - id
  - descriptor
  - fullSpec
  - reducedSpec
  - offSpec
  - local helper
```

## Registry Rule

registry は descriptor の read-only collection を基本とする。

### Merge Priority

1. host local overrides
2. explicitly installed extension packs
3. built-in stable registry
4. fallback recipe

### Conflict Policy Modes

| Policy | Meaning | Default use |
|---|---|---|
| `Fail` | duplicate id で構築失敗 | stable default |
| `PreferHost` | host override を優先 | explicit app composition |
| `PreferExtension` | extension を優先 | advanced/experimental only |

Rules:

- stable runtime default は `Fail`
- silent overwrite を禁止する
- built-in stable IDs は immutable contract とする

## Extension Loading Rule

- implicit classpath scanning を既定にしない
- extension install は explicit registration を基本にする
- API と SPI を分離し、暗黙 SPI を禁止する

### Required Extension Metadata

- pack id
- pack version
- core API compatibility range
- descriptor schema version
- declared stability tier
- publisher / namespace ownership information

### ID Ownership Rule

- built-in: `io.github.canimation.*`
- first-party pack: `io.github.canimation.<pack>.*`
- third-party: reverse-domain style を推奨する

## Descriptor Validation Order

1. structural validation
2. semantic validation
3. accessibility validation
4. performance validation
5. docs completeness validation

### Required Checks

- id が空でない
- tags が重複しない
- full/reduced/off が揃っている
- `family` が `intent` と矛盾しない
- off spec duration が 0 である
- reduced が full より高負荷になっていない
- `Layout` cost で benchmarkRequired が false でない

## Compatibility Bridge Rule

移行期の alias/bridge は descriptor を参照するだけにする。

禁止事項:

- alias が独自 spec を持つこと
- compatibility layer が descriptor SSoT を複製すること

## Sources

- Kotlin Docs, Backward compatibility guidelines for library authors
  - https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html
- Kotlin Docs, Introduction to library authors' guidelines
  - https://kotlinlang.org/docs/api-guidelines-introduction.html
