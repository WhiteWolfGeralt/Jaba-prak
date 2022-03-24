package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.utils.HibernateUtil;

import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public void addPerson(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updatePerson(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletePerson(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(person);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Person> getPersonByName(String personName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Person> query = session.createQuery("FROM Person WHERE name LIKE :gotName", Person.class)
                .setParameter("gotName", likeExpr(personName));
        return query.getResultList().size() == 0 ? null : query.getResultList();
    }

    @Override
    public Person getPersonById(Integer personId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Person> query = session.createQuery("FROM Person WHERE personId = :param", Person.class)
                .setParameter("param", personId);
        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public List<Person> getPersonAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Person> query = session.createQuery("FROM Person", Person.class);
        return query.getResultList().size() == 0 ? null : query.getResultList();
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
