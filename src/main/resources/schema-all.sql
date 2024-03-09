CREATE SCHEMA ors;
CREATE TABLE IF NOT EXISTS ors.category (code NUMERIC(3) PRIMARY KEY,  categoryText VARCHAR(200), displayLevel NUMERIC(2), selectable VARCHAR(1)  );
CREATE TABLE IF NOT EXISTS ors.additive (code NUMERIC(3) PRIMARY KEY,  additiveText VARCHAR(200), displayLevel NUMERIC(2), selectable VARCHAR(1)  );

CREATE TABLE IF NOT EXISTS ors.industry (code serial PRIMARY KEY,  industrytext VARCHAR(200), displayLevel NUMERIC(2), selectable VARCHAR(1)  );
