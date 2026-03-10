# Accessibility Tier 1 Validation

## Purpose

platform 差分を除いた共通 accessibility contract を検証する。

## Scope

- policy resolution
- motion-level mapping
- reduced-motion behavior
- off-mode behavior
- regression signals in tests

## Policy Resolution Matrix

`CanimationPolicyResolver` resolves motion as follows:

| Policy | `systemReducedMotion = true` | `false` | `null` |
|---|---|---|---|
| `SystemAware` | `Reduced` | `Full` | `Reduced` |
| `AlwaysFull` | `Full` | `Full` | `Full` |
| `AlwaysReduced` | `Reduced` | `Reduced` | `Reduced` |
| `AlwaysOff` | `Off` | `Off` | `Off` |

`null -> Reduced` is intentional and keeps the runtime conservative when the platform preference cannot be determined.

## Spec Resolution Rules

### Preset-backed APIs

Preset-based APIs resolve through:

- `CanimationPreset`
- `CanimationPresetSpec`
- `PresetRegistry`

Each preset defines:

- `fullEnter`
- `fullExit`
- `reducedEnter`
- `reducedExit`

`Off` mode keeps the directional shape but forces `durationMs = 0`.

### Effect-backed APIs

Effect-based APIs resolve through `CanimationEffect` and `CanimationSpec`.

When reduced motion is needed, `CanimationSpec.toReduced()` applies these rules:

- duration -> short duration token (`120ms`)
- easing -> decelerate token
- alpha -> preserved
- offsetX / offsetY -> scaled down
- scale -> compressed toward `1f`
- rotation -> scaled down

This preserves meaning while reducing motion intensity instead of collapsing everything to a pure alpha fade.

## Validation Expectations

- reduced specs must not be longer than full specs
- reduced motion must not introduce new motion channels
- reduced motion must not amplify offset / scale / rotation magnitude
- off mode must keep the enter/exit directional contract

## Current Test Signals

- `CanimationPolicyResolverTest`
- `CanimationResolverIntegrationTest`
- `A11yMotionPolicyAdapterTest`
- `PresetIntegrityAuditTest`

Together they verify:

- policy resolution
- custom spec reduction
- preset reduction constraints
- fallback behavior

## Source Inputs

- current runtime implementation
- current accessibility tests
- previous accessibility validation notes promoted into the stable quality tree

## Last Updated Trigger

- refresh when policy resolution, reduced/off behavior, or accessibility tests change
