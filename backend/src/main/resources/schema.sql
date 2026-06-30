CREATE TABLE IF NOT EXISTS "tasks" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "title" VARCHAR(255) NOT NULL,
    "description" TEXT,
    "status" VARCHAR(50) NOT NULL,
    "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "users" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "status" VARCHAR(20) NOT NULL DEFAULT 'active',
    "gender" VARCHAR(10),
    "role" VARCHAR(10) NOT NULL DEFAULT 'user'
);

-- ユーザー補助金（users.id を外部キー。ユーザーごと複数行あり得るため CSV では id 最大の1件を代表とする）
CREATE TABLE IF NOT EXISTS "user_subsidies" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "user_id" BIGINT NOT NULL,
    "document_status" VARCHAR(50),
    "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "fk_user_subsidies_user" FOREIGN KEY ("user_id") REFERENCES "users" ("id")
);
CREATE INDEX IF NOT EXISTS "idx_user_subsidies_user_id" ON "user_subsidies" ("user_id");
