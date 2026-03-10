# Semantic Taxonomy and Stability Tiering Guideline

## Scope

- semantic taxonomy
- intent / surface / intensity / cost
- stable と experimental の境界
- recipe naming と package placement

## Goal

`Canimation` の stable motion は、見た目ではなく意味で分類する。
この文書は semantic taxonomy の唯一の強制規約である。

## Classification Order

新しい motion は次の順で分類する。

1. 何を伝えるか
2. どの UI surface で使うか
3. 強度はどの程度か
4. cost は何か
5. stable に置くか experimental に置くか

禁止事項:

- 見た目だけで stable family を増やすこと
- 同じ意味を複数 family に重複配置すること
- showcase の見栄えを taxonomy より優先すること

## Canonical Intent Set

| Intent | 定義 | Stable 既定 |
|---|---|---|
| `Content` | 通常コンテンツの出現・消失 | yes |
| `Feedback` | ユーザー操作への即時応答 | yes |
| `Navigation` | 画面階層、前進、後退 | yes |
| `Surface` | dialog/sheet/snackbar など overlay surface | yes |
| `Emphasis` | 注意喚起、重点強調 | yes |
| `Transition` | state 間切り替え | yes |
| `Ambient` | 背景的、補助的 motion | conditional |
| `Recovery` | success/error/retry/restore | yes |
| `Experimental` | stable に収まらない表現 | no |

### Intent Selection Rules

- card/list/section の通常登場は `Content`
- press/select/toggle/hover の反応は `Feedback`
- page/pane/detail push は `Navigation`
- dialog/sheet/snackbar/tooltip は `Surface`
- CTA の重点強調は `Emphasis`
- tab/filter/content swap は `Transition`
- shimmer/floating accent は `Ambient`
- success settle/error shake は `Recovery`

## Canonical Surface Role Set

| Surface role | 定義 |
|---|---|
| `Text` | title, label, paragraph |
| `Inline` | badge, chip, icon label |
| `Control` | button, switch, tab, slider |
| `Item` | list, grid, menu item |
| `Container` | card, panel, section |
| `Overlay` | dialog, sheet, snackbar, tooltip |
| `Page` | route, pane, full screen |
| `Decoration` | highlight, accent, background support |

Rules:

- 操作反応は `Control`
- collection 内 1 単位は `Item`
- overlay は `Overlay`
- full route or pane は `Page`

## Canonical Intensity Set

| Intensity | 用途 |
|---|---|
| `Subtle` | 反復利用前提の低刺激 |
| `Standard` | 既定 |
| `Strong` | 例外的な強調 |

Rules:

- stable `Content` は原則 `Subtle` または `Standard`
- stable `Feedback` は原則 `Subtle`
- `Strong` は `Surface`, `Emphasis`, `Recovery` で限定利用する

## Canonical Cost Set

| Cost | 技術的意味 |
|---|---|
| `Draw` | draw phase 中心 |
| `Transform` | graphicsLayer / transform 中心 |
| `Layout` | layout/measurement を伴う |

Rules:

- stable default は `Draw` または `Transform`
- `Layout` cost recipe は usage guidance と benchmark を必須にする
- lazy list default に `Layout` cost recipe を置かない

## Stable vs Experimental Boundary

### Stable 条件

- semantic intent が明確
- reduced/off を自然に定義できる
- 典型 UI context が説明できる
- performance envelope が管理可能
- naming が役割ベース

### Experimental 条件

- novelty が主役
- reduced 化で意味を保ちにくい
- attention stealing が強い
- high-cost / high-risk で一般既定に不向き

## Naming and Placement Rules

- package は intent family に従う
- file 名は `XxxRecipe.kt`
- 旧 effect 名を stable naming の主語にしない
- `Experimental` は stable namespace と別 package/module に置く

### Canonical Package Layout

```text
io/github/canimation/recipes/
├── content/
├── feedback/
├── navigation/
├── surface/
├── emphasis/
├── transition/
├── ambient/
├── recovery/
└── experimental/
```

## Review Checklist

1. intent は 1 つに決まっているか
2. surface role は UI 実態と一致するか
3. intensity は常用負荷に見合うか
4. cost は実装と一致するか
5. stable / experimental 境界をまたいでいないか

## Sources

- Android Developers, Compose architecture
  - https://developer.android.com/develop/ui/compose/architecture
- Kotlin Docs, Introduction to library authors' guidelines
  - https://kotlinlang.org/docs/api-guidelines-introduction.html
