package ru.msu.cmc.webprak.DAO.impl;

import ru.msu.cmc.webprak.DAO.PlaceDAO;
import ru.msu.cmc.webprak.models.Place;

public class PlaceDAOImpl extends CommonDAOImpl<Place, Long> implements PlaceDAO {

    public PlaceDAOImpl() {
        super(Place.class);
    }
}
