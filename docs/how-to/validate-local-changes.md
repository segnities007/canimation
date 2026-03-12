# Validate Local Changes

## Goal

docs-only 変更と repository-wide 変更で、現在の validation baseline を迷わず選ぶ。

## 1. Docs and governance only

`docs/`, `README`, governance 文書の整合確認はまず次から始める。

```bash
bash scripts/validate-governance-docs.sh
```

この script は CI でも使われる docs/governance alignment の最短チェックである。

## 2. Core test and coverage baseline

library の baseline は次を使う。

```bash
./gradlew allTests --max-workers=2 --no-daemon
./gradlew :koverHtmlReport :koverXmlReport --max-workers=2 --no-daemon
```

`allTests` と Kover は CI と同じく別 invocation で流す。

## 3. Platform and app checks

platform compile と showcase host compile は次でそろえる。

```bash
./gradlew compileLibraryAndroid compileLibraryJvm :androidApp:assembleDebug :composeApp:compileKotlinJvm --max-workers=2 --no-daemon
./gradlew compileLibraryApple :composeApp:packageDistributionForCurrentOS :composeApp:linkDebugFrameworkIosSimulatorArm64 --max-workers=2 --no-daemon
./gradlew compileLibraryWeb :composeApp:compileKotlinWasmJs --max-workers=2 --no-daemon
```

## 4. Release and audit checks

release / workflow / security まで含める場合は次を追加する。

```bash
./gradlew releaseReadiness --max-workers=2 --no-daemon
bash scripts/security-audit.sh
bash scripts/validate-workflows.sh
bash scripts/validate-governance-docs.sh
```

docs / governance のみを触った場合は、最短では次だけでもよい。

```bash
bash scripts/validate-workflows.sh
bash scripts/validate-governance-docs.sh
```

## Related

- [Contributor Quickstart](../tutorials/contributor-quickstart.md)
- [Implementation Overview](../explanation/architecture/implementation-overview.md)
- [Ideal Architecture Blueprint](../reference/architecture/ideal-architecture-blueprint.md)
- [Documentation Index](../README.md)
