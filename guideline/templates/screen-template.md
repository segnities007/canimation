# Screen Template

## Files

- `XxxState.kt`
- `XxxStateHolder.kt`
- `XxxScreen.kt`
- `XxxStateTest.kt`

## `XxxState.kt`

```kotlin
package com.example.screen

internal data class XxxUiState(
    val headerStage: Int = -1,
)

internal sealed interface XxxEvent {
    data class HeaderStageChanged(val stage: Int) : XxxEvent
}

internal fun reduceXxxState(
    current: XxxUiState,
    event: XxxEvent,
): XxxUiState = when (event) {
    is XxxEvent.HeaderStageChanged -> current.copy(headerStage = event.stage)
}
```

## `XxxStateHolder.kt`

```kotlin
package com.example.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Stable
internal class XxxStateHolder(
    initialState: XxxUiState = XxxUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: XxxEvent) {
        uiState = reduceXxxState(uiState, event)
    }
}

@Composable
internal fun rememberXxxStateHolder(): XxxStateHolder {
    val stateHolder = remember { XxxStateHolder() }

    LaunchedEffect(Unit) {
        for (stage in 0..3) {
            stateHolder.onEvent(XxxEvent.HeaderStageChanged(stage))
            delay(60)
        }
    }

    return stateHolder
}
```

## `XxxScreen.kt`

```kotlin
package com.example.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun XxxScreen(
    modifier: Modifier = Modifier,
) {
    val stateHolder = rememberXxxStateHolder()
    val uiState = stateHolder.uiState

    XxxContent(
        state = uiState,
        onEvent = stateHolder::onEvent,
        modifier = modifier,
    )
}
```

## `XxxStateTest.kt`

```kotlin
package com.example.screen

import kotlin.test.Test
import kotlin.test.assertEquals

class XxxStateTest {

    @Test
    fun reducerUpdatesHeaderStage() {
        val base = XxxUiState()

        val updated = reduceXxxState(
            current = base,
            event = XxxEvent.HeaderStageChanged(2),
        )

        assertEquals(2, updated.headerStage)
    }
}
```
