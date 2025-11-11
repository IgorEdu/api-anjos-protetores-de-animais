ALTER TABLE races
    ADD COLUMN species_id UUID NOT NULL;

ALTER TABLE races
    ADD CONSTRAINT fk_races_species FOREIGN KEY (species_id) REFERENCES species(id);