package com.chathuran.weather.check.travelweathercheck.repository;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureRepository extends JpaRepository<Temperature,Long> {

    @Query("SELECT t FROM City c, Temperature t WHERE t.city = c AND c.cityId.cityName = :city AND c.cityId.countryCode = :countryCode AND t.date = :date AND t.createdDate >= :afterDateTime")
    List<Temperature> getAllNewestTempsByCityDate(@Param("city") String city,@Param("countryCode") String countryCode, @Param("date") LocalDate date, @Param("afterDateTime") LocalDateTime afterDateTime);

    @Query("SELECT t FROM City c, Temperature t WHERE t.city = c AND c.cityId.cityName = :city AND c.cityId.countryCode = :countryCode AND t.createdDate >= :afterDateTime")
    List<Temperature> getAllFiveDayTemperatureListByCitiesWithinHour(@Param("city") String cityName,@Param("countryCode") String countryCode,@Param("afterDateTime") LocalDateTime afterDate);
}
