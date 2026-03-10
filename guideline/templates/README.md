# Template Index

このディレクトリは、`Canimation` の CoC をそのまま実装へ落とすための雛形集である。新規追加時は、まず対応する template を起点にする。

## Templates

- `screen-template.md`
  - screen / state holder / reducer / test の標準形
- `state-holder-template.md`
  - state holder 単体の標準形
- `viewmodel-equivalent-template.md`
  - KMP 向け ViewModel 相当の標準形
- `preset-template.md`
  - preset file と registry 接続の標準形
- `canimation-modifier-template.md`
  - `Modifier.canimation` の標準形
- `canimation-transition-template.md`
  - `Modifier.canimationTransition` の標準形
- `canimation-effect-namespace-template.md`
  - `Canimation.*` namespace 利用の標準形
- `custom-spec-template.md`
  - custom `CanimationSpec` の標準形
- `animation-showcase-template.md`
  - preset preview / replay UI の標準形
- `diagnostics-overlay-template.md`
  - diagnostics overlay の標準形
- `catalog-template.md`
  - catalog / option SSoT の標準形
- `component-template.md`
  - stateless leaf component の標準形
- `reducer-test-template.md`
  - reducer test の標準形
- `platform-test-template.md`
  - platform adapter test の標準形
- `platform-adapter-template.md`
  - common contract + target adapter の標準形
- `workflow-template.yml`
  - GitHub Actions workflow の標準形
- `script-template.sh`
  - repo root 起点 shell script の標準形
- `adr-template.md`
  - ADR の標準形

## Rule

- template を使わずに新規パターンを増やさない。
- template が不足している場合は、先に template を更新してから実装する。
