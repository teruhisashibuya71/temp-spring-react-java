# temp-spring-react-java

Java + TypeScript + OpenAPI のモノレポ雛形。
プロジェクト開発の雛形として利用するために作成した。

## 構成

```
temp-spring-react-java/
├── backend/          # Java + Spring Boot（ポート 8080）
├── frontend/         # React + TypeScript + React Router v7 SSR（ポート 3000）
├── project_docs/     # ドキュメント
└── scripts/          # 共通スクリプト
```

## クイックスタート

```bash
# 1. OpenAPI からコード生成（BE + FE）
./scripts/generate-api.sh

# 2. バックエンド起動
cd backend && ./gradlew bootRun

# 3. フロントエンド起動（別ターミナル）
cd frontend && npm install && npm run dev
```

- BE: http://localhost:8080
- FE: http://localhost:3000/tasks
- API 例: http://localhost:8080/api/tasks

## ドキュメント

- [backend/README.md](backend/README.md)
- [frontend/README.md](frontend/README.md)
- [project_docs/2.よく使うコマンド.md](project_docs/2.よく使うコマンド.md)

## 技術スタック

| 層 | 技術 |
|----|------|
| BE | Java 21, Spring Boot 3.5, Gradle Groovy, OpenAPI Generator (`spring`) |
| FE | React, TypeScript, React Router v7（SSR）, OpenAPI Generator (`typescript-fetch`) |
| API 仕様 | `backend/src/main/resources/openapi.yaml`（Single Source of Truth） |
