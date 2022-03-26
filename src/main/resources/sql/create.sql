DROP TABLE IF EXISTS person CASCADE;
CREATE TABLE person (
    person_id      serial    PRIMARY KEY,
    person_name    text      NOT NULL,
    gender         text      NOT NULL,
    date_of_birth  integer,
    date_of_death  integer,
    characteristic text
);

DROP TABLE IF EXISTS relation CASCADE;
CREATE TABLE relation (
    relation_id       serial     PRIMARY KEY,
    target_person     integer    NOT NULL REFERENCES person (person_id) ON DELETE CASCADE,
    perform_person    integer    NOT NULL REFERENCES person (person_id) ON DELETE CASCADE,
    type_of_relation  integer    NOT NULL,
    start_of_relation integer,
    end_of_relation   integer
);

DROP TABLE IF EXISTS person2place CASCADE;
CREATE TABLE person2place (
    node_id serial     PRIMARY KEY,
    person  integer    NOT NULL REFERENCES person (person_id) ON DELETE CASCADE,
    place   integer    NOT NULL REFERENCES person (person_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS place CASCADE;
CREATE TABLE place (
    place_id    serial PRIMARY KEY,
    name        text   NOT NULL,
    description text   NOT NULL
);
