DROP TABLE IF EXISTS relation CASCADE;
CREATE TABLE relation (
	relation_id       SERIAL     PRIMARY KEY,
	target_person     integer    NOT NULL,     
	perform_person    integer    NOT NULL,
	type_of_relation  integer    NOT NULL,
	start_of_relation date,
	end_of_relation   date
);