package io.github.canimation.presets

import io.github.canimation.core.CanimationCost
import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRecipeAccessibility
import io.github.canimation.core.CanimationRecipeDescriptor
import io.github.canimation.core.CanimationRecipeId
import io.github.canimation.core.CanimationRecipePerformance
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationRecipeSpecSet
import io.github.canimation.core.CanimationReducedStrategy

internal val defaultPresetRecipeDescriptors: Map<CanimationPreset, CanimationRecipeDescriptor> =
    defaultPresetSpecs.mapValues { (preset, spec) ->
        preset.toRecipeDescriptor(spec)
    }

internal val defaultRecipeRegistry: CanimationRecipeRegistry =
    CanimationRecipeRegistry(
        descriptors = defaultPresetRecipeDescriptors.values.associateBy { descriptor -> descriptor.id },
    )

internal fun CanimationPreset.toRecipeDescriptor(spec: CanimationPresetSpec): CanimationRecipeDescriptor {
    val semantic = toSemantic()
    return CanimationRecipeDescriptor(
        id = CanimationRecipeId("preset.$name"),
        semantic = semantic,
        accessibility =
            CanimationRecipeAccessibility(
                reducedStrategy = semantic.intent.defaultReducedStrategy(),
                supportsOff = true,
                notes = "Built-in preset compatibility descriptor.",
            ),
        performance =
            CanimationRecipePerformance(
                cost = spec.recipeCost(),
                listSafe = semantic.intent != CanimationIntent.Experimental,
                benchmarkRequired = false,
                notes = "Derived from preset compatibility spec.",
            ),
        docs = toDocs(semantic.intent),
        specs =
            CanimationRecipeSpecSet(
                full = spec.fullEnter,
                reduced = spec.reducedEnter,
                off = spec.fullEnter.copy(durationMs = 0),
            ),
    )
}

private fun CanimationIntent.defaultReducedStrategy(): CanimationReducedStrategy =
    when (this) {
        CanimationIntent.Feedback -> CanimationReducedStrategy.FadeOnly
        CanimationIntent.Experimental -> CanimationReducedStrategy.Alternative
        else -> CanimationReducedStrategy.Compress
    }

private fun CanimationPresetSpec.recipeCost(): CanimationCost =
    when {
        fullEnter.offsetX != null ||
            fullEnter.offsetY != null ||
            fullEnter.scale != null ||
            fullEnter.rotation != null -> CanimationCost.Transform

        else -> CanimationCost.Draw
    }
