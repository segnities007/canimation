# Task Pack: a11y + platform（1ファイル厳守版）

- 文書ID: `CP-TASK-A11Y-002`
- 前提:
  - `task-pack-core-presets.md` 完了
  - `spec-values.md` 参照済み

## 実行ルール

- 上から順に実行
- 1タスク1ファイル
- 対象ファイル欄でのワイルドカード/省略パス禁止

## タスク一覧

### AP-001
- 状態: 未着手
- 目的: `canimation-a11y` module include
- 前提条件: なし
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-a11y")` を追加
- 実行コマンド: `./gradlew projects`
- 完了条件: `:canimation-a11y` 表示
- 失敗時手順:
1. include 名修正
- 証跡: `AP-001_*`

### AP-002
- 状態: 未着手
- 目的: a11y build 設定
- 前提条件: AP-001
- 対象ファイル: `modules/canimation-a11y/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. KMP と core 依存設定
- 実行コマンド: `./gradlew :canimation-a11y:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. 依存パス修正
- 証跡: `AP-002_*`

### AP-003
- 状態: 未着手
- 目的: a11y package marker
- 前提条件: AP-002
- 対象ファイル: `modules/canimation-a11y/src/commonMain/kotlin/io/github/canimation/a11y/A11yMarker.kt`
- 変更種別: 新規作成
- 作業手順:
1. marker 型作成
- 実行コマンド: `./gradlew :canimation-a11y:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. package 修正
- 証跡: `AP-003_*`

### AP-004
- 状態: 未着手
- 目的: SystemMotionPreference 定義
- 前提条件: AP-003
- 対象ファイル: `modules/canimation-a11y/src/commonMain/kotlin/io/github/canimation/a11y/SystemMotionPreference.kt`
- 変更種別: 新規作成
- 作業手順:
1. system motion preference 型定義
- 実行コマンド: `./gradlew :canimation-a11y:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. level 責務分離
- 証跡: `AP-004_*`

### AP-005
- 状態: 未着手
- 目的: MotionPreferenceDataSource 契約
- 前提条件: AP-004
- 対象ファイル: `modules/canimation-a11y/src/commonMain/kotlin/io/github/canimation/a11y/MotionPreferenceDataSource.kt`
- 変更種別: 新規作成
- 作業手順:
1. 現在値取得 API 定義
2. 変更監視 API 定義
- 実行コマンド: `./gradlew :canimation-a11y:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. API を同期/非同期で簡素化
- 証跡: `AP-005_*`

### AP-006
- 状態: 未着手
- 目的: A11yMotionPolicyAdapter 実装
- 前提条件: AP-005
- 対象ファイル: `modules/canimation-a11y/src/commonMain/kotlin/io/github/canimation/a11y/A11yMotionPolicyAdapter.kt`
- 変更種別: 新規作成
- 作業手順:
1. preference から level 変換実装
- 実行コマンド: `./gradlew :canimation-a11y:compileKotlinMetadata`
- 完了条件: SystemAware 経路実装済み
- 失敗時手順:
1. resolver 連携ロジック修正
- 証跡: `AP-006_*`

### AP-007
- 状態: 未着手
- 目的: A11y adapter test
- 前提条件: AP-006
- 対象ファイル: `modules/canimation-a11y/src/commonTest/kotlin/io/github/canimation/a11y/A11yMotionPolicyAdapterTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. preference->level マッピング表テスト
- 実行コマンド: `./gradlew :canimation-a11y:allTests --tests '*A11yMotionPolicyAdapterTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. mapping 表修正
- 証跡: `AP-007_*`

### AP-008
- 状態: 未着手
- 目的: Android platform include
- 前提条件: AP-007
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-platform-android")` 追加
- 実行コマンド: `./gradlew projects`
- 完了条件: module 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `AP-008_*`

### AP-009
- 状態: 未着手
- 目的: Android platform build
- 前提条件: AP-008
- 対象ファイル: `modules/canimation-platform-android/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. android target と core/a11y 依存設定
- 実行コマンド: `./gradlew :canimation-platform-android:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. AGP 設定修正
- 証跡: `AP-009_*`

### AP-010
- 状態: 未着手
- 目的: Android motion preference 実装
- 前提条件: AP-009
- 対象ファイル: `modules/canimation-platform-android/src/androidMain/kotlin/io/github/canimation/platform/android/AndroidMotionPreferenceDataSource.kt`
- 変更種別: 新規作成
- 作業手順:
1. OS設定から reduced motion 取得実装
- 実行コマンド: `./gradlew :canimation-platform-android:compileDebugKotlinAndroid`
- 完了条件: compile 成功
- 失敗時手順:
1. API レベル差異吸収
- 証跡: `AP-010_*`

### AP-011
- 状態: 未着手
- 目的: Android preference test
- 前提条件: AP-010
- 対象ファイル: `modules/canimation-platform-android/src/androidUnitTest/kotlin/io/github/canimation/platform/android/AndroidMotionPreferenceDataSourceTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. ON/OFF モック検証
- 実行コマンド: `./gradlew :canimation-platform-android:testDebugUnitTest --tests '*AndroidMotionPreferenceDataSourceTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. OS 依存分離を強化
- 証跡: `AP-011_*`

### AP-012
- 状態: 未着手
- 目的: Desktop platform include
- 前提条件: AP-007
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-platform-desktop")` 追加
- 実行コマンド: `./gradlew projects`
- 完了条件: module 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `AP-012_*`

### AP-013
- 状態: 未着手
- 目的: Desktop platform build
- 前提条件: AP-012
- 対象ファイル: `modules/canimation-platform-desktop/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. desktop target と core/a11y 依存設定
- 実行コマンド: `./gradlew :canimation-platform-desktop:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. desktop target 設定修正
- 証跡: `AP-013_*`

### AP-014
- 状態: 未着手
- 目的: Desktop motion preference 実装
- 前提条件: AP-013
- 対象ファイル: `modules/canimation-platform-desktop/src/desktopMain/kotlin/io/github/canimation/platform/desktop/DesktopMotionPreferenceDataSource.kt`
- 変更種別: 新規作成
- 作業手順:
1. desktop 設定から preference 取得
- 実行コマンド: `./gradlew :canimation-platform-desktop:compileKotlinDesktop`
- 完了条件: compile 成功
- 失敗時手順:
1. fallback を Reduced 固定で実装
- 証跡: `AP-014_*`

### AP-015
- 状態: 未着手
- 目的: Desktop preference test
- 前提条件: AP-014
- 対象ファイル: `modules/canimation-platform-desktop/src/desktopTest/kotlin/io/github/canimation/platform/desktop/DesktopMotionPreferenceDataSourceTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. 設定変更反映テスト
- 実行コマンド: `./gradlew :canimation-platform-desktop:desktopTest --tests '*DesktopMotionPreferenceDataSourceTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. change notification 実装修正
- 証跡: `AP-015_*`

### AP-016
- 状態: 未着手
- 目的: iOS platform include
- 前提条件: AP-007
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-platform-ios")` 追加
- 実行コマンド: `./gradlew projects`
- 完了条件: module 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `AP-016_*`

### AP-017
- 状態: 未着手
- 目的: iOS platform build
- 前提条件: AP-016
- 対象ファイル: `modules/canimation-platform-ios/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. ios targets と core/a11y 依存設定
- 実行コマンド: `./gradlew :canimation-platform-ios:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. target 設定修正
- 証跡: `AP-017_*`

### AP-018
- 状態: 未着手
- 目的: iOS motion preference 実装
- 前提条件: AP-017
- 対象ファイル: `modules/canimation-platform-ios/src/iosMain/kotlin/io/github/canimation/platform/ios/IosMotionPreferenceDataSource.kt`
- 変更種別: 新規作成
- 作業手順:
1. iOS reduced motion 取得実装
- 実行コマンド: `./gradlew :canimation-platform-ios:compileKotlinIosX64`
- 完了条件: compile 成功
- 失敗時手順:
1. iOS API 呼び出しを wrapper 化
- 証跡: `AP-018_*`

### AP-019
- 状態: 未着手
- 目的: iOS preference test
- 前提条件: AP-018
- 対象ファイル: `modules/canimation-platform-ios/src/iosTest/kotlin/io/github/canimation/platform/ios/IosMotionPreferenceDataSourceTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. mock source で mapping 検証
- 実行コマンド: `./gradlew :canimation-platform-ios:iosX64Test --tests '*IosMotionPreferenceDataSourceTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. contract 実装を簡素化
- 証跡: `AP-019_*`

### AP-020
- 状態: 未着手
- 目的: Web platform include
- 前提条件: AP-007
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-platform-web")` 追加
- 実行コマンド: `./gradlew projects`
- 完了条件: module 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `AP-020_*`

### AP-021
- 状態: 未着手
- 目的: Web platform build
- 前提条件: AP-020
- 対象ファイル: `modules/canimation-platform-web/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. js target と core/a11y 依存設定
- 実行コマンド: `./gradlew :canimation-platform-web:tasks`
- 完了条件: tasks 表示
- 失敗時手順:
1. js target 設定修正
- 証跡: `AP-021_*`

### AP-022
- 状態: 未着手
- 目的: Web motion preference 実装
- 前提条件: AP-021
- 対象ファイル: `modules/canimation-platform-web/src/jsMain/kotlin/io/github/canimation/platform/web/WebMotionPreferenceDataSource.kt`
- 変更種別: 新規作成
- 作業手順:
1. `prefers-reduced-motion` を監視
- 実行コマンド: `./gradlew :canimation-platform-web:compileKotlinJs`
- 完了条件: compile 成功
- 失敗時手順:
1. browser interop を wrapper 化
- 証跡: `AP-022_*`

### AP-023
- 状態: 未着手
- 目的: Web preference test
- 前提条件: AP-022
- 対象ファイル: `modules/canimation-platform-web/src/jsTest/kotlin/io/github/canimation/platform/web/WebMotionPreferenceDataSourceTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. media query mock 検証
- 実行コマンド: `./gradlew :canimation-platform-web:jsTest --tests '*WebMotionPreferenceDataSourceTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. listener 実装修正
- 証跡: `AP-023_*`

### AP-024
- 状態: 未着手
- 目的: Android no-op fallback 実装
- 前提条件: AP-011
- 対象ファイル: `modules/canimation-platform-android/src/androidMain/kotlin/io/github/canimation/platform/android/AndroidMotionPreferenceFallback.kt`
- 変更種別: 新規作成
- 作業手順:
1. 取得失敗時 Reduced 返却
- 実行コマンド: `./gradlew :canimation-platform-android:compileDebugKotlinAndroid`
- 完了条件: compile 成功
- 失敗時手順:
1. fallback 経路追加
- 証跡: `AP-024_*`

### AP-025
- 状態: 未着手
- 目的: Desktop no-op fallback 実装
- 前提条件: AP-015
- 対象ファイル: `modules/canimation-platform-desktop/src/desktopMain/kotlin/io/github/canimation/platform/desktop/DesktopMotionPreferenceFallback.kt`
- 変更種別: 新規作成
- 作業手順:
1. 取得失敗時 Reduced 返却
- 実行コマンド: `./gradlew :canimation-platform-desktop:compileKotlinDesktop`
- 完了条件: compile 成功
- 失敗時手順:
1. fallback 経路追加
- 証跡: `AP-025_*`

### AP-026
- 状態: 未着手
- 目的: iOS no-op fallback 実装
- 前提条件: AP-019
- 対象ファイル: `modules/canimation-platform-ios/src/iosMain/kotlin/io/github/canimation/platform/ios/IosMotionPreferenceFallback.kt`
- 変更種別: 新規作成
- 作業手順:
1. 取得失敗時 Reduced 返却
- 実行コマンド: `./gradlew :canimation-platform-ios:compileKotlinIosX64`
- 完了条件: compile 成功
- 失敗時手順:
1. fallback 経路追加
- 証跡: `AP-026_*`

### AP-027
- 状態: 未着手
- 目的: Web no-op fallback 実装
- 前提条件: AP-023
- 対象ファイル: `modules/canimation-platform-web/src/jsMain/kotlin/io/github/canimation/platform/web/WebMotionPreferenceFallback.kt`
- 変更種別: 新規作成
- 作業手順:
1. 取得失敗時 Reduced 返却
- 実行コマンド: `./gradlew :canimation-platform-web:compileKotlinJs`
- 完了条件: compile 成功
- 失敗時手順:
1. fallback 経路追加
- 証跡: `AP-027_*`

### AP-028
- 状態: 未着手
- 目的: SystemAware integration test
- 前提条件: AP-027
- 対象ファイル: `modules/canimation-a11y/src/commonTest/kotlin/io/github/canimation/a11y/SystemAwareIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. mock source で Full/Reduced/Off 遷移検証
- 実行コマンド: `./gradlew :canimation-a11y:allTests --tests '*SystemAwareIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. adapter mapping 修正
- 証跡: `AP-028_*`

### AP-029
- 状態: 未着手
- 目的: a11y API KDoc 指針作成
- 前提条件: AP-028
- 対象ファイル: `modules/canimation-a11y/src/commonMain/kotlin/io/github/canimation/a11y/README_API_KDOC.md`
- 変更種別: 新規作成
- 作業手順:
1. 公開 API の KDoc 要件整理
- 実行コマンド: `rg -n 'SystemAware|Reduced|fallback' modules/canimation-a11y/src/commonMain/kotlin/io/github/canimation/a11y/README_API_KDOC.md`
- 完了条件: 要件記載あり
- 失敗時手順:
1. 要件項目追加
- 証跡: `AP-029_*`

### AP-030
- 状態: 未着手
- 目的: Tier1 A11y 手動検証書
- 前提条件: AP-029
- 対象ファイル: `docs/a11y-tier1-validation.md`
- 変更種別: 新規作成
- 作業手順:
1. Android/Desktop の検証シナリオ記述
- 実行コマンド: `rg -n 'Android|Desktop|Full|Reduced|Off' docs/a11y-tier1-validation.md`
- 完了条件: 2平台x3レベルを記載
- 失敗時手順:
1. シナリオを補完
- 証跡: `AP-030_*`

### AP-031
- 状態: 未着手
- 目的: Tier2 互換検証書
- 前提条件: AP-030
- 対象ファイル: `docs/a11y-tier2-compatibility.md`
- 変更種別: 新規作成
- 作業手順:
1. iOS/Web compile + SystemAware チェック手順記載
- 実行コマンド: `rg -n 'iOS|Web|compile|SystemAware' docs/a11y-tier2-compatibility.md`
- 完了条件: 2平台の手順記載
- 失敗時手順:
1. 手順を追加
- 証跡: `AP-031_*`

### AP-032
- 状態: 未着手
- 目的: a11y 総合 check 結果記録
- 前提条件: AP-031
- 対象ファイル: `docs/task-results/a11y-platform-result.md`
- 変更種別: 新規作成
- 作業手順:
1. 関連 modules の check 結果記録
- 実行コマンド: `./gradlew :canimation-a11y:check :canimation-platform-android:check :canimation-platform-desktop:check :canimation-platform-ios:check :canimation-platform-web:check`
- 完了条件: 全 check 成功
- 失敗時手順:
1. 失敗 module を該当タスクへ差し戻し
- 証跡: `AP-032_*`
