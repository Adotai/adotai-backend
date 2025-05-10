ALTER TABLE breeds
ADD COLUMN species_id INTEGER NOT NULL;

ALTER TABLE breeds
ADD CONSTRAINT fk_species_id
FOREIGN KEY (species_id)
REFERENCES species(id);

ALTER TABLE breeds
DROP COLUMN species;
