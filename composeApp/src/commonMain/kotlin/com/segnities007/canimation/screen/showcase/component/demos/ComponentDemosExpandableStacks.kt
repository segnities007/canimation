package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_kmp
import canimation.composeapp.generated.resources.component_kotlin_multiplatform
import canimation.composeapp.generated.resources.component_notification_build_passed
import canimation.composeapp.generated.resources.component_notification_new_message
import canimation.composeapp.generated.resources.component_notification_review_requested
import canimation.composeapp.generated.resources.component_stacked_notifications
import canimation.composeapp.generated.resources.demo_expandable_chip
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingToggle
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpandableChip(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val autoExpanded = rememberLoopingToggle(
        initialValue = false,
        initialDelayMillis = 1800L,
        toggleDelayMillis = 1800L,
    )
    var expanded by remember { mutableStateOf(autoExpanded) }
    LaunchedEffect(autoExpanded) { expanded = autoExpanded }
    val width by animateFloatAsState(if (expanded) 200f else 80f, tween(300))

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Micro.Pulse),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.demo_expandable_chip), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .height(36.dp)
                .width(width.dp)
                .clickable { expanded = !expanded },
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    stringResource(
                        if (expanded) Res.string.component_kotlin_multiplatform
                        else Res.string.component_kmp,
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun StackedNotifications(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val items = listOf(
        stringResource(Res.string.component_notification_new_message),
        stringResource(Res.string.component_notification_build_passed),
        stringResource(Res.string.component_notification_review_requested),
    )

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Swipe.Right),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_stacked_notifications), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(modifier = Modifier.height(100.dp).width(200.dp)) {
            items.forEachIndexed { index, text ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    tonalElevation = (2 + index * 2).dp,
                    shadowElevation = (1 + index).dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (index * 12).dp)
                        .padding(horizontal = (index * 6).dp),
                ) {
                    Text(text, Modifier.padding(12.dp), style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
