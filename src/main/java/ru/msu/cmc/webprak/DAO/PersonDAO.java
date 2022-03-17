package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;

import java.util.List;

public interface PersonDAO {
    void addPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(Person person);

    Person getPersonById(Long personId);
    List<Person> getPersonAll();
}
