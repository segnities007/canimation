# Reducer Test Template

```kotlin
class FeatureStateTest {

    @Test
    fun reducerUpdatesStage() {
        val base = FeatureUiState()

        val updated = reduceFeatureState(
            current = base,
            event = FeatureEvent.StageChanged(3),
        )

        assertEquals(3, updated.stage)
    }
}
```
