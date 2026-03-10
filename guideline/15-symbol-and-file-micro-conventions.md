# Symbol and File Micro Convention Guideline

## Goal

この文書は「何という名前にするか」「ファイルの中でどう並べるか」「引数をどの順で置くか」まで既定化する。CoC をミクロに効かせるための規約である。

## File Interior Order

- Kotlin file の既定順:
  1. package
  2. imports
  3. public/internal constants
  4. public/internal model/state/event types
  5. reducer / pure helper
  6. state holder / adapter implementation
  7. composable / API entrypoint
  8. private helpers
- file の先頭に main concept を置き、末尾に private helper を寄せる。

## State Holder Micro Convention

- 既定名:
  - `FooUiState`
  - `FooEvent`
  - `reduceFooState`
  - `FooStateHolder`
  - `rememberFooStateHolder`
- `FooStateHolder` の既定 shape:
  - primary constructor に `initialState`
  - `var uiState by mutableStateOf(initialState)`
  - `private set`
  - `fun onEvent(event: FooEvent)`
- `@Stable` を state holder に付ける。
- reducer は `when (event)` の pure function にする。

## Reducer Convention

- reducer 名は `reduceXxxState` に固定する。
- reducer 引数順は `current, event` に固定する。
- reducer は副作用を持たない。
- event が state にどう効くかを 1 event 1 branch で読めるようにする。

## Model Convention

- immutable model は `data class` を既定にする。
- mutable class は state holder / adapter のような境界責務だけに使う。
- validation は `init` または factory で行う。
- optional field は「意味がある optional」だけにする。

## Function Signature Convention

- 引数順の既定:
  1. domain input
  2. options/policy
  3. callback
  4. `modifier`
- composable の既定引数順:
  1. `state`
  2. `actions` または event lambda
  3. `modifier: Modifier = Modifier`
  4. その他 optional UI parameter
- `modifier` は UI component の先頭寄りに置きすぎない。state/event の後に置く。

## Naming Convention

- `UiState` は UI 表示用 state
- `State` は domain-neutral state か sheet/dialog state
- `Event` は user/system input
- `Action` は callback bundle
- `Catalog` は選択肢や表示データの SSoT
- `Model` は UI/domain をまたぐ read model
- `Facade` は複数ロジックの単純な窓口

## Composable Convention

- screen composable 名は `XxxScreen`
- leaf component は UI role を名前に入れる
  - `PolicyToggle`
  - `PresetSelector`
  - `AnimationShowcase`
- side effect を持つ composable helper は `remember*` または `Provide*` で始める。

## File Naming Convention

- 1 file 1 main concept を守る。
- `Part2` は既存整理の途中以外では増やさない。
- 内容で名前を付けられるなら `Part2` ではなく責務名を使う。
- `Shared`, `Utils`, `Helper` は最後の手段にする。

## Sources

- Kotlin Docs, Coding conventions
  - https://kotlinlang.org/docs/coding-conventions.html
- Android Developers, Compose UI Architecture
  - https://developer.android.com/develop/ui/compose/architecture
