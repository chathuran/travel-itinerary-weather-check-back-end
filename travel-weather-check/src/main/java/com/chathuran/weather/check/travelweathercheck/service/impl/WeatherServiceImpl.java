package com.chathuran.weather.check.travelweathercheck.service.impl;

import com.chathuran.weather.check.travelweathercheck.common.ForecastResponse;
import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.CityId;
import com.chathuran.weather.check.travelweathercheck.model.FavoriteCity;
import com.chathuran.weather.check.travelweathercheck.model.Temperature;
import com.chathuran.weather.check.travelweathercheck.resource.ForecastResource;
import com.chathuran.weather.check.travelweathercheck.resource._ForecastListItem;
import com.chathuran.weather.check.travelweathercheck.service.CityService;
import com.chathuran.weather.check.travelweathercheck.service.FavoriteCityService;
import com.chathuran.weather.check.travelweathercheck.service.TemperatureService;
import com.chathuran.weather.check.travelweathercheck.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
    private static Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    @Autowired
    private CityService cityService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TemperatureService temperatureService;
    @Autowired
    private FavoriteCityService favoriteCityService;

    /**
     * Get Selected weatherDetails by Date and city name
     *
     * @param cityNameNCountryCode
     * @param date
     * @return
     */
    @Override
    public List<ForecastResponse> getWeatherDetailsByCityAndDate(String cityNameNCountryCode, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");


        //convert String to LocalDate
        LocalDate selectedDate = LocalDate.parse(date);
        LocalDateTime afterTime = LocalDateTime.now().minusMinutes(59);

        /**
         * 1. Check availability cash for existing weather details related to the city
         */
        List<City> availableCities = cityService.getAllCitiesByCityNameNCountryCode(cityNameNCountryCode);
        List<ForecastResponse> returnForecastResponseList = new ArrayList<>();


        /**
         * 2.1. If Yes return that weather details
         */
        if (availableCities.size() > 0) {

            for (City selectedCity :
                    availableCities) {


                /**
                 * Check the latest forecast availability for the City from the Database.
                 */
//                List<Temperature> returnTemperatureList = temperatureService.getAllNewestTemperatureListByCitiesWithinHour(selectedCity.getCityId().getCityName(), selectedCity.getCityId().getCountryCode(), selectedDate, afterTime);
                List<Temperature> returnTemperatureList = temperatureService.getAllFiveDayTemperatureListByCitiesWithinHour(selectedCity.getCityId().getCityName(), selectedCity.getCityId().getCountryCode(), afterTime);

                int indexSelectedCity = availableCities.indexOf(selectedCity);
                /**
                 * Return available forecast in data base
                 */
                if (returnTemperatureList.size() > 0) {
                    ForecastResponse returnForecastResponse = new ForecastResponse();
                    returnForecastResponse.setCity(selectedCity);
                    returnForecastResponse.setTemperatureMapList(group5dayForecastResponse(returnTemperatureList));
                    returnForecastResponseList.add(returnForecastResponse);
                }
                /**
                 * Get updated forecast form https://openweathermap.org API and update the database
                 */
                else {
                    ForecastResource forecastResource = getWeatherForecastFromAPIByCityNameNCountryCode(selectedCity.getCityId().getCityName()+","+selectedCity.getCityId().getCountryCode());
                    /**
                     * save updated forecast
                     */
                    if (forecastResource != null) {

                        //ForecastResource --> TempList
                        List<Temperature> newTemperatureList = generateTempListFromForecastResource(forecastResource, selectedCity);

                        temperatureService.saveTemperatureList(newTemperatureList);
                        ForecastResponse returnForecastResponse = new ForecastResponse();
                        returnForecastResponse.setCity(selectedCity);
                        returnForecastResponse.setTemperatureMapList(group5dayForecastResponse(returnTemperatureList));
                        returnForecastResponseList.add(returnForecastResponse);

                    }
                }

            }
        }
        /**
         * 2.2. If required City Not in the Database. get the Weather details for https://openweathermap.org APi
         */
        else {
            ForecastResource forecastResource = getWeatherForecastFromAPIByCityNameNCountryCode(cityNameNCountryCode);
            if (forecastResource != null) {



                City newCity = new City();
                CityId newCityId = new CityId();

                newCityId.setCityName(forecastResource.getCity().getName().toUpperCase());
                newCityId.setCountryCode(forecastResource.getCity().getCountry());
                newCity.setCityId(newCityId);

                //ForecastResource --> TempList
                List<Temperature> newTemperatureList = generateTempListFromForecastResource(forecastResource, newCity);
                newCity.setTemperatures(newTemperatureList);
                /**
                 * 3.  Save new weather details in Cache DB
                 */
               City createdCity = cityService.saveCity(newCity);
//                newCity.setTemperatures(filterTempListByDate(newTemperatureList,selectedDate));
                newCity.setCityId(createdCity.getCityId());
                ForecastResponse returnForecastResponse = new ForecastResponse();
                returnForecastResponse.setCity(newCity);
                returnForecastResponse.setTemperatureMapList(group5dayForecastResponse(newTemperatureList));
                returnForecastResponseList.add(returnForecastResponse);
            }
            /**
             * Selected City not available| incorrect city
             */
            else {
                returnForecastResponseList = null;
            }

        }
        /**
         * 4. return new Weather details
         */
        return returnForecastResponseList;
    }

    /**
     * Get Selected weatherDetails from Favorites Cities
     *
     * @return
     */
    @Override
    public List<ForecastResponse> getFavoriteWeatherDetails() {


        LocalDateTime afterTime = LocalDateTime.now().minusMinutes(59);

        /**
         * 1. Check availability cash for existing weather details related to the city
         */
        List<FavoriteCity> favoriteCities = favoriteCityService.getAllFavorites();
        List<ForecastResponse> returnForecastResponseList  = new ArrayList<>();


        /**
         * 2.1. If Yes return that weather details
         */
        if (favoriteCities.size() > 0) {

            for (FavoriteCity favCity :
                    favoriteCities) {
                City selectedCity = favCity.getCity();
                /**
                 * Check the latest forecast availability for the City from the Database.
                 */
                List<Temperature> returnTemperatureList = temperatureService.getAllFiveDayTemperatureListByCitiesWithinHour(selectedCity.getCityId().getCityName(), selectedCity.getCityId().getCountryCode(), afterTime);

                /**
                 * Return available forecast in data base
                 */
                if (returnTemperatureList.size() > 0) {

                    ForecastResponse returnForecastResponse = new ForecastResponse();
                    returnForecastResponse.setCity(selectedCity);
                    returnForecastResponse.setTemperatureMapList(group5dayForecastResponse(returnTemperatureList));
                    returnForecastResponseList.add(returnForecastResponse);
                }
                /**
                 * Get updated forecast form https://openweathermap.org API and update the database
                 */
                else {
                    ForecastResource forecastResource = getWeatherForecastFromAPIByCityNameNCountryCode(selectedCity.getCityId().getCityName()+","+selectedCity.getCityId().getCountryCode());
                    /**
                     * save updated forecast
                     */
                    if (forecastResource != null) {



                            //ForecastResource --> TempList
                            List<Temperature> newTemperatureList = generateTempListFromForecastResource(forecastResource, selectedCity);

                            temperatureService.saveTemperatureList(newTemperatureList);
                        ForecastResponse returnForecastResponse = new ForecastResponse();
                        returnForecastResponse.setCity(selectedCity);
                        returnForecastResponse.setTemperatureMapList(group5dayForecastResponse(returnTemperatureList));
                        returnForecastResponseList.add(returnForecastResponse);
                    }
                }

            }
        }

        return returnForecastResponseList;

    }

    @Override
    public FavoriteCity removeFromFavorites(City favoriteCity) {
        return favoriteCityService.removeFavoriteCity(favoriteCity);
    }

    @Override
    public FavoriteCity addToFavorites(City city) {
        return favoriteCityService.addFavoriteCity(city);
    }


    ForecastResource getWeatherForecastFromAPIByCityNameNCountryCode(String cityNameNCountryCode) {

        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityNameNCountryCode + "&appid=47ae796f88a447c6ae2441abff043c11&units=metric";
        ResponseEntity<ForecastResource> responseEntity = null;
        ForecastResource returnForecastResource = new ForecastResource();
        try {
            responseEntity = restTemplate.getForEntity(url, ForecastResource.class);
            returnForecastResource = responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            returnForecastResource = null;
        }
        return returnForecastResource;
    }


    /**
     * Generate Temperature List from API Data
     *
     * @return
     */
    List<Temperature> generateTempListFromForecastResource(ForecastResource forecastResource, City city) {
        List<Temperature> temperatureList = new ArrayList<>();
        for (_ForecastListItem forecastItem :
                forecastResource.getForecastListItemList()) {

            String[] forecastDateNTime = forecastItem.getDt_txt().split(" ");
            LocalTime forecastTime = LocalTime.parse(forecastDateNTime[1]);
            LocalDate forecastDate = LocalDate.parse(forecastDateNTime[0]);

            LocalTime beforeTime = LocalTime.parse("11:59:59");
            LocalTime afterTime = LocalTime.parse("18:00:59");
            //Check forecast time between 12PM -6 PM
            if (forecastTime.isAfter(beforeTime) && forecastTime.isBefore(afterTime)) {
                Temperature temperature = new Temperature();
                if (city != null) {
                    temperature.setCity(city);
                }


                //Select Primary weather condition and icon for the weather
                temperature.setWeather(forecastItem.getWeather().get(0).getMain());
                temperature.setWeatherIcon(forecastItem.getWeather().get(0).getIcon());

                temperature.setClouds(forecastItem.getClouds().getAll());
                temperature.setTemp(forecastItem.getMain().getTemp());

                temperature.setDate(forecastDate);
                temperature.setTime(forecastTime);

                temperatureList.add(temperature);
            }

        }
        return temperatureList;
    }

    /**
     * Filter Temperature List by Date
     *
     * @param fullTempList
     * @param date
     * @return
     */
    List<Temperature> filterTempListByDate(List<Temperature> fullTempList, LocalDate date) {
        List<Temperature> temperatureList = new ArrayList<>();
        for (Temperature temperature :
                fullTempList) {

            if (temperature.getDate().equals(date)) {
                temperatureList.add(temperature);
            }


        }
        return temperatureList;
    }


    Map<LocalDate, List<Temperature>> group5dayForecastResponse(List<Temperature> fullTempList) {

        Map<LocalDate, List<Temperature>> temperatureGroup =
                fullTempList.stream().collect(Collectors.groupingBy(temp -> temp.getDate()));


        return temperatureGroup;
    }

    /**
     * Check the Returning WeatherDetails value have Selected City for avoiding duplication.
     *
     * @param cityList
     * @param selectedCity
     * @return
     */
    Boolean isSelectedCityWeatherInReturnCityWeatherList(List<City> cityList, City selectedCity) {

        if (cityList.indexOf(selectedCity) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
