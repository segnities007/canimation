# Public API Migration Policy

この文書は effect-first / preset-first API から semantics-first API への migration reference である。
規約は `guideline/23-public-api-compatibility-and-migration.md` を参照する。

## Stable Entry Points

- `CanimationProvider`
- `Modifier.canimation(recipe = ...)`
- `CanimationVisibility(...)`
- `CanimationTransition(...)`
- `CanimationRecipeRegistry`

## Current Implemented Semantic Surface

The repository currently exposes a first built-in semantic recipe catalog through:

- `DefaultCanimationRecipeRegistry`
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

This is the first implemented stable path. Effect-first and preset-first APIs still coexist during migration.

## Stable Semantic Namespaces

- `Canimation.Content`
- `Canimation.Feedback`
- `Canimation.Navigation`
- `Canimation.Surface`
- `Canimation.Emphasis`
- `Canimation.Transition`
- `Canimation.Ambient`
- `Canimation.Recovery`

## Compatibility Policy

- old effect/preset APIs are compatibility-only
- compatibility APIs must not become the first docs path
- migration follows `deprecate -> migrate -> remove`

## Current Module Mapping

- stable semantic entry surfaces:
  - `canimation-semantics`
  - `canimation-recipes`
  - `canimation-runtime`
- extracted primitive foundations:
  - `canimation-tokens`
  - `canimation-primitives`
- compatibility-oriented surfaces:
  - `canimation-compat`
  - `canimation-presets`

## Deprecation Message Requirements

- why deprecated
- where to migrate
- how to reinterpret semantic intent
