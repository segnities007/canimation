package com.segnities007.canimation.screen.apireference

internal val apiReferenceEntries: List<ApiEntry> = buildList {
    addModifierEntries()
    addComposableEntries()
    addDataClassEntries()
    addEnumEntries()
    addFactoryEntries()
    addNamespaceEntries()
}
