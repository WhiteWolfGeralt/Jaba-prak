package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;

import java.util.List;

public interface PersonDAO {
    void addPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(Person person);

    List<Person> getPersonByName(String personName);
    Person getPersonById(Integer personId);
    List<Person> getPersonAll();
}
