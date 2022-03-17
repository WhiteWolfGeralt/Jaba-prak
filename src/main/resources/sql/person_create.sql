DROP TABLE IF EXISTS person CASCADE;
CREATE TABLE person (
	person_id      SERIAL    PRIMARY KEY,
	person_name    text      NOT NULL,     
	gender         text      NOT NULL,
	date_of_birth  integer,
	date_of_death  integer,
	characteristic text
);