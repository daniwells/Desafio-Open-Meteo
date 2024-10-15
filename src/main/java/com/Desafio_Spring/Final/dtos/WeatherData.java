package com.Desafio_Spring.Final.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherData {
    @JsonProperty("hourly")
    private HourlyData hourly;

    public static class HourlyData {
        @JsonProperty("time")
        private List<String> times;

        @JsonProperty("temperature_2m")
        private List<Double> temperatures;

        public List<String> getTimes() {
            return times;
        }

        public List<Double> getTemperatures() {
            return temperatures;
        }

        @Override
        public String toString() {
            return "HourlyData{" +
                    "times=" + times +
                    ", temperatures=" + temperatures +
                    '}';
        }
    }

    public HourlyData getHourly() {
        return hourly;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "hourly=" + hourly +
                '}';
    }
}
