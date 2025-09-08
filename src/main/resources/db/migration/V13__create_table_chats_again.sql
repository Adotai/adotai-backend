CREATE TABLE chat_room (
                           id SERIAL PRIMARY KEY,
                           user_id INT NOT NULL REFERENCES account(id) ON DELETE CASCADE,
                           ong_id INT NOT NULL REFERENCES account(id) ON DELETE CASCADE,
                           created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                           updated_at TIMESTAMPTZ,
                           CONSTRAINT uq_chat_room UNIQUE(user_id, ong_id)
);

-- 6️⃣ Criar chat_message
CREATE TABLE chat_message (
                              id SERIAL PRIMARY KEY,
                              chat_id INT NOT NULL REFERENCES chat_room(id) ON DELETE CASCADE,
                              sender_id INT NOT NULL REFERENCES account(id) ON DELETE CASCADE,
                              content TEXT,
                              content_type VARCHAR(20) NOT NULL DEFAULT 'TEXT',
                              created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                              edited_at TIMESTAMPTZ,
                              deleted_at TIMESTAMPTZ
);

-- 7️⃣ Criar chat_attachment
CREATE TABLE chat_attachment (
                                 id SERIAL PRIMARY KEY,
                                 message_id INT NOT NULL REFERENCES chat_message(id) ON DELETE CASCADE,
                                 url VARCHAR(255) NOT NULL,
                                 mime_type VARCHAR(100),
                                 width INT,
                                 height INT,
                                 size_bytes BIGINT
);

-- 8️⃣ Criar chat_receipt (usando sender_id)
CREATE TABLE chat_receipt (
                              message_id INT NOT NULL REFERENCES chat_message(id) ON DELETE CASCADE,
                              sender_id INT NOT NULL REFERENCES account(id) ON DELETE CASCADE,
                              status VARCHAR(12) NOT NULL,
                              delivered_at TIMESTAMPTZ,
                              read_at TIMESTAMPTZ,
                              PRIMARY KEY (message_id, sender_id)
);