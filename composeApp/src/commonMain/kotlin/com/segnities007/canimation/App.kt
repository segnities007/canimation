package com.segnities007.canimation

import androidx.compose.runtime.Composable
import com.segnities007.canimation.app.CanimationApp

@Composable
fun App() {
    CanimationApp()
}

@Composable
expect fun rememberSystemReducedMotion(): Boolean?
