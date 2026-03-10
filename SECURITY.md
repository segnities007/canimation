# Security Policy

## Supported Versions

Security fixes are primarily tracked on:

- `main`
- the latest release tag

Older versions may receive guidance, but they should not be assumed to receive backported fixes.
For the general support window, see `SUPPORT.md`.

## Reporting A Vulnerability

Please do not open a public issue for exploitable vulnerabilities.

Use GitHub's private vulnerability reporting flow when it is available for this repository.
If private reporting is unavailable, contact the maintainer privately through the contact details on the relevant GitHub profile before disclosing details publicly.

Include:

- the affected version, tag, or commit
- the impacted module, platform, or workflow surface
- reproduction steps or a proof of concept
- expected impact
- any known mitigation or workaround

## Scope

Relevant reports include issues such as:

- secret exposure
- unsafe platform-adapter behavior
- CSP or web-surface hardening regressions
- dependency or workflow supply-chain risks
- vulnerabilities that bypass the library's documented safety or accessibility guarantees

Dependency vulnerability reports are in scope when they materially affect published artifacts, release automation, or consumer safety posture.

## Response Expectations

- reports are triaged as maintainer capacity allows
- no formal response-time SLA is promised
- maintainers may ask for a reduced test case or additional environment details before confirming severity
- when a fix is available, the public changelog and release notes should describe remediation at an appropriate level of detail

## Severity Guidance

Reports are treated with higher urgency when they involve:

- secret leakage or credential misuse
- supply-chain compromise risk in workflows or published artifacts
- unsafe behavior that violates documented platform or security guarantees
- vulnerabilities that materially affect published consumers rather than local development only

## Disclosure Expectations

- keep exploitable details private until maintainers have had a reasonable chance to investigate
- avoid publishing proof-of-concept exploit steps in public issues before coordinated disclosure
- once a fix is available, disclose the impact and remediation path clearly enough for consumers to act

## Related Documents

- `SUPPORT.md`
- `CHANGELOG.md`
- `docs/quality/security/security-audit-summary.md`
- `docs/reference/release/versioning-policy.md`
- `guideline/19-oss-governance-and-maintainer-experience.md`
- `guideline/25-quality-evidence-and-audit-artifacts.md`
