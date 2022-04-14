package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Place;

import java.util.List;

public interface PlaceDAO extends CommonDAO<Place, Long> {
    List<Person> personsFromPlace(Place place);
}
