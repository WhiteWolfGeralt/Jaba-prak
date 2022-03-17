INSERT INTO relation (target_person, perform_person, type_of_relation, start_of_relation, end_of_relation) VALUES
    /* Tемерия */
    (2, 1, 0, 1232, NULL),

    /* Цинтра */
    (3, 4, 1, 1251, NULL),
    (3, 5, 1, 1235, 1250),
    (6, 3, 0, 1236, NULL),
    (6, 5, 0, 1236, NULL),
    (7, 6, 0, 1252, NULL),
    (7, 8, 0, 1252, NULL),

    /* Лирия и Ривия */
    (9, 10, 1, NULL, 1259),
    (11, 9, 0, 1251, NULL),
    (11, 10, 0, 1251, NULL),
    (12, 9, 0, 1250, NULL),
    (12, 10, 0, 1250, NULL);