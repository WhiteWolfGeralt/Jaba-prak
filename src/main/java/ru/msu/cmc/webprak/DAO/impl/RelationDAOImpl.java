package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.RelationDAO;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RelationDAOImpl extends CommonDAOImpl<Relation, Long> implements RelationDAO {

    public RelationDAOImpl(){
        super(Relation.class);
    }

    @Override
    public List<Person> getPerformByRelType(Person person, Relation.RelType type) {
        if (type == Relation.RelType.SPOUSE_IN_LAW) {
            return getSpouse(person);
        }

        List<Person> res = new ArrayList<>();
        for (var relation : getRelation(type)) {
            if (Objects.equals(relation.getPerform().getId(), person.getId())) {
                res.add(relation.getTarget());
            }
        }
        return res;
    }

    @Override
    public List<Person> getTargetByRelType(Person person, Relation.RelType type) {
        if (type == Relation.RelType.SPOUSE_IN_LAW) {
            return getSpouse(person);
        }

        List<Person> res = new ArrayList<>();
        for (var relation : getRelation(type)) {
            if (Objects.equals(relation.getTarget().getId(), person.getId())) {
                res.add(relation.getPerform());
            }
        }
        return res;
    }

    @Override
    public Person getMother(Person person) {
        List<Person> personList = getTargetByRelType(person, Relation.RelType.CHILD_IN_LAW);
        personList.addAll(getTargetByRelType(person, Relation.RelType.BASTARD));
        for (Person mother : personList) {
            if (mother.getGender().equals("Жен")) {
                return mother;
            }
        }
        return null;
    }

    @Override
    public Person getFather(Person person) {
        List<Person> personList = getTargetByRelType(person, Relation.RelType.CHILD_IN_LAW);
        personList.addAll(getTargetByRelType(person, Relation.RelType.BASTARD));
        for (Person father : personList) {
            if (father.getGender().equals("Муж")) {
                return father;
            }
        }
        return null;
    }

    @Override
    public boolean bornInMarriage(Person person) {
        for (Relation relation : getRelation(Relation.RelType.BASTARD)) {
            if (Objects.equals(relation.getTarget().getId(), person.getId())) {
                return false;
            }
        }
        return true;
    }

    private List<Relation> getRelation(Relation.RelType type) {
        try (Session session = sessionFactory.openSession()) {
            Query<Relation> query = session.createQuery("FROM Relation WHERE type = :gotType", Relation.class)
                    .setParameter("gotType", type);

            return query.getResultList();
        }
    }

    private List<Person> getSpouse(Person person) {
        List<Person> res = new ArrayList<>();
        for (var relation : getRelation(Relation.RelType.SPOUSE_IN_LAW)) {
            if (Objects.equals(relation.getTarget().getId(), person.getId())) {
                res.add(relation.getPerform());
            }
            if (Objects.equals(relation.getPerform().getId(), person.getId())) {
                res.add(relation.getTarget());
            }
        }
        return res;
    }
}
