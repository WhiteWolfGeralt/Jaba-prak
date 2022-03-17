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
    public Person getPersonById(Long personId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Person> query = session.createQuery("FROM Person WHERE person_id = :param", Person.class)
                .setParameter("param", personId);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Person> getPersonAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Person> query = session.createQuery("FROM Person", Person.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}
