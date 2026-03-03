# canimation 開発計画書 技術レビュー

- レビュー日: 2026-03-02
- 総合評価: 目的達成に **致命的障害 19 箇所** あり → 修正版で解消済み（Round 1: F-01〜F-07、Round 2: F-08〜F-16、Round 3: F-17〜F-19）

---

## 総評

プロセス設計は堅牢。タスク粒度も CMP のビルドコストを考慮すれば妥当。
しかし **API 設計に技術的な穴** があり、このまま 152 タスクを実行しても
「使えるアニメーションライブラリ」にはたどり着けない。
以下はすべて「修正しなければ目的が達成できない」欠陥のみを列挙する。

---

## 致命欠陥一覧

### F-01: Exit アニメーション API が未定義

**現状**: `canimationEnter` と `CanimationVisibility` は `visible: Boolean` を受け取る。
`visible=false→true` の Enter 挙動はプリセットで定義されているが、
`visible=true→false` の Exit 挙動がどこにも定義されていない。

**影響**: ユースケース #1「画面遷移時のフェード + スライド」は Enter だけでは半分。
画面から消える要素が制御できないのは実用に耐えない。

**修正**:
1. `basic-design.md` に Exit 方針を追加（逆再生デフォルト + カスタム Exit プリセット）
2. `spec-values.md` に各プリセットの Exit spec を追加
3. `Modifier.canimationExit` API を追加
4. `CanimationVisibility` に `exitPreset` パラメータを追加
5. task-pack に Exit 実装・テストタスクを追加

### F-02: カスタムアニメーション定義手段が存在しない

**現状**: 5 プリセット（FadeUp / Fade / ScaleIn / SlideLeft / SlideRight）のみ使用可能。
ユーザーが `duration=300ms, easing=linear, offsetY=32.dp` を指定する手段がない。

**影響**: プリセットに合わない要件が発生した時点でライブラリを捨てることになる。
基本設計書の目的「実装量を減らす」は達成されるが、
「統一感を維持しつつ柔軟に使える」にはならない。

**修正**:
1. `CanimationSpec` 型を公開して、ユーザーがカスタム spec を定義可能にする
2. `Modifier.canimationEnter(visible, spec)` オーバーロードを追加
3. プリセットは `CanimationSpec` の定義済みインスタンスとして再定義
4. task-pack にカスタム API 関連タスクを追加

### F-03: EasingTokens の standard と emphasized が同一値

**現状** (`spec-values.md`):
- `standard = CubicBezier(0.2, 0.0, 0.0, 1.0)`
- `emphasized = CubicBezier(0.2, 0.0, 0.0, 1.0)`

**影響**: 2つの異なる名前が同じカーブを指す。実装者が混乱し、
コードレビューで「これ standard と emphasized どっち使うべき？」が発生する。
Material Design 3 の Motion 仕様では emphasized は異なるカーブ。

**修正**: Material Design 3 準拠で emphasized を修正。
- `emphasized = CubicBezier(0.2, 0.0, 0.0, 1.0)` のまま（enter 部分）
  → 分割カーブ: `emphasizedAccelerate = CubicBezier(0.3, 0.0, 0.8, 0.15)`,
    `emphasizedDecelerate = CubicBezier(0.05, 0.7, 0.1, 1.0)` に分離

### F-04: core と presets 間のプリセット解決アーキテクチャが未定義

**現状**:
- `canimation-core` に `CanimationPreset` enum（CP-012）と `CanimationSpecResolver`（CP-014）がある
- `canimation-presets` に各 Preset 実装（CP-029〜CP-033）と `PresetRegistry`（CP-034）がある
- 依存方向は `presets → core`（core は presets に依存しない）

**問題**: core の `SpecResolver` がプリセットを解決するには spec 値が必要。
しかし spec 値は presets モジュールにある。core は presets に依存できない。
**core の SpecResolver はどうやって FadeUp の spec を取得するのか？**

現在の設計ではこれが動かない。

**修正案**:
1. 5 つの組み込みプリセット spec を core に配置する
2. `PresetRegistry` は拡張用で、ユーザーが追加プリセットを登録する機構にする
3. presets モジュールは追加プリセットの提供 + カスタムプリセット構築 DSL にする
4. `CanimationProvider` に `presetRegistry` パラメータを追加し、
   デフォルトは core 組み込みの 5 プリセット

### F-05: エラーハンドリング方針が未定義

以下のケースの振る舞いが設計書に記載されていない:

1. **`CanimationProvider` なしで `canimationEnter` を呼んだ場合**
   → 安全デフォルト（アニメーションなし）で動作すべきか、例外を投げるべきか？
2. **プラットフォームが motion preference 取得に失敗した場合**
   → Fallback ファイル（AP-024〜027）は存在するが、方針が basic-design に書かれていない
3. **`CanimationTestClock` をテスト外で使った場合**
   → 黙って本番クロックで動く？警告を出す？

**修正**: basic-design.md に「エラーハンドリング方針」セクションを追加。

### F-06: タスク間依存で独立作業が不必要に直列化されている

CMPのビルドコストが高いからタスクを細分化する方針は正しい。
しかし **論理的に独立した作業** に不必要な依存を張るのは、
細分化のメリットを殺す。

具体例:
- CP-004（DurationTokens）→ CP-005（EasingTokens）→ CP-006（DistanceTokens）→ CP-007（SpringTokens）
  これらは同一パッケージ内の独立した型定義。互いに参照しない。
  全て CP-003（パッケージ確立）だけに依存すべき。
- CP-029〜CP-033（各 Preset 実装）も全て CP-028（presets build 設定）だけに依存すべき。
- AP-010, AP-014, AP-018, AP-022（各プラットフォーム preference 実装）も
  共通契約（AP-005）完了後は並列可能。

**影響**: 1人で順番にやる場合は問題ないが、依存関係は「技術的に必要な順序」を
表すべきであって「作業の推奨順」ではない。
技術的に不要な依存を入れると、BLOCKED の連鎖が発生した際に影響範囲を誤判定する。

**修正**: task-pack 内の依存を「技術的に必須な依存」のみに修正。

### F-07: `Modifier.canimationEnter` の naming が Enter/Exit 両方を扱うのに不適切

API 設計では `canimationEnter(visible: Boolean)` が visible の真偽で Enter/Exit を切り替える。
しかし名前が `Enter` なので、ユーザーは Exit は別 API だと誤解する。

**修正**:
- `Modifier.canimationEnter` → Enter 専用（visible=true 時のみ効果）
- `Modifier.canimationExit` → Exit 専用（visible=false 時のみ効果）
- `Modifier.canimationTransition` → 新規 API、Enter + Exit 両方を扱う

---

## 修正作業

上記 F-01〜F-07 を以下のファイルに反映する:

1. `basic-design.md` — Exit API、Custom API、エラーハンドリング、core/presets 解決方式
2. `spec-values.md` — emphasized easing 修正、Exit spec 追加
3. `master-plan.md` — API 固定リスト更新
4. `execution-rules.md` — 依存関係ルール追記
5. `task-pack-core-presets.md` — Exit/Custom タスク追加、依存修正
6. `task-pack-a11y-platform.md` — 依存修正
7. `task-pack-diagnostics-platform.md` — 依存修正
8. `task-pack-test-samples-ci.md` — Exit/Custom テストタスク追加

---

## Round 2: 再監査で発見された欠陥

### F-08 [CRITICAL]: CanimationSpec の ClosedFloatingPointRange が Exit アニメーションを表現不能

**現状**: `alpha: ClosedFloatingPointRange<Float>?` — Kotlin では `1f..0f` は空の範囲。
Exit アニメーションは alpha を `1f → 0f` に遷移させるが、この型では表現できない。
`scale` も同様（`1.0f..0.92f` は空の範囲）。

**修正**: `CanimationRange(from: Float, to: Float)` を導入し、alpha/scale に使用。
`ClosedFloatingPointRange` は使わない。

### F-09 [CRITICAL]: CanimationSpec.offsetX/offsetY が単一 Dp 値で範囲を表現不能

**現状**: `offsetX: Dp?` は単一値。仕様値は `16.dp -> 0.dp` のように始点→終点で定義。
`reversed()` は始点と終点の入替を行うが、終点がデータ型に存在しない。

**修正**: `CanimationDpRange(from: Dp, to: Dp)` を導入し、offsetX/offsetY に使用。

### F-10 [MODERATE]: reversed() の offset 規則記述が誤解を招く

**現状** (spec-values.md §8.2): `offsetX → 符号を反転`
しかし実際の動作は `0.dp -> 16.dp` → `16.dp -> 0.dp` であり、
これは「符号の反転」ではなく「始点と終点の入替」。

**修正**: `from と to を入れ替え` に統一（alpha/scale と同じ記述）。

### F-11 [MODERATE]: toReduced() の easing 規則に方向の考慮が欠落

**現状**: `easing → decelerate に置換`
Exit Reduced spec は `accelerate` を使用する（spec-values.md §2.1 等）。
Exit spec に対して toReduced() を呼ぶと decelerate になり、仕様と矛盾。

**修正**: toReduced() は Enter spec 専用と明記。
Exit Reduced は `enterReducedSpec.reversed()` で導出（reversed() が easing を accelerate に置換）。
API デフォルト引数 `exitReducedSpec = enterReducedSpec.reversed()` で自動的に正しくなる。

### F-12 [MODERATE]: CP-008 の依存が CP-007 のみ（4 Token 全て必要）

**現状**: `CanimationTokens` は Duration/Easing/Distance/Spring を集約。
CP-004〜007 は並列化済みだが、CP-008 は CP-007 だけに依存。

**修正**: `CP-008 前提条件: CP-004, CP-005, CP-006, CP-007`

### F-13 [MODERATE]: CP-009/CP-010 が不必要に CP-008 に直列化

**現状**: `CanimationLevel`（enum）と `CanimationPolicy`（sealed interface）は
`CanimationTokens` と一切関係がないのに、CP-009→CP-008, CP-010→CP-009 と連鎖。

**修正**: CP-009, CP-010 → CP-003 に変更。CP-011 → CP-009 + CP-010 に変更。

### F-14 [MODERATE]: CP-013 (PresetSpec) の依存が CP-013a (Spec) を含まない

**現状**: `CanimationPresetSpec` は `CanimationSpec` を保持するが、
CP-013 は CP-012 に依存。CP-013a (CanimationSpec) への依存がない。

**修正**: CP-013a → CP-003、CP-013 → CP-013a、CP-013b → CP-013 + CP-012 + CP-009

### F-15 [MINOR]: CP-035 テストコマンドのクラス名不一致

**現状**: `--tests '*PresetRegistryTest'` だが、ファイル名は `PresetsExtensionRegistryTest.kt`。

**修正**: `--tests '*PresetsExtensionRegistryTest'`

### F-16 [MINOR]: PresetRegistry.resolve() に direction パラメータが欠落

**現状**: `resolve(preset, level)` では Enter/Exit を区別できない。

**修正**: `resolve(preset, level, direction: AnimationDirection)` に更新。
`AnimationDirection { Enter, Exit }` を追加。

---

## Round 3: 依存チェーン最終修正

### F-17 [MODERATE]: CP-021〜CP-026 テストタスクが不必要に直列化

**現状**: CP-021→CP-022→CP-023→CP-024→...と連鎖しているが、各テストは独立したテストクラスであり
互いに技術的依存は存在しない。全テストは実装完了（CP-020）のみに依存する。

**影響**: テスト実行の並列化が不可能。CI での所要時間増大。

**修正**: CP-021〜CP-026 および CP-024a〜CP-024d の全テストタスクの前提条件を CP-020 に変更。

### F-18 [MODERATE]: CP-027 が CP-026（テスト）に依存

**現状**: Presets モジュール追加（CP-027）が Visibility テスト（CP-026）に依存。
presets モジュールの include は core 実装完了のみで実行可能。

**修正**: CP-027 の前提条件を CP-020 に変更。

### F-19 [MODERATE]: DG-012〜DG-019 プラットフォーム別 collector が不必要に直列化

**現状**: Android→Desktop→iOS→Web の順に完全直列化。
各プラットフォームの collector は共通インタフェース（DG-011）のみに依存し、
互いに技術的依存は存在しない。

**影響**: 4 プラットフォームの並列実装が不可能。

**修正**:
- DG-012（Android impl）: DG-011
- DG-013（Android test）: DG-012
- DG-014（Desktop impl）: DG-011
- DG-015（Desktop test）: DG-014
- DG-016（iOS no-op）: DG-011
- DG-017（Web no-op）: DG-011
- DG-018（iOS test）: DG-016
- DG-019（Web test）: DG-017
- DG-020（KDoc）: DG-013, DG-015, DG-018, DG-019（全 collector テスト完了後）
