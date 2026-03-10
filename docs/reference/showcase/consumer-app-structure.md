# Consumer App and Showcase Structure

この文書は `composeApp` と showcase の stable structure reference である。
規約は `guideline/24-showcase-catalog-and-consumer-app-conventions.md` を参照する。

## Current composeApp Mapping

現在の app-only showcase は、target-state の `screen/showcase/` を段階的に目指しつつ、次の責務分離で運用している。

```text
composeApp/src/commonMain/kotlin/com/segnities007/canimation/
├── app/
├── navigation/
├── component/
├── screen/
│   ├── home/
│   ├── docs/
│   ├── apireference/
│   ├── presets/
│   └── examples/
│       ├── gallery/
│       ├── detail/
│       ├── data/
│       ├── component/
│       └── preview/
└── theme/
```

- `screen/examples/gallery/` は showcase catalog の一覧 state と filter UI を持つ
- `screen/examples/detail/` は single entry detail と live demo を持つ
- `screen/examples/data/` は gallery/detail が参照する app-only catalog source を持つ
- `screen/examples/component/` は component demo registry を持ち、catalog data と分離する
- `screen/examples/preview/` は preview renderer を持つ

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

## Gallery Filter Axes

- target-state:
  - intent
  - surfaceRole
  - intensity
  - cost
  - accessibility mode
  - stability tier
- current app baseline:
  - tag-based filtering over the app-only showcase catalog
  - reducer-owned search query and selected tag state

## Pattern Minimum Set

- dialog
- bottom sheet
- snackbar
- list item
- tab/content switch
- page forward/back
- CTA emphasis
- error recovery
