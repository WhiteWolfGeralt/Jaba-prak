package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.RelationDAO;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RelationDAOImpl extends CommonDAOImpl<Relation, Long> implements RelationDAO {

    @Override
    public Relation getRelationById(Long relationId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Relation> query = session.createQuery("FROM Relation WHERE id = :param", Relation.class)
                    .setParameter("param", relationId);
            return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
        }
    }

    @Override
    public List<Relation> getRelationAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Relation> query = session.createQuery("FROM Relation", Relation.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Person> getByRelType(Long personId, Relation.RelType type, String method) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            if (!method.equals("perform") && !method.equals("target")) {
                throw new Exception("'getByRelType': Wrong method");
            }
            Query<Relation> query = session.createQuery(
                            "FROM Relation WHERE (type = :gotType AND perform = :person)",
                            Relation.class)
                    .setParameter("gotType", type)
                    .setParameter("person", personId);

            if (query.getResultList().size() == 0) {
                return null;
            }

            List<Person> res = new ArrayList<>();
            for (var relation : query.getResultList()) {
                res.add(relation.getPerform());
            }
            return res;
        }
    }
}
