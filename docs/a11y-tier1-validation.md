# Accessibility — Tier 1 Validation

## Purpose

This document defines the Tier 1 accessibility validation criteria for
canimation. Tier 1 covers **policy-level behavior**: ensuring that
animation levels map correctly to user and system preferences.

## Animation Levels

| Level      | Visual Behavior                        | Duration        |
|------------|----------------------------------------|-----------------|
| `Full`     | All effects (translate, scale, alpha). | Designed value. |
| `Reduced`  | Alpha-only transitions.               | ≤ 200 ms.       |
| `Off`      | Instant state change, no animation.   | 0 ms.           |

### Expected Modifier Behavior per Level

| Modifier                       | Full                 | Reduced          | Off            |
|--------------------------------|----------------------|------------------|----------------|
| `Modifier.canimationEnter()`   | Full preset effect   | Fade-in ≤ 200 ms | Instant appear |
| `Modifier.canimationExit()`    | Full preset effect   | Fade-out ≤ 200 ms| Instant remove |
| `Modifier.canimationTransition()` | Full preset effect| Crossfade ≤ 200 ms| Instant swap |

## System Reduce-Motion Detection

`CanimationPolicyResolver` reads the platform's reduce-motion preference and
returns the resolved `CanimationLevel`:

| Platform | API Used                                                 | Reduce-Motion → Level |
|----------|----------------------------------------------------------|-----------------------|
| Android  | `Settings.Global.ANIMATOR_DURATION_SCALE`                | `0f` → `Off`, else check `AccessibilityManager` |
| iOS      | `UIAccessibility.isReduceMotionEnabled`                  | `true` → `Reduced`   |
| JVM      | `System.getProperty("canimation.reduceMotion")`          | `"true"` → `Reduced` |
| JS/Wasm  | `window.matchMedia("(prefers-reduced-motion: reduce)")` | matches → `Reduced`  |

### CanimationPolicyResolver Behavior

```
resolve(policy, systemSetting) → CanimationLevel
```

| Policy           | System reduce-motion OFF | System reduce-motion ON |
|------------------|--------------------------|-------------------------|
| `SystemAware`    | `Full`                   | `Reduced`               |
| `AlwaysFull`     | `Full`                   | `Full`                  |
| `AlwaysReduced`  | `Reduced`                | `Reduced`               |
| `AlwaysOff`      | `Off`                    | `Off`                   |

> **Note:** `SystemAware` is the recommended default. It respects user
> preferences while allowing developers to override when necessary.

## Test Matrix

The following matrix must pass before a release is tagged.

### Unit Tests

| ID    | Test Case                                             | Expected Result          |
|-------|-------------------------------------------------------|--------------------------|
| T1-01 | `SystemAware` + reduce-motion OFF → `Full`            | Level is `Full`          |
| T1-02 | `SystemAware` + reduce-motion ON → `Reduced`          | Level is `Reduced`       |
| T1-03 | `AlwaysFull` ignores system setting                   | Level is `Full`          |
| T1-04 | `AlwaysReduced` ignores system setting                | Level is `Reduced`       |
| T1-05 | `AlwaysOff` ignores system setting                    | Level is `Off`           |
| T1-06 | `Reduced` level applies alpha-only to `SlideUp`       | No translate, only alpha |
| T1-07 | `Off` level completes instantly for all presets        | Duration = 0             |
| T1-08 | Runtime policy change propagates to active animations  | Level updates live       |

### Instrumented / UI Tests

| ID    | Test Case                                              | Platform | Expected Result          |
|-------|--------------------------------------------------------|----------|--------------------------|
| T1-09 | Toggle device reduce-motion, verify level change       | Android  | Level follows system     |
| T1-10 | Toggle device reduce-motion, verify level change       | iOS      | Level follows system     |
| T1-11 | `CanimationTestClock` advances to end, verify final state | All   | Composable in end state  |
| T1-12 | `CanimationTestHost` renders preset at each level      | All      | Visual snapshot matches  |

## Acceptance Criteria

- [ ] All T1-01 through T1-12 tests pass on CI.
- [ ] No animation plays when level is `Off`.
- [ ] `Reduced` level animations complete within 200 ms.
- [ ] `CanimationPolicyResolver` correctly reads system settings on every
      supported platform.
