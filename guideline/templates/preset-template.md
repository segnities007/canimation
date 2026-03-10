# Preset Template

## File

- `modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/XxxPreset.kt`

## Template

```kotlin
package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationSpec

internal object XxxPreset {
    val spec: CanimationPresetSpec =
        CanimationPresetSpec(
            fullEnter = CanimationSpec(
                durationMs = 220,
                easing = easingToken,
            ),
            fullExit = CanimationSpec(
                durationMs = 180,
                easing = easingToken,
            ),
            reducedEnter = CanimationSpec(
                durationMs = 120,
                easing = reducedEasingToken,
            ),
            reducedExit = CanimationSpec(
                durationMs = 100,
                easing = reducedEasingToken,
            ),
        )
}
```

## Checklist

- enum に追加
- registry に追加
- gallery/reference に必要な説明を追加
- integrity test を確認
- reduced-motion spec を同時定義
