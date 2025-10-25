-- 1️⃣ Criar tabela base account
CREATE TABLE account (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         password VARCHAR(255) NOT NULL
);

-- 2️⃣ Alterar tabela users
ALTER TABLE users
DROP COLUMN name,
DROP COLUMN email,
DROP COLUMN password;

-- Ajustar id como FK para account
ALTER TABLE users
    ADD CONSTRAINT fk_users_account
        FOREIGN KEY (id) REFERENCES account(id) ON DELETE CASCADE;

-- 3️⃣ Alterar tabela ongs
ALTER TABLE ongs
DROP COLUMN name,
DROP COLUMN email,
DROP COLUMN password;

-- Ajustar id como FK para account
ALTER TABLE ongs
    ADD CONSTRAINT fk_ongs_account
        FOREIGN KEY (id) REFERENCES account(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS chat_participant;
DROP TABLE IF EXISTS device_token;
DROP TABLE IF EXISTS chat_message;
DROP TABLE IF EXISTS chat_room;



