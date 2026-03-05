package com.segnities007.canimation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import io.github.canimation.a11y.SystemMotionPreference
import io.github.canimation.platform.ios.IosMotionPreferenceDataSource

@Composable
actual fun rememberSystemReducedMotion(): Boolean? {
    val dataSource = remember { IosMotionPreferenceDataSource() }
    val preference by dataSource.observePreference().collectAsState(
        initial = dataSource.currentPreference(),
    )
    return preference == SystemMotionPreference.ReduceMotion
}
