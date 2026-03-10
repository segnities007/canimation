# ADR-0002: Use Descriptor as Recipe Single Source of Truth

## Status

Accepted

## Context

recipe metadata、a11y、performance、spec、docs が分散すると、gallery、validation、registry、docs の整合性が崩れる。

## Decision

`CanimationRecipeDescriptor` を recipe の唯一の SSoT にする。

- semantic metadata
- accessibility contract
- performance contract
- docs metadata
- full/reduced/off specs

を同一 descriptor に集約する。

## Consequences

- gallery/filter/docs/validation が同一入力を使える
- compatibility bridge は descriptor 参照型で構成できる
- duplicate catalog を禁止できる

## Alternatives Considered

- spec と docs metadata を別管理する
- runtime 側で off/reduced を自動生成する

## Supersedes / Superseded by

- supersedes descriptor-related dated blueprint drafts
- superseded by: none
