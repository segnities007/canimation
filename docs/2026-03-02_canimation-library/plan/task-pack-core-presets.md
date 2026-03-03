# Task Pack: core + presets（1ファイル厳守版）

- 文書ID: `CP-TASK-CORE-002`
- 前提文書:
  - `master-plan.md`
  - `toolchain-lock.md`
  - `spec-values.md`
  - `execution-rules.md`

## 実行ルール

- 本ファイルは上から順に実行する
- 各タスクの対象ファイルは必ず1つ
- 失敗時は当該タスクを `BLOCKED` にして再実行手順を適用する

## タスク一覧

### CP-001
- 状態: TODO
- 目的: `canimation-core` を settings に登録
- 前提条件: なし
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-core")` を追加する
- 実行コマンド: `./gradlew projects`
- 完了条件: `:canimation-core` が表示される
- 失敗時手順:
1. include 名のtypoを修正
- 証跡: `CP-001_*`

### CP-002
- 状態: TODO
- 目的: `canimation-core` の build 設定作成
- 前提条件: CP-001
- 対象ファイル: `modules/canimation-core/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. KMP plugin と commonMain/commonTest を定義
2. Compose 依存を追加
- 実行コマンド: `./gradlew :canimation-core:tasks`
- 完了条件: module task が列挙される
- 失敗時手順:
1. plugin と target 設定を修正
- 証跡: `CP-002_*`

### CP-003
- 状態: TODO
- 目的: core package marker を作成
- 前提条件: CP-002
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CoreMarker.kt`
- 変更種別: 新規作成
- 作業手順:
1. package 宣言と最小 marker 型を作る
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. package と階層不一致を修正
- 証跡: `CP-003_*`

### CP-004
- 状態: TODO
- 目的: DurationTokens 実装
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/DurationTokens.kt`
- 変更種別: 新規作成
- 作業手順:
1. `short/medium/long` を `spec-values.md` の値で実装
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. `kotlin.time.Duration` import 修正
- 証跡: `CP-004_*`

### CP-005
- 状態: TODO
- 目的: EasingTokens 実装
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/EasingTokens.kt`
- 変更種別: 新規作成
- 作業手順:
1. `standard/emphasizedDecelerate/emphasizedAccelerate/decelerate/accelerate` を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. easing 型 import 修正
- 証跡: `CP-005_*`

### CP-006
- 状態: TODO
- 目的: DistanceTokens 実装
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/DistanceTokens.kt`
- 変更種別: 新規作成
- 作業手順:
1. `small/medium/large` を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. Dp 型 import 修正
- 証跡: `CP-006_*`

### CP-007
- 状態: TODO
- 目的: SpringTokens 実装
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/SpringTokens.kt`
- 変更種別: 新規作成
- 作業手順:
1. `gentle/snappy` を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. 値域を仕様値に合わせる
- 証跡: `CP-007_*`

### CP-008
- 状態: TODO
- 目的: CanimationTokens 実装
- 前提条件: CP-004, CP-005, CP-006, CP-007
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationTokens.kt`
- 変更種別: 新規作成
- 作業手順:
1. token 4種を集約
2. Default を規範値から生成
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: `CanimationTokens.Default` が参照可能
- 失敗時手順:
1. token import を修正
- 証跡: `CP-008_*`

### CP-009
- 状態: TODO
- 目的: CanimationLevel 実装
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationLevel.kt`
- 変更種別: 新規作成
- 作業手順:
1. `Full/Reduced/Off` enum 定義
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. enum 名を規範に一致させる
- 証跡: `CP-009_*`

### CP-010
- 状態: TODO
- 目的: CanimationPolicy 実装
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationPolicy.kt`
- 変更種別: 新規作成
- 作業手順:
1. `SystemAware/AlwaysFull/AlwaysReduced/AlwaysOff` 定義
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. sealed interface の object 定義を修正
- 証跡: `CP-010_*`

### CP-011
- 状態: TODO
- 目的: PolicyResolver 実装
- 前提条件: CP-009, CP-010
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationPolicyResolver.kt`
- 変更種別: 新規作成
- 作業手順:
1. policy と system preference から level を解決
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: when 網羅で compile 成功
- 失敗時手順:
1. `Off` 分岐漏れを追加
- 証跡: `CP-011_*`

### CP-012
- 状態: TODO
- 目的: CanimationPreset 実装
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationPreset.kt`
- 変更種別: 新規作成
- 作業手順:
1. 5 preset enum 定義
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: 5項目が定義済み
- 失敗時手順:
1. preset 名を規範に一致
- 証跡: `CP-012_*`

### CP-013
- 状態: TODO
- 目的: CanimationPresetSpec 型実装
- 前提条件: CP-013a
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationPresetSpec.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit を表現できる spec 型定義
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: resolver から参照可能
- 失敗時手順:
1. spec 項目を最小化して再定義
- 証跡: `CP-013_*`

### CP-013a
- 状態: TODO
- 目的: CanimationSpec / CanimationRange / CanimationDpRange 型実装（カスタムアニメーション定義用）
- 前提条件: CP-003
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationSpec.kt`
- 変更種別: 新規作成
- 作業手順:
1. `CanimationRange(from: Float, to: Float)` 定義
2. `CanimationDpRange(from: Dp, to: Dp)` 定義
3. `CanimationSpec` に `durationMs/easing/alpha/offsetX/offsetY/scale` フィールド定義（alpha/scale は `CanimationRange?`、offsetX/offsetY は `CanimationDpRange?`）
4. コンストラクタで不正値検証（`durationMs < 0` → `IllegalArgumentException`、`alpha` の `from`/`to` が `0f..1f` 外 → `IllegalArgumentException`）
5. `toReduced()` 関数: offset の from/to を 0.25 倍、duration を `short`、easing を `decelerate` に変換
6. `reversed()` 関数: alpha/offset/scale の `from` と `to` を入れ替え、easing を `accelerate` に置換
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: `CanimationSpec`、`toReduced()`、`reversed()` が参照可能
- 失敗時手順:
1. 不正値検証のエッジケースを修正
- 証跡: `CP-013a_*`

### CP-013b
- 状態: TODO
- 目的: PresetRegistry 実装（core 組み込み版）
- 前提条件: CP-013, CP-012, CP-009
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/PresetRegistry.kt`
- 変更種別: 新規作成
- 作業手順:
1. `AnimationDirection { Enter, Exit }` 列挙型定義
2. `CanimationPreset + CanimationLevel + AnimationDirection → CanimationSpec` の解決テーブル定義
3. `PresetRegistry.Default` に組み込み 5 プリセットの `CanimationPresetSpec`（Enter/Exit × Full/Reduced）を `spec-values.md` から登録
4. `resolve(preset, level, direction)` メソッドで対応する `CanimationSpec` を返却
5. 未登録プリセット参照時は `Fade` にフォールバック
6. ユーザーはプリセットの spec をカスタマイズした `PresetRegistry` インスタンスを `CanimationProvider` に渡せる
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: `PresetRegistry.Default` が 5 プリセットの Enter/Exit を `AnimationDirection` 指定で解決可能
- 失敗時手順:
1. lookup key の不一致を修正
- 証跡: `CP-013b_*`

### CP-014
- 状態: TODO
- 目的: SpecResolver 実装
- 前提条件: CP-013b
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationSpecResolver.kt`
- 変更種別: 新規作成
- 作業手順:
1. `CanimationPreset + CanimationLevel + AnimationDirection` から spec 解決（`PresetRegistry` を使用）
2. Off は `duration=0ms` を返却
3. Enter/Exit 両方の spec を解決可能にする
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: Full/Reduced/Off 全分岐実装
- 失敗時手順:
1. Off 規範値を `spec-values.md` と一致
- 証跡: `CP-014_*`

### CP-015
- 状態: TODO
- 目的: CanimationContext 実装
- 前提条件: CP-014
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationContext.kt`
- 変更種別: 新規作成
- 作業手順:
1. tokens/level/timeSource/presetRegistry を保持
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: context compile 成功
- 失敗時手順:
1. 型循環依存を分離
- 証跡: `CP-015_*`

### CP-016
- 状態: TODO
- 目的: LocalCanimationContext 実装
- 前提条件: CP-015
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/LocalCanimationContext.kt`
- 変更種別: 新規作成
- 作業手順:
1. CompositionLocal と安全デフォルトを定義
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: provider 未指定でも compile/実行可能
- 失敗時手順:
1. default null を排除
- 証跡: `CP-016_*`

### CP-017
- 状態: TODO
- 目的: CanimationProvider 実装
- 前提条件: CP-016
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationProvider.kt`
- 変更種別: 新規作成
- 作業手順:
1. tokens/policy/presetRegistry を受けて context 提供
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: APIシグネチャが規範一致
- 失敗時手順:
1. provider 引数順を仕様通りに修正
- 証跡: `CP-017_*`

### CP-018
- 状態: TODO
- 目的: canimationEnter 実装
- 前提条件: CP-017
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationEnterModifier.kt`
- 変更種別: 新規作成
- 作業手順:
1. `Modifier.canimationEnter(visible, preset)` を実装（preset 版）
2. `Modifier.canimationEnter(visible, fullSpec, reducedSpec)` を実装（custom spec 版）
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: 両オーバーロードが参照可能
- 失敗時手順:
1. extension receiver 型を修正
- 証跡: `CP-018_*`

### CP-018a
- 状態: TODO
- 目的: canimationExit 実装
- 前提条件: CP-017
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationExitModifier.kt`
- 変更種別: 新規作成
- 作業手順:
1. `Modifier.canimationExit(visible, preset)` を実装（preset 版）
2. `Modifier.canimationExit(visible, fullSpec, reducedSpec)` を実装（custom spec 版）
3. Exit spec は `PresetRegistry` から Exit 用 spec を取得、または `CanimationSpec.reversed()` を使用
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: 両オーバーロードが参照可能
- 失敗時手順:
1. exit spec 解決順序を修正
- 証跡: `CP-018a_*`

### CP-018b
- 状態: TODO
- 目的: canimationTransition 実装
- 前提条件: CP-018, CP-018a
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationTransitionModifier.kt`
- 変更種別: 新規作成
- 作業手順:
1. `Modifier.canimationTransition(visible, enterPreset, exitPreset)` を実装（preset 版）
2. `Modifier.canimationTransition(visible, enterFullSpec, enterReducedSpec, exitFullSpec, exitReducedSpec)` を実装（custom spec 版）
3. 内部的に Enter/Exit modifier を組み合わせて実装
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: 両オーバーロードが参照可能
- 失敗時手順:
1. Enter/Exit の適用タイミング制御を修正
- 証跡: `CP-018b_*`

### CP-019
- 状態: TODO
- 目的: canimationEmphasize 実装
- 前提条件: CP-018
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationEmphasizeModifier.kt`
- 変更種別: 新規作成
- 作業手順:
1. `Modifier.canimationEmphasize` を実装
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: 公開APIが参照可能
- 失敗時手順:
1. active 分岐を実装
- 証跡: `CP-019_*`

### CP-020
- 状態: TODO
- 目的: CanimationVisibility 実装
- 前提条件: CP-018b
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationVisibility.kt`
- 変更種別: 新規作成
- 作業手順:
1. `visible/enterPreset/exitPreset/content` API を実装（preset 版）
2. `visible/enterFullSpec/enterReducedSpec/exitFullSpec/exitReducedSpec/content` を実装（custom spec 版）
3. 内部的に `canimationTransition` を使用する
- 実行コマンド: `./gradlew :canimation-core:compileKotlinMetadata`
- 完了条件: 両オーバーロードのAPIシグネチャが規範一致
- 失敗時手順:
1. composable 引数順を仕様に合わせる
- 証跡: `CP-020_*`

### CP-021
- 状態: TODO
- 目的: TokensDefault test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationTokensDefaultTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Default 値が `spec-values.md` と一致することを検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationTokensDefaultTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. spec 値に合わせて実装修正
- 証跡: `CP-021_*`

### CP-022
- 状態: TODO
- 目的: PolicyResolver test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationPolicyResolverTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. policy x preference の全組み合わせを検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationPolicyResolverTest'`
- 完了条件: 全ケース成功
- 失敗時手順:
1. when 分岐不足を補完
- 証跡: `CP-022_*`

### CP-023
- 状態: TODO
- 目的: SpecResolver test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationSpecResolverTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. 5 preset x 3 level x 2 direction(Enter/Exit) = 30 ケースを検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationSpecResolverTest'`
- 完了条件: 30ケース成功
- 失敗時手順:
1. spec mapping を修正
- 証跡: `CP-023_*`

### CP-024
- 状態: TODO
- 目的: Enter modifier integration test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationEnterIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. visible false->true の時系列検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationEnterIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. transition spec 解決順序を修正
- 証跡: `CP-024_*`

### CP-024a
- 状態: TODO
- 目的: Exit modifier integration test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationExitIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. visible true->false の時系列検証（alpha 1f→0f、offset 反転）
2. Exit 完了後の状態検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationExitIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. exit spec の反転ロジックを修正
- 証跡: `CP-024a_*`

### CP-024b
- 状態: TODO
- 目的: Transition modifier integration test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationTransitionIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. visible false→true→false の往復遷移検証
2. enterPreset と exitPreset が異なるケースの検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationTransitionIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. Enter/Exit 切替タイミングを修正
- 証跡: `CP-024b_*`

### CP-024c
- 状態: TODO
- 目的: CanimationSpec test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationSpecTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. `toReduced()` 変換が `spec-values.md` 8.1章の規則に従うことを検証（`CanimationDpRange` の from/to 縮小、`CanimationRange` の圧縮）
2. `reversed()` 変換が `spec-values.md` 8.2章の規則に従うことを検証（from/to 入替、easing → accelerate）
3. 不正値（`durationMs < 0`、`alpha` の from/to が `0f..1f` 外）で `IllegalArgumentException` が投げられることを検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationSpecTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. 変換ロジックまたは検証ロジックを修正
- 証跡: `CP-024c_*`

### CP-024d
- 状態: TODO
- 目的: PresetRegistry test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/PresetRegistryTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Default registry が 5 プリセットの Enter/Exit を正しく解決することを検証
2. 未登録プリセット参照時に Fade へフォールバックすることを検証
3. ユーザー拡張登録後に新プリセットが解決可能であることを検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*PresetRegistryTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. registry の lookup/fallback ロジックを修正
- 証跡: `CP-024d_*`

### CP-025
- 状態: TODO
- 目的: Emphasize modifier integration test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationEmphasizeIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. active true/false 切替検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationEmphasizeIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. active 状態遷移を修正
- 証跡: `CP-025_*`

### CP-026
- 状態: TODO
- 目的: Visibility integration test
- 前提条件: CP-020
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CanimationVisibilityIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. visible 切替時の enter/exit を検証
2. enterPreset ≠ exitPreset のケースを検証
3. custom spec 版の CanimationVisibility を検証
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CanimationVisibilityIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. visibility 判定分岐を修正
- 証跡: `CP-026_*`

### CP-027
- 状態: TODO
- 目的: Presets module include
- 前提条件: CP-020
- 対象ファイル: `settings.gradle.kts`
- 変更種別: 更新
- 作業手順:
1. `include(":canimation-presets")` 追加
- 実行コマンド: `./gradlew projects`
- 完了条件: `:canimation-presets` 表示
- 失敗時手順:
1. include typo 修正
- 証跡: `CP-027_*`

### CP-028
- 状態: TODO
- 目的: presets build 設定
- 前提条件: CP-027
- 対象ファイル: `modules/canimation-presets/build.gradle.kts`
- 変更種別: 新規作成
- 作業手順:
1. KMP と `canimation-core` 依存を追加
- 実行コマンド: `./gradlew :canimation-presets:tasks`
- 完了条件: task 列挙成功
- 失敗時手順:
1. dependency path 修正
- 証跡: `CP-028_*`

### CP-029
- 状態: TODO
- 目的: FadeUpPreset 実装
- 前提条件: CP-028
- 対象ファイル: `modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/FadeUpPreset.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-presets:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. spec 値の一致確認
- 証跡: `CP-029_*`

### CP-030
- 状態: TODO
- 目的: FadePreset 実装
- 前提条件: CP-028
- 対象ファイル: `modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/FadePreset.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-presets:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. alpha range を修正
- 証跡: `CP-030_*`

### CP-031
- 状態: TODO
- 目的: ScaleInPreset 実装
- 前提条件: CP-028
- 対象ファイル: `modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/ScaleInPreset.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-presets:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. scale 範囲を修正
- 証跡: `CP-031_*`

### CP-032
- 状態: TODO
- 目的: SlideLeftPreset 実装
- 前提条件: CP-028
- 対象ファイル: `modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/SlideLeftPreset.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-presets:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. offset 符号を修正
- 証跡: `CP-032_*`

### CP-033
- 状態: TODO
- 目的: SlideRightPreset 実装
- 前提条件: CP-028
- 対象ファイル: `modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/SlideRightPreset.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit を `spec-values.md` で実装
- 実行コマンド: `./gradlew :canimation-presets:compileKotlinMetadata`
- 完了条件: compile 成功
- 失敗時手順:
1. offset 符号を修正
- 証跡: `CP-033_*`

### CP-034
- 状態: TODO
- 目的: PresetsExtensionRegistry 実装
- 前提条件: CP-029, CP-030, CP-031, CP-032, CP-033
- 対象ファイル: `modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/PresetsExtensionRegistry.kt`
- 変更種別: 新規作成
- 作業手順:
1. 追加プリセット登録用の拡張 registry 実装
2. core の `PresetRegistry.Default` に presets モジュールの追加プリセットを合成する関数を提供
3. 未定義 fallback は core の `PresetRegistry.Default` に委譲
- 実行コマンド: `./gradlew :canimation-presets:compileKotlinMetadata`
- 完了条件: fallback 実装済み
- 失敗時手順:
1. default 分岐追加
- 証跡: `CP-034_*`

### CP-035
- 状態: TODO
- 目的: PresetsExtensionRegistry test
- 前提条件: CP-034
- 対象ファイル: `modules/canimation-presets/src/commonTest/kotlin/io/github/canimation/presets/PresetsExtensionRegistryTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. 追加プリセット登録 + Enter/Exit 解決検証
2. core default への委譲検証
- 実行コマンド: `./gradlew :canimation-presets:allTests --tests '*PresetsExtensionRegistryTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. key mapping を修正
- 証跡: `CP-035_*`

### CP-036
- 状態: TODO
- 目的: FadeUpPreset test
- 前提条件: CP-029
- 対象ファイル: `modules/canimation-presets/src/commonTest/kotlin/io/github/canimation/presets/FadeUpPresetTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit の規範値一致を検証
- 実行コマンド: `./gradlew :canimation-presets:allTests --tests '*FadeUpPresetTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. spec-values との差分修正
- 証跡: `CP-036_*`

### CP-037
- 状態: TODO
- 目的: FadePreset test
- 前提条件: CP-030
- 対象ファイル: `modules/canimation-presets/src/commonTest/kotlin/io/github/canimation/presets/FadePresetTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit 規範値検証
- 実行コマンド: `./gradlew :canimation-presets:allTests --tests '*FadePresetTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. alpha/duration 値修正
- 証跡: `CP-037_*`

### CP-038
- 状態: TODO
- 目的: ScaleInPreset test
- 前提条件: CP-031
- 対象ファイル: `modules/canimation-presets/src/commonTest/kotlin/io/github/canimation/presets/ScaleInPresetTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit 規範値検証
- 実行コマンド: `./gradlew :canimation-presets:allTests --tests '*ScaleInPresetTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. scale 値修正
- 証跡: `CP-038_*`

### CP-039
- 状態: TODO
- 目的: SlideLeftPreset test
- 前提条件: CP-032
- 対象ファイル: `modules/canimation-presets/src/commonTest/kotlin/io/github/canimation/presets/SlideLeftPresetTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit 規範値検証
- 実行コマンド: `./gradlew :canimation-presets:allTests --tests '*SlideLeftPresetTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. offset 値修正
- 証跡: `CP-039_*`

### CP-040
- 状態: TODO
- 目的: SlideRightPreset test
- 前提条件: CP-033
- 対象ファイル: `modules/canimation-presets/src/commonTest/kotlin/io/github/canimation/presets/SlideRightPresetTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Full Enter/Exit、Reduced Enter/Exit 規範値検証
- 実行コマンド: `./gradlew :canimation-presets:allTests --tests '*SlideRightPresetTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. offset 値修正
- 証跡: `CP-040_*`

### CP-041
- 状態: TODO
- 目的: core API KDoc 整備
- 前提条件: CP-040
- 対象ファイル: `modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/README_API_KDOC.md`
- 変更種別: 新規作成
- 作業手順:
1. KDoc 要件チェックリストを作成
- 実行コマンド: `rg -n 'CanimationProvider|CanimationVisibility|canimationEnter' modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/README_API_KDOC.md`
- 完了条件: 主要APIが記載
- 失敗時手順:
1. API一覧を追加
- 証跡: `CP-041_*`

### CP-042
- 状態: TODO
- 目的: core smoke integration test
- 前提条件: CP-041
- 対象ファイル: `modules/canimation-core/src/commonTest/kotlin/io/github/canimation/core/CoreSmokeIntegrationTest.kt`
- 変更種別: 新規作成
- 作業手順:
1. Provider + Preset + Modifier 接続の最小テスト
- 実行コマンド: `./gradlew :canimation-core:allTests --tests '*CoreSmokeIntegrationTest'`
- 完了条件: テスト成功
- 失敗時手順:
1. context 注入順を修正
- 証跡: `CP-042_*`

### CP-043
- 状態: TODO
- 目的: core/presets 総合 check
- 前提条件: CP-042
- 対象ファイル: `docs/task-results/core-presets-result.md`
- 変更種別: 新規作成
- 作業手順:
1. `:canimation-core:check` 実行結果を記録
2. `:canimation-presets:check` 実行結果を記録
- 実行コマンド: `./gradlew :canimation-core:check :canimation-presets:check`
- 完了条件: 両 check 成功
- 失敗時手順:
1. 失敗タスクIDを特定し差し戻し
- 証跡: `CP-043_*`

### CP-044
- 状態: TODO
- 目的: task-pack 完了確認
- 前提条件: CP-043
- 対象ファイル: `docs/task-results/core-presets-signoff.md`
- 変更種別: 新規作成
- 作業手順:
1. 全タスクの DONE 状態を記録
2. 未解決事項0件を確認
- 実行コマンド: `rg -n '^### CP-' plan/task-pack-core-presets.md | wc -l`
- 完了条件: タスク一覧の完了確認記録がある
- 失敗時手順:
1. 未完タスクを再オープン
- 証跡: `CP-044_*`
