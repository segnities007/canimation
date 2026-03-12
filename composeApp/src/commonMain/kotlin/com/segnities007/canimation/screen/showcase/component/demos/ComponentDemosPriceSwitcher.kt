package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun PriceSwitcher(
    monthlyPrice: String = "$9.99",
    yearlyPrice: String = "$99.99",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var isMonthly by remember { mutableStateOf(true) }
    val animatable = remember { Animatable(9.99f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2500)
            isMonthly = !isMonthly
        }
    }
    LaunchedEffect(isMonthly) {
        animatable.animateTo(
            targetValue = if (isMonthly) 9.99f else 99.99f,
            animationSpec = spring(stiffness = Spring.StiffnessLow),
        )
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Monthly",
                style = MaterialTheme.typography.labelSmall,
                color = if (isMonthly) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = if (isMonthly) FontWeight.Bold else FontWeight.Normal,
            )
            Text(
                text = "Yearly",
                style = MaterialTheme.typography.labelSmall,
                color = if (!isMonthly) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = if (!isMonthly) FontWeight.Bold else FontWeight.Normal,
            )
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.Top) {
            Text("$", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Text(
                text = animatable.value.let { value ->
                    val whole = value.toInt()
                    val fraction = ((value - whole) * 100).toInt()
                    "$whole.${fraction.toString().padStart(2, '0')}"
                },
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Text(
            text = if (isMonthly) "/month" else "/year - save 17%",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
