package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.msu.cmc.webprak.DAO.RelationDAO;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;
import ru.msu.cmc.webprak.utils.HibernateUtil;

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
        Query<Relation> query = session.createQuery("FROM Relation WHERE relation_id = :param", Relation.class)
                .setParameter("param", relationId);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Relation> getRelationAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Relation> query = session.createQuery("FROM Relation", Relation.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Person> getAllByRelType(Long personId, Relation.RelType type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Relation> query = session.createQuery("SELECT relation_id FROM Relation WHERE type = :gotType", Relation.class)
                .setParameter("gotType", type);
        return null;
    }
}
