package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;

import java.util.List;

public interface RelationDAO extends CommonDAO<Relation, Long> {

    List<Person> getPerformByRelType(Person person, Relation.RelType type);
    List<Person> getTargetByRelType(Person person, Relation.RelType type);

    Person getMother(Person person);
    Person getFather(Person person);
    boolean bornInMarriage(Person person);
}
