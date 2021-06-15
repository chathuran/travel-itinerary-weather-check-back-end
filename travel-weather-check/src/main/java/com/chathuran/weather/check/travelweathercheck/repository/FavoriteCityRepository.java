package com.chathuran.weather.check.travelweathercheck.repository;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity,Long> {
    void deleteByCity(City favoriteCity);
}
