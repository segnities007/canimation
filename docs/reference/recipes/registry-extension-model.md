# Recipe Registry and Extension Model

この文書は stable registry and extension model の reference である。
規約は `guideline/22-recipe-descriptor-registry-and-extension-model.md` を参照する。

## Registry Default

- immutable by default
- descriptor-only storage
- explicit registration
- deterministic merge
- stable conflict policy is `Fail`

Current repository mapping:

- `canimation-recipes` exports the stable default registry that merges semantic and preset descriptors
- `canimation-presets` keeps its own preset-derived descriptor registry as a lower-level source set

## Merge Priority

1. host local overrides
2. explicitly installed extension packs
3. built-in stable registry
4. fallback recipe

## Extension Metadata

- pack id
- pack version
- core API compatibility range
- descriptor schema version
- declared stability tier
- publisher / namespace ownership information

## ID Ownership

- built-in: `io.github.canimation.*`
- first-party pack: `io.github.canimation.<pack>.*`
- third-party: reverse-domain style recommended
