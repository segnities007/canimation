# Jetpack Compose Guideline

## Scope

- Jetpack Compose / Android Compose 的な設計原則
- state hoisting
- UDF
- semantics / testing / performance

## State and UDF

- UI は immutable state を受け取り、event を上位へ返す。
- stateful composable より stateless composable を優先する。
- state は最低共通親かつ書き込み責務を持つ owner に hoist する。
- screen-level state は state holder に集約する。
- composition 中に state を書き換えない。backwards write を禁止する。

## `remember`

- `remember` は expensive calculation のメモ化か local ephemeral state に限定する。
- `rememberSaveable` は process / configuration recreation をまたいで保持したい UI state に限定する。
- lazy item 内で `LaunchedEffect` により毎回再始動する animation を作らない。必要なら外へ hoist する。

## Architecture

- composable は state を表示し、callback/event を発火するだけにする。
- IO、repository call、navigation decision、domain decision を composable 本体に混ぜない。
- immutable state と event lambda を渡す。

## Accessibility

- semantics のデフォルトを理解し、custom component には適切な semantics を付ける。
- decorative icon はノイズを避ける。
- traversal order、merge/clear semantics は意図がある時だけ変える。
- accessibility check を UI test 設計に織り込む。

## Performance

- composable body で重い計算をしない。必要なら `remember` を使う。
- stable `key` を lazy list に与える。
- animation は composition/layout/draw のどこに負荷が掛かるかを意識して選ぶ。
- lambda modifier や `graphicsLayer` で draw phase に寄せられるものは寄せる。
- recompose を抑えるために、頻繁に変わる state の読み取り位置を局所化する。

## Sources

- Android Developers, State and Jetpack Compose
  - https://developer.android.com/develop/ui/compose/state
- Android Developers, Compose UI Architecture
  - https://developer.android.com/develop/ui/compose/architecture
- Android Developers, Accessibility in Jetpack Compose
  - https://developer.android.com/develop/ui/compose/accessibility
- Android Developers, Compose performance best practices
  - https://developer.android.com/develop/ui/compose/performance/bestpractices
