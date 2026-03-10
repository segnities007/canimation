# Ideal Architecture Blueprint

## Purpose

この文書は、`Canimation` の理想 target-state を 1 枚で把握するための master blueprint である。
規約の SSoT は `guideline/` にあるが、ここでは product から file までの全体像を top-down で束ねる。

## Product Identity

`Canimation` は、Compose Multiplatform 向けの accessibility-first semantic motion system である。

主従関係は次のとおり。

1. semantic motion library
2. accessibility-aware runtime
3. low-level animation toolkit

## Non-Negotiable Axioms

- semantics first
- accessibility as contract
- descriptor as SSoT
- immutable registry by default
- outer-to-inner dependency direction
- convention over configuration
- backward compatibility over cleverness

## Canonical Repository Layout

```text
repository
├── build-logic/
├── gradle/
├── modules/
├── samples/
├── benchmarks/
├── docs/
├── guideline/
├── scripts/
└── .github/
```

## Canonical Module Architecture

```text
canimation-runtime
  -> canimation-a11y
  -> canimation-recipes
  -> canimation-semantics
  -> canimation-primitives
  -> canimation-tokens
```

stable core line:

- `canimation-tokens`
- `canimation-primitives`
- `canimation-semantics`
- `canimation-recipes`
- `canimation-runtime`
- `canimation-a11y`

current implementation note:

- these target-state modules now exist as publishable wrapper surfaces over the current core implementation
- tokens, primitives, semantics, and recipe catalog ownership have already started moving out of `canimation-core`
- deeper runtime extraction from `canimation-core` is still a continuing migration step

isolated line:

- `canimation-diagnostics`
- `canimation-test-kit`
- `canimation-compat`
- `canimation-experimental`
- `canimation-platform-*`

## Public API Shape

stable entry points:

- `CanimationProvider`
- `Modifier.canimation(recipe = ...)`
- `CanimationVisibility(...)`
- `CanimationTransition(...)`
- `CanimationRecipeRegistry`

stable semantic namespaces:

- `Canimation.Content`
- `Canimation.Feedback`
- `Canimation.Navigation`
- `Canimation.Surface`
- `Canimation.Emphasis`
- `Canimation.Transition`
- `Canimation.Ambient`
- `Canimation.Recovery`

currently implemented first-party semantic recipes:

- `Canimation.Content.EnterSubtle`
- `Canimation.Content.EnterStandard`
- `Canimation.Feedback.Press`
- `Canimation.Feedback.SelectionPulse`
- `Canimation.Navigation.Forward`
- `Canimation.Navigation.Backward`
- `Canimation.Surface.DialogReveal`
- `Canimation.Surface.SheetReveal`
- `Canimation.Emphasis.CallToAction`
- `Canimation.Transition.ContentSwap`
- `Canimation.Ambient.Highlight`
- `Canimation.Recovery.ErrorShake`

## Semantic Taxonomy

すべての stable recipe は次を持つ。

- intent
- surfaceRole
- intensity
- cost

stable intent set:

- `Content`
- `Feedback`
- `Navigation`
- `Surface`
- `Emphasis`
- `Transition`
- `Ambient`
- `Recovery`

## Descriptor and Registry Rule

recipe の唯一の真実は descriptor とする。

descriptor required blocks:

- `semantic`
- `accessibility`
- `performance`
- `docs`
- `specs`

registry rule:

- immutable by default
- explicit registration
- deterministic merge
- no silent overwrite

## Compose and App Rule

- runtime は意味論を持たず、解決して適用するだけ
- showcase app は library の consumer として構成する
- screen は `State + StateHolder + Screen` を既定形にする

## Documentation System

```text
docs/
├── tutorials/
├── how-to/
├── reference/
├── explanation/
├── architecture/decisions/
└── quality/
```

boundary:

- rules: `guideline/`
- reference contract: `docs/reference/`
- rationale: `docs/explanation/`
- ADR: `docs/architecture/decisions/`
- evidence: `docs/quality/`

## Governing Guidelines

- `guideline/18-target-state-architecture-and-package-topology.md`
- `guideline/19-oss-governance-and-maintainer-experience.md`
- `guideline/20-documentation-adr-and-knowledge-ssot.md`
- `guideline/21-semantic-taxonomy-and-stability-tiering.md`
- `guideline/22-recipe-descriptor-registry-and-extension-model.md`
- `guideline/23-public-api-compatibility-and-migration.md`
- `guideline/24-showcase-catalog-and-consumer-app-conventions.md`
- `guideline/25-quality-evidence-and-audit-artifacts.md`

## Final Rule

新しい設計判断は、必ず次の順で行う。

1. product fit
2. module boundary fit
3. semantic fit
4. API tier fit
5. package/file fit
6. docs/quality/governance fit
