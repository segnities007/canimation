# canimation 計画書セット（厳格版）

このディレクトリは、`canimation` を再現可能に実装するための実行文書セットである。

## 参照順序（固定）

1. `master-plan.md`
2. `toolchain-lock.md`
3. `spec-values.md`
4. `execution-rules.md`
5. `task-pack-core-presets.md`
6. `task-pack-a11y-platform.md`
7. `task-pack-diagnostics-platform.md`
8. `task-pack-test-samples-ci.md`
9. `task-pack-release-ops.md`

## 重要ルール

- 1タスクは 1ファイルのみ変更する
- 対象ファイル欄で `...` や `*` を含む曖昧パスは禁止
- 未定義コマンドは禁止（事前にタスクとして定義する）
- 数値仕様は `spec-values.md` を唯一の真実とする
- ツールチェーンは `toolchain-lock.md` から逸脱しない
- タスク間依存は技術的に必須な依存のみ記述する（`execution-rules.md` 5.1章 参照）

## レビュー記録

- `REVIEW.md` に技術レビュー結果と修正内容を記録
- 修正は F-01〜F-07 の致命欠陥を解消済み

## 成果物保証

上記順序で実行し、各 task-pack の完了条件を満たした場合のみ完了とする。
