package com.chathuran.weather.check.travelweathercheck.controller;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.FavoriteCity;
import com.chathuran.weather.check.travelweathercheck.service.CityService;
import com.chathuran.weather.check.travelweathercheck.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CityService cityService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCity(@RequestBody City city){
        return ResponseEntity.ok( cityService.saveCity(city));
    }

    @GetMapping("/getAllCities")
    public ResponseEntity<?> getAllCities(){
        return ResponseEntity.ok( cityService.getAllCities());
    }
    @GetMapping("/getWeather")
    public ResponseEntity<?> getWeatherDetailsByCityAndDate(@RequestParam String city,@RequestParam String date) throws NoSuchFieldException {

        return ResponseEntity.ok( weatherService.getWeatherDetailsByCityAndDate(city,date));
    }
    @PostMapping("/addToFavorites")
    public ResponseEntity<?> addToFavorites(@RequestBody City city)  {

        return ResponseEntity.ok( weatherService.addToFavorites(city));
    }
    @PostMapping("/removeFromFavorites")
    public ResponseEntity<?> removeFromFavorites(@RequestBody City favoriteCity) {
            ;
        return ResponseEntity.ok(weatherService.removeFromFavorites(favoriteCity));

    }
    @GetMapping("/getFavoriteWeather")
    public ResponseEntity<?> getFavoriteWeatherDetails() throws NoSuchFieldException {

        return ResponseEntity.ok( weatherService.getFavoriteWeatherDetails());
    }



}
