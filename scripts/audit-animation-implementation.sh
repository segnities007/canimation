#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

README_FILE="README.md"
PRESET_ENUM_FILE="modules/canimation-core/src/commonMain/kotlin/io/github/canimation/core/CanimationPreset.kt"
REGISTRY_FILE="modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets/PresetsExtensionRegistry.kt"
GALLERY_FILE="composeApp/src/commonMain/kotlin/com/segnities007/canimation/screen/PresetGalleryScreen.kt"
PRESET_DIR="modules/canimation-presets/src/commonMain/kotlin/io/github/canimation/presets"

if ! command -v rg >/dev/null 2>&1; then
  echo "ERROR: rg is required for this script."
  exit 1
fi

tmp_rows="$(mktemp)"
tmp_enum="$(mktemp)"
trap 'rm -f "$tmp_rows" "$tmp_enum"' EXIT

awk -F'|' '
  /<summary>All 83 Presets<\/summary>/ { in_table=1; next }
  /<\/details>/ { in_table=0 }
  in_table && /^\|/ {
    name=$2
    source=$3
    gsub(/`/, "", name)
    gsub(/^ +| +$/, "", name)
    gsub(/^ +| +$/, "", source)
    if (name != "" && name != "Preset" && name !~ /^-+$/) {
      print name "\t" source
    }
  }
' "$README_FILE" >"$tmp_rows"

awk '
  /enum class CanimationPreset/ { in_enum=1; next }
  in_enum && /^[[:space:]]*}/ { in_enum=0 }
  in_enum {
    if (match($0, /^[[:space:]]*([A-Za-z][A-Za-z0-9]*)[,]?[[:space:]]*$/, m)) {
      print m[1]
    }
  }
' "$PRESET_ENUM_FILE" >"$tmp_enum"

declare -A readme_source_by_name
declare -A source_total
declare -A source_impl
declare -A enum_present
declare -A readme_present

while IFS= read -r preset; do
  enum_present["$preset"]=1
done <"$tmp_enum"

readme_total=0
mismatch=0

echo "== Animation Implementation Audit =="
echo ""

while IFS=$'\t' read -r name source; do
  readme_total=$((readme_total + 1))
  readme_present["$name"]=1
  readme_source_by_name["$name"]="$source"
  source_total["$source"]=$(( ${source_total["$source"]:-0} + 1 ))

  enum_ok=1
  registry_ok=1
  file_ok=1
  gallery_ok=1

  if [ -z "${enum_present["$name"]+x}" ]; then
    enum_ok=0
  fi
  if ! rg -q "CanimationPreset\\.$name to ${name}Preset\\.spec" "$REGISTRY_FILE"; then
    registry_ok=0
  fi
  if [ ! -f "$PRESET_DIR/${name}Preset.kt" ]; then
    file_ok=0
  fi
  if ! rg -q "CanimationPreset\\.$name ->" "$GALLERY_FILE"; then
    gallery_ok=0
  fi

  if [ "$enum_ok" -eq 1 ] && [ "$registry_ok" -eq 1 ] && [ "$file_ok" -eq 1 ] && [ "$gallery_ok" -eq 1 ]; then
    source_impl["$source"]=$(( ${source_impl["$source"]:-0} + 1 ))
  else
    mismatch=$((mismatch + 1))
    echo "MISMATCH: $name (source=$source)"
    [ "$enum_ok" -eq 0 ] && echo "  - missing in CanimationPreset enum"
    [ "$registry_ok" -eq 0 ] && echo "  - missing in PresetsExtensionRegistry mapping"
    [ "$file_ok" -eq 0 ] && echo "  - missing preset file: $PRESET_DIR/${name}Preset.kt"
    [ "$gallery_ok" -eq 0 ] && echo "  - missing description mapping in PresetGalleryScreen"
  fi
done <"$tmp_rows"

while IFS= read -r preset; do
  if [ -z "${readme_present["$preset"]+x}" ]; then
    mismatch=$((mismatch + 1))
    echo "MISMATCH: enum preset '$preset' is not listed in README table"
  fi
done <"$tmp_enum"

echo ""
printf "%-18s %8s %12s %10s\n" "Source" "Total" "Implemented" "Rate"
for source in "${!source_total[@]}"; do
  total=${source_total["$source"]}
  impl=${source_impl["$source"]:-0}
  rate=$(awk -v i="$impl" -v t="$total" 'BEGIN{printf "%.1f", (t==0 ? 0 : (i/t)*100)}')
  printf "%-18s %8d %12d %9s%%\n" "$source" "$total" "$impl" "$rate"
done | sort -k2,2nr -k1,1

all_impl=0
external_total=0
external_impl=0

for source in "${!source_total[@]}"; do
  total=${source_total["$source"]}
  impl=${source_impl["$source"]:-0}
  all_impl=$((all_impl + impl))
  if [ "$source" != "Core" ]; then
    external_total=$((external_total + total))
    external_impl=$((external_impl + impl))
  fi
done

all_rate=$(awk -v i="$all_impl" -v t="$readme_total" 'BEGIN{printf "%.1f", (t==0 ? 0 : (i/t)*100)}')
external_rate=$(awk -v i="$external_impl" -v t="$external_total" 'BEGIN{printf "%.1f", (t==0 ? 0 : (i/t)*100)}')

echo ""
printf "%-18s %8d %12d %9s%%\n" "EXTERNAL" "$external_total" "$external_impl" "$external_rate"
printf "%-18s %8d %12d %9s%%\n" "ALL" "$readme_total" "$all_impl" "$all_rate"

if [ "$mismatch" -gt 0 ]; then
  echo ""
  echo "Audit failed: $mismatch mismatch(es) detected."
  exit 1
fi

echo ""
echo "Audit passed: no mismatches detected."
