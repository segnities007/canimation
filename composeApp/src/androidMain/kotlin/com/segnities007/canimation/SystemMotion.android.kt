package com.segnities007.canimation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import io.github.canimation.a11y.SystemMotionPreference
import io.github.canimation.platform.android.AndroidMotionPreferenceDataSource

@Composable
actual fun rememberSystemReducedMotion(): Boolean? {
    val context = LocalContext.current
    val dataSource = remember(context) { AndroidMotionPreferenceDataSource(context) }
    val preference by dataSource.observePreference().collectAsState(
        initial = dataSource.currentPreference(),
    )
    return preference == SystemMotionPreference.ReduceMotion
}
