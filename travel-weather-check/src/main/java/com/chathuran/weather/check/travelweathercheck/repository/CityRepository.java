package com.chathuran.weather.check.travelweathercheck.repository;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.CityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CityRepository extends JpaRepository<City,CityId> {

    @Query("SELECT c FROM City c WHERE c.cityId.cityName = :cityName AND c.cityId.countryCode = :countryCode")
    List<City> getAllCitiesByCityNameNCountryCode(@Param("cityName") String cityName,@Param("countryCode") String countryCode);


}
