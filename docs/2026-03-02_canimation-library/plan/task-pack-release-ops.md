# Task Pack: release + ops（1ファイル厳守版）

- 文書ID: `CP-TASK-REL-002`
- 前提:
  - `task-pack-test-samples-ci.md` 完了（`TS-033` まで）

## 実行ルール

- 上から順に実行
- 1タスク1ファイル
- 運用操作は直後のログタスクで結果を記録する（同一タスクで複数ファイル更新しない）

## タスク一覧

### RO-001
- 状態: TODO
- 目的: version を固定
- 前提条件: なし
- 対象ファイル: `gradle.properties`
- 変更種別: 更新
- 作業手順:
1. `version=0.1.0` を設定
- 実行コマンド: `./gradlew properties | rg '^version:'`
- 完了条件: version が `0.1.0`
- 失敗時手順:
1. 重複定義を排除
- 証跡: `RO-001_*`

### RO-002
- 状態: TODO
- 目的: バージョン運用規約作成
- 前提条件: RO-001
- 対象ファイル: `docs/release-versioning-policy.md`
- 変更種別: 新規作成
- 作業手順:
1. patch/minor の運用ルール記述
- 実行コマンド: `rg -n '0\.1\.0|patch|minor|breaking' docs/release-versioning-policy.md`
- 完了条件: 規約記載あり
- 失敗時手順:
1. 章追加
- 証跡: `RO-002_*`

### RO-003
- 状態: TODO
- 目的: 公開 metadata 集約設定
- 前提条件: RO-002
- 対象ファイル: `build-logic/publishing.gradle.kts`
- 変更種別: 新規作成/更新
- 作業手順:
1. group/artifact/license/scm/developer 設定の共通化
2. 公開先と認証情報を環境変数（`CANIMATION_MAVEN_URL`、`CANIMATION_MAVEN_USERNAME`、`CANIMATION_MAVEN_PASSWORD`）で受け取る設定を追加
3. 認証情報未設定時は fail-fast で停止
- 実行コマンド: `./gradlew :canimation-core:publishToMavenLocal`
- 完了条件: publishToMavenLocal 成功
- 失敗時手順:
1. publication 設定修正
- 証跡: `RO-003_*`

### RO-004
- 状態: TODO
- 目的: 署名設定
- 前提条件: RO-003
- 対象ファイル: `gradle/signing.gradle.kts`
- 変更種別: 新規作成/更新
- 作業手順:
1. signing plugin 設定
2. 署名情報を環境変数（`CANIMATION_SIGNING_KEY`、`CANIMATION_SIGNING_PASSWORD`、`CANIMATION_SIGNING_KEY_ID`）で受け取る
3. key 未設定 fail-fast
- 実行コマンド: `./gradlew :canimation-core:signMavenPublication`
- 完了条件: sign task 成功
- 失敗時手順:
1. key 参照名修正
- 証跡: `RO-004_*`

### RO-005
- 状態: TODO
- 目的: CHANGELOG 作成
- 前提条件: RO-004
- 対象ファイル: `CHANGELOG.md`
- 変更種別: 新規作成
- 作業手順:
1. `0.1.0` の Added/Changed/Fixed/Breaking 記載
- 実行コマンド: `rg -n '## \[0\.1\.0\]|Added|Changed|Fixed|Breaking' CHANGELOG.md`
- 完了条件: 全章存在
- 失敗時手順:
1. 欠落章を追加
- 証跡: `RO-005_*`

### RO-006
- 状態: TODO
- 目的: README 最終化
- 前提条件: RO-005
- 対象ファイル: `README.md`
- 変更種別: 更新
- 作業手順:
1. 10分導入手順
2. A11y/Diagnostics 導線
- 実行コマンド: `rg -n 'Quick Start|CanimationProvider|canimationEnter|Reduced|Diagnostics' README.md`
- 完了条件: 必須導線記載
- 失敗時手順:
1. 項目不足を追記
- 証跡: `RO-006_*`

### RO-007
- 状態: TODO
- 目的: API docs 設定
- 前提条件: RO-006
- 対象ファイル: `build.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. Dokka タスクを有効化
- 実行コマンド: `./gradlew dokkaHtml`
- 完了条件: docs 生成成功
- 失敗時手順:
1. plugin 設定修正
- 証跡: `RO-007_*`

### RO-008
- 状態: TODO
- 目的: release note 草案
- 前提条件: RO-007
- 対象ファイル: `docs/release-note-draft.md`
- 変更種別: 新規作成
- 作業手順:
1. 主要機能・制約・移行注意を記述
- 実行コマンド: `rg -n 'Canimation|Tier1|Tier2|Known Limitations' docs/release-note-draft.md`
- 完了条件: 必須項目記載
- 失敗時手順:
1. 欠落項目追加
- 証跡: `RO-008_*`

### RO-009
- 状態: TODO
- 目的: release dry-run 実行記録
- 前提条件: RO-008
- 対象ファイル: `docs/release-dry-run-report.md`
- 変更種別: 新規作成
- 作業手順:
1. `clean check publishToMavenLocal` を実行
2. 成否を記録
- 実行コマンド: `./gradlew clean check publishToMavenLocal`
- 完了条件: 全コマンド成功をレポート記載
- 失敗時手順:
1. build/test/publish を分離再実行
- 証跡: `RO-009_*`

### RO-010
- 状態: TODO
- 目的: RC タグ手順書作成
- 前提条件: RO-009
- 対象ファイル: `docs/release-tagging-rc.md`
- 変更種別: 新規作成
- 作業手順:
1. `v0.1.0-rc1` タグ作成手順を記述
- 実行コマンド: `rg -n 'v0\.1\.0-rc1|git tag' docs/release-tagging-rc.md`
- 完了条件: 手順書完成
- 失敗時手順:
1. 手順の不足項目追加
- 証跡: `RO-010_*`

### RO-011
- 状態: TODO
- 目的: RC タグ作成・push・検証ログ
- 前提条件: RO-010
- 対象ファイル: `docs/release-tagging-rc-log.md`
- 変更種別: 新規作成
- 作業手順:
1. `v0.1.0-rc1` タグを作成する
2. `origin` へタグを push する
3. local/remote の両方で存在確認する
- 実行コマンド: `git tag -a v0.1.0-rc1 -m 'v0.1.0-rc1' && git push origin v0.1.0-rc1 && git tag --list | rg 'v0\\.1\\.0-rc1' && git ls-remote --tags origin | rg 'refs/tags/v0\\.1\\.0-rc1$'`
- 完了条件: local/remote の両方で `v0.1.0-rc1` が確認できる
- 失敗時手順:
1. タグ重複時は CR 起票
- 証跡: `RO-011_*`

### RO-012
- 状態: TODO
- 目的: 本番タグ手順書作成
- 前提条件: RO-011
- 対象ファイル: `docs/release-tagging-ga.md`
- 変更種別: 新規作成
- 作業手順:
1. `v0.1.0` タグ作成/検証手順記述
- 実行コマンド: `rg -n '^v0\.1\.0|git tag' docs/release-tagging-ga.md`
- 完了条件: 手順書完成
- 失敗時手順:
1. 手順不足を追記
- 証跡: `RO-012_*`

### RO-013
- 状態: TODO
- 目的: 本番タグ作成・push・検証ログ
- 前提条件: RO-012
- 対象ファイル: `docs/release-tagging-ga-log.md`
- 変更種別: 新規作成
- 作業手順:
1. `v0.1.0` タグを作成する
2. `origin` へタグを push する
3. local/remote の両方で存在確認する
- 実行コマンド: `git tag -a v0.1.0 -m 'v0.1.0' && git push origin v0.1.0 && git tag --list | rg '^v0\\.1\\.0$' && git ls-remote --tags origin | rg 'refs/tags/v0\\.1\\.0$'`
- 完了条件: local/remote の両方で `v0.1.0` が確認できる
- 失敗時手順:
1. タグ整合修正
- 証跡: `RO-013_*`

### RO-014
- 状態: TODO
- 目的: publish 実行ログ
- 前提条件: RO-013
- 対象ファイル: `docs/publish-run-log.md`
- 変更種別: 新規作成
- 作業手順:
1. 必須環境変数（maven/signing）存在確認
2. publish 実行結果記録
- 実行コマンド: `bash -lc 'test -n \"$CANIMATION_MAVEN_URL\" && test -n \"$CANIMATION_MAVEN_USERNAME\" && test -n \"$CANIMATION_MAVEN_PASSWORD\" && test -n \"$CANIMATION_SIGNING_KEY\" && test -n \"$CANIMATION_SIGNING_PASSWORD\" && test -n \"$CANIMATION_SIGNING_KEY_ID\"' && ./gradlew publish`
- 完了条件: 変数確認と publish 成功を記録
- 失敗時手順:
1. 認証/署名/metadata を分離調査
- 証跡: `RO-014_*`

### RO-015
- 状態: TODO
- 目的: post-release smoke 手順書
- 前提条件: RO-014
- 対象ファイル: `docs/post-release-smoke-procedure.md`
- 変更種別: 新規作成
- 作業手順:
1. 新規プロジェクト導入確認手順記述
- 実行コマンド: `rg -n 'CanimationProvider|canimationEnter|dependency' docs/post-release-smoke-procedure.md`
- 完了条件: 手順書完成
- 失敗時手順:
1. 手順不足を追記
- 証跡: `RO-015_*`

### RO-016
- 状態: TODO
- 目的: post-release smoke 実行ログ
- 前提条件: RO-015
- 対象ファイル: `docs/post-release-smoke-report.md`
- 変更種別: 新規作成
- 作業手順:
1. smoke 実行結果記録
- 実行コマンド: `./gradlew :sample-chat:build :sample-commerce:build`
- 完了条件: build 成功を記録
- 失敗時手順:
1. 依存解決/公開座標を修正
- 証跡: `RO-016_*`

### RO-017
- 状態: TODO
- 目的: release announcement 作成
- 前提条件: RO-016
- 対象ファイル: `docs/release-announcement.md`
- 変更種別: 新規作成
- 作業手順:
1. 公開情報と既知制約記載
- 実行コマンド: `rg -n 'v0\.1\.0|Known Limitations|Tier1|Tier2' docs/release-announcement.md`
- 完了条件: 必須項目記載
- 失敗時手順:
1. 欠落項目追加
- 証跡: `RO-017_*`

### RO-018
- 状態: TODO
- 目的: 初期運用監視規約作成
- 前提条件: RO-017
- 対象ファイル: `docs/initial-ops-monitoring.md`
- 変更種別: 新規作成
- 作業手順:
1. severity と SLA を定義
- 実行コマンド: `rg -n 'Severity|SLA|bug|perf|a11y|doc' docs/initial-ops-monitoring.md`
- 完了条件: 監視規約完成
- 失敗時手順:
1. 分類不足を追記
- 証跡: `RO-018_*`

### RO-019
- 状態: TODO
- 目的: 初期運用報告書作成
- 前提条件: RO-018
- 対象ファイル: `docs/initial-ops-report.md`
- 変更種別: 新規作成
- 作業手順:
1. 障害件数/重大度/対応状況を記録
- 実行コマンド: `rg -n '件数|重大|対応|再発防止' docs/initial-ops-report.md`
- 完了条件: 報告書完成
- 失敗時手順:
1. データ欠落項目追記
- 証跡: `RO-019_*`

### RO-020
- 状態: TODO
- 目的: 次版バックログ作成
- 前提条件: RO-019
- 対象ファイル: `docs/next-backlog.md`
- 変更種別: 新規作成
- 作業手順:
1. P0/P1/P2 で優先度整理
- 実行コマンド: `rg -n 'P0|P1|P2|CR' docs/next-backlog.md`
- 完了条件: 優先度付き一覧完成
- 失敗時手順:
1. 優先基準を影響度/コストで再整理
- 証跡: `RO-020_*`

### RO-021
- 状態: TODO
- 目的: release+ops 総合判定記録
- 前提条件: RO-020
- 対象ファイル: `docs/task-results/release-ops-result.md`
- 変更種別: 新規作成
- 作業手順:
1. release/ops 全結果を集約
- 実行コマンド: `./gradlew check && bash scripts/ai-evidence-check.sh`
- 完了条件: コマンド成功と結果記録
- 失敗時手順:
1. 失敗カテゴリごとにタスク差し戻し
- 証跡: `RO-021_*`
