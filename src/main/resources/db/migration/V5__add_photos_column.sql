CREATE TABLE ong_photos (
        id SERIAL PRIMARY KEY,
        ong_id INT NOT NULL,
        photo_url VARCHAR(255) NOT NULL,
        CONSTRAINT fk_ong_photos_ong FOREIGN KEY (ong_id) REFERENCES ongs (id) ON DELETE CASCADE
);