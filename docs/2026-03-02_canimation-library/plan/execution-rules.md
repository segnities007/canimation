# canimation 実行ルール（厳格）

- 文書ID: `CP-RULES-001`
- 版: `4.0`
- 適用対象: 全 task-pack

## 1. タスク粒度

- 1タスク = 1ファイル変更のみ
- 1タスクの想定時間 = 30〜90分
- 実装タスクとテストタスクは分離する

## 2. 禁止事項

- 1タスクで複数ファイル変更
- 対象ファイル欄への `...` / `*` を含む曖昧パス記述
- task-pack 未定義コマンドの実行
- 証跡なしで `DONE` へ変更

## 3. タスク必須項目

1. Task ID
2. 目的
3. 前提条件
4. 対象ファイル（絶対に1つ）
5. 変更種別
6. 作業手順
7. 実行コマンド
8. 完了条件
9. 失敗時手順
10. 証跡

## 4. Task 状態

- `NOT_STARTED`
- `IN_PROGRESS`
- `BLOCKED`
- `DONE`

`DONE` 条件:

- 完了条件を満たすコマンド結果がある
- 証跡3点（prompt/output/review）が保存済み

## 5. コーディング規約

- 公開型: `Canimation*`
- Modifier: `Modifier.canimation*`
- 公開API以外は `internal` 優先
- 数値は `spec-values.md` から参照

## 5.1 タスク依存関係ルール

- タスク間の依存は **技術的に必須な依存** のみを記述する
- 同一パッケージ内の独立した型定義（例: 各 Token 型）は並列実行可能であり、
  互いに依存しない。共通の前提タスク（パッケージ確立）のみに依存する
- 各プラットフォーム実装は共通契約（interface/expect）完了後は並列実行可能
- 「推奨実行順」は依存関係ではなく、タスク一覧の記載順で表現する

## 6. テスト規約

- 公開APIに最低1つの単体テスト
- 修正には再発防止テスト必須
- Golden更新時は理由を証跡に記録
- perf測定は `spec-values.md` 6章/7章の固定条件で実行
- カバレッジ判定は `koverVerify` で line>=80%、branch>=70%
- フレーク率判定は `scripts/measure-flake-rate.sh` で `<2%`

## 7. CI規約

PR 必須ジョブ:

1. format-check
2. lint-detekt
3. unit-test
4. integration-test
5. artifact-build
6. coverage-verify
7. flake-scan
8. ai-evidence-check
9. linux-check
10. macos-ios-check

マージ条件:

- 全ジョブ成功
- レビュー承認済み
- AI証跡あり

## 8. 失敗時標準手順

- build 失敗: 直近1タスク差分のみ切り分け
- test 失敗: 再現最小化 -> 原因分類 -> 修正 -> 再実行
- perf 未達: 寄与箇所特定 -> 軽量化 -> 再測定

## 9. CR対象

- API変更
- 仕様値変更
- ツールチェーン変更
- タスク順変更
- 品質基準変更

## 10. 証跡命名

- `TASKID_runNN_prompt.md`
- `TASKID_runNN_output.md`
- `TASKID_review.md`

保存先:

- `docs/ai-evidence/prompts/`
- `docs/ai-evidence/outputs/`
- `docs/ai-evidence/reviews/`
