# ADR-0001: Adopt Semantic-First Product Architecture

## Status

Accepted

## Context

`Canimation` を long-lived OSS として成立させるには、effect-first catalog ではなく、UI meaning を主語にした architecture が必要だった。

## Decision

`Canimation` を accessibility-first semantic motion system と定義する。

- public API の第一導線は semantics-first にする
- stable motion は semantic taxonomy に収める
- reduced/off を contract として扱う

## Consequences

- naming、docs、showcase、module split の基準が effect 名から semantic intent へ移る
- novelty motion は stable から分離しやすくなる

## Alternatives Considered

- effect-first catalog を stable public center に据える
- preset-first closed system を維持する

## Supersedes / Superseded by

- supersedes dated design drafts that treated effect-first discovery as the primary path
- superseded by: none
