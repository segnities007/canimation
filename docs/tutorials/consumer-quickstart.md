# Consumer Quickstart

## Goal

consumer 観点で `canimation` を最短導入し、現在の first path である `canimation-core` と
semantics-first recipe API を 1 画面で確認する。

## Step 1

現在の consumer-facing dependency は `canimation-core` を基準に考える。
セットアップの全体像は [README.md の Setup](../../README.md#setup) を参照する。

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":canimation-core"))
        }
    }
}
```

## Step 2

`CanimationProvider` に `DefaultCanimationRecipeRegistry` を渡し、
semantic recipe の解決点を 1 箇所に固定する。

```kotlin
CanimationProvider(
    policy = CanimationPolicy.SystemAware,
    recipeRegistry = DefaultCanimationRecipeRegistry,
) {
    AppContent()
}
```

## Step 3

effect 名ではなく UI の意味で recipe を選ぶ。

```kotlin
Box(
    modifier = Modifier.canimation(
        visible = isVisible,
        recipe = Canimation.Content.EnterStandard,
    ),
)
```

## Step 4

overlay は `CanimationVisibility` で同じ semantic path を使う。

```kotlin
CanimationVisibility(
    visible = isDialogVisible,
    recipe = Canimation.Surface.DialogReveal,
) {
    DialogCard()
}
```

## Step 5

reduced/off mode では descriptor の reduced/off spec が自動で使われる。
長期的な semantic namespace と recipe 一覧は
[Semantic Taxonomy](../reference/semantics/taxonomy.md) と
[API Migration Policy](../reference/api/migration-policy.md) で確認する。

## Next

- [First Semantic Animation](first-semantic-animation.md)
- [Use Semantic Recipes](../how-to/use-semantic-recipes.md)
- [Semantic Taxonomy](../reference/semantics/taxonomy.md)
- [Implementation Overview](../explanation/architecture/implementation-overview.md)
