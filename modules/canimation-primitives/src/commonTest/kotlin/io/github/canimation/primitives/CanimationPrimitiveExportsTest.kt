package io.github.canimation.primitives

import io.github.canimation.tokens.CanimationTokens
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CanimationPrimitiveExportsTest {
    @Test
    fun effectToEnterSpecUsesTokenDefaults() {
        val spec = CanimationEffect.fade().toEnterSpec(CanimationTokens.Default)

        assertEquals(CanimationTokens.Default.duration.mediumMs, spec.durationMs)
        assertEquals(CanimationTokens.Default.easing.standard, spec.easing)
    }

    @Test
    fun plusKeepsRightHandOffset() {
        val effect = CanimationEffect.slideUp() + CanimationEffect.slideDown()
        val offsetY = assertNotNull(effect.offsetY)

        assertEquals((-16).toFloat(), offsetY.from.value)
        assertEquals(0f, offsetY.to.value)
    }

    @Test
    fun primitivesPackageExposesHierarchicalNamespaceFacade() {
        assertEquals(CanimationFade.Up, Canimation.Fade.Up)
        assertEquals(CanimationSlide.Left, Canimation.Slide.Left)
    }
}
