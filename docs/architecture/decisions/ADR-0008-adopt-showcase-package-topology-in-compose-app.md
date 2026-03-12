# ADR-0008: Adopt showcase package topology in composeApp

## Status

Accepted

## Context

`composeApp` の showcase 系 screen は長く `screen/examples/` 配下に置かれていた。
しかし repository guideline では consumer app showcase の標準形を
`screen/showcase/{gallery,detail,data,preview,component}` と定義している。

`examples` という名称を維持すると次の問題が起きる。

- package 名だけで責務が推測しづらい
- `guideline/24-showcase-catalog-and-consumer-app-conventions.md` と実装がずれる
- screen / state / route の naming と topology が target-state へ揃わない

## Decision

- `composeApp` の app-only showcase packages は `screen/showcase/` を正規配置とする
- 画面 entry は `ShowcaseGalleryScreen` と `ShowcaseDetailScreen` を使う
- navigation contract は `ShowcaseGalleryRoute` / `ShowcaseDetailRoute` を使う
- gallery/detail reducer and state holder は showcase naming に揃える

## Consequences

### Positive

- package topology が guideline と一致する
- screen, route, state holder の責務が file/path 名から読み取りやすくなる
- 将来の `patterns`, `lab`, `benchmark`, `a11y` 追加先が明確になる

### Trade-offs

- 現時点では component demo registry や data model 名に `Example*` が残っており、完全移行ではない
- navigation surface 名の変更に追随する repository 内参照更新が必要になる

## Follow-up

- `Example*` data/model/demo symbol を `Showcase*` naming へ段階移行する
- `screen/showcase/patterns` など canonical subtrees を追加する
