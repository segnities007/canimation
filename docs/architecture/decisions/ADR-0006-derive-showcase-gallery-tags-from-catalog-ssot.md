# ADR-0006: Derive showcase gallery tags from catalog SSoT

## Status

Accepted

## Context

`composeApp` の showcase gallery は、`screen/showcase/data/` にある category metadata を参照しつつ、
gallery 側でも別の string-based tag catalog を再定義していた。

この構造には次の問題があった。

- `accentLabel` と `tags` が string literal で重複し、SSoT を壊す
- gallery filter chip の label / accent color / selected state が category metadata と分離する
- reducer state が string ID に依存し、typed contract を持たない

これは `AGENTS.md` の SSoT 規約、`guideline/24-showcase-catalog-and-consumer-app-conventions.md` の
catalog/data separation、`guideline/18-target-state-architecture-and-package-topology.md` の
責務可視化方針に反する。

## Decision

- showcase tag metadata は `screen/showcase/data/ShowcaseTagCatalog.kt` に集約する
- `ShowcaseCategory` は string の `accentLabel` を廃止し、typed `tags: List<ShowcaseTagId>` を持つ
- accent tag は category の first tag から導出する
- gallery filter tag 一覧は category entries の tag 使用状況から導出する
- gallery reducer state と event は `ShowcaseTagId?` を使い、`null` を all filter とする

## Consequences

### Positive

- showcase tag metadata の SSoT が 1 箇所になる
- gallery と detail が同じ typed tag descriptor を参照できる
- filter derivation の回帰を test で固定できる

### Trade-offs

- current showcase はまだ semantic descriptor 軸まで完全移行していない
- accent tag を first tag とする既定を category author が守る必要がある

## Follow-up

- tag 以外の filter axis も descriptor SSoT から導出する
- filter axis を tag から semantic descriptor 軸へ拡張する
