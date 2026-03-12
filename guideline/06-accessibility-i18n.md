# Accessibility and I18N Guideline

## Scope

- accessibility
- reduced-motion
- keyboard/focus/touch
- localization
- pseudolocale

## Accessibility

- a11y は後付け禁止。component 設計時に role、state、description、focus を決める。
- reduced-motion preference を first-class input として扱う。
- user interaction による animation は disable 可能であること。
- keyboard と touch の両方で操作できること。
- screen reader が理解できる semantics tree を保つ。

## Reduced Motion

- reduced-motion 時は「削る」だけでなく、意味を保つ代替表現を用意する。
- motion off / reduced / full の policy を明確に分ける。
- autoplay や looping motion は特に厳しく扱う。

## Localization

- UI 文言は resource に移し、ハードコードしない。
- default resources を欠落させない。
- translator comment や context を付け、短い label の意味を伝える。
- layout は文言伸長を前提に柔軟にする。
- pseudolocale で伸長と RTL 崩れを確認する。

## Sources

- Android Developers, Accessibility in Jetpack Compose
  - https://developer.android.com/develop/ui/compose/accessibility
- MDN, `prefers-reduced-motion`
  - https://developer.mozilla.org/en-US/docs/Web/CSS/@media/prefers-reduced-motion
- W3C WAI, Understanding SC 2.3.3 Animation from Interactions
  - https://www.w3.org/WAI/WCAG22/Understanding/animation-from-interactions.html
- Android Developers, Localize your app
  - https://developer.android.com/guide/topics/resources/localization
- Android Developers, Test your app with pseudolocales
  - https://developer.android.com/guide/topics/resources/pseudolocales
