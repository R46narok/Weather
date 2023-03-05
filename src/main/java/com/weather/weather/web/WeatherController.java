package com.weather.weather.web;

import com.weather.weather.data.CurrentWeather;
import com.weather.weather.data.Location;
import com.weather.weather.data.service.LiveWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class WeatherController
{
    private final LiveWeatherService liveWeatherService;

    public WeatherController(LiveWeatherService service)
    {
        this.liveWeatherService = service;
    }

    @GetMapping("/weather")
    public String getCurrentWeather(Model model)
    {
        model.addAttribute("location", new Location());
        return "weather";
    }

    @PostMapping("/weather")
    public String getCurrentWeatherSubmit(@ModelAttribute Location location, Model model)
    {
        var currentWeather = liveWeatherService.getCurrentWeather(location.getCity(), location.getCountry());
        model.addAttribute("currentWeather", currentWeather);
        return "result";
    }
}