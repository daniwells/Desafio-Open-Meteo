package com.Desafio_Spring.Final.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class WeatherRequest {

    @Min(value = -90, message = "Latitude must be between -90 and 90")
    @Max(value = 90, message = "Latitude must be between -90 and 90")
    private double latitude;

    @Min(value = -180, message = "Longitude must be between -180 and 180")
    @Max(value = 180, message = "Longitude must be between -180 and 180")
    private double longitude;
}
