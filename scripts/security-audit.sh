#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

if command -v rg >/dev/null 2>&1; then
  SEARCH_CMD="rg"
elif command -v grep >/dev/null 2>&1; then
  SEARCH_CMD="grep"
else
  echo "ERROR: either rg or grep is required for this script."
  exit 1
fi

ERRORS=0
ANDROID_MANIFEST="androidApp/src/main/AndroidManifest.xml"

check_literal() {
  local file="$1"
  local literal="$2"
  local message="$3"
  if [ "$SEARCH_CMD" = "rg" ]; then
    if ! rg -q --fixed-strings "$literal" "$file"; then
      echo "ERROR: $message ($file)"
      ERRORS=$((ERRORS + 1))
    fi
  else
    if ! grep -qF "$literal" "$file"; then
      echo "ERROR: $message ($file)"
      ERRORS=$((ERRORS + 1))
    fi
  fi
}

search_regex() {
  local pattern="$1"
  shift
  if [ "$SEARCH_CMD" = "rg" ]; then
    rg -n "$pattern" "$@" || true
  else
    grep -REn "$pattern" "$@" || true
  fi
}

search_http_hits() {
  if [ "$SEARCH_CMD" = "rg" ]; then
    rg -n "http://" composeApp/src modules | rg -v "schemas.android.com|www.w3.org|www.apple.com/DTDs" || true
  else
    grep -REn "http://" composeApp/src modules | grep -Ev "schemas.android.com|www.w3.org|www.apple.com/DTDs" || true
  fi
}

search_project_repo_hits() {
  if [ "$SEARCH_CMD" = "rg" ]; then
    rg -n "^[[:space:]]*repositories[[:space:]]*\\{" --glob '**/build.gradle.kts' . || true
  else
    grep -REn "^[[:space:]]*repositories[[:space:]]*\\{" --include='*build.gradle.kts' . || true
  fi
}

search_secret_hits() {
  search_regex "(AKIA[0-9A-Z]{16}|ghp_[A-Za-z0-9]{36}|AIza[0-9A-Za-z\\-_]{35}|-----BEGIN [A-Z ]+ PRIVATE KEY-----)" \
    composeApp modules gradle .github scripts build.gradle.kts settings.gradle.kts gradle.properties
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

check_literal "$ANDROID_MANIFEST" "android:allowBackup=\"false\"" \
  "Android backup must be disabled"
check_literal "$ANDROID_MANIFEST" "android:fullBackupContent=\"false\"" \
  "Android full backup must be disabled"
check_literal "$ANDROID_MANIFEST" "android:usesCleartextTraffic=\"false\"" \
  "Cleartext traffic must be disabled"

check_literal "composeApp/src/webMain/resources/index.html" "Content-Security-Policy" \
  "Web entrypoint must define CSP"
check_literal "composeApp/src/webMain/resources/index.html" "Permissions-Policy" \
  "Web entrypoint must define Permissions-Policy"
check_literal "composeApp/src/webMain/resources/index.html" "name=\"referrer\"" \
  "Web entrypoint must define referrer policy"

SECRET_HITS="$(search_secret_hits)"
if [ -n "$SECRET_HITS" ]; then
  echo "ERROR: potential secret material detected:"
  echo "$SECRET_HITS"
  ERRORS=$((ERRORS + 1))
fi

HTTP_HITS="$(search_http_hits)"
if [ -n "$HTTP_HITS" ]; then
  echo "ERROR: insecure http:// usage detected:"
  echo "$HTTP_HITS"
  ERRORS=$((ERRORS + 1))
fi

PROJECT_REPO_HITS="$(search_project_repo_hits)"
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
