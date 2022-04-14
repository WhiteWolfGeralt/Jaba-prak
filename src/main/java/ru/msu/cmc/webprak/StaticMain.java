package ru.msu.cmc.webprak;

import ru.msu.cmc.webprak.DAO.impl.PersonDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.RelationDAOImpl;
import ru.msu.cmc.webprak.models.Person;

public class StaticMain {
    public static void main(String[] args) {
        PersonDAOImpl personDAO = new PersonDAOImpl();
        Person ciri = personDAO.getSinglePersonByName("Цирилла");
        
        Tree tree = new Tree(ciri);
        tree.addAllAncestors(tree);
    }
}
