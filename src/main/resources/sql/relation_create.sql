DROP TABLE IF EXISTS relation CASCADE;
CREATE TABLE relation (
	relation_id       SERIAL     PRIMARY KEY,
	target_person     integer    NOT NULL REFERENCES person (person_id) ON DELETE CASCADE,
	perform_person    integer    NOT NULL REFERENCES person (person_id) ON DELETE CASCADE,
	type_of_relation  integer    NOT NULL,
	start_of_relation integer,
	end_of_relation   integer
);