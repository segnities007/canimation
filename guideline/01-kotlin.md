# Kotlin Guideline

## Scope

- Kotlin 実装全般
- public/internal API
- model/state/reducer/use case
- library authoring 観点の Kotlin 設計

## Core Rules

- `public` をデフォルトにしない。可視性は必ず明示する。
- public 関数と public property の型は必ず明示する。
- 同じ意味の値を `Boolean` フラグで増殖させない。必要なら enum / sealed type / value object に上げる。
- mutable collection や mutable state を API 境界で公開しない。
- 不正入力は `require`、不正状態は `check` で fail-fast に扱う。
- 正常系の分岐に例外を使わない。表現可能な失敗は `Result` か型で持つ。

## API Design

- API は mental complexity を増やさない。
- core concept を先に定義し、その上に convenience API を積む。
- 引数順は general -> specific、required -> optional に揃える。
- 名前は既存 API と整合させる。`OrNull`、`Catching` など Kotlin 標準の規約を使う。
- factory function は意味がある名前を付ける。同名 constructor 置換を乱用しない。
- data/state は class で表現し、手続きだけを並べる API にしない。

## State and Mutability

- read-only collection を返し、内部 mutable collection を露出しない。
- array を public API に出さない。必要なら defensive copy する。
- immutable state を基本にし、更新は copy ベースで扱う。
- state mutation は event handler または reducer 経由に限定する。

## Style

- Kotlin coding conventions に従う。
- top-level extension は API 汚染を避け、`private` / `internal` を優先する。
- no-arg function を property にできる条件を満たすなら property を優先する。
- scope function は意図が曖昧になる場合に使わない。

## Library Authoring

- explicit API mode を前提に public surface を設計する。
- internal implementation detail を accidentally 公開しない。
- KDoc は public API に対して必須。
- backward compatibility を考慮し、実装都合で public type inference を変えない。

## Sources

- Kotlin Docs, Coding conventions
  - https://kotlinlang.org/docs/coding-conventions.html
- Kotlin Docs, Introduction to library authors' guidelines
  - https://kotlinlang.org/docs/api-guidelines-introduction.html
- Kotlin Docs, Simplicity
  - https://kotlinlang.org/docs/api-guidelines-simplicity.html
- Kotlin Docs, Consistency
  - https://kotlinlang.org/docs/api-guidelines-consistency.html
- Kotlin Docs, Predictability
  - https://kotlinlang.org/docs/api-guidelines-predictability.html
