# ADR-0005: Adopt Structured Documentation and Quality Evidence IA

## Status

Accepted

## Context

dated proposal docs と flat docs tree では、stable reference と historical draft の境界が曖昧になっていた。

## Decision

documentation IA を次で固定する。

- `docs/tutorials/`
- `docs/how-to/`
- `docs/reference/`
- `docs/explanation/`
- `docs/architecture/decisions/`
- `docs/quality/`
- `guideline/`

quality evidence は `docs/quality/` に集約する。

## Consequences

- stable contract と rationale と evidence の境界が明確になる
- old design drafts を残さずとも decision history を維持できる

## Alternatives Considered

- flat docs tree を継続する
- dated drafts を stable SSoT として扱い続ける

## Supersedes / Superseded by

- supersedes flat and draft-heavy architecture note placement
- superseded by: none
