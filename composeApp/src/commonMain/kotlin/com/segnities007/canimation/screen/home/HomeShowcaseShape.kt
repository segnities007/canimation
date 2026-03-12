package com.segnities007.canimation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.showcase_demo_abc
import canimation.composeapp.generated.resources.showcase_demo_avatar
import canimation.composeapp.generated.resources.showcase_demo_click
import canimation.composeapp.generated.resources.showcase_demo_label
import canimation.composeapp.generated.resources.showcase_demo_new
import canimation.composeapp.generated.resources.showcase_demo_tag
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeShowcaseShapePreview(
    shape: HomeShowcaseShape,
    accent: Color,
) {
    when (shape) {
        HomeShowcaseShape.Circle ->
            Surface(
                shape = CircleShape,
                color = accent.copy(alpha = 0.25f),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.5f)),
                modifier = Modifier.size(40.dp),
            ) {}

        HomeShowcaseShape.Pill ->
            Surface(
                shape = RoundedCornerShape(50),
                color = accent.copy(alpha = 0.2f),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.4f)),
                modifier = Modifier.size(56.dp, 28.dp),
            ) {}

        HomeShowcaseShape.Star ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(24.dp),
            )

        HomeShowcaseShape.Diamond ->
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = accent.copy(alpha = 0.25f),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.5f)),
                modifier = Modifier.size(32.dp).graphicsLayer { rotationZ = 45f },
            ) {}

        HomeShowcaseShape.Text ->
            Text(
                stringResource(Res.string.showcase_demo_abc),
                style = MaterialTheme.typography.titleMedium,
                color = accent,
                fontWeight = FontWeight.Bold,
            )

        HomeShowcaseShape.Row ->
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                repeat(3) { Box(Modifier.size(12.dp).background(accent.copy(alpha = 0.4f), RoundedCornerShape(3.dp))) }
            }

        HomeShowcaseShape.Button ->
            Surface(shape = RoundedCornerShape(8.dp), color = accent) {
                Text(
                    stringResource(Res.string.showcase_demo_click),
                    Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                )
            }

        HomeShowcaseShape.Card ->
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = accent.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.3f)),
                modifier = Modifier.size(48.dp, 36.dp),
            ) {
                Column(Modifier.padding(6.dp)) {
                    Box(Modifier.fillMaxWidth().height(4.dp).background(accent.copy(alpha = 0.4f), RoundedCornerShape(2.dp)))
                    Spacer(Modifier.height(4.dp))
                    Box(Modifier.fillMaxWidth(0.6f).height(3.dp).background(accent.copy(alpha = 0.2f), RoundedCornerShape(2.dp)))
                }
            }

        HomeShowcaseShape.Badge ->
            Surface(shape = RoundedCornerShape(12.dp), color = accent) {
                Text(
                    stringResource(Res.string.showcase_demo_new),
                    Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }

        HomeShowcaseShape.Icon ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(24.dp),
            )

        HomeShowcaseShape.Avatar ->
            Surface(shape = CircleShape, color = accent, modifier = Modifier.size(36.dp)) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        stringResource(Res.string.showcase_demo_avatar),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

        HomeShowcaseShape.Ring ->
            Box(
                Modifier.size(40.dp).background(Color.Transparent, CircleShape)
                    .then(Modifier.background(accent.copy(alpha = 0.0f), CircleShape)),
                contentAlignment = Alignment.Center,
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.Transparent,
                    border = BorderStroke(3.dp, accent),
                    modifier = Modifier.size(36.dp),
                ) {}
            }

        HomeShowcaseShape.Line ->
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Box(Modifier.size(40.dp, 4.dp).background(accent.copy(alpha = 0.6f), RoundedCornerShape(2.dp)))
                Box(Modifier.size(28.dp, 4.dp).background(accent.copy(alpha = 0.3f), RoundedCornerShape(2.dp)))
            }

        HomeShowcaseShape.Dots ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(3) { Surface(shape = CircleShape, color = accent.copy(alpha = 0.5f + it * 0.15f), modifier = Modifier.size(10.dp)) {} }
            }

        HomeShowcaseShape.Tag ->
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = accent.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.4f)),
            ) {
                Text(
                    stringResource(Res.string.showcase_demo_tag),
                    Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = accent,
                )
            }

        HomeShowcaseShape.Chip ->
            Surface(shape = RoundedCornerShape(16.dp), color = accent.copy(alpha = 0.15f)) {
                Row(
                    Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Surface(shape = CircleShape, color = accent, modifier = Modifier.size(8.dp)) {}
                    Text(stringResource(Res.string.showcase_demo_label), style = MaterialTheme.typography.labelSmall, color = accent)
                }
            }
    }
}
