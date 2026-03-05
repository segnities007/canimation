#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

if ! command -v rg >/dev/null 2>&1; then
  echo "ERROR: rg is required for this script."
  exit 1
fi

ERRORS=0

check_literal() {
  local file="$1"
  local literal="$2"
  local message="$3"
  if ! rg -q --fixed-strings "$literal" "$file"; then
    echo "ERROR: $message ($file)"
    ERRORS=$((ERRORS + 1))
  fi
}

echo "== Security audit =="

check_literal "settings.gradle.kts" "mavenCentral()" \
  "mavenCentral must be defined in settings.gradle.kts"
check_literal "settings.gradle.kts" "exclusiveContent {" \
  "Node.js distribution repository must be explicitly declared in settings.gradle.kts"
check_literal ".github/dependabot.yml" "package-ecosystem: \"gradle\"" \
  "Dependabot must track Gradle dependencies"
check_literal ".github/dependabot.yml" "package-ecosystem: \"github-actions\"" \
  "Dependabot must track GitHub Actions dependencies"

if [ ! -s "gradle/verification-metadata.xml" ]; then
  echo "ERROR: gradle/verification-metadata.xml must exist for dependency checksum verification"
  ERRORS=$((ERRORS + 1))
fi

check_literal "composeApp/src/androidMain/AndroidManifest.xml" "android:allowBackup=\"false\"" \
  "Android backup must be disabled"
check_literal "composeApp/src/androidMain/AndroidManifest.xml" "android:fullBackupContent=\"false\"" \
  "Android full backup must be disabled"
check_literal "composeApp/src/androidMain/AndroidManifest.xml" "android:usesCleartextTraffic=\"false\"" \
  "Cleartext traffic must be disabled"

check_literal "composeApp/src/webMain/resources/index.html" "Content-Security-Policy" \
  "Web entrypoint must define CSP"
check_literal "composeApp/src/webMain/resources/index.html" "Permissions-Policy" \
  "Web entrypoint must define Permissions-Policy"
check_literal "composeApp/src/webMain/resources/index.html" "name=\"referrer\"" \
  "Web entrypoint must define referrer policy"

SECRET_HITS="$(rg -n "(AKIA[0-9A-Z]{16}|ghp_[A-Za-z0-9]{36}|AIza[0-9A-Za-z\\-_]{35}|-----BEGIN [A-Z ]+ PRIVATE KEY-----)" \
  composeApp modules gradle .github scripts build.gradle.kts settings.gradle.kts gradle.properties || true)"
if [ -n "$SECRET_HITS" ]; then
  echo "ERROR: potential secret material detected:"
  echo "$SECRET_HITS"
  ERRORS=$((ERRORS + 1))
fi

HTTP_HITS="$(rg -n "http://" composeApp/src modules \
  | rg -v "schemas.android.com|www.w3.org|www.apple.com/DTDs" || true)"
if [ -n "$HTTP_HITS" ]; then
  echo "ERROR: insecure http:// usage detected:"
  echo "$HTTP_HITS"
  ERRORS=$((ERRORS + 1))
fi

PROJECT_REPO_HITS="$(rg -n "^[[:space:]]*repositories[[:space:]]*\\{" --glob '**/build.gradle.kts' . || true)"
if [ -n "$PROJECT_REPO_HITS" ]; then
  echo "ERROR: project-level repositories are not allowed. Declare repositories only in settings.gradle.kts:"
  echo "$PROJECT_REPO_HITS"
  ERRORS=$((ERRORS + 1))
fi

if [ "$ERRORS" -gt 0 ]; then
  echo ""
  echo "Security audit failed with $ERRORS error(s)."
  exit 1
fi

echo "Security audit passed."
