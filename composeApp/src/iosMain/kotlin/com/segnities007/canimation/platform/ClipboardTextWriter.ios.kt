package com.segnities007.canimation.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.UIPasteboard

@Composable
internal actual fun rememberClipboardTextWriter(): ClipboardTextWriter {
    return remember {
        val pasteboard = UIPasteboard.generalPasteboard
        ClipboardTextWriter { value ->
            pasteboard.string = value
        }
    }
}
