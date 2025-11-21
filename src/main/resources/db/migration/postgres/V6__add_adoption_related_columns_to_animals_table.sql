ALTER TABLE animals
    ADD COLUMN adopted_by_id UUID NULL REFERENCES users (id);

CREATE TABLE adoption_requests (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    animal_id UUID NOT NULL REFERENCES animals(id),
    adopter_id UUID NOT NULL REFERENCES users (id)
);