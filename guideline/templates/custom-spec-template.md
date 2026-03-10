# Custom Spec Template

```kotlin
import androidx.compose.animation.core.LinearEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

val customSpec = CanimationSpec(
    durationMs = 220,
    easing = LinearEasing,
    alpha = CanimationRange(from = 0f, to = 1f),
    offsetY = CanimationDpRange(from = 12.dp, to = 0.dp),
    scale = CanimationRange(from = 0.96f, to = 1f),
)
```

## Rule

- channel は必要最小限に絞る
- reduced spec は `toReduced()` か明示 spec を併記する
- spec は UI ではなく preset / sample / test fixture に寄せる
