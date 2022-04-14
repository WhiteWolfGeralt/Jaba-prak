package ru.msu.cmc.webprak.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.Person2PlaceDAO;
import ru.msu.cmc.webprak.DAO.PlaceDAO;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Person2Place;
import ru.msu.cmc.webprak.models.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PlaceDAOImpl extends CommonDAOImpl<Place, Long> implements PlaceDAO {

    public PlaceDAOImpl() {
        super(Place.class);
    }
    @Autowired
    private Person2PlaceDAO person2PlaceDAO = new Person2PlaceDAOImpl();

    @Override
    public List<Person> personsFromPlace(Place place) {
        List<Person> ret = new ArrayList<>();
        for(Person2Place person2Place : person2PlaceDAO.getAll()) {
            if (Objects.equals(person2Place.getPlace().getId(), place.getId())) {
                ret.add(person2Place.getPerson());
            }
        }
        return ret;
    }
}
