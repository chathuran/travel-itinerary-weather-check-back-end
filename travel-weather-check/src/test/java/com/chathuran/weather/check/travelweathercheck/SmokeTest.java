package com.chathuran.weather.check.travelweathercheck;

import com.chathuran.weather.check.travelweathercheck.controller.WeatherController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class SmokeTest {

    @Autowired
    private WeatherController weatherController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(weatherController).isNotNull();
    }
}
