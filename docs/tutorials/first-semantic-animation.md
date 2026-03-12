# First Semantic Animation

## Goal

最小構成で semantics-first animation を使い始める。
セットアップから始める場合は [Consumer Quickstart](consumer-quickstart.md) を先に読む。

## Step 1

`CanimationProvider` に `DefaultCanimationRecipeRegistry` を渡す。

```kotlin
CanimationProvider(
    recipeRegistry = DefaultCanimationRecipeRegistry,
) {
    ExampleScreen()
}
```

## Step 2

content appearance に semantic recipe を選ぶ。

```kotlin
Modifier.canimation(
    visible = true,
    recipe = Canimation.Content.EnterStandard,
)
```

## Step 3

overlay surface に semantic recipe を選ぶ。

```kotlin
CanimationVisibility(
    visible = open,
    recipe = Canimation.Surface.DialogReveal,
) {
    DialogBody()
}
```

## Step 4

reduced/off mode では descriptor の reduced/off spec が自動的に使われる。

## Next

- [Consumer Quickstart](consumer-quickstart.md)
- [Use Semantic Recipes](../how-to/use-semantic-recipes.md)
- [Semantic Taxonomy](../reference/semantics/taxonomy.md)
- [Implementation Overview](../explanation/architecture/implementation-overview.md)
