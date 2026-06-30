# Frontend 設計

React Router v7（フレームワークモード）を使った、**コンテナ・プレゼンテーション方式 + SSR** のフロントエンド設計。

---

## 技術スタック

| 項目 | 採用 |
|------|------|
| フレームワーク | React Router v7（フレームワークモード） |
| レンダリング | SSR（`loader` 関数） |
| ルーティング | `src/routes.ts`（1ファイルで URL 管理） |
| 言語 | TypeScript |
| BE ポート | 8080（SSR 時は `BE_API_URL` で直接接続） |
| FE dev server | 3000 |

---

## ディレクトリ構成

```
frontend/
├── src/
│   ├── routes.ts                    # URL パス定義（1ファイル）
│   ├── root.tsx                     # HTML シェル
│   │
│   ├── routes/                      # コンテナファイル群
│   │   └── tasks.tsx
│   │
│   └── features/                    # 機能単位の実装
│       └── tasks/
│           ├── index.tsx            # プレゼンター（表示のみ）
│           ├── components/
│           ├── functions/
│           │   ├── ssr-api.ts       # SSR 時の BE アクセス
│           │   └── csr-api.ts       # クライアント側の BE アクセス
│           ├── hooks/
│           ├── types/
│           └── styles/
│
├── react-router.config.ts
└── package.json
```

---

## 各レイヤーの責務

| ファイル / ディレクトリ | 責務 |
|------------------------|------|
| `routes.ts` | URL パスとコンテナファイルのマッピング |
| `routes/*.tsx` | コンテナ（SSR 処理 → フックス → プレゼンターへ props 渡し） |
| `features/*/index.tsx` | プレゼンター（props を受け取って表示するだけ） |
| `features/*/functions/ssr-api.ts` | SSR 時の BE API 通信 |
| `features/*/functions/csr-api.ts` | クライアント側の BE API 通信 |
| `features/*/hooks/` | データ加工（loader から呼ぶ純粋関数） |
| `features/*/components/` | 画面内で再利用する UI 部品 |

---

## リクエストフロー（/tasks）

```
1. ユーザーが /tasks にアクセス
          ↓
2. routes.ts が "./routes/tasks.tsx" にマッピング
          ↓
3. routes/tasks.tsx の loader() が実行（サーバー側）
   ├─ features/tasks/functions/ssr-api.ts で BE にアクセス
   ├─ features/tasks/hooks/use-tasks.ts でデータ加工
   └─ 加工済み props を return
          ↓
4. routes/tasks.tsx の default コンポーネント
   useLoaderData() → <Presenter {...props} />
          ↓
5. features/tasks/index.tsx（Presenter）が表示
```

---

## よく使うコマンド

```bash
cd frontend

npm install                        # 初回のみ
npm run generate-api-types         # OpenAPI から TS 型生成
npm run dev                        # 開発サーバー（:3000）
npm run build                      # 本番ビルド
npm run start                      # 本番 SSR サーバー起動
```

---

## OpenAPI 型生成

- 入力: `../backend/src/main/resources/openapi.yaml`
- 出力: `src/types/generated/`

```bash
npm run generate-api-types
```

---

## 環境変数

`.env`:

```
BE_API_URL=http://localhost:8080
```

SSR の loader は Vite プロキシを使えないため、Node から BE へ直接接続する。

---

## 新機能の追加手順

1. `src/features/[機能名]/` を作成
2. `src/routes/[機能名].tsx`（コンテナ）を作成
3. `src/routes.ts` に URL を追加

```typescript
route("/新しいパス", "./routes/新機能.tsx"),
```
