# Animation Showcase Template

```kotlin
@Composable
fun PresetPreviewCard(
    preset: CanimationPreset,
    spec: CanimationPresetSpec,
    tuning: PresetPreviewTuning,
    autoPlayEnabled: Boolean,
    autoPlayTick: Int,
    modifier: Modifier = Modifier,
) {
    val previewSpec = remember(spec, tuning) { tunePresetSpec(spec, tuning) }
    var visible by remember(preset) { mutableStateOf(false) }

    suspend fun replay() {
        visible = false
        delay(220)
        visible = true
    }

    LaunchedEffect(preset) { replay() }
    LaunchedEffect(autoPlayEnabled, autoPlayTick) {
        if (autoPlayEnabled) replay()
    }

    Box(
        modifier = modifier.canimationTransition(
            visible = visible,
            enterFullSpec = previewSpec.fullEnter,
            enterReducedSpec = previewSpec.reducedEnter,
            exitFullSpec = previewSpec.fullExit,
            exitReducedSpec = previewSpec.reducedExit,
        ),
    )
}
```

## Rule

- tuning は helper 経由で spec 変換する
- replay timing を各所で重複させない
