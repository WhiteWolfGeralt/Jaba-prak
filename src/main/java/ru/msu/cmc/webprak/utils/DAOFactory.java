package ru.msu.cmc.webprak.utils;

import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.DAO.RelationDAO;
import ru.msu.cmc.webprak.DAO.impl.PersonDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.RelationDAOImpl;

public class DAOFactory {

    private static PersonDAO personDAO = null;
    private static RelationDAO relationDAO = null;
    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance(){
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public PersonDAO getPersonDAO(){
        if (personDAO == null){
            personDAO = new PersonDAOImpl();
        }
        return personDAO;
    }

    public RelationDAO getRelationDAO(){
        if (relationDAO == null){
            relationDAO = new RelationDAOImpl();
        }
        return relationDAO;
    }
}
