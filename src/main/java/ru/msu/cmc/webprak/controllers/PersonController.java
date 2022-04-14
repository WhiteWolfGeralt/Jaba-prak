package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.DAO.impl.PersonDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.PlaceDAOImpl;
import ru.msu.cmc.webprak.models.Person;

import java.sql.Date;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private final PersonDAOImpl personDAO = new PersonDAOImpl();

    @Autowired
    private final PlaceDAOImpl placeDAO = new PlaceDAOImpl();

    @GetMapping("/persons")
    public String peopleListPage(Model model) {
        List<Person> people = (List<Person>) personDAO.getAll();
        model.addAttribute("people", people);
        model.addAttribute("personService", personDAO);
        return "persons";
    }

    @GetMapping("/person")
    public String personPage(@RequestParam(name = "personId") Long personId, Model model) {
        Person person = personDAO.getById(personId);

        if (person == null) {
            model.addAttribute("error_msg", "В базе нет человека с ID = " + personId);
            return "errorPage";
        }

        model.addAttribute("person", person);
        model.addAttribute("personService", personDAO);
        model.addAttribute("placeService", placeDAO);
        return "person";
    }

    @GetMapping("/editPerson")
    public String editPersonPage(@RequestParam(name = "personId", required = false) Long personId, Model model) {
        if (personId == null) {
            model.addAttribute("person", new Person());
            model.addAttribute("personService", personDAO);
            model.addAttribute("placeService", placeDAO);
            return "editPerson";
        }

        Person person = personDAO.getById(personId);

        if (person == null) {
            model.addAttribute("error_msg", "В базе нет человека с ID = " + personId);
            return "errorPage";
        }

        model.addAttribute("person", person);
        model.addAttribute("personService", personDAO);
        model.addAttribute("placeService", placeDAO);
        return "editPerson";
    }

    @PostMapping("/savePerson")
    public String savePersonPage(@RequestParam(name = "personId") Long personId,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "gender") String gender,
                                 @RequestParam(name = "birthDate", required = false) Long birthDate,
                                 @RequestParam(name = "deathDate", required = false) Long deathDate,
                                 @RequestParam(name = "info", required = false) String info,
                                 Model model) {
        Person person = personDAO.getById(personId);
        boolean changeIsSuccessful = false;

        if (person != null) {
            person.setName(name);
            person.setGender(gender);
            person.setBirth(birthDate);
            person.setDeath(deathDate);
            person.setCharacter(info);
        } else {
            person = new Person(personId, name, gender, birthDate, deathDate, info);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "errorPage";
    }

    @PostMapping("/removePerson")
    public String removePersonPage(@RequestParam(name = "personId") Long personId) {
        personDAO.deleteById(personId);
        return "redirect:/persons";
    }
}