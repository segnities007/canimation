package com.segnities007.canimation.screen.showcase.gallery

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.screen.showcase.preview.LivePreview
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
internal fun ShowcaseGalleryPreviewCard(
    galleryItem: ShowcaseGalleryDisplayItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val item = galleryItem.entry.item
    val accent = galleryItem.entry.accentTag.accentColor
    val entered = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        border = BorderStroke(1.dp, accent.copy(alpha = 0.3f)),
        modifier = modifier.canimation(visible = entered, effect = Canimation.Fade.Up),
    ) {
        Column {
            Surface(
                color = accent.copy(alpha = 0.06f),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    LivePreview(item = item)
                }
            }

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp, end = 12.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier =
                        Modifier
                            .width(4.dp)
                            .height(32.dp)
                            .background(accent, RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)),
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = galleryItem.tagLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = accent,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = galleryItem.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
