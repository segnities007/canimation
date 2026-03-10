package com.segnities007.canimation.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
internal actual fun rememberClipboardTextWriter(): ClipboardTextWriter {
    return remember {
        ClipboardTextWriter(::writeClipboardText)
    }
}

private fun writeClipboardText(value: String) {
    js("if (globalThis.navigator && globalThis.navigator.clipboard) { globalThis.navigator.clipboard.writeText(value); }")
}
