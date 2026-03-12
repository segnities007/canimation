package com.segnities007.canimation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberLoopingVisibility
import com.segnities007.canimation.navigation.HomeDestination
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import canimation.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

// ======= HERO =======

@Composable
internal fun HeroSection(stage: Int, presetCount: Int, onNavigate: (HomeDestination) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.background)),
            ),
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .widthIn(max = 900.dp)
                    .padding(horizontal = 32.dp, vertical = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Box(Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up)) {
                    Text(
                        text = stringResource(Res.string.app_title),
                        style = MaterialTheme.typography.displayLarge.copy(
                            brush = Brush.linearGradient(
                                listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimaryContainer, MaterialTheme.colorScheme.secondary),
                            ),
                        ),
                        fontWeight = FontWeight.Bold,
                    )
                }

                Box(Modifier.canimation(visible = stage >= 1, effect = Canimation.Fade.Up)) {
                    Text(
                        text = stringResource(Res.string.hero_subtitle),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                ) {
                    homeHeroBadges.forEachIndexed { index, badge ->
                        Box(Modifier.canimation(visible = stage >= 2 + index, effect = badge.effect)) {
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.06f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            ) {
                                Text(
                                    text = stringResource(badge.labelRes),
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                Box(Modifier.canimation(visible = stage >= 7, effect = Canimation.Scale.Pop)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = { onNavigate(HomeDestination.Docs) }) {
                            Text(stringResource(Res.string.hero_get_started))
                        }
                        FilledTonalButton(onClick = { onNavigate(HomeDestination.ShowcaseGallery) }) {
                            Text(stringResource(Res.string.hero_examples))
                        }
                    }
                }
            }
        }
    }
}

// ======= LIVE SHOWCASE =======

@Composable
internal fun LiveShowcaseSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        DarkCenteredSection {
            Box(Modifier.canimation(visible = stage >= 7, effect = Canimation.Fade.Up)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.showcase_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = stringResource(Res.string.showcase_title),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                homeLiveShowcaseItems.chunked(4).forEachIndexed { rowIndex, row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        row.forEachIndexed { colIndex, item ->
                            AnimatedEffectCell(
                                name = stringResource(item.nameRes),
                                effect = item.effect,
                                shape = item.shape,
                                accent = item.accentColor,
                                delayMs = ((rowIndex * 4 + colIndex) * 200L),
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun AnimatedEffectCell(
    name: String,
    effect: io.github.canimation.core.CanimationEffect,
    shape: HomeShowcaseShape,
    accent: androidx.compose.ui.graphics.Color,
    delayMs: Long,
    modifier: Modifier = Modifier,
) {
    val visible = rememberLoopingVisibility(
        initialDelayMillis = delayMs,
        visibleDurationMillis = 2000L,
        hiddenDurationMillis = 600L,
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.3f)),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(12.dp).height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Box(Modifier.canimation(visible = visible, effect = effect)) {
                    HomeShowcaseShapePreview(shape, accent)
                }
            }
            Text(
                text = name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
