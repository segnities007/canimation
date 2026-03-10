# Platform Adapter Template

## Common Contract

```kotlin
package com.example.platform

interface FeaturePort {
    fun perform(value: String): Boolean
}
```

## Android / JVM / JS / iOS Adapter

```kotlin
package com.example.platform

internal class FeatureAdapter : FeaturePort {
    override fun perform(value: String): Boolean {
        return true
    }
}
```

## Rule

- common には contract だけ置く
- target source set には platform API access だけ置く
- fallback が必要なら `FeatureFallback` を追加する
- UI/domain から adapter 実装型を直接参照しない
