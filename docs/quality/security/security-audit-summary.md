# Security Audit Summary

## Purpose

dependency と workflow supply-chain を含む security audit の summary を stable path に残す。

## Scope

- dependency audit
- workflow security posture
- secret handling review

## Source Inputs Or Commands

```bash
bash scripts/security-audit.sh
```

## Required Inputs

- security audit script or tool
- audited scope
- finding severity summary

## Result Summary

- this document describes the maintained audit surface and expected security checks; it is not a live security-status dashboard
- audited repository controls include:
  - Gradle repository centralization in `settings.gradle.kts`
  - Dependabot coverage for Gradle and GitHub Actions
  - dependency review policy in `.github/dependency-review-config.yml`
  - dependency verification metadata presence
  - release workflow OIDC permission, protected environment usage, and provenance attestation
  - CodeQL coverage for the active `main` and `dev` branches
  - Android manifest hardening flags
  - web entrypoint CSP / Permissions-Policy / referrer policy
- workflow presence for dependency review, CodeQL, labeler, and stale management
  - full-SHA pinning for third-party GitHub Actions in workflows and composite actions
  - secret-pattern and insecure `http://` scanning
- high-risk finding status:
  - review the generated audit artifact for the current run before release or workflow changes are accepted
- machine-readable artifact:
  - `docs/quality/security/security-audit-report.json`

## Known Limitations

- this summary reflects repository-file checks only and does not prove external GitHub security settings such as secret scanning, private vulnerability reporting, or ruleset enforcement
- intended GitHub-side protection baseline is documented separately in `docs/reference/release/repository-protection-baseline.md`

## Last Updated Trigger

- update when workflow security posture, dependency-review policy, or repository security audit rules change
