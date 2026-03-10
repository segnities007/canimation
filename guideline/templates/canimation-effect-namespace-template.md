# `Canimation` Effect Namespace Template

```kotlin
import io.github.canimation.core.Canimation

val subtleEnter = Canimation.Fade.Up
val emphasizedEnter = Canimation.Scale.Pop + Canimation.Fade.In
val rotateEnter = Canimation.Rotate.FadeIn
```

## Rule

- 新しい UI はまず namespace から effect を選ぶ
- ad-hoc `CanimationEffect(...)` 生成は既存 namespace で足りない場合だけ
- namespace 追加時は分類を崩さない
