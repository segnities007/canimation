# ADR-0003: Use Immutable Explicit Registry and Extension Model

## Status

Accepted

## Context

global mutable registry は再現性、supportability、test determinism、extension conflict visibility を悪化させる。

## Decision

recipe registry は immutable を既定にし、extension install は explicit registration を基本にする。

- default conflict policy is `Fail`
- no silent overwrite
- no implicit classpath scanning by default

## Consequences

- host app composition が明示的になる
- OSS support 時に構成差異を追いやすくなる
- extension model が安全になる

## Alternatives Considered

- global mutable default registry
- implicit discovery / automatic install

## Supersedes / Superseded by

- supersedes registry-related dated manifesto proposals
- superseded by: none
