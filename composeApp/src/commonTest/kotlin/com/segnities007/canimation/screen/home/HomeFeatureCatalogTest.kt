package com.segnities007.canimation.screen.home

import io.github.canimation.core.CanimationPreset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HomeFeatureCatalogTest {
    @Test
    fun homeFeatureRowsKeepExpectedLayout() {
        assertEquals(2, homeFeatureRows.size)
        assertEquals(listOf(3, 3), homeFeatureRows.map { it.size })
    }

    @Test
    fun presetCountCardRemainsFirstAndUnique() {
        val flattened = homeFeatureRows.flatten()

        assertTrue(flattened.first().usesPresetCount)
        assertEquals(CanimationPreset.BounceIn, flattened.first().demoPreset)
        assertEquals(1, flattened.count { it.usesPresetCount })
        assertFalse(flattened.drop(1).any { it.usesPresetCount })
    }
}
