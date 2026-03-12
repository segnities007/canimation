# ADR-0007: Standardize browser test launcher for JS and Wasm

## Status

Accepted

## Context

`composeApp:allTests` は `commonTest` の browser execution を JS / Wasm 両方で含む。
一方で Kotlin Multiplatform の browser test は、実行環境にある headless browser へ強く依存する。

この repository では次の問題が発生していた。

- `ChromeHeadless` が Linux 環境で capture timeout を起こす
- `allTests` が browser launcher の環境差分で不安定になる
- build script 側に module ごとの ad-hoc launcher 設定を増やすと CoC が崩れる

## Decision

- browser test launcher の `composeApp` 既定値は module-local `composeApp/karma.config.d/` に置く
- `composeApp/karma.config.d/chrome-headless-ci.js` で `CHROME_BIN` の既定値と CI-safe flags を定義する
- JS / Wasm browser tests は同じ custom launcher を共有する
- capture / no-activity timeout は repository 標準値として引き上げる

## Consequences

### Positive

- `composeApp` の JS / Wasm browser test が Linux CI で安定しやすくなる
- launcher 設定が module build script へ散らばらない
- Kotlin Multiplatform の generated Karma config に対する overlay point が明確になる

### Trade-offs

- `/usr/bin/google-chrome-stable` を既定値としているため、別 path の環境では `CHROME_BIN` 上書きが必要になる
- browser runner の問題は完全には消えず、host 側 browser install は依然必要である

## Follow-up

- root build logic へ browser test convention を共通化する
- CI setup action 側で browser availability を明示検証する
