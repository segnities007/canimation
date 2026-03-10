# Platform Test Template

```kotlin
class FeaturePlatformAdapterTest {

    @Test
    fun adapterReturnsExpectedCapabilityResult() {
        val adapter = FeatureAdapter()

        val result = adapter.perform("value")

        assertTrue(result)
    }
}
```
