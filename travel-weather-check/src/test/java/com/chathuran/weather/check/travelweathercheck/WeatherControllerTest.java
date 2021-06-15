package com.chathuran.weather.check.travelweathercheck;

import com.chathuran.weather.check.travelweathercheck.controller.WeatherController;
import com.chathuran.weather.check.travelweathercheck.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService weatherService;


    @Test
    void whenValidInput_thenReturns200() throws Exception{

    }
}
