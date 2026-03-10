# AGENTS.md

このファイルは、本リポジトリにおける実装・設計・運用の強制規約を定義する。
この文書は「推奨」ではなく「規約」であり、原則としてすべて必須である。

## 0. Scope と優先順位

- 適用範囲: 本リポジトリ配下のすべてのコード、設定、ドキュメント、テスト。
- 優先順位:
  - 1) ユーザー価値・正確性・安全性
  - 2) API 安定性・互換性
  - 3) 保守性・可読性・変更容易性
  - 4) 性能・運用効率
- 用語:
  - MUST: 必須。違反はマージ不可。
  - SHOULD: 強く推奨。逸脱時は理由を PR に明記。
  - MAY: 任意。

## 1. Non-Negotiable Rules（絶対遵守）

### 1.1 SSoT (Single Source of Truth) - MUST
- 同一ドメイン概念の定義は必ず 1 箇所に集約する。
- マスターデータ・定数・仕様値・マッピングを複数ファイルに重複定義しない。
- 参照側はコピーを持たず、必ず SSoT から参照する。
- 仕様変更時は SSoT のみ変更し、派生値は生成・計算で追従させる。

禁止事項:
- 同じ enum / preset / label / mapping の重複定義
- UI 都合でのハードコード複製
- 同期前提の二重管理

### 1.2 UDF (Unidirectional Data Flow) - MUST
- データフローは `State -> UI -> Event -> Update -> State` の一方向のみ許可する。
- UI は状態を描画するだけとし、状態を直接更新しない。
- 画面ロジックは Event を受けて State を更新する層へ集約する。
- 子コンポーネントは state hoisting を前提に、必要最小限の状態と callback だけを受け取る。

禁止事項:
- 双方向バインディング
- 子から親 state への暗黙更新
- 同一 state の多重保持と手動同期

### 1.3 Dependency Rule（依存方向）- MUST
- 依存方向は外側 -> 内側のみ（UI -> Application/State -> Domain -> Data/Platform）。
- 内側層は外側層を import しない。
- Domain は UI フレームワーク（Compose/Android API）へ依存しない。

### 1.4 Backward Compatibility - MUST
- 公開 API の破壊的変更は禁止。必要時は段階的移行（deprecate -> migrate -> remove）を行う。
- 既存挙動を変える変更は、変更理由・影響・移行手順を明記する。

## 2. Architecture Baseline

- 既定構成の第一参照先は `guideline/00-convention-over-configuration.md` とする。
- 新規実装は「自由設計」ではなく、まず既定形へ合わせる。

### 2.1 Layer Responsibilities
- UI Layer:
  - 描画、入力受付、Event 発行のみ。
  - ドメイン判断、IO、永続化判断を持たない。
- State/Application Layer:
  - Reducer / UseCase 実行、状態遷移、副作用オーケストレーション。
  - UI からの Event を受け、Domain を呼び、UI State を組み立てる。
- Domain Layer:
  - ビジネスルール、仕様、ポリシー、モデル不変条件。
  - 純粋関数優先。副作用を持たない。
- Data/Platform Layer:
  - 外部 API、DB、端末機能、expect/actual 実装を担当。
  - Adapter を通じて Domain 契約へ接続する。

### 2.2 Module Boundaries
- `canimation-core`:
  - 仕様・モデル・基本 API・時間/環境抽象。
- `canimation-presets`:
  - preset 定義および registry。preset の SSoT。
- `canimation-a11y`:
  - reduced-motion、アクセシビリティ方針・補助ロジック。
- `canimation-diagnostics`:
  - 観測・計測・デバッグ表示。
- `composeApp`:
  - デモ/アプリシェル/UI 表示層。

### 2.3 Ports and Adapters - MUST
- 外部依存は Port（interface）越しに扱う。
- 実装差分は Adapter に閉じ込める。
- Domain から具体実装型を参照しない。

## 3. Principles（設計原則）

### 3.1 SOLID - MUST
- S: クラス/関数は単一責務。
- O: 仕様追加は既存コード改変最小で行う。
- L: 置換可能性を壊す継承をしない。
- I: 巨大 interface を避け、用途別に分割する。
- D: 高水準は抽象に依存し、詳細に依存しない。

### 3.2 KISS / YAGNI / DRY - MUST
- KISS: 複雑化より明快さを優先。
- YAGNI: 使われない拡張点を先回り実装しない。
- DRY: ただし「偶然の重複」と「意図的分離」を区別し、早すぎる共通化を避ける。

### 3.3 Composition over Inheritance - MUST
- 継承より合成を優先。
- 振る舞い切り替えは Strategy/Policy 注入で行う。

### 3.4 Immutability First - SHOULD
- state/model は不変を基本とする。
- mutable 状態は境界層に限定し、可視範囲を最小化する。

## 4. Required Patterns（採用すべきパターン）

- Repository Pattern:
  - データ取得・保存は Repository 経由で統一。
- Strategy Pattern:
  - easing、motion policy、platform policy など切替可能ロジックに適用。
- Factory/Builder Pattern:
  - 構築パラメータが多い spec/model 生成に適用。
- Adapter Pattern:
  - Platform 実装（Android/iOS/JVM/JS/Wasm）を共通契約へ接続。
- Reducer Pattern:
  - 画面 state 遷移は reducer の純粋関数として記述。
- State Holder Pattern:
  - Screen 単位の state と event dispatcher を明示する。

禁止事項:
- 神クラス（巨大 Controller/Manager）
- 暗黙 shared mutable state
- 依存注入なしの直接 new 多発

## 5. UI/Compose Rules

- Composable は「見た目 + Event 発火」に限定する。
- Screen の state は 1 箇所で生成・更新する（UDF）。
- `remember` の乱用禁止。意味のある所有境界でのみ使用。
- リスト描画には安定した `key` を与える。
- マジック値を避け、token/constants に抽出する。
- アニメーション仕様は preset/spec SSoT から参照し、UI 側で再定義しない。
- クリップボードやプラットフォーム API の非推奨は追跡し、局所抑制時は理由をコメントする。

## 6. Domain Rules

- 仕様値の検証は model 生成時に fail-fast で行う。
- 不変条件はテストで明示する。
- 時刻・乱数・環境依存値は抽象化し、直接参照しない。
- 例外より `Result` / 型で表現可能な失敗は型で表す。

## 7. Data/Platform Rules

- expect/actual の差分は最小化し、共通ロジックを `commonMain` に寄せる。
- プラットフォーム固有 API は adapter 層から漏らさない。
- シリアライズ/デシリアライズ境界で入力検証を必ず行う。

## 8. API Design Guidelines

- 命名は「型名は名詞」「関数は動詞」を徹底。
- 引数が 4 つを超える場合は value object 化を検討する。
- `Boolean` フラグ引数を多用しない。意図が明確な型へ分解する。
- nullable を安易に増やさない。null の意味をドキュメント化する。
- 破壊的変更時は deprecation message に移行先を必ず記載する。

## 9. Error Handling and Resilience

- エラーは握りつぶさず、層に応じて変換・伝搬する。
- リトライは idempotency を確認した上で導入する。
- UI 表示向けメッセージと内部エラー詳細を分離する。

## 10. Performance Rules

- 「計測してから最適化」を徹底する。
- ホットパスで不要な allocation を作らない。
- 大きなデータは分割し、必要時ロードを優先する。
- 大規模変更時は before/after の比較結果を示す。
- OOM が出るタスクは build 設定で再現可能に抑え、恒常化させる。

## 11. Accessibility and Inclusive Design

- reduced-motion を常に考慮し、同等体験を提供する。
- コントラスト、フォーカス、読み上げ semantics を必ず確認する。
- 入力はキーボード/タッチ双方を想定する。
- 文言は i18n を意識し、ハードコードを減らす。

## 12. Security and Privacy

- 秘密情報をソースに含めない。
- 外部入力は常に検証・正規化する。
- ログへ個人情報・機密情報を出さない。

## 13. Testing Strategy

### 13.1 必須テスト
- Domain:
  - 正常系、異常系、不変条件を網羅。
- Reducer/State:
  - Event ごとの状態遷移テストを追加。
- UI:
  - 主要分岐の表示/イベント連携を検証。
- Platform:
  - expect/actual 差分の挙動テストを持つ。

### 13.2 テスト品質
- flaky test は禁止。非決定要素を排除する。
- テストで `sleep` 依存を避ける。
- バグ修正時は再発防止テストを先に追加する。

## 14. Documentation and Decision Record

- 仕様変更・設計変更時は該当ドキュメントを同時更新する。
- 重要な設計判断は ADR として残す。
- 「なぜこの設計か」をコードから辿れる状態を保つ。
- 技術領域別ガイドラインの SSoT は `guideline/` ディレクトリとする。
- 変更前に、対象が属する技術領域を特定し、`guideline/README.md` と該当ガイドラインを確認する。
- 複数領域にまたがる変更は、関係するすべてのガイドラインに従う。
- `Kotlin` / `Kotlin Multiplatform` / `Jetpack Compose` / `Compose Multiplatform` / `Animation` / `Accessibility & I18N` / `Testing` / `Gradle` / `GitHub Actions` / `Release & API Compatibility` / `Platform Adapters` の運用ルールは `guideline/*.md` を参照する。
- module build、workflow/script、docs/navigation/resource の既定形も `guideline/*.md` を参照する。
- symbol 命名、file 内順序、Compose effect、test/workflow step のミクロ規約も `guideline/*.md` を参照する。
- 新規実装の雛形は `guideline/templates/` を起点にする。
- 領域ガイドラインに影響する変更は、対応する `guideline/*.md` を同時更新する。

## 15. Code Writing Tactics（立ち回り）

- 変更前に境界・責務・依存方向を先に定義する。
- まずテスト可能な最小単位に分割してから実装する。
- 大規模リファクタは「互換層 -> 置換 -> 削除」の 3 段階で進める。
- 1 変更 1 意図を徹底し、レビュー可能性を最大化する。
- 警告を放置しない。放置する場合は期限付き TODO を付与する。

## 16. Review Gate（PR 必須）

PR は以下すべてを満たすこと:
- [ ] SSoT を破っていない（新しい重複定義がない）
- [ ] UDF を破っていない（state 更新点が一方向）
- [ ] 依存方向違反がない（外->内のみ）
- [ ] 変更箇所に対するテストが追加/更新されている
- [ ] public API の互換性が確認されている
- [ ] ドキュメント/コメントが更新されている
- [ ] `guideline/*.md` の該当領域に反していない
- [ ] パフォーマンス劣化がないか確認した
- [ ] a11y/reduced-motion 影響を確認した

## 17. Exception Policy

例外は原則禁止。やむを得ない場合のみ、以下を同時に満たす:
- 理由（なぜ通常ルールで不可か）
- 影響範囲（どこまで波及するか）
- 期限（いつ通常構造へ戻すか）
- 代替案比較（最低 1 案）
- 追跡チケット（必須）

上記が揃わない例外実装は却下する。

## 18. Anti-Patterns（禁止カタログ）

- コピペ実装での仕様分岐
- Screen 内巨大関数（読み解けない条件分岐）
- グローバル mutable state
- Domain 層での platform API 直接呼び出し
- テストなしのリファクタ
- コメントで仕様を隠し、型に表現しない実装

## 19. Continuous Improvement Policy

- 警告ゼロ運用を目標とし、少なくとも「新規警告ゼロ」を維持する。
- 技術負債は「可視化 -> 優先順位付け -> 期限管理」で運用する。
- 互換フラグや暫定措置は `Temporary` コメントと撤去条件を必ず記載する。
- 四半期ごとに以下を見直す:
  - アーキ整合性
  - テスト有効性
  - ビルド時間と安定性
  - a11y と性能指標

## 20. Refactor Execution Protocol（強制）

- 大規模変更は必ず `責務分離 -> 互換維持 -> 不要コード削除 -> 検証` の順で行う。
- 1ファイルに `UI描画 + 状態遷移 + ドメイン判定 + 文字列生成` を同居させない。
- 画面ファイルは「画面構成」に集中し、生成ロジック・カタログ・変換関数は別ファイルへ分離する。
- 複数箇所で使う判定・整形ロジックは必ず共通関数へ昇格する。
- リファクタ後は以下を必ず満たす:
  - 既存機能の挙動差分がない
  - テスト追加または既存テスト更新で回帰防止がある
  - 依存方向違反が新規に増えていない

## 21. Pattern Selection Matrix（採用基準）

- State Holder + Reducer:
  - 画面状態が2つ以上ある場合は必須。
- Strategy:
  - 実行時に振る舞いが切り替わるロジックへ適用（motion policy, filter policy など）。
- Factory/Builder:
  - 生成引数が多く、誤組み立てリスクがある型へ適用。
- Adapter:
  - expect/actual や外部 API 差分の吸収に必須。
- Repository:
  - データ取得元が将来増える可能性がある箇所へ適用。
- Anti-rule:
  - パターン適用自体を目的化しない。問題を下げないパターン導入は禁止。

## 22. File and Symbol Hygiene

- 1ファイル1責務を徹底し、責務境界をファイル名に反映する（`*State`, `*Catalog`, `*Content`, `*Adapter`）。
- 外部公開不要な型・関数は `internal` / `private` を強制し、デフォルト `public` を避ける。
- 依存される順に配置する（model/state -> pure logic -> UI）。
- 長大ファイルは分割する。目安:
  - 300行超: 分割検討必須
  - 500行超: 分割しない変更は原則却下
- 新規ファイル命名・配置は `guideline/00-convention-over-configuration.md` の既定形に従う。

## 22.1 Convention Over Configuration

- Screen を追加する場合、原則として `Screen + StateHolder + State/Event/Reducer` の組で作る。
- 設定 UI、dialog、bottom sheet も screen-level state と責務が違う場合は専用 state holder を持つ。
- preset は `1 preset 1 file` を既定とし、registry 接続点を固定する。
- platform capability は `common contract + target adapter` を既定とし、platform API 直接参照を広げない。
- workflow 名、job 名、test 配置も `guideline/00-convention-over-configuration.md` の標準形へ合わせる。
- module の `build.gradle.kts` 骨格、script 定型、docs/navigation/resource 配置も既定形へ合わせる。
- symbol 名、引数順、state holder shape、`LaunchedEffect` の key、workflow step 名も既定形へ合わせる。

## 23. Definition of Done（DoD）

- DoD を満たさない変更は完了とみなさない:
  - [ ] 新規/変更ロジックに対するテストがある
  - [ ] Lint/Build/Test が通る
  - [ ] SSoT/UDF/依存方向に反しない
  - [ ] 例外規約がある場合は期限と撤去条件が記載されている
  - [ ] 主要設計判断を追跡可能（コメント or ADR）

## 24. Blocking Conditions（マージ拒否条件）

- 次のいずれかに該当した場合、レビューは即時 Reject:
  - SSoT を破る重複定義
  - UDF を破る双方向更新や隠れた mutable state
  - テスト未更新の大規模リファクタ
  - 無期限の `TODO`, `FIXME`, `Suppress`
  - public API の無告知破壊変更
