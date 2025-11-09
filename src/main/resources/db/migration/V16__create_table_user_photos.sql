CREATE TABLE user_photos (
                             id SERIAL PRIMARY KEY,
                             user_id INT NOT NULL,
                             photo_url VARCHAR(255) NOT NULL,
                             CONSTRAINT fk_user_photos_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
