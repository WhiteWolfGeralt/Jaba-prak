package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.models.Person;

import java.util.List;

@Repository
public class PersonDAOImpl extends CommonDAOImpl<Person, Long> implements PersonDAO {

    @Override
    public List<Person> getPersonByName(String personName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Person> query = session.createQuery("FROM Person WHERE name LIKE :gotName", Person.class)
                    .setParameter("gotName", likeExpr(personName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Person getPersonById(Integer personId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Person> query = session.createQuery("FROM Person WHERE id = :param", Person.class)
                    .setParameter("param", personId);
            return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
        }
    }

    @Override
    public List<Person> getPersonAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Person> query = session.createQuery("FROM Person", Person.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
