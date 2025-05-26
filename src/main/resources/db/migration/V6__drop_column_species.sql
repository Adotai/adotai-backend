ALTER TABLE animals
ADD COLUMN species_id INTEGER NOT NULL;

ALTER TABLE animals
ADD CONSTRAINT fk_species_id
FOREIGN KEY (species_id)
REFERENCES species(id);

ALTER TABLE animals
DROP COLUMN species;
