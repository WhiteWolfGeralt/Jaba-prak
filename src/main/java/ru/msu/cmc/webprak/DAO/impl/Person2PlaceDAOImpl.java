package ru.msu.cmc.webprak.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.Person2PlaceDAO;
import ru.msu.cmc.webprak.models.Person2Place;

@Repository
public class Person2PlaceDAOImpl extends CommonDAOImpl<Person2Place, Long> implements Person2PlaceDAO {

    public Person2PlaceDAOImpl() {
        super(Person2Place.class);
    }
}
