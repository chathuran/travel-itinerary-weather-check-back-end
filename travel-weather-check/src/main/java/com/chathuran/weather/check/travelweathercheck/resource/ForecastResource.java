package com.chathuran.weather.check.travelweathercheck.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ForecastResource {
    @JsonProperty("city")
    ApiCity city;
    @JsonProperty("list")
    List<ApiForecastListItem> forecastListItemList;
}

