INSERT INTO "tasks" ("title", "description", "status", "created_at", "updated_at") VALUES
('タスク1', 'これは最初のタスクです。サンプルデータとして追加されました。', 'TODO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('タスク2', '進行中のタスクのサンプルです。', 'IN_PROGRESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('タスク3', '完了したタスクのサンプルです。', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 動作確認用: ユーザー 10 件
INSERT INTO "users" ("name", "email", "created_at") VALUES
('ユーザー0001', 'user0001@example.com', CURRENT_TIMESTAMP),
('ユーザー0002', 'user0002@example.com', CURRENT_TIMESTAMP),
('ユーザー0003', 'user0003@example.com', CURRENT_TIMESTAMP),
('ユーザー0004', 'user0004@example.com', CURRENT_TIMESTAMP),
('ユーザー0005', 'user0005@example.com', CURRENT_TIMESTAMP),
('ユーザー0006', 'user0006@example.com', CURRENT_TIMESTAMP),
('ユーザー0007', 'user0007@example.com', CURRENT_TIMESTAMP),
('ユーザー0008', 'user0008@example.com', CURRENT_TIMESTAMP),
('ユーザー0009', 'user0009@example.com', CURRENT_TIMESTAMP),
('ユーザー0010', 'user0010@example.com', CURRENT_TIMESTAMP);

-- user_subsidies: user_id=1 は複数行（CSV は代表として id 最大 = verified）、user_id=3 は1件、他ユーザーは未紐付け
INSERT INTO "user_subsidies" ("user_id", "document_status") VALUES
(1, 'pending'),
(2, 'verified'),
(3, 'submitted');
