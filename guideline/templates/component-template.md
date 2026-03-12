# Component Template

```kotlin
@Composable
internal fun FeatureToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
    )
}
```

## Rule

- leaf component は stateless を既定にする
- state は親から渡す
