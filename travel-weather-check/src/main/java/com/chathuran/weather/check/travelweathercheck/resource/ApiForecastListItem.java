package com.chathuran.weather.check.travelweathercheck.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiForecastListItem {
    @JsonProperty("main")
    ApiMain main;
    @JsonProperty("weather")
    List<ApiWeather> weather;
    @JsonProperty("clouds")
    ApiClouds clouds;
    String dt_txt;
}
