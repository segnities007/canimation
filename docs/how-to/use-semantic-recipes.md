# Use Semantic Recipes

## Goal

semantic recipe API を使って、UI の意味に基づいて motion を適用する。
セットアップから入りたい場合は [Consumer Quickstart](../tutorials/consumer-quickstart.md) を先に読む。

## 1. Provide the semantic recipe registry

```kotlin
CanimationProvider(
    recipeRegistry = DefaultCanimationRecipeRegistry,
) {
    AppContent()
}
```

The low-level `io.github.canimation.core.CanimationProvider` default remains conservative.
Use the runtime or recipes-layer default registry explicitly when you want the built-in semantic catalog.

## 2. Apply a recipe to a modifier

```kotlin
Box(
    modifier = Modifier.canimation(
        visible = true,
        recipe = Canimation.Content.EnterSubtle,
    ),
)
```

## 3. Use a semantic visibility transition

```kotlin
CanimationVisibility(
    visible = isDialogVisible,
    recipe = Canimation.Surface.DialogReveal,
) {
    DialogCard()
}
```

## 4. Choose by meaning, not by effect name

- standard content -> `Canimation.Content.*`
- interaction feedback -> `Canimation.Feedback.*`
- route movement -> `Canimation.Navigation.*`
- overlays -> `Canimation.Surface.*`
- emphasis -> `Canimation.Emphasis.*`

## Related

- [Consumer Quickstart](../tutorials/consumer-quickstart.md)
- [Semantic Taxonomy](../reference/semantics/taxonomy.md)
- [Descriptor Schema](../reference/recipes/descriptor-schema.md)
- [API Migration Policy](../reference/api/migration-policy.md)
- [Implementation Overview](../explanation/architecture/implementation-overview.md)
