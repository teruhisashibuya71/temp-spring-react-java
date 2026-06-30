#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"

(cd "$ROOT_DIR/backend" && ./gradlew openApiGenerate)
(cd "$ROOT_DIR/frontend" && npm run generate-api-types)

echo "OpenAPI code generation completed."
