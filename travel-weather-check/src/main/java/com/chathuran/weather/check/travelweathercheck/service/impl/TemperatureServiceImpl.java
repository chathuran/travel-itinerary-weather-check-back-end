package com.chathuran.weather.check.travelweathercheck.service.impl;

import com.chathuran.weather.check.travelweathercheck.model.Temperature;
import com.chathuran.weather.check.travelweathercheck.repository.TemperatureRepository;
import com.chathuran.weather.check.travelweathercheck.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public Temperature saveTemperature(Temperature temperature) {
        return temperatureRepository.save(temperature);
    }

    @Override
    public List<Temperature> getAllNewestTemperatureListByCitiesWithinHour(String cityName,String countryCode, LocalDate date, LocalDateTime afterDate) {
        return temperatureRepository.getAllNewestTempsByCityDate(cityName,countryCode,date,afterDate);
    }

    @Override
    public List<Temperature> getAllFiveDayTemperatureListByCitiesWithinHour(String cityName, String countryCode, LocalDateTime afterDate) {
        return temperatureRepository.getAllFiveDayTemperatureListByCitiesWithinHour(cityName,countryCode,afterDate);
    }

    @Override
    public List<Temperature> saveTemperatureList(List<Temperature> temperatureList) {
        return temperatureRepository.saveAll(temperatureList);
    }


}
