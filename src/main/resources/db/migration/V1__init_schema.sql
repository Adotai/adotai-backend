CREATE TABLE address (
    id       SERIAL       PRIMARY KEY,
    street   VARCHAR(100) NOT NULL,
    number   INTEGER      NOT NULL,
    city     VARCHAR(100) NOT NULL,
    state    VARCHAR(2)   NOT NULL,
    zip_code VARCHAR(20)  NOT NULL,

    CONSTRAINT state_check CHECK (
        state IN (
            'AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG',
            'PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO'
        )
    )
);

CREATE TABLE colors (
    id   SERIAL      PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE breeds (
    id      SERIAL       PRIMARY KEY,
    species VARCHAR(10)  NOT NULL CHECK (species IN ('Dog', 'Cat')),
    name    VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE ongs (
    id               SERIAL       PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    phone            VARCHAR(20),
    cnpj             VARCHAR(20)  UNIQUE NOT NULL,
    email            VARCHAR(100) UNIQUE NOT NULL,
    password         VARCHAR(100) NOT NULL,
    social_statute   TEXT         NOT NULL,
    board_meeting    TEXT         NOT NULL,
    status           BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ong_animal_type (
    ong_id      INTEGER     NOT NULL REFERENCES ongs(id) ON DELETE CASCADE,
    animal_type VARCHAR(10) NOT NULL,

    CONSTRAINT animal_type_check CHECK (animal_type IN ('cat', 'dog')),
    PRIMARY KEY (ong_id, animal_type)
);

CREATE TABLE users (
    id         SERIAL    PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    cpf        VARCHAR(30)  UNIQUE,
    email      VARCHAR(150) NOT NULL UNIQUE,
    role       VARCHAR(10)  DEFAULT 'normal',
    password   VARCHAR(100) NOT NULL,
    telephone  VARCHAR(30)  NOT NULL,
    address_id INTEGER      NOT NULL,
    created_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT role_types CHECK (role IN ('admin','normal')),
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE animals (
    id          SERIAL      PRIMARY KEY,
    ong_id      INTEGER     NOT NULL REFERENCES ongs(id),
    name        VARCHAR(50) NOT NULL,
    gender      VARCHAR(10) NOT NULL,
    color_id    INTEGER     NOT NULL REFERENCES colors(id),
    breed_id    INTEGER     NOT NULL REFERENCES breeds(id),
    age         INTEGER,
    health      VARCHAR(20) NOT NULL,
    status      BOOLEAN     NOT NULL DEFAULT TRUE,
    vaccinated  BOOLEAN     NOT NULL DEFAULT TRUE,
    neutered    BOOLEAN     NOT NULL DEFAULT TRUE,
    dewormed    BOOLEAN     NOT NULL DEFAULT TRUE,
    temperament VARCHAR(20) NOT NULL,
    species     VARCHAR(20) NOT NULL,
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT gender_type CHECK (gender IN ('male', 'female', 'unknown')),
    CONSTRAINT health_status CHECK (health IN ('healthy', 'sick', 'disabled', 'recovering', 'unknown')),
    CONSTRAINT temperament_type CHECK (temperament IN (
        'calm', 'playful', 'aggressive', 'shy', 'protective', 'sociable', 'independent', 'unknown'
    )),
    CONSTRAINT species_type CHECK (species IN('cat','dog'))
);

CREATE TABLE user_prefer (
    id           SERIAL PRIMARY KEY,
    user_id      INTEGER NOT NULL REFERENCES users(id),
    species      VARCHAR(5),
    gender       VARCHAR(5),
    color_id     INTEGER REFERENCES colors(id),
    temperament  VARCHAR(20),

    CONSTRAINT temperament_type CHECK (temperament IN (
        'calm', 'playful', 'aggressive', 'shy', 'protective', 'sociable', 'independent', 'unknown'
    )),
    CONSTRAINT species_type CHECK (species IN ('cat','dog','anyone')),
    CONSTRAINT gender_type CHECK (gender IN ('male','female'))
);

CREATE TABLE animal_photos (
    id         SERIAL  PRIMARY KEY,
    animal_id  INTEGER NOT NULL REFERENCES animals(id),
    photo_url  TEXT    NOT NULL
);

-- TRIGGER FUNCTION FOR updated_at
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- TRIGGERS FOR AUTOMATIC updated_at
CREATE TRIGGER trigger_update_animals
BEFORE UPDATE ON animals
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER trigger_update_users
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER trigger_update_ongs
BEFORE UPDATE ON ongs
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
