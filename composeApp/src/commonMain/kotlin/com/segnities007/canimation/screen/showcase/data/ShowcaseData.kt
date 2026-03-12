package com.segnities007.canimation.screen.showcase.data

internal val showcaseCategories: List<ShowcaseCategory> =
    buildList {
        addAll(showcaseCategoriesCoreEffects)
        addAll(showcaseCategoriesPatternShowcase)
        addAll(showcaseCategoriesInteractiveShowcase)
        addAll(showcaseCategoriesExtendedEffects)
        addAll(showcaseCategoriesComponentShowcase)
        addAll(showcaseCategoriesAdvancedShowcase)
    }
