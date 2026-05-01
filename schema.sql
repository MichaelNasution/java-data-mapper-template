-- SQL schema for DummyEntity table
-- Table name: dummy
-- Columns: id (int, primary key), name (varchar)

CREATE TABLE IF NOT EXISTS dummy (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
