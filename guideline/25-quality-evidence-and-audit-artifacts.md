# Quality Evidence and Audit Artifact Guideline

## Scope

- `docs/quality/` layout
- quality report kinds
- report naming and required fields
- evidence lifecycle

## Goal

品質確認を issue comment や一時的ログに閉じ込めない。
release と architecture judgment を再現可能な evidence として残す。

## Canonical Quality Layout

```text
docs/quality/
├── accessibility/
├── compatibility/
├── performance/
├── workflow/
├── security/
└── README.md
```

## Required Evidence Classes

- accessibility validation
- platform compatibility validation
- API compatibility validation
- performance / benchmark validation
- workflow validation
- security / dependency audit

## Naming Rule

- hand-written summary: `kebab-case.md`
- machine-readable artifact: `<concern>-<tool>-<date>.json|txt|xml`
- dated ad hoc filenames を恒久運用しない

### Recommended Stable Names

- `docs/quality/accessibility/tier-1-validation.md`
- `docs/quality/accessibility/tier-2-platform-compatibility.md`
- `docs/quality/compatibility/public-api-compatibility.md`
- `docs/quality/performance/benchmark-summary.md`
- `docs/quality/workflow/workflow-validation.md`
- `docs/quality/security/security-audit-summary.md`

## Required Report Fields

すべての quality summary は最低限次を含む。

- purpose
- scope
- source inputs or commands
- result summary
- known limitations
- last updated trigger

### Additional Required Fields By Concern

- accessibility
  - policy matrix、reduced/off expectation、test signals
- compatibility
  - validated modules、ABI/source gate、migration note link
- performance
  - benchmark target、environment、threshold、regression status
- workflow/security
  - audited workflow scope、tool/script source、high-risk finding status

## When Evidence Is Mandatory

- stable public API change
  - compatibility evidence required
- reduced-motion contract change
  - accessibility evidence required
- `Layout` cost recipe addition/change
  - performance evidence required
- workflow or release process change
  - workflow/security evidence required

## Lifecycle Rule

- summary は stable path へ更新する
- generated raw artifact は dated filename でよい
- obsolete evidence は後継 summary へのリンクを残して置換する

禁止事項:

- release judgment を logs だけに依存すること
- benchmark 結果を再現不能な 1 回限りのメモにすること
- outdated quality doc を最新として放置すること

## Sources

- GitHub Docs, Security hardening for GitHub Actions
  - https://docs.github.com/en/actions/security-for-github-actions/security-guides/security-hardening-for-github-actions
- Kotlin Docs, Backward compatibility guidelines for library authors
  - https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html
