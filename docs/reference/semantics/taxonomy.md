# Semantic Taxonomy Reference

この文書は `Canimation` の stable semantic taxonomy reference である。
規約は `guideline/21-semantic-taxonomy-and-stability-tiering.md` を参照する。

## Intent

| Intent | Meaning |
|---|---|
| `Content` | 通常コンテンツの出現・消失 |
| `Feedback` | ユーザー操作への即時応答 |
| `Navigation` | 画面階層、前進、後退 |
| `Surface` | overlay surface の提示 |
| `Emphasis` | 注意喚起、重点強調 |
| `Transition` | state 間切り替え |
| `Ambient` | 背景的、補助的 motion |
| `Recovery` | success/error/retry/restore |

## Surface Role

| Surface role | Meaning |
|---|---|
| `Text` | 文字主体の要素 |
| `Inline` | badge, chip, icon label |
| `Control` | button, switch, tab, slider |
| `Item` | list, grid, menu item |
| `Container` | card, panel, section |
| `Overlay` | dialog, sheet, snackbar, tooltip |
| `Page` | route, pane, full screen |
| `Decoration` | accent, highlight, background support |

## Intensity

| Intensity | Meaning |
|---|---|
| `Subtle` | 反復利用前提の低刺激 |
| `Standard` | 既定 |
| `Strong` | 例外的な強調 |

## Cost

| Cost | Meaning |
|---|---|
| `Draw` | draw phase 中心 |
| `Transform` | graphicsLayer / transform 中心 |
| `Layout` | layout/measurement を伴う |

## Stable Boundary

stable recipe は次を満たす。

- semantic intent が明確
- reduced/off を自然に定義できる
- 典型 UI context がある
- performance envelope が管理可能
- naming が役割ベース

## Current Built-In Semantic Recipes

| Namespace | Recipes |
|---|---|
| `Canimation.Content` | `EnterSubtle`, `EnterStandard` |
| `Canimation.Feedback` | `Press`, `SelectionPulse` |
| `Canimation.Navigation` | `Forward`, `Backward` |
| `Canimation.Surface` | `DialogReveal`, `SheetReveal` |
| `Canimation.Emphasis` | `CallToAction` |
| `Canimation.Transition` | `ContentSwap` |
| `Canimation.Ambient` | `Highlight` |
| `Canimation.Recovery` | `ErrorShake` |
