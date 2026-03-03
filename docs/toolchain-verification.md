# Toolchain Verification Record

## Purpose

This document records the verified toolchain versions used to build, test,
and publish canimation v0.1.0. All CI and local builds must use these
versions to ensure reproducibility.

## Verified Versions

| Tool                          | Version    | Notes                                   |
|-------------------------------|------------|-----------------------------------------|
| **Kotlin**                    | 2.3.0      | Kotlin Multiplatform compiler and plugin.|
| **Compose Multiplatform**     | 1.10.0     | JetBrains Compose Multiplatform plugin.  |
| **Android Gradle Plugin (AGP)** | 9.0.0   | Android build tooling.                   |
| **Gradle**                    | 9.2.1      | Build system (wrapper distribution).     |
| **JDK**                       | 21         | Eclipse Temurin 21 recommended.          |

## Configuration References

### `gradle/libs.versions.toml`

```toml
[versions]
kotlin = "2.3.0"
compose-multiplatform = "1.10.0"
agp = "9.0.0"

[plugins]
kotlinMultiplatform = { id = "org.jetbrains.kotlin.multiplatform", version.ref = "kotlin" }
composeMultiplatform = { id = "org.jetbrains.compose", version.ref = "compose-multiplatform" }
composeCompiler = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
androidApplication = { id = "com.android.application", version.ref = "agp" }
androidLibrary = { id = "com.android.library", version.ref = "agp" }
```

### `gradle/wrapper/gradle-wrapper.properties`

```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-9.2.1-bin.zip
```

### CI Environment

```yaml
# GitHub Actions example
- uses: actions/setup-java@v4
  with:
    distribution: temurin
    java-version: '21'

- uses: gradle/actions/setup-gradle@v4
```

## Verification Steps

1. **Kotlin version:**
   ```bash
   ./gradlew kotlinCompilerVersion
   # Expected: 2.3.0
   ```

2. **Gradle version:**
   ```bash
   ./gradlew --version | grep "^Gradle"
   # Expected: Gradle 9.2.1
   ```

3. **JDK version:**
   ```bash
   java -version
   # Expected: openjdk version "21.x.x"
   ```

4. **AGP version:**
   Confirmed in `libs.versions.toml` → `agp = "9.0.0"`.

5. **Compose Multiplatform version:**
   Confirmed in `libs.versions.toml` → `compose-multiplatform = "1.10.0"`.

## Compatibility Notes

- **Kotlin 2.3.0** requires Gradle 9.x; earlier Gradle versions are not
  supported.
- **AGP 9.0.0** requires JDK 21 as the minimum JDK for the Gradle daemon.
- **Compose Multiplatform 1.10.0** is aligned with Kotlin 2.3.0 via the
  Compose compiler plugin.
- WasmJs target requires the Kotlin/Wasm toolchain bundled with Kotlin
  2.3.0.

## Update Procedure

When updating any toolchain component:

1. Create a branch `toolchain/update-<component>-<version>`.
2. Update the version in `libs.versions.toml` and/or `gradle-wrapper.properties`.
3. Run the full CI pipeline (`./gradlew build allTests`).
4. Run the [post-release smoke procedure](post-release-smoke-procedure.md)
   on all platforms.
5. Update this document with the new verified versions.
6. Merge via pull request with at least one approval.
