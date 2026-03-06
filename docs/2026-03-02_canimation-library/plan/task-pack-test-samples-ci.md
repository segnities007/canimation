# Task Pack: test + samples + CI（1ファイル厳守版）

- 文書ID: `CP-TASK-QA-002`
- 前提:
  - `task-pack-diagnostics-platform.md` 完了
  - `toolchain-lock.md` と `spec-values.md` 参照済み

## 実行ルール

- 上から順に実行
- 1タスク1ファイル
- 対象ファイル欄でのワイルドカード/省略パスは禁止

## タスク一覧

### TS-100
- 状態: 未着手
- 目的: Gradle Wrapper バージョン固定
- 前提条件: なし
- 対象ファイル: `gradle/wrapper/gradle-wrapper.properties`
- 変更種別: 更新
- 作業手順:
1. `distributionUrl` を `8.10.2` に固定する
- 実行コマンド: `./gradlew --version | rg 'Gradle 8\\.10\\.2'`
- 完了条件: Gradle バージョンが `8.10.2`
- 失敗時手順:
1. wrapper 設定の重複を解消
- 証跡: `TS-100_*`

### TS-101
- 状態: 未着手
- 目的: ライブラリバージョン固定
- 前提条件: TS-100
- 対象ファイル: `gradle/libs.versions.toml`
- 変更種別: 新規作成/更新
- 作業手順:
1. Kotlin/Compose/AGP の固定バージョンを `toolchain-lock.md` に一致させる
- 実行コマンド: `./gradlew -q dependencies`
- 完了条件: 依存解決が成功し、固定バージョンが有効
- 失敗時手順:
1. 競合バージョンの解決順を修正
- 証跡: `TS-101_*`

### TS-102
- 状態: 未着手
- 目的: gradle.properties の固定値整備
- 前提条件: TS-101
- 対象ファイル: `gradle.properties`
- 変更種別: 更新
- 作業手順:
1. `toolchain-lock.md` 準拠の固定値を設定する
- 実行コマンド: `./gradlew properties | rg '^version:'`
- 完了条件: properties 参照が成功し固定値を維持
- 失敗時手順:
1. 設定重複を排除
- 証跡: `TS-102_*`

### TS-103
- 状態: 未着手
- 目的: toolchain 検証ログ作成
- 前提条件: TS-102
- 対象ファイル: `docs/toolchain-verification.md`
- 変更種別: 新規作成
- 作業手順:
1. Linux runner の `./gradlew --version`、`./gradlew -q dependencies`、`node -v`、`java -version` を記録
2. macOS runner の `xcodebuild -version` を記録（非macOS実行時は `N/A` を明記）
- 実行コマンド: `./gradlew --version && ./gradlew -q dependencies && node -v && java -version && (if command -v xcodebuild >/dev/null 2>&1; then xcodebuild -version; else echo 'xcodebuild: N/A (non-macOS runner)'; fi)`
- 完了条件: 5項目の検証結果が runner 種別付きで記録されている
- 失敗時手順:
1. 失敗したツールの固定バージョン設定を修正
- 証跡: `TS-103_*`

### TS-104
- 状態: 未着手
- 目的: AI証跡ディレクトリ規約作成
- 前提条件: TS-103
- 対象ファイル: `docs/ai-evidence/README.md`
- 変更種別: 新規作成
- 作業手順:
1. `prompts/outputs/reviews` の保存規約を記述
- 実行コマンド: `rg -n 'prompts|outputs|reviews' docs/ai-evidence/README.md`
- 完了条件: 3ディレクトリ規約が記載されている
- 失敗時手順:
1. 規約項目を補完
- 証跡: `TS-104_*`

### TS-001
- 状態: 未着手
- 目的: canimation-test module include
- 前提条件: TS-104
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-test")` を追加
- 実行コマンド: `./gradlew projects`
- 完了条件: `:canimation-test` 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `TS-001_*`

### TS-002
- 状態: 未着手
- 目的: canimation-test build 設定
- 前提条件: TS-001
- 対象ファイル: `modules/canimation-test/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. KMP と core 依存設定
- 実行コマンド: `./gradlew :canimation-test:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. 依存設定修正
- 証跡: `TS-002_*`

### TS-003
- 状態: 未着手
- 目的: CanimationTestClock 実装
- 前提条件: TS-002
- 対象ファイル: `modules/canimation-test/src/commonMain/kotlin/io/github/canimation/test/CanimationTestClock.kt`
- 変更種別: 新規作成
- 作業手順:
1. `advanceBy` と `advanceTo` を実装
- 実行コマンド: `./gradlew :canimation-test:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. 時刻単位を millis に統一
- 証跡: `TS-003_*`

### TS-004
- 状態: 未着手
- 目的: CanimationTestHost 実装
- 前提条件: TS-003
- 対象ファイル: `modules/canimation-test/src/commonMain/kotlin/io/github/canimation/test/CanimationTestHost.kt`
- 変更種別: 新規作成
- 作業手順:
1. test clock 注入 composable 実装
- 実行コマンド: `./gradlew :canimation-test:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. context 注入経路修正
- 証跡: `TS-004_*`

### TS-005
- 状態: 未着手
- 目的: TestClock test
- 前提条件: TS-004
- 対象ファイル: `modules/canimation-test/src/commonTest/kotlin/io/github/canimation/test/CanimationTestClockTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. `advanceBy` 累積検証
2. `advanceTo` 絶対移動検証
- 実行コマンド: `./gradlew :canimation-test:allTests --tests '*CanimationTestClockTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. clock 更新ロジック修正
- 証跡: `TS-005_*`

### TS-006
- 状態: 未着手
- 目的: TestHost test
- 前提条件: TS-005
- 対象ファイル: `modules/canimation-test/src/commonTest/kotlin/io/github/canimation/test/CanimationTestHostTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. clock 注入確認
2. 再現性確認
- 実行コマンド: `./gradlew :canimation-test:allTests --tests '*CanimationTestHostTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. host 注入順序修正
- 証跡: `TS-006_*`

### TS-007
- 状態: 未着手
- 目的: integration base 実装
- 前提条件: TS-006
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/IntegrationTestBase.kt`
- 変更種別: 新規作成
- 作業手順:
1. test host 利用の共通ヘルパー実装
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*IntegrationTestBase*'`
- 完了条件: compile/test 成功
- 失敗時手順:
1. 依存注入 API 修正
- 証跡: `TS-007_*`

### TS-008
- 状態: 未着手
- 目的: EnterTransition integration test
- 前提条件: TS-007
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/EnterTransitionIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. visible false->true 時系列検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*EnterTransitionIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. transition timeline 修正
- 証跡: `TS-008_*`

### TS-009
- 状態: 未着手
- 目的: LevelSwitch integration test
- 前提条件: TS-008
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/LevelSwitchIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full/Reduced/Off 切替検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*LevelSwitchIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. resolver 実装修正
- 証跡: `TS-009_*`

### TS-010
- 状態: 未着手
- 目的: Provider default safety test
- 前提条件: TS-009
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/ProviderDefaultSafetyTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. provider 未指定時 no-crash 検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*ProviderDefaultSafetyTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. local default 修正
- 証跡: `TS-010_*`

### TS-011
- 状態: 未着手
- 目的: golden base 実装
- 前提条件: TS-010
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/GoldenTestBase.kt`
- 変更種別: 新規作成
- 作業手順:
1. snapshot 比較ヘルパー作成
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*GoldenTestBase*'`
- 完了条件: compile 成功
- 失敗時手順:
1. test framework 設定修正
- 証跡: `TS-011_*`

### TS-012
- 状態: 未着手
- 目的: preset golden test 実装
- 前提条件: TS-011
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/PresetGoldenTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. 5 preset x 3 level x 2 direction(Enter/Exit) の 30 ケース比較
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*PresetGoldenTest'`
- 完了条件: 30ケース成功
- 失敗時手順:
1. baseline 差分理由を記録して修正
- 証跡: `TS-012_*`

### TS-013
- 状態: 未着手
- 目的: sample-chat include
- 前提条件: TS-012
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":sample-chat")` を追加
- 実行コマンド: `./gradlew projects`
- 完了条件: `:sample-chat` 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `TS-013_*`

### TS-014
- 状態: 未着手
- 目的: sample-chat build 設定
- 前提条件: TS-013
- 対象ファイル: `samples/sample-chat/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. core/presets/a11y/diagnostics 依存設定
- 実行コマンド: `./gradlew :sample-chat:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. module 依存修正
- 証跡: `TS-014_*`

### TS-015
- 状態: 未着手
- 目的: ChatScreen 実装
- 前提条件: TS-014
- 対象ファイル: `samples/sample-chat/src/commonMain/kotlin/io/github/canimation/sample/chat/ChatScreen.kt`
- 変更種別: 新規作成
- 作業手順:
1. message item へ `Modifier.canimationEnter` 適用
- 実行コマンド: `./gradlew :sample-chat:check`
- 完了条件: check 成功
- 失敗時手順:
1. state 遷移タイミング修正
- 証跡: `TS-015_*`

### TS-016
- 状態: 未着手
- 目的: ChatApp 実装
- 前提条件: TS-015
- 対象ファイル: `samples/sample-chat/src/commonMain/kotlin/io/github/canimation/sample/chat/ChatApp.kt`
- 変更種別: 新規作成
- 作業手順:
1. Reduced/Off 切替 UI
2. Diagnostics overlay トグル
- 実行コマンド: `./gradlew :sample-chat:check`
- 完了条件: check 成功
- 失敗時手順:
1. overlay ラップ順修正
- 証跡: `TS-016_*`

### TS-017
- 状態: 未着手
- 目的: sample-commerce include
- 前提条件: TS-016
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":sample-commerce")` 追加
- 実行コマンド: `./gradlew projects`
- 完了条件: `:sample-commerce` 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `TS-017_*`

### TS-018
- 状態: 未着手
- 目的: sample-commerce build 設定
- 前提条件: TS-017
- 対象ファイル: `samples/sample-commerce/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. core/presets/a11y/diagnostics 依存設定
- 実行コマンド: `./gradlew :sample-commerce:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. module 依存修正
- 証跡: `TS-018_*`

### TS-019
- 状態: 未着手
- 目的: ProductCard 実装
- 前提条件: TS-018
- 対象ファイル: `samples/sample-commerce/src/commonMain/kotlin/io/github/canimation/sample/commerce/ProductCard.kt`
- 変更種別: 新規作成
- 作業手順:
1. `Modifier.canimationEmphasize` 適用
2. Off 即時反映
- 実行コマンド: `./gradlew :sample-commerce:check`
- 完了条件: check 成功
- 失敗時手順:
1. active state 制御修正
- 証跡: `TS-019_*`

### TS-020
- 状態: 未着手
- 目的: CommerceApp 実装
- 前提条件: TS-019
- 対象ファイル: `samples/sample-commerce/src/commonMain/kotlin/io/github/canimation/sample/commerce/CommerceApp.kt`
- 変更種別: 新規作成
- 作業手順:
1. diagnostics overlay の導線実装
2. jankThreshold UI 設定導線実装
- 実行コマンド: `./gradlew :sample-commerce:check`
- 完了条件: check 成功
- 失敗時手順:
1. state hoisting 修正
- 証跡: `TS-020_*`

### TS-021
- 状態: 未着手
- 目的: パフォーマンス実行スクリプト作成（固定プロファイル準拠）
- 前提条件: TS-020
- 対象ファイル: `scripts/run-perf-scenarios.sh`
- 変更種別: 新規作成
- 作業手順:
1. `spec-values.md` 6章/7章の固定条件をスクリプト定数として実装（`warmup=10s`、`measure=60s`、Tier1判定）
2. シナリオA（sample-chat）を固定実行（初期300件描画、50件連続追加、`CanimationLevel=Full`）
3. シナリオB（sample-commerce）を固定実行（初期120件描画、強調切替200回、`CanimationLevel=Full`）
4. baseline 計測（diagnostics off）と比較計測（diagnostics on）を両方実行
5. `docs/perf-raw/` に4ファイルを保存（`sample-chat_baseline.json`、`sample-chat_with_library.json`、`sample-commerce_baseline.json`、`sample-commerce_with_library.json`）
6. 各JSONに `frameTimes`、`p95FrameTimeMs`、`jankCount` を出力
- 実行コマンド: `bash scripts/run-perf-scenarios.sh`
- 完了条件: 終了コード0かつ `docs/perf-raw/` の4ファイルに必須キーが存在
- 失敗時手順:
1. 欠落キーがあるシナリオを分離実行して修正
- 証跡: `TS-021_*`

### TS-022
- 状態: 未着手
- 目的: unit test 集計レポート作成
- 前提条件: TS-021
- 対象ファイル: `docs/unit-test-report.md`
- 変更種別: 新規作成
- 作業手順:
1. module別テスト結果を記録
- 実行コマンド: `./gradlew :canimation-core:allTests :canimation-presets:allTests :canimation-a11y:allTests :canimation-diagnostics:allTests :canimation-test:allTests`
- 完了条件: 全 tests 成功を記録
- 失敗時手順:
1. 失敗 test を該当 task へ差し戻し
- 証跡: `TS-022_*`

### TS-023
- 状態: 未着手
- 目的: integration test レポート作成
- 前提条件: TS-022
- 対象ファイル: `docs/integration-test-report.md`
- 変更種別: 新規作成
- 作業手順:
1. sample-chat/sample-commerce 結果記録
- 実行コマンド: `./gradlew :sample-chat:check :sample-commerce:check`
- 完了条件: 合否を文書化
- 失敗時手順:
1. 失敗シナリオを切り分け
- 証跡: `TS-023_*`

### TS-024
- 状態: 未着手
- 目的: perf report 作成
- 前提条件: TS-023
- 対象ファイル: `docs/perf-report.md`
- 変更種別: 新規作成
- 作業手順:
1. `docs/perf-raw/` の4ファイルから scenario A/B の baseline/with-library の `p95FrameTimeMs` と `jankCount` を記録
2. 各シナリオで `with_library / baseline` を計算し、劣化率を明記
3. `spec-values.md` 5章の合格式 `p95(frameTime_with_library) <= p95(baseline) * 1.10` で PASS/FAIL 判定
- 実行コマンド: `rg -n 'sample-chat|sample-commerce|baseline|with-library|p95|jank|PASS|FAIL|1\\.10' docs/perf-report.md`
- 完了条件: シナリオA/Bの数値、劣化率、PASS/FAIL がすべて記録されている
- 失敗時手順:
1. TS-021 へ差し戻して raw 収集条件を再確認
- 証跡: `TS-024_*`

### TS-025
- 状態: 未着手
- 目的: workflow 検証スクリプト作成
- 前提条件: TS-024
- 対象ファイル: `scripts/validate-workflows.sh`
- 変更種別: 新規作成
- 作業手順:
1. `pr.yml` の必須 job（`linux-check`、`macos-ios-check`、`format-check`、`lint-detekt`、`unit-test`、`integration-test`、`artifact-build`、`coverage-verify`、`flake-scan`、`ai-evidence-check`）を検証
2. `release.yml` の必須 job（`check`、`sign`、`publish`、`docs-deploy`、`release-secrets-check`）を検証
3. runner 行（`ubuntu-24.04`、`macos-15`）を検証
4. 欠落時 非0終了
- 実行コマンド: `bash scripts/validate-workflows.sh`
- 完了条件: 終了コード0/1が仕様通り
- 失敗時手順:
1. path と grep 条件修正
- 証跡: `TS-025_*`

### TS-026
- 状態: 未着手
- 目的: PR workflow 作成
- 前提条件: TS-025
- 対象ファイル: `.github/workflows/pr.yml`
- 変更種別: 新規作成
- 作業手順:
1. `linux-check`（`ubuntu-24.04`）と `macos-ios-check`（`macos-15`）を定義
2. `format-check`、`lint-detekt`、`unit-test`、`integration-test`、`artifact-build`、`coverage-verify`、`flake-scan`、`ai-evidence-check` を定義
- 実行コマンド: `bash scripts/validate-workflows.sh`
- 完了条件: pr.yml の必須 job を検出
- 失敗時手順:
1. job 名を規定値へ修正
- 証跡: `TS-026_*`

### TS-027
- 状態: 未着手
- 目的: release workflow 作成
- 前提条件: TS-026
- 対象ファイル: `.github/workflows/release.yml`
- 変更種別: 新規作成
- 作業手順:
1. `check`、`sign`、`publish`、`docs-deploy`、`release-secrets-check` を定義
2. `release-secrets-check` で `CANIMATION_MAVEN_URL`、`CANIMATION_MAVEN_USERNAME`、`CANIMATION_MAVEN_PASSWORD`、`CANIMATION_SIGNING_KEY`、`CANIMATION_SIGNING_PASSWORD`、`CANIMATION_SIGNING_KEY_ID` の存在を検証
- 実行コマンド: `bash scripts/validate-workflows.sh`
- 完了条件: release.yml の必須 job を検出
- 失敗時手順:
1. job 名を規定値へ修正
- 証跡: `TS-027_*`

### TS-028
- 状態: 未着手
- 目的: ai-evidence-check 実装
- 前提条件: TS-027
- 対象ファイル: `scripts/ai-evidence-check.sh`
- 変更種別: 新規作成
- 作業手順:
1. task ID ごとの prompt/output/review 3点存在を検証
2. 欠落時 非0終了
- 実行コマンド: `bash scripts/ai-evidence-check.sh`
- 完了条件: 検証仕様どおり終了コード
- 失敗時手順:
1. パス検出ロジック修正
- 証跡: `TS-028_*`

### TS-029
- 状態: 未着手
- 目的: QA 中間結果記録（workflow/証跡）
- 前提条件: TS-028
- 対象ファイル: `docs/task-results/test-samples-ci-result.md`
- 変更種別: 新規作成
- 作業手順:
1. `check`、workflow 検証、証跡検証の結果を集約
- 実行コマンド: `./gradlew check && bash scripts/validate-workflows.sh && bash scripts/ai-evidence-check.sh`
- 完了条件: 全コマンド終了コード0
- 失敗時手順:
1. 失敗カテゴリを分離して該当タスクへ差し戻し
- 証跡: `TS-029_*`

### TS-030
- 状態: 未着手
- 目的: カバレッジ閾値設定
- 前提条件: TS-029
- 対象ファイル: `build.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `toolchain-lock.md` の `Kover Plugin=0.8.3` を適用
2. Kover を設定し line>=80%、branch>=70% の verify ルールを定義
3. 主要モジュール（core/presets/a11y/diagnostics/test）を集計対象に含める
- 実行コマンド: `./gradlew koverVerify`
- 完了条件: `koverVerify` が成功し閾値を満たす
- 失敗時手順:
1. 未達モジュールを特定して該当タスクへ差し戻し
- 証跡: `TS-030_*`

### TS-031
- 状態: 未着手
- 目的: フレーク率測定スクリプト作成
- 前提条件: TS-030
- 対象ファイル: `scripts/measure-flake-rate.sh`
- 変更種別: 新規作成
- 作業手順:
1. 対象テスト群を 50 回反復実行し pass/fail を集計
2. `flakeRate = failRuns / totalRuns` を算出
3. `docs/quality/flake-rate.json` へ `totalRuns`、`failRuns`、`flakeRate` を保存
4. `flakeRate >= 0.02` の場合は非0終了
- 実行コマンド: `bash scripts/measure-flake-rate.sh`
- 完了条件: 終了コード0かつ `flakeRate < 0.02`
- 失敗時手順:
1. 失敗テストの再現条件を固定して差し戻し
- 証跡: `TS-031_*`

### TS-032
- 状態: 未着手
- 目的: 品質ゲート測定レポート作成
- 前提条件: TS-031
- 対象ファイル: `docs/quality-gate-report.md`
- 変更種別: 新規作成
- 作業手順:
1. `koverXmlReport` から line/branch 実測値を記録
2. `flake-rate.json` からフレーク率を記録
3. `line>=80%`、`branch>=70%`、`flake<2%` の PASS/FAIL 判定を記録
- 実行コマンド: `./gradlew koverXmlReport koverVerify && bash scripts/measure-flake-rate.sh && rg -n 'line|branch|flake|PASS|FAIL|80|70|2%' docs/quality-gate-report.md`
- 完了条件: 3指標の実測値と判定が記録されている
- 失敗時手順:
1. 閾値未達の指標を該当タスクへ差し戻し
- 証跡: `TS-032_*`

### TS-033
- 状態: 未着手
- 目的: QA 最終結果記録
- 前提条件: TS-032
- 対象ファイル: `docs/task-results/test-samples-ci-result.md`
- 変更種別: 更新
- 作業手順:
1. テスト/CI/証跡/品質ゲート測定の結果を最終集約
2. Gate-G3/G4/G6 の達成可否を明記
- 実行コマンド: `./gradlew check koverVerify && bash scripts/validate-workflows.sh && bash scripts/ai-evidence-check.sh && bash scripts/measure-flake-rate.sh`
- 完了条件: 全コマンド終了コード0かつ最終判定が記録されている
- 失敗時手順:
1. 失敗カテゴリを分離して該当タスクへ差し戻し
- 証跡: `TS-033_*`
