# Public API, Compatibility, and Migration Guideline

## Scope

- stable public entry
- tier separation
- semantic namespace
- compatibility layer
- deprecation and migration rule

## Goal

public API の第一導線は semantics-first に固定する。
effect-first / preset-first API は compatibility layer へ隔離し、段階移行で扱う。

## Canonical API Tiers

- `stable`
  - SemVer 契約対象
- `experimental`
  - opt-in 必須、将来変更可能
- `compatibility`
  - 旧 API bridge、第一導線ではない
- `internal`
  - consumer contract 外

artifact、package、docs navigation で tier が推測できる状態を既定にする。

## Stable Entry Points

```text
CanimationProvider
Modifier.canimation(recipe = ...)
CanimationVisibility(...)
CanimationTransition(...)
CanimationRecipeRegistry
```

## Canonical Namespace Policy

stable docs の第一導線は次を既定にする。

```text
Canimation.Content
Canimation.Feedback
Canimation.Navigation
Canimation.Surface
Canimation.Emphasis
Canimation.Transition
Canimation.Ambient
Canimation.Recovery
```

Rules:

- primitive path は advanced path として残してよい
- experimental は stable semantic root と分離する
- README 先頭や quick start は semantic API を使う

## Compatibility Layer Rule

- compatibility surface は `canimation-compat` に隔離する
- docs の第一導線から外す
- alias は descriptor を参照するだけにする
- deprecation message に移行先を必ず書く

## Deprecation Stages

| Stage | Meaning |
|---|---|
| A | docs の第一導線から外す |
| B | `@Deprecated(level = WARNING)` |
| C | `@Deprecated(level = ERROR)` |
| D | 次の major で削除 |

### Message Rule

deprecation message は必ず次を含む。

- なぜ deprecated か
- 何に移るべきか
- semantic intent をどう読み替えるか

## Old-to-New Mapping Rule

- `Fade` 系は原則 `Content`
- `Scale` 系は `Content` または `Feedback`
- `Slide` 系は文脈に応じて `Content`, `Navigation`, `Surface`
- `Attention` 系は `Emphasis` または `Recovery`
- `Page` 系は `Navigation`
- novelty motion は stable へ入れず `experimental` へ送る

## Public Docs Rule

README / reference / showcase docs は次の順で並べる。

1. semantic recipe の使い方
2. reduced/off contract
3. custom recipe authoring
4. compatibility API

禁止事項:

- README の先頭が raw effect catalog になること
- primitive を第一導線にすること

## Acceptance Criteria

- semantic namespace が stable public entry である
- compatibility API が tier として隔離されている
- migration path が docs と deprecation message で追える
- global mutable registry API を canonical path にしない

## Sources

- Kotlin Docs, Backward compatibility guidelines for library authors
  - https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html
- Kotlin Docs, Introduction to library authors' guidelines
  - https://kotlinlang.org/docs/api-guidelines-introduction.html
