# Backend（Java + Spring Boot + OpenAPI）

## 概要

- Java 21 / Spring Boot 3.5.8 / Gradle Groovy
- Spring Data JPA（Hibernate）+ H2（`ddl-auto: update` で Task / TestTodo を管理）
- OpenAPI YAML から API インターフェース・モデルを自動生成
- 設定ファイル: `src/main/resources/application.yaml`（ポート **8080**）

## アーキテクチャ

```
controller/  →  service/  →  repository/  →  domain/ (JPA Entity)
     ↓
  mapper/    Domain ↔ OpenAPI 生成モデル変換
```

- `users` / `user_subsidies` テーブルは `schema.sql` + `data.sql` で管理（JPA Entity なし）

## よく使うコマンド

```bash
cd backend

# OpenAPI から Java interface / model を生成
./gradlew openApiGenerate

# クリーン後に再生成
./gradlew clean openApiGenerate

# ビルド（compile 前に openApiGenerate が実行される）
./gradlew build

# 起動（http://localhost:8080）
./gradlew bootRun
```

## OpenAPI

- 仕様の正: `src/main/resources/openapi.yaml`
- 分割定義: `src/main/resources/components/`
- 生成先: `build/generated/src/main/java/com/example/generated/`

Controller は生成された interface（例: `TasksApi`）を `implements` して実装する。

## 依存関係（OpenAPI 生成）

`build.gradle` に以下を設定:

- プラグイン: `org.openapi.generator` 7.5.0
- generator: `spring`（`interfaceOnly: true`）
- 追加: `org.openapitools:jackson-databind-nullable`（生成モデル用）
