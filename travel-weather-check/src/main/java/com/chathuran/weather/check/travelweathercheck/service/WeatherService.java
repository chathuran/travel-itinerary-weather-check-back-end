package com.chathuran.weather.check.travelweathercheck.service;

import com.chathuran.weather.check.travelweathercheck.common.ForecastResponse;
import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.FavoriteCity;
import com.chathuran.weather.check.travelweathercheck.resource.ForecastResource;

import javax.persistence.FetchType;
import java.time.LocalDate;
import java.util.List;

public interface WeatherService {

    public List<ForecastResponse> getWeatherDetailsByCityAndDate(String cityName, String Date) throws NoSuchFieldException;

    public List<ForecastResponse> getFavoriteWeatherDetails();

    FavoriteCity removeFromFavorites(City city);

    FavoriteCity addToFavorites(City city);
}
