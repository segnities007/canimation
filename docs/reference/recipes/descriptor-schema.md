# Recipe Descriptor Schema Reference

この文書は stable recipe descriptor schema の reference である。
規約は `guideline/22-recipe-descriptor-registry-and-extension-model.md` を参照する。

## Canonical Shape

```text
CanimationRecipeDescriptor
  - id
  - semantic
  - accessibility
  - performance
  - docs
  - specs
```

## Required Stable Fields

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

## Validation Order

1. structural validation
2. semantic validation
3. accessibility validation
4. performance validation
5. docs completeness validation

## Registry Rule

- descriptor only
- immutable by default
- explicit registration
- deterministic merge
- stable default conflict policy is `Fail`

## Current Built-In Semantic Catalog

The repository currently ships a first-party semantic recipe catalog via
`DefaultCanimationRecipeRegistry`.

At the recipes-layer entrypoint, `DefaultCanimationRecipeRegistry` includes:

- built-in semantic recipes from `canimation-recipes`
- built-in preset-derived descriptors from `canimation-presets`

The catalog currently contains:

- `semantic.content.enter-subtle`
- `semantic.content.enter-standard`
- `semantic.feedback.press`
- `semantic.feedback.selection-pulse`
- `semantic.navigation.forward`
- `semantic.navigation.backward`
- `semantic.surface.dialog-reveal`
- `semantic.surface.sheet-reveal`
- `semantic.emphasis.call-to-action`
- `semantic.transition.content-swap`
- `semantic.ambient.highlight`
- `semantic.recovery.error-shake`
