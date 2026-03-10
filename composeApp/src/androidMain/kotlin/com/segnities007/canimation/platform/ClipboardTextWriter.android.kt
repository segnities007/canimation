package com.segnities007.canimation.platform

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun rememberClipboardTextWriter(): ClipboardTextWriter {
    val context = LocalContext.current
    return remember(context) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        ClipboardTextWriter { value ->
            clipboardManager.setPrimaryClip(ClipData.newPlainText("canimation", value))
        }
    }
}
