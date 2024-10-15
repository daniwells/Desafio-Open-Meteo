package com.Desafio_Spring.Final.controllers;
import com.Desafio_Spring.Final.dtos.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.Desafio_Spring.Final.services.WeatherService;
import jakarta.validation.Valid;

@Controller
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather() {
        return "weatherForm";
    }

    @PostMapping("/postWeather")
    public String postWeather(@RequestParam String latitude, @RequestParam String longitude, Model model){
        try {
            double lat = Double.parseDouble(latitude);
            double longi = Double.parseDouble(longitude);

            if (lat < -90 || lat > 90) {
                model.addAttribute("error", "Latitude must be between -90 and 90");
                return "weatherForm";
            }

            if (longi < -180 || longi > 180) {
                model.addAttribute("error", "Longitude must be between -180 and 180");
                return "weatherForm";
            }

            WeatherData weatherData = weatherService.getWeather(lat, longi);

            if (weatherData != null) {
                String filePath = "./src/main/java/com/Desafio_Spring/Final/weatherData.json";
                weatherService.addWeatherDataToJson(weatherData, filePath);
                model.addAttribute("weatherData", weatherData);
            } else {
                model.addAttribute("error", "Error getting the weather data.");
                return "weatherForm";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid input: Please enter valid numbers for latitude and longitude.");
            return "weatherForm";
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred.");
            return "weatherForm";
        }
        return "weatherDatas";
    }
}
