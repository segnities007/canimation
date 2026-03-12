package com.segnities007.canimation.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@Composable
internal actual fun rememberClipboardTextWriter(): ClipboardTextWriter {
    return remember {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        ClipboardTextWriter { value ->
            clipboard.setContents(StringSelection(value), null)
        }
    }
}
