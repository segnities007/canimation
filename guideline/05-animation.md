# Animation Guideline

## Scope

- motion token
- preset/spec
- timing/easing
- animation API selection
- performance

## Product Rules

- animation spec は SSoT から参照し、UI 側で duration/easing/distance を再定義しない。
- preset は full motion と reduced motion の両方を設計対象にする。
- animation は意味がある時だけ使う。視覚効果のためだけに複雑な motion を追加しない。
- interaction feedback と screen transition を同じ強度で扱わない。

## API Selection

- show/hide は `AnimatedVisibility` を優先する。
- state 間切り替えは `AnimatedContent` / `updateTransition` を優先する。
- custom sequential / concurrent animation は `Animatable` を使う。
- default は spring だが、brand/spec が duration-based なら `tween` / `keyframes` を使う。

## Performance

- layout を変える animation より draw phase で完結する animation を優先する。
- 60fps を前提に、1 frame あたり約16.7ms を超えない設計を意識する。
- lazy list item の composition 出入りで animation が再起動しないように設計する。
- browser/web target では property cost を意識し、重い CSS/layout property を避ける。

## Motion Language

- easing は少数の token に絞る。
- duration は短い interaction、標準 transition、強調 motion の階層で揃える。
- 同一カテゴリの preset 間で timing と naming の一貫性を保つ。

## Sources

- Android Developers, Quick guide to Animations in Compose
  - https://developer.android.com/develop/ui/compose/animation/quick-guide
- Android Developers, Customize animations
  - https://developer.android.com/develop/ui/compose/animation/customize
- MDN, Animation performance and frame rate
  - https://developer.mozilla.org/en-US/docs/Web/Performance/Guides/Animation_performance_and_frame_rate
