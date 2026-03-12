# Gradle and Build Guideline

## Scope

- Gradle build logic
- Kotlin DSL
- configuration cache
- dependency verification
- build performance

## Core Rules

- build logic は Kotlin DSL を使う。
- plugin は `plugins` block で適用する。
- configuration cache 対応を優先し、execution time に `Project` や他 task state を読む実装を禁止する。
- dependency verification を維持し、外部依存の provenance を追える状態を保つ。

## Configuration Cache

- task execution 時に undeclared input を読まない。
- task 同士を直接参照せず、input/output relation で接続する。
- shared mutable object を task field に持ち込まない。
- secret は config cache に載せない設計を優先し、必要なら `GRADLE_USER_HOME/gradle.properties` を使う。

## Performance

- config cache hit 率を意識する。
- multi-project build は parallel 実行可能性を損なわない。
- detached configuration や configuration-time resolution を増やさない。
- build scan や report で bottleneck を観測してから最適化する。

## Dependency Integrity

- `gradle/verification-metadata.xml` を維持する。
- checksum / signature mismatch を無視しない。
- plugin と transitive dependency も verification 対象として扱う。

## Sources

- Gradle Docs, General Gradle Best Practices
  - https://docs.gradle.org/current/userguide/best_practices_general.html
- Gradle Docs, Configuration Cache
  - https://docs.gradle.org/current/userguide/configuration_cache.html
- Gradle Docs, Configuration Cache Requirements for your Build Logic
  - https://docs.gradle.org/current/userguide/configuration_cache_requirements.html
- Gradle Docs, Verifying dependencies
  - https://docs.gradle.org/current/userguide/dependency_verification.html
- Gradle Docs, Improve the Performance of Gradle Builds
  - https://docs.gradle.org/current/userguide/performance.html
