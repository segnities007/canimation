# Compose Multiplatform Guideline

## Scope

- Compose Multiplatform
- common UI code
- shared resources
- Android Compose と Compose Multiplatform の差分管理

## Core Rules

- Android-only API を common UI に持ち込まない。
- common UI は Compose Multiplatform でサポートされる API に閉じる。
- Android 向けの convenience を追加しても、common 契約を壊さない。
- common UI と target-specific UI bridge を分離する。

## Resources

- 文字列・画像・フォントは Compose Multiplatform resources を SSoT にする。
- 同じ string を Android `R` と common `Res` に重複させない。
- resource loading の同期/非同期制約を理解して、大きい raw asset を同期読込しない。

## Interop

- Android Compose 向けライブラリを使う場合、その API が common code で使えるか先に確認する。
- unavailable API は adapter 層か target-specific wrapper に閉じ込める。
- target ごとの差分は source set と file 名から追えるようにする。

## Navigation and State

- navigation state と screen state を分離する。
- common UI は route string の知識を持ちすぎない。
- common screen は target-specific lifecycle 実装に依存しない。

## Sources

- Kotlin Multiplatform Docs, Compose Multiplatform and Jetpack Compose
  - https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-and-jetpack-compose.html
- Kotlin Multiplatform Docs, Resources overview
  - https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-resources.html
