#!/usr/bin/env bash
set -euo pipefail

ERRORS=0

echo "=== Security Audit ==="

# 1. Check for hardcoded credentials (literal assignment to sensitive variable names)
echo ""
echo "--- Checking for hardcoded credentials ---"
SCAN_DIRS=("composeApp/src" "modules")
for dir in "${SCAN_DIRS[@]}"; do
  if [ -d "$dir" ]; then
    MATCHES=$(grep -rniE \
      '(password|passwd|secret|api_key|apikey|private_key)\s*=\s*"[^"$\\]{6,}"' \
      "$dir" --include="*.kt" --include="*.kts" 2>/dev/null || true)
    if [ -n "$MATCHES" ]; then
      echo "ERROR: Potential hardcoded credential found:"
      echo "$MATCHES"
      ERRORS=$((ERRORS + 1))
    fi
  fi
done

# 2. Check workflow files for hardcoded secrets
echo ""
echo "--- Checking workflow files for hardcoded secrets ---"
WORKFLOW_MATCHES=$(grep -rniE \
  '(password|secret|token|key)\s*:\s*['\''"][^'\''"\$\\]{6,}['\''"]' \
  .github/workflows/ 2>/dev/null || true)
if [ -n "$WORKFLOW_MATCHES" ]; then
  echo "ERROR: Potential hardcoded secret in workflow file:"
  echo "$WORKFLOW_MATCHES"
  ERRORS=$((ERRORS + 1))
fi

# 3. Check that .gitignore covers sensitive file types
echo ""
echo "--- Checking .gitignore coverage ---"
SENSITIVE_TYPES=("local.properties" "*.keystore" "*.jks" "*.p12")
for entry in "${SENSITIVE_TYPES[@]}"; do
  if ! grep -qF "$entry" .gitignore 2>/dev/null; then
    echo "WARNING: .gitignore does not include '$entry'"
    ERRORS=$((ERRORS + 1))
  fi
done

# 4. Check that no secrets files are tracked by git
echo ""
echo "--- Checking for tracked sensitive files ---"
TRACKED_SECRETS=$(git ls-files | grep -iE '\.(keystore|jks|p12|pem|pfx)$' 2>/dev/null || true)
if [ -n "$TRACKED_SECRETS" ]; then
  echo "ERROR: Sensitive files are tracked by git:"
  echo "$TRACKED_SECRETS"
  ERRORS=$((ERRORS + 1))
fi

echo ""
if [ "$ERRORS" -gt 0 ]; then
  echo "Security audit completed with $ERRORS issue(s)."
  exit 1
fi

echo "Security audit passed."
