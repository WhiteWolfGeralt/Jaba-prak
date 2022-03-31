package ru.msu.cmc.webprak.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.PlaceDAO;
import ru.msu.cmc.webprak.models.Place;

@Repository
public class PlaceDAOImpl extends CommonDAOImpl<Place, Long> implements PlaceDAO {

    public PlaceDAOImpl() {
        super(Place.class);
    }
}
