# canimation 固定仕様値（実装規範）

- 文書ID: `CP-SPEC-001`
- 目的: 実装者ごとの差異をゼロにするため、数値仕様を固定する
- 変更ルール: 値変更は CR 必須

## 1. Token 規範値

## 1.1 DurationTokens

- `short = 120ms`
- `medium = 220ms`
- `long = 360ms`

## 1.2 EasingTokens

- `standard = CubicBezier(0.2, 0.0, 0.0, 1.0)`
- `emphasizedDecelerate = CubicBezier(0.05, 0.7, 0.1, 1.0)`
- `emphasizedAccelerate = CubicBezier(0.3, 0.0, 0.8, 0.15)`
- `decelerate = CubicBezier(0.0, 0.0, 0.0, 1.0)`
- `accelerate = CubicBezier(0.3, 0.0, 1.0, 1.0)`

注: Material Design 3 Motion 準拠。emphasized は加速区間と減速区間で分割カーブを使用する。
Enter 時は `emphasizedDecelerate`、Exit 時は `emphasizedAccelerate` を使い分ける。

## 1.3 DistanceTokens

- `small = 8.dp`
- `medium = 16.dp`
- `large = 24.dp`

## 1.4 SpringTokens

- `gentle = (dampingRatio=0.85, stiffness=280f)`
- `snappy = (dampingRatio=0.80, stiffness=420f)`

## 2. Preset 規範値

## 2.1 FadeUp

- Full Enter:
  - `duration=220ms`
  - `offsetY=16.dp -> 0.dp`
  - `alpha=0f -> 1f`
  - `easing=standard`
- Full Exit:
  - `duration=220ms`
  - `offsetY=0.dp -> 16.dp`
  - `alpha=1f -> 0f`
  - `easing=accelerate`
- Reduced Enter:
  - `duration=120ms`
  - `offsetY=4.dp -> 0.dp`
  - `alpha=0f -> 1f`
  - `easing=decelerate`
- Reduced Exit:
  - `duration=120ms`
  - `offsetY=0.dp -> 4.dp`
  - `alpha=1f -> 0f`
  - `easing=accelerate`

## 2.2 Fade

- Full Enter:
  - `duration=220ms`
  - `alpha=0f -> 1f`
  - `easing=standard`
- Full Exit:
  - `duration=220ms`
  - `alpha=1f -> 0f`
  - `easing=accelerate`
- Reduced Enter:
  - `duration=120ms`
  - `alpha=0f -> 1f`
  - `easing=decelerate`
- Reduced Exit:
  - `duration=120ms`
  - `alpha=1f -> 0f`
  - `easing=accelerate`

## 2.3 ScaleIn

- Full Enter:
  - `duration=220ms`
  - `scale=0.92f -> 1.0f`
  - `alpha=0f -> 1f`
  - `easing=standard`
- Full Exit:
  - `duration=220ms`
  - `scale=1.0f -> 0.92f`
  - `alpha=1f -> 0f`
  - `easing=accelerate`
- Reduced Enter:
  - `duration=120ms`
  - `scale=0.98f -> 1.0f`
  - `alpha=0f -> 1f`
  - `easing=decelerate`
- Reduced Exit:
  - `duration=120ms`
  - `scale=1.0f -> 0.98f`
  - `alpha=1f -> 0f`
  - `easing=accelerate`

## 2.4 SlideLeft

- Full Enter:
  - `duration=220ms`
  - `offsetX=16.dp -> 0.dp`
  - `alpha=0f -> 1f`
  - `easing=standard`
- Full Exit:
  - `duration=220ms`
  - `offsetX=0.dp -> 16.dp`
  - `alpha=1f -> 0f`
  - `easing=accelerate`
- Reduced Enter:
  - `duration=120ms`
  - `offsetX=4.dp -> 0.dp`
  - `alpha=0f -> 1f`
  - `easing=decelerate`
- Reduced Exit:
  - `duration=120ms`
  - `offsetX=0.dp -> 4.dp`
  - `alpha=1f -> 0f`
  - `easing=accelerate`

## 2.5 SlideRight

- Full Enter:
  - `duration=220ms`
  - `offsetX=-16.dp -> 0.dp`
  - `alpha=0f -> 1f`
  - `easing=standard`
- Full Exit:
  - `duration=220ms`
  - `offsetX=0.dp -> -16.dp`
  - `alpha=1f -> 0f`
  - `easing=accelerate`
- Reduced Enter:
  - `duration=120ms`
  - `offsetX=-4.dp -> 0.dp`
  - `alpha=0f -> 1f`
  - `easing=decelerate`
- Reduced Exit:
  - `duration=120ms`
  - `offsetX=0.dp -> -4.dp`
  - `alpha=1f -> 0f`
  - `easing=accelerate`

## 3. CanimationLevel 規範

- `Full`: Full spec を使用
- `Reduced`: Reduced spec を使用
- `Off`: すべて `snap`（duration=0ms）

## 4. Diagnostics 規範

- `jankThresholdMs default = 16`
- `warningThreshold p95 = 18ms`
- `criticalThreshold p95 = 22ms`

## 5. Performance 判定規範

- ベースライン計測は diagnostics off
- 比較計測は diagnostics on
- 合格条件:
  - `p95(frameTime_with_library) <= p95(baseline) * 1.10`

## 6. ベンチマークプロファイル固定

- シナリオA（sample-chat）:
  - 300メッセージの初期描画
  - 50件連続追加
  - `CanimationLevel=Full`
- シナリオB（sample-commerce）:
  - 商品カード 120件の初期描画
  - 強調切替 200回
  - `CanimationLevel=Full`
- 計測窓:
  - warmup: 10秒
  - measure: 60秒
- 収集項目:
  - frame time 全サンプル
  - p95 frame time
  - jank count

## 7. 判定対象環境固定

- Tier1 計測端末:
  - Android: Pixel 8 相当クラス
  - Desktop: 8コアCPU / 16GB RAM / 60Hz
- 判定は Tier1 で実施し、Tier2 は互換判定のみ

## 8. CanimationSpec 規範

- `durationMs`: 0 以上の整数（`durationMs < 0` は `IllegalArgumentException`）
- `alpha`: `null`（未使用）または `CanimationRange(from, to)`（`from`/`to` 共に `0f..1f`）
- `offsetX`: `null`（未使用）または `CanimationDpRange(from, to)`
- `offsetY`: `null`（未使用）または `CanimationDpRange(from, to)`
- `scale`: `null`（未使用）または `CanimationRange(from, to)`（`from`/`to` 共に `0f` 以上）
- `easing`: `Easing` 型（Compose 標準）

## 8.1 CanimationSpec.toReduced() 変換規則

- `durationMs` → `DurationTokens.short`（120ms）に置換
- `offsetX` → `CanimationDpRange(from * 0.25, to * 0.25)` に縮小
- `offsetY` → `CanimationDpRange(from * 0.25, to * 0.25)` に縮小
- `scale` → `CanimationRange(1f - (1f - from) * 0.25f, 1f - (1f - to) * 0.25f)` に圧縮
- `alpha` → 変更なし
- `easing` → `decelerate` に置換

注: `toReduced()` は Enter spec に対して使用する前提で設計されている。Exit の Reduced spec は `enterReducedSpec.reversed()` で導出する（`reversed()` により easing は `accelerate` に置換される）。

## 8.2 CanimationSpec.reversed() 変換規則

- `alpha` → `from` と `to` を入れ替え
- `offsetX` → `from` と `to` を入れ替え（`CanimationDpRange(0.dp, 16.dp)` → `CanimationDpRange(16.dp, 0.dp)`）
- `offsetY` → `from` と `to` を入れ替え
- `scale` → `from` と `to` を入れ替え
- `durationMs` → 変更なし
- `easing` → `accelerate` に置換
