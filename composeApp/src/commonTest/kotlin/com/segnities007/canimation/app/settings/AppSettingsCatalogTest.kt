package com.segnities007.canimation.app.settings

import com.segnities007.canimation.component.canimationPolicyOptions
import io.github.canimation.core.CanimationPolicy
import kotlin.test.Test
import kotlin.test.assertEquals

class AppSettingsCatalogTest {
    @Test
    fun canimationPolicyOptionsExposeExpectedPolicyOrder() {
        assertEquals(
            listOf(
                CanimationPolicy.SystemAware,
                CanimationPolicy.AlwaysFull,
                CanimationPolicy.AlwaysReduced,
                CanimationPolicy.AlwaysOff,
            ),
            canimationPolicyOptions.map { it.policy },
        )
    }

    @Test
    fun formatSettingsScaleKeepsTwoDecimalPlaces() {
        assertEquals("1.20", formatSettingsScale(1.2f))
        assertEquals("0.35", formatSettingsScale(0.35f))
    }
}
