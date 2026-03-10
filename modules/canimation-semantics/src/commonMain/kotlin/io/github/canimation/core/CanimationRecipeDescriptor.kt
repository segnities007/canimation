package io.github.canimation.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlin.jvm.JvmInline

@JvmInline
@Immutable
value class CanimationRecipeId(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "CanimationRecipeId must not be blank." }
    }
}

enum class CanimationIntent {
    Content,
    Feedback,
    Navigation,
    Surface,
    Emphasis,
    Transition,
    Ambient,
    Recovery,
    Experimental,
}

enum class CanimationSurfaceRole {
    Text,
    Inline,
    Control,
    Item,
    Container,
    Overlay,
    Page,
    Decoration,
}

enum class CanimationIntensity {
    Subtle,
    Standard,
    Strong,
}

enum class CanimationCost {
    Draw,
    Transform,
    Layout,
}

enum class CanimationReducedStrategy {
    Compress,
    FadeOnly,
    Snap,
    Alternative,
}

@Immutable
data class CanimationRecipeSemantic(
    val intent: CanimationIntent,
    val surfaceRole: CanimationSurfaceRole,
    val intensity: CanimationIntensity,
    val family: String,
    val tags: Set<String> = emptySet(),
)

@Immutable
data class CanimationRecipeAccessibility(
    val reducedStrategy: CanimationReducedStrategy,
    val supportsOff: Boolean,
    val notes: String = "",
)

@Immutable
data class CanimationRecipePerformance(
    val cost: CanimationCost,
    val listSafe: Boolean,
    val benchmarkRequired: Boolean,
    val notes: String = "",
)

@Immutable
data class CanimationRecipeDocs(
    val title: String,
    val summary: String,
    val usageGuidance: String,
    val doNotUseWhen: String? = null,
    val relatedRecipeIds: Set<CanimationRecipeId> = emptySet(),
)

@Immutable
data class CanimationRecipeSpecSet(
    val full: CanimationSpec,
    val reduced: CanimationSpec,
    val off: CanimationSpec,
)

@Immutable
data class CanimationRecipeDescriptor(
    val id: CanimationRecipeId,
    val semantic: CanimationRecipeSemantic,
    val accessibility: CanimationRecipeAccessibility,
    val performance: CanimationRecipePerformance,
    val docs: CanimationRecipeDocs,
    val specs: CanimationRecipeSpecSet,
) {
    init {
        require(semantic.family.isNotBlank()) { "semantic.family must not be blank." }
        require(docs.title.isNotBlank()) { "docs.title must not be blank." }
        require(docs.summary.isNotBlank()) { "docs.summary must not be blank." }
        require(docs.usageGuidance.isNotBlank()) { "docs.usageGuidance must not be blank." }
        require(specs.off.durationMs == 0) { "specs.off must snap instantly (durationMs == 0)." }
        require(!accessibility.supportsOff || specs.off.durationMs == 0) {
            "recipes supporting motion off must provide a zero-duration off spec."
        }
        require(semantic.tags.none { it.isBlank() }) { "semantic.tags must not contain blank values." }
    }
}

@JvmInline
@Immutable
value class CanimationRecipe(
    val descriptor: CanimationRecipeDescriptor,
) {
    val id: CanimationRecipeId
        get() = descriptor.id
}

enum class CanimationRecipeRegistryMergePolicy {
    Fail,
    PreferHost,
    PreferExtension,
}

@Stable
class CanimationRecipeRegistry(
    private val descriptors: Map<CanimationRecipeId, CanimationRecipeDescriptor>,
) {
    fun descriptorFor(id: CanimationRecipeId): CanimationRecipeDescriptor? = descriptors[id]

    fun getValue(id: CanimationRecipeId): CanimationRecipeDescriptor =
        descriptors[id] ?: error("No recipe descriptor registered for '${id.value}'.")

    fun all(): Collection<CanimationRecipeDescriptor> = descriptors.values

    fun ids(): Set<CanimationRecipeId> = descriptors.keys

    fun resolve(recipe: CanimationRecipe): CanimationRecipeDescriptor = descriptorFor(recipe.id) ?: recipe.descriptor

    fun withDescriptor(descriptor: CanimationRecipeDescriptor): CanimationRecipeRegistry =
        CanimationRecipeRegistry(descriptors + (descriptor.id to descriptor))

    fun withDescriptors(additional: Iterable<CanimationRecipeDescriptor>): CanimationRecipeRegistry =
        CanimationRecipeRegistry(descriptors + additional.associateBy { it.id })

    fun merge(
        extension: CanimationRecipeRegistry,
        policy: CanimationRecipeRegistryMergePolicy = CanimationRecipeRegistryMergePolicy.Fail,
    ): CanimationRecipeRegistry {
        val duplicates = ids().intersect(extension.ids())
        if (duplicates.isEmpty()) {
            return withDescriptors(extension.all())
        }

        return when (policy) {
            CanimationRecipeRegistryMergePolicy.Fail ->
                error(
                    "Duplicate recipe ids are not allowed: ${duplicates.joinToString { it.value }}",
                )

            CanimationRecipeRegistryMergePolicy.PreferHost -> this
            CanimationRecipeRegistryMergePolicy.PreferExtension ->
                CanimationRecipeRegistry(descriptors + extension.all().associateBy { it.id })
        }
    }

    companion object {
        val Empty: CanimationRecipeRegistry = CanimationRecipeRegistry(emptyMap())
    }
}
