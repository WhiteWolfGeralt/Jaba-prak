package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.msu.cmc.webprak.DAO.RelationDAO;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;
import ru.msu.cmc.webprak.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class RelationDAOImpl implements RelationDAO {
    @Override
    public void addRelation(Relation relation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(relation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateRelation(Relation relation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(relation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteRelation(Relation relation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(relation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Relation getRelationById(Long relationId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Relation> query = session.createQuery("FROM Relation WHERE relationId = :param", Relation.class)
                .setParameter("param", relationId);
        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public List<Relation> getRelationAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Relation> query = session.createQuery("FROM Relation", Relation.class);
        return query.getResultList().size() == 0 ? null : query.getResultList();
    }

    @Override
    public List<Person> getByRelType(Long personId, Relation.RelType type, String method) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Relation> query = session.createQuery(
                "FROM Relation WHERE (type = :gotType AND " + method + " = :person)",
                        Relation.class)
                .setParameter("gotType", type)
                .setParameter("person", personId);

        if (query.getResultList().size() == 0) {
            return null;
        }

        List<Person> res = new ArrayList<>();
        for (var relation: query.getResultList()) {
            res.add(relation.getPerform());
        }
        return res;
    }
}
