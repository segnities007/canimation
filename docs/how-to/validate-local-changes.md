# Validate Local Changes

## Goal

docs-only 変更と repository-wide 変更で、現在の validation baseline を迷わず選ぶ。

## 1. Docs and governance only

`docs/`, `README`, governance 文書の整合確認はまず次から始める。

```bash
bash scripts/validate-governance-docs.sh
```

この script は CI でも使われる docs/governance alignment の最短チェックである。

## 2. Core library tests and compiles

library の baseline は次を使う。

```bash
./gradlew allTests compileLibraryAndroid compileLibraryJvm --max-workers=2 --no-daemon
./gradlew compileLibraryApple compileLibraryWeb --max-workers=2 --no-daemon
```

## 3. App and release checks

showcase app と release readiness は次でそろえる。

```bash
./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :androidApp:assembleDebug --max-workers=2 --no-daemon
./gradlew releaseReadiness --max-workers=2 --no-daemon
```

## 4. Security and workflow checks

workflow / governance 周辺まで含める場合は次を追加する。

```bash
bash scripts/security-audit.sh
bash scripts/validate-workflows.sh
```

## Related

- [Contributor Quickstart](../tutorials/contributor-quickstart.md)
- [Implementation Overview](../explanation/architecture/implementation-overview.md)
- [Ideal Architecture Blueprint](../reference/architecture/ideal-architecture-blueprint.md)
- [Documentation Index](../README.md)
