# Catalog Template

```kotlin
package com.example.feature

internal data class ExampleCatalogItem(
    val id: String,
    val title: String,
    val summary: String,
)

internal val exampleCatalog: List<ExampleCatalogItem> = listOf(
    ExampleCatalogItem(
        id = "fade_up",
        title = "Fade Up",
        summary = "Subtle upward entry with fade.",
    ),
)
```

## Rule

- UI 表示用の選択肢は catalog に集約する
- screen 内に同種リストを直書きしない
