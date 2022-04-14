package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.Person2PlaceDAO;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Person2Place;
import ru.msu.cmc.webprak.models.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class Person2PlaceDAOImpl extends CommonDAOImpl<Person2Place, Long> implements Person2PlaceDAO {

    public Person2PlaceDAOImpl() {
        super(Person2Place.class);
    }

    @Override
    public List<Place> getAllPlaces(Long id) {
        List<Place> ret = new ArrayList<>();
        for (Person2Place person2Place : getAll()) {
            if (Objects.equals(person2Place.getPerson().getId(), id)) {
                ret.add(person2Place.getPlace());
            }
        }
        return ret;
    }
}
