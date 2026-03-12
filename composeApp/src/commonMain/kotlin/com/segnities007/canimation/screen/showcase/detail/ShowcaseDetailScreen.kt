package com.segnities007.canimation.screen.showcase.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_item_not_found
import com.segnities007.canimation.screen.showcase.gallery.showcaseGalleryCatalog
import org.jetbrains.compose.resources.stringResource

@Composable
fun ShowcaseDetailScreen(
    itemIndex: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val catalog = remember { showcaseGalleryCatalog }
    val galleryEntry =
        remember(itemIndex, catalog) {
            catalog.entry(itemIndex)
        }

    if (galleryEntry == null) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(Res.string.demo_item_not_found))
        }
        return
    }

    val item = galleryEntry.item
    val title = stringResource(item.title)
    val description = stringResource(item.description)
    val tagLabel = stringResource(galleryEntry.accentTag.labelRes)
    val codeSnippet = stringResource(item.codeSnippet)
    val stateHolder = rememberShowcaseDetailStateHolder()

    ShowcaseDetailContent(
        item = item,
        title = title,
        description = description,
        tagLabel = tagLabel,
        codeSnippet = codeSnippet,
        uiState = stateHolder.uiState,
        onPreviewMotionSelected = { stateHolder.onEvent(ShowcaseDetailEvent.PreviewMotionSelected(it)) },
        onBack = onBack,
        modifier = modifier,
    )
}
