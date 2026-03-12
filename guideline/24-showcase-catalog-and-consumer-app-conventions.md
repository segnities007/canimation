# Showcase, Catalog, and Consumer App Convention Guideline

## Scope

- consumer app architecture
- showcase/gallery/detail/pattern/lab structure
- catalog/data/demo separation
- showcase state holder and reducer shape

## Goal

showcase app は library の consumer であり、library 本体の責務を持たない。
gallery は effect catalog ではなく semantic catalog とする。

## Consumer App Rule

- app shell は consumer として runtime API を使う
- library 本体の SSoT を app 側に複製しない
- showcase 都合で core API や module boundary を歪めない

## Canonical App Layout

```text
composeApp/.../
├── app/
├── navigation/
├── component/
├── screen/
│   ├── home/
│   ├── docs/
│   ├── apireference/
│   └── showcase/
└── theme/
```

## Canonical Showcase Layout

```text
screen/showcase/
├── gallery/
├── detail/
├── patterns/
├── lab/
├── benchmark/
├── a11y/
├── data/
├── preview/
└── component/
```

### Responsibilities

- `gallery/`
  - semantic catalog 一覧、filter、sort
- `detail/`
  - single recipe / pattern detail
- `patterns/`
  - dialog, sheet, snackbar, list, transition など文脈画面
- `lab/`
  - advanced experimentation, not stable docs first path
- `benchmark/`
  - perf evidence visualization
- `a11y/`
  - reduced/off comparative validation
- `data/`
  - derived catalog data only
- `preview/`
  - preview-only renderer

## Gallery Filter Contract

gallery は最低限次で filter できる状態を既定にする。

- intent
- surfaceRole
- intensity
- cost
- accessibility mode
- stability tier

## Pattern Screen Minimum Set

- dialog
- bottom sheet
- snackbar
- list item
- tab/content switch
- page forward/back
- CTA emphasis
- error recovery

## Catalog and Demo Rule

- catalog は descriptor 派生 data に限定する
- component demo registry は catalog と責務分離する
- docs 用文言を `data/` 側に重複保持しない

## Stateful Screen Shape

```text
ShowcaseGalleryState.kt
ShowcaseGalleryStateHolder.kt
ShowcaseGalleryScreen.kt
ShowcaseGalleryCatalog.kt
```

Rules:

- screen-level mutable state は `StateHolder` に集約する
- reducer は pure function を既定にする
- composable は表示と event 発火に集中する

## Forbidden Patterns

- `examples/` に flat file を増やし続けること
- effect gallery と pattern gallery を混ぜること
- showcase 専用 metadata を recipe 外で二重管理すること

## Sources

- Android Developers, Where to hoist state
  - https://developer.android.com/develop/ui/compose/state-hoisting
- Android Developers, Compose architecture
  - https://developer.android.com/develop/ui/compose/architecture
