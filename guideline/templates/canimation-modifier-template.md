# `Modifier.canimation` Template

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun FeatureCard(
    visible: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.canimation(
            visible = visible,
            effect = Canimation.Fade.Up,
        ),
    ) {
        // content
    }
}
```

## Variants

```kotlin
modifier.canimation(
    visible = visible,
    effect = Canimation.Scale.Pop + Canimation.Fade.In,
)
```

## Rule

- effect は namespace から選ぶ
- visible は state holder 由来にする
- duration/easing を UI に直書きしない
