# `Modifier.canimationTransition` Template

## Preset-Based

```kotlin
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.canimationTransition

modifier.canimationTransition(
    visible = visible,
    enterPreset = CanimationPreset.FadeUp,
    exitPreset = CanimationPreset.FadeUp,
)
```

## Custom Spec-Based

```kotlin
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.canimationTransition
import androidx.compose.animation.core.LinearEasing

val enterSpec = CanimationSpec(
    durationMs = 220,
    easing = LinearEasing,
    alpha = CanimationRange(from = 0f, to = 1f),
)

modifier.canimationTransition(
    visible = visible,
    enterFullSpec = enterSpec,
)
```

## Rule

- preset で表現できるなら preset を優先する
- custom spec は library sample / tuning UI / new preset authoring に限定する
