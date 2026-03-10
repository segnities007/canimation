# Support Policy

## Supported Lines

`canimation` support is tracked by repository line rather than by long-lived maintenance branches.

| Line | Status | Notes |
|---|---|---|
| `main` | active development | first landing place for normal fixes; may be unstable between tags |
| latest release tag | active | primary support target for consumers |
| older release tags | best effort | no backport guarantee unless maintainers announce one |

Support status vocabulary:

- `active`: normal bug-fix and compatibility attention
- `security-fixes-only`: only security or critical safety fixes
- `best effort`: guidance may be provided, but no fix or backport commitment exists
- `eol`: unsupported; users should move to a newer tag or `main`

The repository does not currently publish a separate long-term-support line policy.
No release line is explicitly marked `eol` in this document today.

## Supported Platforms

The project targets:

- Android
- iOS
- Desktop (JVM)
- Web (`js`, `wasmJs`)

Platform details and current implementation notes live in `docs/explanation/architecture/implementation-overview.md` and `docs/quality/accessibility/tier-2-platform-compatibility.md`.

## Supported Tooling Baseline

- Kotlin follows the version documented in `README.md` and `gradle/libs.versions.toml`
- Compose Multiplatform follows the version documented in `README.md` and `gradle/libs.versions.toml`
- Gradle follows the checked-in wrapper and repository build logic

If you report a toolchain issue, include the exact repository commit or release tag plus your local Kotlin, Compose, Gradle, and platform versions.

## Backport Policy

- normal fixes land on `main` first
- backports are considered only for the latest supported release tag
- security-sensitive fixes may justify a targeted backport when maintainers explicitly announce it

## Where To Ask For What

- bug report: `.github/ISSUE_TEMPLATE/bug-report.yml`
- feature request: `.github/ISSUE_TEMPLATE/feature-request.yml`
- documentation issue: `.github/ISSUE_TEMPLATE/docs-request.yml`
- usage or support question: `.github/ISSUE_TEMPLATE/question.yml`
- sensitive vulnerability report: `SECURITY.md`

## Expectations

- support is strongest on `main` and the latest release tag
- reduced-motion, accessibility-contract, and public-API-compatibility issues are high priority
- responses are best effort and maintainer-capacity dependent
- this repository does not promise response-time SLAs

## Out Of Scope

Maintainers may decline support for:

- heavily modified local forks without a minimal reproduction
- unsupported toolchain combinations
- behavior that contradicts documented non-goals or experimental-tier expectations
- requests that require custom consulting, migration services, or private support commitments

## Related Documents

- `README.md`
- `CONTRIBUTING.md`
- `SECURITY.md`
- `docs/README.md`
- `docs/reference/release/versioning-policy.md`
- `guideline/19-oss-governance-and-maintainer-experience.md`
