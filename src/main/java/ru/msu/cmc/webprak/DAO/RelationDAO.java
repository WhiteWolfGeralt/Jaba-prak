package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;

import java.util.List;

public interface RelationDAO {
    void addRelation(Relation relation);
    void updateRelation(Relation relation);
    void deleteRelation(Relation relation);

    Relation getRelationById(Long relationId);
    List<Relation> getRelationAll();

    List<Person> getAllByRelType(Long personId, Relation.RelType type);
}
