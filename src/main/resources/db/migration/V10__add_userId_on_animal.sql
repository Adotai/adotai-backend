ALTER TABLE animals ADD COLUMN user_id Integer;
ALTER TABLE animals ADD CONSTRAINT id_user FOREIGN KEY (user_id) REFERENCES users(id);