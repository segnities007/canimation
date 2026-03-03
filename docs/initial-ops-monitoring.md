# Initial Operations Monitoring Plan

## Purpose

This document defines the monitoring, triage, and escalation process for
issues reported against canimation after the initial public release.

## Severity Levels

| Severity | Name     | Description                                              | Response SLA  | Resolution SLA |
|----------|----------|----------------------------------------------------------|---------------|----------------|
| **S1**   | Critical | Library crashes, data loss, or security vulnerability.   | 4 hours       | 24 hours       |
| **S2**   | High     | Core animation broken, a11y policy not applied, major regression. | 8 hours | 3 business days |
| **S3**   | Medium   | Non-critical animation glitch, minor deviation from spec.| 2 business days | 2 weeks       |
| **S4**   | Low      | Cosmetic issue, typo in docs, minor enhancement request. | 5 business days | Best effort   |

> **Response SLA** = time to acknowledge the issue.
> **Resolution SLA** = time to publish a fix or workaround.

## Issue Categories

| Category | Label          | Examples                                                    |
|----------|----------------|-------------------------------------------------------------|
| Bug      | `bug`          | Animation not playing, crash on specific device.            |
| Perf     | `performance`  | Jank during animation, excessive recomposition.             |
| A11y     | `accessibility`| Reduce-motion not detected, wrong level applied.            |
| Doc      | `documentation`| Incorrect example, missing API reference.                   |

Issues should be tagged with **both** a severity label (`S1`–`S4`) and a
category label.

## Triage Process

1. **New issue filed** → auto-labeled `needs-triage`.
2. **Maintainer review** (within Response SLA):
   - Assign severity (`S1`–`S4`) and category.
   - Reproduce the issue or request more information.
   - Assign to a team member.
3. **Investigation** → move issue to `in-progress`.
4. **Fix or workaround** → open a PR referencing the issue.
5. **Verification** → reporter confirms the fix; issue closed.

## Escalation Procedure

| Condition                                       | Action                                           |
|-------------------------------------------------|--------------------------------------------------|
| S1 not acknowledged within 4 hours              | Notify project lead directly (DM / mention).     |
| S1 not resolved within 24 hours                 | Publish a status update in the issue and README.  |
| S2 not acknowledged within 8 hours              | Escalate to project lead.                        |
| S2 not resolved within 3 business days          | Evaluate hotfix release.                         |
| Multiple S3+ issues on same feature area        | Prioritize a patch release addressing the area.  |
| Security vulnerability (any severity)           | Follow responsible disclosure; coordinate patch.  |

## Monitoring Channels

| Channel                   | Purpose                                          |
|---------------------------|--------------------------------------------------|
| GitHub Issues             | Primary bug and feature tracking.                |
| GitHub Discussions        | Community questions and usage help.              |
| CI Dashboard              | Build and test status for `main` and releases.   |
| Release Smoke Test Report | Post-release verification (see smoke procedure). |

## Metrics to Track

- **Open issue count** by severity and category (weekly review).
- **Mean time to acknowledge** (target: within SLA).
- **Mean time to resolve** (target: within SLA).
- **CI pass rate** on `main` branch (target: ≥ 95%).
- **Number of regressions** introduced per release (target: 0).

## Review Cadence

- **Weekly:** Review open issues, update priorities, check SLA compliance.
- **Per release:** Retrospective on issues resolved, SLA misses, and
  process improvements.
