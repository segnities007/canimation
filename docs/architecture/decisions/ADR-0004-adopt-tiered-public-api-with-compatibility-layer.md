# ADR-0004: Adopt Tiered Public API with Explicit Compatibility Layer

## Status

Accepted

## Context

effect-first / preset-first API を即時削除すると consumer migration cost が高く、stable OSS として不適切だった。

## Decision

public API を `stable`, `experimental`, `compatibility`, `internal` に tier 分離する。

- stable first path is semantic namespace
- old effect/preset APIs live in explicit compatibility surface
- migration follows `deprecate -> migrate -> remove`

## Consequences

- README と docs の第一導線を semantics-first に統一できる
- source compatibility を段階移行で維持できる

## Alternatives Considered

- full rewrite without compatibility bridge
- compatibility APIsを stable と同列に維持する

## Supersedes / Superseded by

- supersedes public API migration draft docs
- superseded by: none
