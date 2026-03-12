# Docs, Navigation, and Resource Convention Guideline

## Why This Exists

docs、navigation、resources は小さな差異が大量に積み上がりやすい。ここを CoC にすると探索コストが下がる。

## Docs Convention

- 規約は `guideline/`
- 計画・判断・調査・方針は `docs/`
- stable docs IA は次を既定にする。
  - `docs/tutorials/`
  - `docs/how-to/`
  - `docs/reference/`
  - `docs/explanation/`
  - `docs/architecture/decisions/`
  - `docs/quality/`
- 日付付き文書は `YYYY-MM-DD_topic/` を既定にする。
- 日付付き文書は draft / proposal / working note の entry とし、採択後は stable path へ昇格する。
- release 系文書は root `docs/` に置く。
- quality artifact は `docs/quality/` に置く。

## Navigation Convention

- route key は `ROUTE_KEY_*`
- route object は `*Route`
- destination enum は UI label と route key を同時に持つ
- `matchesRoute` のような共通 helper を再利用し、判定ロジックを散らさない

## Resource Convention

- navigation label は resource 経由で管理する。
- screen title / settings label / action label は string resource を既定にする。
- icon content description は user value がある場合だけ resource 化し、decorative icon は null を既定にする。

## Test Placement Convention

- screen/reducer test は対象 screen package 配下の `commonTest` に置く。
- adapter test は module ごとの target test に置く。
- diagnostics / helper 系 test は対象 module の `commonTest` を既定にする。

## Sources

- Android Developers, Localize your app
  - https://developer.android.com/guide/topics/resources/localization
- Android Developers, Test your app with pseudolocales
  - https://developer.android.com/guide/topics/resources/pseudolocales
