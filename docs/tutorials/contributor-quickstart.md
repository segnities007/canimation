# Contributor Quickstart

## Goal

repo contributor として、current architecture・読む順番・validation baseline を最短で把握する。

## Step 1

まず documentation の入口を固定する。

1. [Documentation Index](../README.md)
2. [Implementation Overview](../explanation/architecture/implementation-overview.md)
3. [Ideal Architecture Blueprint](../reference/architecture/ideal-architecture-blueprint.md)

## Step 2

現在の repository は次の前提で読む。

- consumer-facing implementation の中心はまだ `canimation-core`
- target-state module として `canimation-tokens` / `canimation-primitives` /
  `canimation-semantics` / `canimation-recipes` / `canimation-runtime` / `canimation-a11y` が存在
- isolated surface として `canimation-presets` / `canimation-diagnostics` /
  `canimation-compat` / `canimation-experimental` / `canimation-platform-*` が分かれている
- demo shell は `composeApp`、thin host は `androidApp` と `iosApp`

## Step 3

docs や governance だけを変更したら、まず次を実行する。

```bash
bash scripts/validate-governance-docs.sh
```

## Step 4

実装や build に触れたら、CI と同じ baseline を使う。

```bash
./gradlew allTests compileLibraryAndroid compileLibraryJvm --max-workers=2 --no-daemon
./gradlew compileLibraryApple compileLibraryWeb --max-workers=2 --no-daemon
./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :androidApp:assembleDebug --max-workers=2 --no-daemon
./gradlew releaseReadiness --max-workers=2 --no-daemon
bash scripts/security-audit.sh
bash scripts/validate-workflows.sh
```

## Step 5

迷ったら docs の役割で戻る。

- tutorial -> first contact
- how-to -> task execution
- reference -> stable contract
- explanation -> current implementation reality

## Next

- [Validate Local Changes](../how-to/validate-local-changes.md)
- [How-To Guides](../how-to/README.md)
- [Ideal Architecture Blueprint](../reference/architecture/ideal-architecture-blueprint.md)
- [Implementation Overview](../explanation/architecture/implementation-overview.md)
