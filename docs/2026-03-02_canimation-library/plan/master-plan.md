# canimation マスタープラン（厳格再現版）

- 文書ID: `CP-MASTER-001`
- 版: `4.0`
- 目的: 実装者が誰でも同一成果物に到達できる計画基準を定義する

## 1. 固定文書

以下の文書は同時に有効であり、矛盾時の優先順位は番号順。

1. `master-plan.md`
2. `toolchain-lock.md`
3. `spec-values.md`
4. `execution-rules.md`
5. `task-pack-*.md`

## 2. スコープ固定

- モジュール:
  - `canimation-core`
  - `canimation-presets`
  - `canimation-a11y`
  - `canimation-diagnostics`
  - `canimation-test`
  - `canimation-platform-android`
  - `canimation-platform-desktop`
  - `canimation-platform-ios`
  - `canimation-platform-web`
- サンプル:
  - `sample-chat`
  - `sample-commerce`

## 3. API固定

- `CanimationProvider`（`presetRegistry` パラメータ含む）
- `CanimationTokens`
- `DurationTokens`
- `EasingTokens`
- `DistanceTokens`
- `SpringTokens`
- `CanimationPolicy`
- `CanimationLevel`
- `CanimationPreset`
- `AnimationDirection`
- `CanimationRange`
- `CanimationDpRange`
- `CanimationSpec`
- `CanimationSpec.toReduced()`
- `CanimationSpec.reversed()`
- `CanimationPresetSpec`
- `PresetRegistry`
- `Modifier.canimationEnter`
- `Modifier.canimationExit`
- `Modifier.canimationTransition`
- `Modifier.canimationEmphasize`
- `CanimationVisibility`（`enterPreset` + `exitPreset` パラメータ）
- `CanimationDiagnosticsOverlay`
- `CanimationTestClock`
- `CanimationTestHost`

## 4. 非交渉要件

- 数値仕様は `spec-values.md` を唯一の規範とする
- ツールチェーンは `toolchain-lock.md` を逸脱しない
- すべての task は 1ファイル変更で実行する
- 対象ファイル欄での曖昧パス（`*`, `...`）を禁止する
- 未定義コマンドを禁止する

## 5. 品質基準

- 行カバレッジ >= 80%
- 分岐カバレッジ >= 70%
- Full/Reduced/Off 試験合格率 = 100%
- p95 frame time 劣化 <= 10%
- フレーク率 < 2%
- AI証跡保存率 = 100%

## 6. Quality Gate 定義

## Gate-G1: Toolchain Lock

- `toolchain-lock.md` に記載の固定ファイルが更新済み
- `docs/toolchain-verification.md` に検証ログが保存済み
- 関連タスク: `TS-100` `TS-101` `TS-102` `TS-103`

## Gate-G2: API/Spec Compliance

- 公開APIが 3章 の固定一覧と一致
- Token/Preset 数値が `spec-values.md` と一致
- Exit spec が `spec-values.md` 2章の Enter/Exit 規範と一致
- `CanimationSpec` が `spec-values.md` 8章の規範に準拠
- 関連タスク: `CP-001`〜`CP-044`（`CP-013a` `CP-013b` `CP-018a` `CP-018b` `CP-024a`〜`CP-024d` 含む）、`AP-001`〜`AP-032`、`DG-001`〜`DG-022`

## Gate-G3: Build/Test

- `./gradlew check` 成功
- 行カバレッジ >= 80%、分岐 >= 70%
- フレーク率 < 2%
- 関連タスク: `CP-043` `AP-032` `DG-022` `TS-022` `TS-023` `TS-030` `TS-032` `TS-033`

## Gate-G4: Performance

- `spec-values.md` のベンチマークプロファイルで測定
- `p95(frameTime_with_library) <= p95(baseline) * 1.10`
- 関連タスク: `TS-021` `TS-024`

## Gate-G5: Release Readiness

- `publishToMavenLocal` 成功
- 署名タスク成功
- README クイックスタート検証成功
- リリース認証情報（maven/signing）が workflow と publish 実行で検証済み
- 関連タスク: `TS-027` `RO-003` `RO-004` `RO-006` `RO-009` `RO-014`

## Gate-G6: Traceability

- 対象タスクすべてに証跡 3点（prompt/output/review）が存在
- 関連タスク: `TS-104` `TS-028` `TS-033` `RO-021`

## 7. 完了判定

以下を全て満たした場合のみ完了。

1. 全 task-pack の全タスクが `DONE`
2. Gate-G1〜Gate-G6 が合格
3. 公開アーティファクト作成成功
4. README 手順の第三者再現成功
5. AI証跡監査合格

## 8. 変更管理

- API、仕様値、ツールチェーン、タスク順の変更は CR 必須
- CR 必須項目:
  - 変更対象
  - 理由
  - 影響範囲
  - テスト影響
  - ロールバック方法
