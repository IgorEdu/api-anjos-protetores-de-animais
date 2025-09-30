CREATE TABLE adopters (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL CHECK (name <> ''),
    email text NOT NULL CHECK (email <> ''),
    password text NOT NULL CHECK(password <> ''),
    phone text NULL,
    address text NULL,
    is_deleted boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE species (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL
);

CREATE TABLE races(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL,
    species_id bigint NOT NULL REFERENCES species(id)
);

CREATE TABLE animals (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL CHECK (name <> ''),
    description text NULL,
    status text NOT NULL,
    species_id bigint NOT NULL REFERENCES species(id),
    race_id bigint NOT NULL REFERENCES races(id)
);

