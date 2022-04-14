package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.DAO.impl.PersonDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.PlaceDAOImpl;
import ru.msu.cmc.webprak.models.Place;

import java.util.List;

@Controller
public class PlaceController {
    @Autowired
    private final PersonDAOImpl personDAO = new PersonDAOImpl();

    @Autowired
    private final PlaceDAOImpl placeDAO = new PlaceDAOImpl();

    @GetMapping("/places")
    public String placesListPage(Model model) {
        List<Place> places = (List<Place>) placeDAO.getAll();
        model.addAttribute("places", places);
        return "places";
    }

    @GetMapping("/place")
    public String placePage(@RequestParam(name = "placeId") Long placeId, Model model) {
        Place place = placeDAO.getById(placeId);

        if (place == null) {
            model.addAttribute("error_msg", "В базе нет населённого пункта с ID = " + placeId);
            return "errorPage";
        }

        model.addAttribute("place", place);
        model.addAttribute("personService", personDAO);
        model.addAttribute("placeService", placeDAO);
        return "place";
    }

    @GetMapping("/editPlace")
    public String editPlacePage(@RequestParam(name = "placeId", required = false) Long placeId, Model model) {
        if (placeId == null) {
            model.addAttribute("place", new Place());
            return "editPlace";
        }

        Place place = placeDAO.getById(placeId);

        if (place == null) {
            model.addAttribute("error_msg", "В базе нет места с ID = " + placeId);
            return "errorPage";
        }

        model.addAttribute("place", place);
        return "editPlace";
    }

    @PostMapping("/savePlace")
    public String savePlacePage(@RequestParam(name = "placeName") String placeName,
                                @RequestParam(name = "description") String description,
                                Model model) {

        Place place = new Place(placeName, description);
        placeDAO.save(place);

        return String.format("redirect:/place?placeId=%d", place.getId());

       /* model.addAttribute("error_msg", "Данные о месте не сохранены");
        return "errorPage";*/
    }

    @PostMapping("/removePlace")
    public String removePlacePage(@RequestParam(name = "placeId") Long placeId) {
        placeDAO.deleteById(placeId);
        return "redirect:/places";
    }
}

