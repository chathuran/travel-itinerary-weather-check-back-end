package com.chathuran.weather.check.travelweathercheck.service;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.CityId;

import java.util.List;
public interface CityService {

   City saveCity(City city);
   List<City> getAllCities();
   List<City> getAllCitiesByCityNameNCountryCode(String cityNameNCountryCode);
   City isCityAvailable(CityId cityId);


}
