# Compose Micro Convention Guideline

## Goal

Compose 実装で迷いやすい細部を既定化する。`remember`、`LaunchedEffect`、`Modifier`、semantics、animation trigger の使い方まで固定する。

## `remember` Convention

- `remember` は local ephemeral UI state か expensive calculation に限定する。
- screen-level state を `remember` で直接持たない。state holder へ寄せる。
- `remember(Unit)` のような無意味な key は使わない。
- `remember` した object が event/state owner なら `rememberXxxStateHolder()` に昇格する。

## `LaunchedEffect` Convention

- `LaunchedEffect(Unit)` は「composition 参加時に 1 回だけ必要」な場合に限る。
- key が business meaning を持つなら `Unit` ではなく意味のある key を使う。
- 無限 loop を含む effect は UI local demo か collector に限定する。
- collector と rendering は同じ composable に直書きせず、可能なら state holder へ寄せる。

## Modifier Convention

- leaf component の `modifier` は default `Modifier` を持つ。
- chain の順序は layout -> draw -> interaction -> semantics を基本にする。
- test / accessibility 上重要な semantics は modifier chain の末尾寄りで明示する。

## Parameter Convention

- composable parameter は次の順:
  1. state
  2. actions / callbacks
  3. modifier
  4. optional visuals
- callback が 3 個以上なら `Actions` data class を検討する。

## Animation Trigger Convention

- 一度きりの entry stage は `StateHolder` か local staged state に分離する。
- visibility 切替は boolean state を 1 箇所に持つ。
- preset/spec は UI 内で再生成しない。SSoT から取得する。

## Accessibility Micro Convention

- decorative `Icon` は `contentDescription = null` を既定にする。
- user action を持つ icon/button は string resource 由来の説明を持つ。
- `Text` を clickable にする場合、button semantics の必要性を点検する。

## Sources

- Android Developers, State and Jetpack Compose
  - https://developer.android.com/develop/ui/compose/state
- Android Developers, Side-effects in Compose
  - https://developer.android.com/develop/ui/compose/side-effects
- Android Developers, Modifiers
  - https://developer.android.com/develop/ui/compose/modifiers
- Android Developers, Accessibility in Jetpack Compose
  - https://developer.android.com/develop/ui/compose/accessibility
