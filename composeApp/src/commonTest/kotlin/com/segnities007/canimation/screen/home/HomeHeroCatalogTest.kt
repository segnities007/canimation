package com.segnities007.canimation.screen.home

import kotlin.test.Test
import kotlin.test.assertEquals

class HomeHeroCatalogTest {
    @Test
    fun heroBadgesKeepExpectedOrderAndCount() {
        assertEquals(5, homeHeroBadges.size)
        assertEquals(homeHeroBadges.size, homeHeroBadges.map(HomeHeroBadge::labelRes).distinct().size)
    }

    @Test
    fun liveShowcaseItemsKeepBalancedGridAndVisualVariety() {
        assertEquals(16, homeLiveShowcaseItems.size)
        assertEquals(listOf(4, 4, 4, 4), homeLiveShowcaseItems.chunked(4).map { it.size })
        assertEquals(homeLiveShowcaseItems.size, homeLiveShowcaseItems.map(HomeShowcaseItem::shape).distinct().size)
    }
}
