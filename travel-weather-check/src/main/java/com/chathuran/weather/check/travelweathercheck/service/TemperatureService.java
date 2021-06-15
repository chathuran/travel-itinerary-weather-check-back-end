package com.chathuran.weather.check.travelweathercheck.service;

import com.chathuran.weather.check.travelweathercheck.model.Temperature;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TemperatureService {

    Temperature saveTemperature(Temperature temperature);

    /**
     * Get all temperature List by City and the given date within an hour
     * @param cityName
     * @param countryCode
     * @param date
     * @param afterDate
     * @return
     */
    List<Temperature> getAllNewestTemperatureListByCitiesWithinHour(String cityName,String countryCode, LocalDate date, LocalDateTime afterDate);

    /**
     * Get 5 day forecast of Temperature List by City within an hour
     * @param cityName
     * @param countryCode
     * @param afterDate
     * @return
     */
    List<Temperature> getAllFiveDayTemperatureListByCitiesWithinHour(String cityName,String countryCode, LocalDateTime afterDate);

    /**
     * Save Bulk Temperatures
     * @param temperatureList
     * @return
     */
    List<Temperature> saveTemperatureList(List<Temperature> temperatureList);
}
