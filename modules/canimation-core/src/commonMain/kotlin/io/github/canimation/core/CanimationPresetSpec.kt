package io.github.canimation.core

import androidx.compose.runtime.Immutable

/**
 * Complete preset specification holding both Full and Reduced specs for Enter and Exit.
 */
@Immutable
data class CanimationPresetSpec(
    val fullEnter: CanimationSpec,
    val fullExit: CanimationSpec,
    val reducedEnter: CanimationSpec,
    val reducedExit: CanimationSpec,
)
