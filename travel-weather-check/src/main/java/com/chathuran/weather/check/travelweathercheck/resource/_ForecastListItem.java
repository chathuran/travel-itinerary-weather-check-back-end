package com.chathuran.weather.check.travelweathercheck.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class _ForecastListItem {
    @JsonProperty("main")
    _Main main;
    @JsonProperty("weather")
    List<_Weather> weather;
    @JsonProperty("clouds")
    _Clouds clouds;
    String dt_txt;
}
