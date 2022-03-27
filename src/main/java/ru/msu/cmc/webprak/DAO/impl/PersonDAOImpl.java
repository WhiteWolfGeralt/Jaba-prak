package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.models.Person;

import java.util.List;

@Repository
public class PersonDAOImpl extends CommonDAOImpl<Person, Long> implements PersonDAO {

    public PersonDAOImpl(){
        super(Person.class);
    }

    @Override
    public List<Person> getAllPersonByName(String personName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Person> query = session.createQuery("FROM Person WHERE name LIKE :gotName", Person.class)
                    .setParameter("gotName", likeExpr(personName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Person getSinglePersonByName(String personName) {
        List<Person> candidates = this.getAllPersonByName(personName);
        return candidates.size() == 1 ? candidates.get(0) : null;
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
