ALTER TABLE animals ADD COLUMN size VARCHAR(20) NOT NULL;

ALTER TABLE animals ADD CONSTRAINT animal_sizes CHECK (size IN ('pequeno', 'medio', 'grande'));
