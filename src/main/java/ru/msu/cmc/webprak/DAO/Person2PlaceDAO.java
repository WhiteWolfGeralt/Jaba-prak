package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Person2Place;
import ru.msu.cmc.webprak.models.Place;

import java.util.List;

public interface Person2PlaceDAO extends CommonDAO<Person2Place, Long> {
    List<Place> getAllPlaces(Long id);
}
