package com.chathuran.weather.check.travelweathercheck.service.impl;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.CityId;
import com.chathuran.weather.check.travelweathercheck.model.Temperature;
import com.chathuran.weather.check.travelweathercheck.repository.CityRepository;
import com.chathuran.weather.check.travelweathercheck.service.CityService;
import com.chathuran.weather.check.travelweathercheck.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TemperatureService temperatureService;
    @Override
    public City saveCity(City city) {

        City createdCity= cityRepository.save(city);

        return createdCity;

    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> getAllCitiesByCityNameNCountryCode(String cityNameNCountryCode) {

        return cityRepository.getAllCitiesByCityNameNCountryCode(cityNameNCountryCode.split(",")[0],cityNameNCountryCode.split(",")[1]);
    }

    @Override
    public City isCityAvailable(CityId cityId) {
        return cityRepository.getById(cityId);
    }
}
