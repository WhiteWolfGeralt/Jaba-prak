package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;

import java.util.List;

public interface RelationDAO extends CommonDAO<Relation, Long> {

    List<Person> getPerformByRelType(Long personId, Relation.RelType type);
    List<Person> getTargetByRelType(Long personId, Relation.RelType type);
}
