package com.segnities007.canimation.platform

import androidx.compose.runtime.Composable

internal fun interface ClipboardTextWriter {
    fun writeText(value: String)
}

@Composable
internal expect fun rememberClipboardTextWriter(): ClipboardTextWriter
