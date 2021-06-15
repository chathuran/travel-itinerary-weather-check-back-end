package com.chathuran.weather.check.travelweathercheck.common;

import com.chathuran.weather.check.travelweathercheck.model.City;
import com.chathuran.weather.check.travelweathercheck.model.Temperature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ForecastResponse {

    private City city;
    Map<LocalDate, List<Temperature>> temperatureMapList;

}
