CREATE TABLE chat_room (
                           id SERIAL PRIMARY KEY,
                           created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                           updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                           last_message_at TIMESTAMPTZ,
                           type VARCHAR(20) NOT NULL DEFAULT 'DIRECT',
                           ong_id INT NOT NULL,
                           user_id INT NOT NULL,
                           UNIQUE (ong_id, user_id),
                           CONSTRAINT fk_chatroom_ong FOREIGN KEY (ong_id) REFERENCES ongs (id) ON DELETE CASCADE,
                           CONSTRAINT fk_chatroom_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE chat_message (
                              id SERIAL PRIMARY KEY,
                              chat_id INT NOT NULL,
                              sender_type VARCHAR(20) NOT NULL CHECK (sender_type IN ('ONG', 'USER')),
                              sender_id INT NOT NULL,
                              message TEXT,
                              message_type VARCHAR(20) NOT NULL DEFAULT 'TEXT',
                              media_url TEXT,
                              created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                              CONSTRAINT fk_chatmessage_chat FOREIGN KEY (chat_id) REFERENCES chat_room (id) ON DELETE CASCADE
);

CREATE TABLE chat_participant (
                                  id SERIAL PRIMARY KEY,
                                  chat_id INT NOT NULL,
                                  participant_type VARCHAR(20) NOT NULL CHECK (participant_type IN ('ONG', 'USER')),
                                  participant_id INT NOT NULL,
                                  joined_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                  CONSTRAINT fk_chatparticipant_chat FOREIGN KEY (chat_id) REFERENCES chat_room (id) ON DELETE CASCADE,
                                  UNIQUE (chat_id, participant_type, participant_id)
);

CREATE TABLE device_token (
                              id SERIAL PRIMARY KEY,
                              user_id INT,
                              ong_id INT,
                              token TEXT NOT NULL,
                              created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                              updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                              CONSTRAINT fk_devicetoken_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                              CONSTRAINT fk_devicetoken_ong FOREIGN KEY (ong_id) REFERENCES ongs (id) ON DELETE CASCADE,
                              UNIQUE (user_id, token),
                              UNIQUE (ong_id, token)
);
