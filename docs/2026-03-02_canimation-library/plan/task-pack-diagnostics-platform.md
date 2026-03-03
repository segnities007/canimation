# Task Pack: diagnostics + platform（1ファイル厳守版）

- 文書ID: `CP-TASK-DIAG-002`
- 前提:
  - `task-pack-a11y-platform.md` 完了
  - `spec-values.md` 参照済み

## 実行ルール

- 上から順に実行
- 1タスク1ファイル
- 未定義コマンド禁止

## タスク一覧

### DG-001
- 状態: TODO
- 目的: diagnostics module include
- 前提条件: なし
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-diagnostics")` を追加
- 実行コマンド: `./gradlew projects`
- 完了条件: module 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `DG-001_*`

### DG-002
- 状態: TODO
- 目的: diagnostics build 設定
- 前提条件: DG-001
- 対象ファイル: `modules/canimation-diagnostics/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. KMP と core 依存設定
- 実行コマンド: `./gradlew :canimation-diagnostics:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. 依存設定修正
- 証跡: `DG-002_*`

### DG-003
- 状態: TODO
- 目的: diagnostics package marker
- 前提条件: DG-002
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/DiagnosticsMarker.kt`
- 変更種別: 新規作成
- 作業手順:
1. marker 型作成
- 実行コマンド: `./gradlew :canimation-diagnostics:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. package 修正
- 証跡: `DG-003_*`

### DG-004
- 状態: TODO
- 目的: FrameMetrics 実装
- 前提条件: DG-003
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/FrameMetrics.kt`
- 変更種別: 新規作成
- 作業手順:
1. fps/frameTime/jankCount フィールドを定義
- 実行コマンド: `./gradlew :canimation-diagnostics:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. 単位定義を修正
- 証跡: `DG-004_*`

### DG-005
- 状態: TODO
- 目的: DiagnosticsCollector 契約
- 前提条件: DG-004
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/DiagnosticsCollector.kt`
- 変更種別: 新規作成
- 作業手順:
1. start/stop/currentMetrics API を定義
- 実行コマンド: `./gradlew :canimation-diagnostics:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. 契約を単純化
- 証跡: `DG-005_*`

### DG-006
- 状態: TODO
- 目的: JankCalculator 実装
- 前提条件: DG-005
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/JankCalculator.kt`
- 変更種別: 新規作成
- 作業手順:
1. jank count と p95 計算を実装
- 実行コマンド: `./gradlew :canimation-diagnostics:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. pure function 化して修正
- 証跡: `DG-006_*`

### DG-007
- 状態: TODO
- 目的: Diagnostics facade 実装
- 前提条件: DG-006
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/CanimationDiagnosticsFacade.kt`
- 変更種別: 新規作成
- 作業手順:
1. collector と overlay 連携 API 実装
- 実行コマンド: `./gradlew :canimation-diagnostics:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. 連携責務を分離
- 証跡: `DG-007_*`

### DG-008
- 状態: TODO
- 目的: Diagnostics overlay 実装
- 前提条件: DG-007
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/CanimationDiagnosticsOverlay.kt`
- 変更種別: 新規作成
- 作業手順:
1. enabled/showFps/showFrameTime/jankThresholdMs を仕様通り実装
- 実行コマンド: `./gradlew :canimation-diagnostics:compileKotlinMetadata`
- 完了条件: APIシグネチャ一致
- 失敗時手順:
1. シグネチャを master-plan の API 固定へ合わせる
- 証跡: `DG-008_*`

### DG-009
- 状態: TODO
- 目的: PerfMeasurementUtils 実装
- 前提条件: DG-008
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/PerfMeasurementUtils.kt`
- 変更種別: 新規作成
- 作業手順:
1. p95 計算ヘルパーを実装
- 実行コマンド: `./gradlew :canimation-diagnostics:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. 入力検証を追加
- 証跡: `DG-009_*`

### DG-010
- 状態: TODO
- 目的: JankCalculator test
- 前提条件: DG-009
- 対象ファイル: `modules/canimation-diagnostics/src/commonTest/kotlin/io/github/canimation/diagnostics/JankCalculatorTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. threshold 境界と p95 テスト作成
- 実行コマンド: `./gradlew :canimation-diagnostics:allTests --tests '*JankCalculatorTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. 計算ロジック修正
- 証跡: `DG-010_*`

### DG-011
- 状態: TODO
- 目的: Overlay test
- 前提条件: DG-010
- 対象ファイル: `modules/canimation-diagnostics/src/commonTest/kotlin/io/github/canimation/diagnostics/CanimationDiagnosticsOverlayTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. enabled=false no-op テスト
2. 表示切替テスト
- 実行コマンド: `./gradlew :canimation-diagnostics:allTests --tests '*CanimationDiagnosticsOverlayTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. 표시分岐ロジック修正
- 証跡: `DG-011_*`

### DG-012
- 状態: TODO
- 目的: Android collector 実装
- 前提条件: DG-011
- 対象ファイル: `modules/canimation-platform-android/src/androidMain/kotlin/io/github/canimation/platform/android/AndroidFrameMetricsCollector.kt`
- 変更種別: 新規作成
- 作業手順:
1. Android frame metrics 取得を実装
- 実行コマンド: `./gradlew :canimation-platform-android:compileDebugKotlinAndroid`
- 完了条件: compile 成功
- 失敗時手順:
1. API レベル分岐追加
- 証跡: `DG-012_*`

### DG-013
- 状態: TODO
- 目的: Android collector test
- 前提条件: DG-012
- 対象ファイル: `modules/canimation-platform-android/src/androidUnitTest/kotlin/io/github/canimation/platform/android/AndroidFrameMetricsCollectorTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. frame sequence モック検証
- 実行コマンド: `./gradlew :canimation-platform-android:testDebugUnitTest --tests '*AndroidFrameMetricsCollectorTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. collector state 修正
- 証跡: `DG-013_*`

### DG-014
- 状態: TODO
- 目的: Desktop collector 実装
- 前提条件: DG-011
- 対象ファイル: `modules/canimation-platform-desktop/src/desktopMain/kotlin/io/github/canimation/platform/desktop/DesktopFrameMetricsCollector.kt`
- 変更種別: 新規作成
- 作業手順:
1. Desktop frame metrics 取得を実装
- 実行コマンド: `./gradlew :canimation-platform-desktop:compileKotlinDesktop`
- 完了条件: compile 成功
- 失敗時手順:
1. frame source を抽象化
- 証跡: `DG-014_*`

### DG-015
- 状態: TODO
- 目的: Desktop collector test
- 前提条件: DG-014
- 対象ファイル: `modules/canimation-platform-desktop/src/desktopTest/kotlin/io/github/canimation/platform/desktop/DesktopFrameMetricsCollectorTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. frame sequence で metrics 算出を検証
- 実行コマンド: `./gradlew :canimation-platform-desktop:desktopTest --tests '*DesktopFrameMetricsCollectorTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. time source 注入修正
- 証跡: `DG-015_*`

### DG-016
- 状態: TODO
- 目的: iOS collector no-op 実装
- 前提条件: DG-011
- 対象ファイル: `modules/canimation-platform-ios/src/iosMain/kotlin/io/github/canimation/platform/ios/IosFrameMetricsCollector.kt`
- 変更種別: 新規作成
- 作業手順:
1. no-op collector 実装
- 実行コマンド: `./gradlew :canimation-platform-ios:compileKotlinIosX64`
- 完了条件: compile 成功
- 失敗時手順:
1. no-op object 化
- 証跡: `DG-016_*`

### DG-017
- 状態: TODO
- 目的: Web collector no-op 実装
- 前提条件: DG-011
- 対象ファイル: `modules/canimation-platform-web/src/jsMain/kotlin/io/github/canimation/platform/web/WebFrameMetricsCollector.kt`
- 変更種別: 新規作成
- 作業手順:
1. no-op collector 実装
- 実行コマンド: `./gradlew :canimation-platform-web:compileKotlinJs`
- 完了条件: compile 成功
- 失敗時手順:
1. no-op object 化
- 証跡: `DG-017_*`

### DG-018
- 状態: TODO
- 目的: iOS collector no-op test
- 前提条件: DG-016
- 対象ファイル: `modules/canimation-platform-ios/src/iosTest/kotlin/io/github/canimation/platform/ios/IosCollectorNoopTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. start/stop 無例外を検証
- 実行コマンド: `./gradlew :canimation-platform-ios:iosX64Test --tests '*IosCollectorNoopTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. no-op 状態管理修正
- 証跡: `DG-018_*`

### DG-019
- 状態: TODO
- 目的: Web collector no-op test
- 前提条件: DG-017
- 対象ファイル: `modules/canimation-platform-web/src/jsTest/kotlin/io/github/canimation/platform/web/WebCollectorNoopTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. start/stop 無例外を検証
- 実行コマンド: `./gradlew :canimation-platform-web:jsTest --tests '*WebCollectorNoopTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. no-op 状態管理修正
- 証跡: `DG-019_*`

### DG-020
- 状態: TODO
- 目的: Diagnostics KDoc 指針
- 前提条件: DG-013, DG-015, DG-018, DG-019
- 対象ファイル: `modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/README_API_KDOC.md`
- 変更種別: 新規作成
- 作業手順:
1. public API KDoc 要件記載
- 実行コマンド: `rg -n 'CanimationDiagnosticsOverlay|jankThresholdMs|Tier' modules/canimation-diagnostics/src/commonMain/kotlin/io/github/canimation/diagnostics/README_API_KDOC.md`
- 完了条件: 記載あり
- 失敗時手順:
1. 欠落項目を追加
- 証跡: `DG-020_*`

### DG-021
- 状態: TODO
- 目的: diagnostics usage 文書
- 前提条件: DG-020
- 対象ファイル: `docs/diagnostics-usage.md`
- 変更種別: 新規作成
- 作業手順:
1. overlay 有効化手順
2. 指標読み方と閾値判定
- 実行コマンド: `rg -n 'Overlay|FPS|frame time|jank|threshold' docs/diagnostics-usage.md`
- 完了条件: 手順記載あり
- 失敗時手順:
1. quickstart 章を追加
- 証跡: `DG-021_*`

### DG-022
- 状態: TODO
- 目的: diagnostics 総合 check 結果記録
- 前提条件: DG-021
- 対象ファイル: `docs/task-results/diagnostics-platform-result.md`
- 変更種別: 新規作成
- 作業手順:
1. diagnostics と platform collectors の check 結果記録
- 実行コマンド: `./gradlew :canimation-diagnostics:check :canimation-platform-android:check :canimation-platform-desktop:check :canimation-platform-ios:check :canimation-platform-web:check`
- 完了条件: 全 check 成功
- 失敗時手順:
1. 失敗 module を該当タスクへ差し戻し
- 証跡: `DG-022_*`

