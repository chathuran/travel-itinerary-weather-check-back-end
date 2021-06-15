package com.chathuran.weather.check.travelweathercheck.service.impl;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.CityId;
import com.chathuran.weather.check.travelweathercheck.model.FavoriteCity;
import com.chathuran.weather.check.travelweathercheck.repository.FavoriteCityRepository;
import com.chathuran.weather.check.travelweathercheck.service.FavoriteCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavoritesCityServiceImpl implements FavoriteCityService {
    @Autowired
    private FavoriteCityRepository favoriteCityRepository;
    @Override
    public List<FavoriteCity> getAllFavorites() {
        return favoriteCityRepository.findAll();
    }

    @Override
    public FavoriteCity addFavoriteCity(City favoriteCity) {
        FavoriteCity newFavoriteCity = new FavoriteCity();
        newFavoriteCity.setCity(favoriteCity);
        CityId cityId = new CityId(favoriteCity.getCityId().getCityName(),favoriteCity.getCityId().getCountryCode());

        newFavoriteCity.setCityId(cityId);
        return favoriteCityRepository.save(newFavoriteCity);
    }

    @Override
    public FavoriteCity removeFavoriteCity(City city) {
        FavoriteCity favoriteCity = new FavoriteCity();
        favoriteCity.setCityId(city.getCityId());
        favoriteCityRepository.delete(favoriteCity);
        return favoriteCity;
    }

}
