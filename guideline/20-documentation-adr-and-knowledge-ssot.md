# Documentation, ADR, and Knowledge SSoT Guideline

## Scope

- documentation information architecture
- ADR
- SSoT boundary
- document lifecycle
- quality evidence と knowledge hygiene

## Goal

理想的な OSS library は、knowledge まで構造化されている必要がある。
同じ事柄を複数の文書で曖昧に持たず、どこに何を書くかを固定する。

## Canonical Knowledge Boundary

- `guideline/`
  - 強制規約の SSoT
- `docs/reference/`
  - user-facing contract、public behavior、support matrix、compatibility reference
- `docs/explanation/`
  - rationale、architecture explanation、trade-off
- `docs/tutorials/`
  - 学習順の導線
- `docs/how-to/`
  - task-oriented recipe
- `docs/architecture/decisions/`
  - ADR
- `docs/quality/`
  - benchmark、audit、coverage、compatibility evidence
- `CHANGELOG.md`
  - release impact の公式履歴
- `SECURITY.md`
  - vulnerability handling の公式窓口
- `SUPPORT.md`
  - support window と support expectations の公式文書

同一 concern を 2 箇所の SSoT にしない。

## Documentation IA Rule

docs は Diataxis の 4 区分を基本にする。

- tutorial
  - 学習導線
- how-to
  - やりたいこと別の手順
- reference
  - 契約、API、support matrix、descriptor schema
- explanation
  - なぜこの設計か、なぜこの trade-off か

禁止事項:

- reference に rationale を大量混在させること
- tutorial に stable contract を埋め込むこと
- dated draft を永続 reference として放置すること

## Dated Draft Lifecycle

- draft は `docs/YYYY-MM-DD_topic/README.md` を既定にする。
- accepted 後は stable path へ昇格する。
- superseded 文書は削除ではなく、後継文書へのリンクを残して退避する。
- draft に binding rule を書く場合は、対応する `guideline/` も同時更新する。

## ADR Policy

### ADR Required Cases

次の変更は ADR を必須にする。

- stable public API boundary の変更
- module split / merge
- semantic taxonomy 変更
- descriptor schema 変更
- registry merge policy 変更
- support matrix 変更
- release / compatibility policy 変更
- workflow security posture の変更

### ADR Path and Naming

- path
  - `docs/architecture/decisions/ADR-XXXX-short-title.md`
- title
  - issue 名ではなく決定内容を表す

### ADR Required Sections

- Status
- Context
- Decision
- Consequences
- Alternatives Considered
- Supersedes / Superseded by

### ADR Status Set

- Proposed
- Accepted
- Superseded
- Rejected

## SSoT Update Rule

- code が behavior を変えるなら doc も同時更新する。
- stable contract を変えるなら reference + changelog + migration guide を更新する。
- rule を変えるなら `guideline/` を更新する。
- decision を変えるなら ADR を追加または supersede する。

## Quality Evidence Rule

`docs/quality/` には次を置ける状態を維持する。

- benchmark report
- compatibility check report
- accessibility audit
- workflow validation result
- dependency / security audit result

artifact は human-readable summary と machine-readable source を分ける。

## Example and Sample Rule

- README / docs 内の code example は compile または test 可能であること。
- sample が reference の代わりになってはならない。
- sample を変更して user path が変わるなら docs を同時更新する。

## Reference Hygiene

- public API reference は stable path に集約する。
- support matrix は 1 箇所だけを公式版とする。
- migration guide は deprecation message と整合させる。
- docs 末尾には source を残し、重要領域は upstream guidance を再確認する。

## File Naming and Placement Rules

- stable doc file 名は kebab-case を既定にする。
- dated draft は directory 単位で持ち、`README.md` を entry にする。
- quality artifact は tool 名ではなく concern 名で grouping する。
- generated file と hand-written narrative を同一 file に混在させない。

## Anti-Patterns

- README だけが最新で reference が古い状態
- issue comment が実質的な唯一の設計記録になること
- changelog に migration path がないこと
- draft 文書が stable contract の唯一の参照先になること
- benchmark や audit の結果が散逸し再現できないこと

## Sources

- Diataxis
  - https://diataxis.fr/
- Architecture Decision Records
  - https://adr.github.io/
- Open Source Guides, Best Practices for Maintainers
  - https://opensource.guide/best-practices/
- Kotlin Docs, Introduction to library authors' guidelines
  - https://kotlinlang.org/docs/api-guidelines-introduction.html
- Kotlin Docs, Backward compatibility guidelines for library authors
  - https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html
