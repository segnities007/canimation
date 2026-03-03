# Webとモバイルでアニメーション採用度が違って見える理由（調査メモ）

- 作成日: 2026-03-02
- 対象: Compose Multiplatform (CMP) を含むモバイルUI設計の文脈

## 1. 結論

「Webはアニメーションが多く、モバイルアプリは少なく見える」主因は、技術不足よりも制約の差にある。

- Web: 標準APIとデバッグ基盤が成熟し、配布も即時で試行錯誤しやすい
- モバイル: フレーム予算、電池/発熱、アクセシビリティ、審査/更新、ストア品質指標を同時に満たす必要がある

## 2. 観測事実

- HTTP Archive Web Almanac 2022では、`animation` がモバイルページの77%、`transition` が85%で利用されている
- Android公式は、60fps=約16ms、90fps=約11ms、120fps=約8msのフレーム予算を示し、超過でジャンク化しやすいと説明している

## 3. 多視点の原因整理

## 3.1 技術・性能

- モバイルはフレーム予算が厳密で、過剰演出がすぐ体感劣化につながる
- Composeでは `composition/layout/draw` のどこで状態を読むかで負荷が変わる
- 端末ごとのリフレッシュレート差（60/90/120Hz）を吸収する設計が必要

## 3.2 電池・熱

- Android公式資料ではFPS制御がGPU/システム電力低減に寄与し得ると示される
- Apple公式でも余計なアニメーションはエネルギー消費増を招くとされる

## 3.3 UX方針

- Androidのモーション指針は「見せるためだけの動き」を避ける方向
- つまり、装飾より「意味のある遷移」が優先される

## 3.4 アクセシビリティ

- AppleはReduced Motionの評価基準を公開し、低モーション対応を要請
- Webは `prefers-reduced-motion` が標準で、実装導線が整っている

## 3.5 配布・運用

- モバイルはストア審査と段階配布の制約がある
- 更新はユーザー側の未更新リスクもあり、Webより改善サイクルが重くなりやすい

## 3.6 ビジネス指標

- Android vitalsの品質指標はPlay上の可視性に影響
- 「重いアニメ」は技術課題だけでなく集客/売上リスクにも直結しやすい

## 3.7 ツール・基盤差

- WebはCSS Animation/Transition、WAAPI、DevToolsの可視化が強い
- モバイルはOS/端末差を吸収する検証負荷が高い

## 3.8 CMP固有の追加難易度

- CMPはUI共通化に有利だが、Kotlin/AGP/Gradle/Xcodeなど互換管理が増える
- 共通コード + 各プラットフォーム最適化の両立が必要

## 4. 推論と注意点

- 「モバイル全体はWebよりアニメ採用率が低い」と断定する公的横断統計は確認できなかった
- ただし、一次情報からは「モバイルの制約が多く、過剰アニメが採算に合いにくい」因果は妥当

## 5. CMP向け2Dアニメライブラリ設計への示唆

- 最初から `Reduced Motion` をAPIレベルで組み込む
- フレーム予算を前提に、デフォルトで軽量に動く設計にする
- 演出は「意味のある最小限」を推奨し、プリセットで安全側を提供する
- デバッグ機能としてFPS・jank・描画負荷の可視化を同梱する
- 端末差を吸収するため、タイムラインは実時間基準で設計する

## 6. 参考ソース

- HTTP Archive Web Almanac 2022 CSS: https://almanac.httparchive.org/en/2022/css
- Android Rendering Performance: https://developer.android.com/topic/performance/vitals/render
- Android Compose Animation Quick Guide: https://developer.android.com/develop/ui/compose/animation/quick-guide
- Android Compose Phases: https://developer.android.com/develop/ui/compose/phases
- Android Compose Mental Model: https://developer.android.com/develop/ui/compose/mental-model
- Android Frame Rate API: https://developer.android.com/media/optimize/performance/frame-rate
- Android Frame Pacing: https://developer.android.com/games/sdk/frame-pacing
- Android FPS Throttling/Power: https://developer.android.com/games/optimize/adpf/gamemode/fps-throttling
- Android MotionLayout Guidance: https://developer.android.com/develop/ui/views/animations/motionlayout
- Android Vitals: https://developer.android.com/topic/performance/vitals
- Google Play Review Time: https://support.google.com/googleplay/android-developer/answer/9859654?hl=en
- Android In-app Updates: https://developer.android.com/guide/playcore/in-app-updates
- Apple App Review: https://developer.apple.com/app-store/review/
- Apple Reduced Motion Criteria: https://developer.apple.com/help/app-store-connect/manage-app-accessibility/reduced-motion-evaluation-criteria
- Apple Energy Guide (Animations): https://developer.apple.com/library/archive/documentation/Performance/Conceptual/EnergyGuide-iOS/AvoidExtraneousGraphicsAndAnimations.html
- MDN `prefers-reduced-motion`: https://developer.mozilla.org/en-US/docs/Web/CSS/Reference/At-rules/@media/prefers-reduced-motion
- MDN CSS `animation`: https://developer.mozilla.org/en-US/docs/Web/CSS/Reference/Properties/animation
- MDN Web Animations API: https://developer.mozilla.org/en-US/docs/Web/API/Web_Animations_API/Using_the_Web_Animations_API
- Chrome DevTools Animations: https://developer.chrome.com/docs/devtools/css/animations/
- Compose Multiplatform compatibility/versioning: https://kotlinlang.org/docs/multiplatform/compose-compatibility-and-versioning.html
- Compose Multiplatform overview: https://kotlinlang.org/compose-multiplatform/
