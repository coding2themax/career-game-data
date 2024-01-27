CREATE SCHEMA ors;
CREATE TABLE IF NOT EXIST ors.category (code serial PRIMARY KEY,  categoryText VARCHAR(200), displayLevel NUMERIC(2), selectable VARCHAR(1)  );
