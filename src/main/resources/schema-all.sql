CREATE SCHEMA ors;
CREATE TABLE IF NOT EXISTS ors.category (code serial PRIMARY KEY,  categoryText VARCHAR(200), displayLevel NUMERIC(2), selectable VARCHAR(1)  );
CREATE TABLE IF NOT EXISTS ors.industry (code serial PRIMARY KEY,  industrytext VARCHAR(200), displayLevel NUMERIC(2), selectable VARCHAR(1)  );
