package io.github.canimation.compat

import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.Canimation as CoreCanimation
import io.github.canimation.core.CanimationEffect as CoreCanimationEffect
import io.github.canimation.core.CanimationPreset as CoreCanimationPreset
import io.github.canimation.core.CanimationPresetSpec as CoreCanimationPresetSpec
import io.github.canimation.core.PresetRegistry as CorePresetRegistry
import io.github.canimation.runtime.DefaultCanimationRecipeRegistry as DefaultRuntimeRecipeRegistry

typealias Canimation = CoreCanimation
typealias CanimationEffect = CoreCanimationEffect
typealias CanimationPreset = CoreCanimationPreset
typealias CanimationPresetSpec = CoreCanimationPresetSpec
typealias PresetRegistry = CorePresetRegistry

@Suppress("unused")
val DefaultCanimationRecipeRegistry: CanimationRecipeRegistry = DefaultRuntimeRecipeRegistry
