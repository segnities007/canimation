# canimation ツールチェーン固定表

- 文書ID: `CP-TOOL-001`
- 目的: 再現性を担保するため、開発・CI環境のバージョンを固定する
- 変更ルール: 1項目でも更新する場合は CR 必須

## 1. 開発環境固定

- OS:
  - Linux x86_64
  - macOS arm64/x86_64
- JDK: `21.0.6`
- Gradle Wrapper: `8.10.2`
- Kotlin: `2.1.20`
- Compose Multiplatform Plugin: `1.8.0`
- Android Gradle Plugin: `8.8.0`
- Kover Plugin: `0.8.3`
- Android Compile SDK: `35`
- Android Min SDK: `24`
- Xcode: `16.2`
- Node.js: `22.14.0`

## 2. CI環境固定

- Runner matrix:
  - `ubuntu-24.04`（共通 build/test/check）
  - `macos-15`（iOS compile/test と Xcode 検証専用）
- JDK distribution: `temurin`
- Node.js: `22.14.0`
- Ruby/Fastlane: 利用しない

## 3. バージョン固定ファイル

- `gradle/wrapper/gradle-wrapper.properties`
- `gradle/libs.versions.toml`
- `gradle.properties`
- `build.gradle.kts`（root、Kover 設定）
- `.github/workflows/pr.yml`
- `.github/workflows/release.yml`

## 4. 検証コマンド

- `./gradlew --version`
- `./gradlew -q dependencies`
- `node -v`
- `java -version`
- `xcodebuild -version`（macOS runner のみ）

## 5. 完了条件

- Linux と macOS の CI ログで固定値一致（runner matrix 差異なし）
- 上記検証コマンド結果を `docs/toolchain-verification.md` に保存
