# ViewModel-Equivalent Template

このリポジトリでは Android `ViewModel` を第一選択にしない。KMP 互換の `StateHolder` を ViewModel 相当の既定形とする。

```kotlin
package com.example.feature

@Stable
internal class FeatureStateHolder(
    initialState: FeatureUiState = FeatureUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: FeatureEvent) {
        uiState = reduceFeatureState(uiState, event)
    }

    suspend fun refresh() {
        onEvent(FeatureEvent.LoadingChanged(true))
        try {
            // call use case / repository
        } finally {
            onEvent(FeatureEvent.LoadingChanged(false))
        }
    }
}
```

## Rule

- lifecycle owner 依存を持ち込まない
- Android 専用 `ViewModel` が必要なら adapter 層で包む
- screen からは `StateHolder` を見る
