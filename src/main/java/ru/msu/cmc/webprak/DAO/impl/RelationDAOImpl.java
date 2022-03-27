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

    public RelationDAOImpl(){
        super(Relation.class);
    }

    @Override
    public List<Person> getPerformByRelType(Long personId, Relation.RelType type) {
        try (Session session = sessionFactory.openSession()) {
            Query<Relation> query = session.createQuery("FROM Relation WHERE (type = :gotType)",
                            Relation.class)
                    .setParameter("gotType", type);

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

    @Override
    public List<Person> getTargetByRelType(Long personId, Relation.RelType type) {
        return null;
    }

    private List<Person> getImplementor(String implementor) {

        return null;
    }
}
