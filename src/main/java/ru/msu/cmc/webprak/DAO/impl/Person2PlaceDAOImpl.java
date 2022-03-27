package ru.msu.cmc.webprak.DAO.impl;

import ru.msu.cmc.webprak.DAO.Person2PlaceDAO;
import ru.msu.cmc.webprak.models.Person2Place;

public class Person2PlaceDAOImpl extends CommonDAOImpl<Person2Place, Long> implements Person2PlaceDAO {

    public Person2PlaceDAOImpl() {
        super(Person2Place.class);
    }
}
