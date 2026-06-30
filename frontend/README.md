# Frontend（React + TypeScript + Vite）

## 概要

- React + TypeScript + Vite
- OpenAPI 生成クライアント（typescript-fetch）で BE と通信
- 開発サーバー: **http://localhost:3000**
- API プロキシ: `/api` → `http://localhost:8080`

## よく使うコマンド

```bash
cd frontend

# 初回のみ
npm install

# OpenAPI から TypeScript 型・API クライアントを生成
npm run generate-api-types

# 開発サーバー起動
npm run dev

# 本番ビルド
npm run build
```

## OpenAPI 型生成

- 入力: `../backend/src/main/resources/openapi.yaml`
- 出力: `src/types/generated/`
- 設定: `openapi-generator-config.json`, `openapitools.json`

使用例:

```typescript
import type { Task } from './types/generated'
import { tasksApi } from './api/tasksClient'

const tasks: Task[] = await tasksApi.listTasks()
```

## 注意

- `src/types/generated/` は自動生成コード。手編集しない。
- openapi.yaml 変更後は `npm run generate-api-types` を再実行する。
