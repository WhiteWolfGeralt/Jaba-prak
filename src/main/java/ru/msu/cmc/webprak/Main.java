package ru.msu.cmc.webprak;

import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.utils.DAOFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = DAOFactory.getInstance().getPersonDAO().getPersonAll();
        var a = 5;
    }
}
