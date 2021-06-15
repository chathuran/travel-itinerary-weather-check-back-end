package com.chathuran.weather.check.travelweathercheck.service;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.FavoriteCity;
import java.util.List;

public interface FavoriteCityService {

    List<FavoriteCity> getAllFavorites();

    FavoriteCity addFavoriteCity(City favoriteCity);

    FavoriteCity removeFavoriteCity(City favoriteCity);


}
