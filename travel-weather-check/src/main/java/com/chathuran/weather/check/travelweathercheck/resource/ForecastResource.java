package com.chathuran.weather.check.travelweathercheck.resource;


import com.chathuran.weather.check.travelweathercheck.model.City;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ForecastResource {
    @JsonProperty("city")
    _City city;
    @JsonProperty("list")
    List<_ForecastListItem> forecastListItemList;
}

