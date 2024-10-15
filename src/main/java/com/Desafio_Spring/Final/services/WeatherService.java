package com.Desafio_Spring.Final.services;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.Desafio_Spring.Final.dtos.WeatherData;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.FileReader;

@Service
public class WeatherService {
    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";
    private final Gson gson = new Gson();

    public WeatherData getWeather(double latitude, double longitude) {
        String url = BASE_URL + "?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m";

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.br.bosch.com", 80));
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("username", "password".toCharArray());
            }
        });

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate(factory);

        try{
            ResponseEntity<WeatherData> responseEntity = restTemplate.getForEntity(url, WeatherData.class);
            WeatherData weatherData = responseEntity.getBody();

            if (weatherData != null && weatherData.getHourly() != null) {
                return weatherData;
            } else {
                System.out.println("Error: No datas was found.");
            }
        }catch(RestClientException e){
            System.out.println("Error: Error Accessing the Open-Meteo API:");
        }
        return null;
    }

    public List<Map<String, Object>> readWeatherDataFromJson(String filePath) throws IOException {
        Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, listType);
        }
    }

    public void addWeatherDataToJson(WeatherData weatherData, String filePath) {
        List<Map<String, Object>> existingWeatherData = new ArrayList<>();

        try {
            existingWeatherData = readWeatherDataFromJson(filePath);
        } catch (IOException e) {
            System.out.println("File wasn't found: " + e.getMessage());
        }

        Map<String, Object> newWeatherEntry = convertWeatherDataToMap(weatherData);
        existingWeatherData.add(newWeatherEntry);

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(existingWeatherData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> convertWeatherDataToMap(WeatherData weatherData) {
        return Map.of(
                "latitude", weatherData.getHourly().getTemperatures(),
                "longitude", weatherData.getHourly().getTimes()
        );
    }
}
