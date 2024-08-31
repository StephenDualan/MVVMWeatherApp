package com.example.wew.Repository;

import com.example.wew.Retrofit.WeatherApi;
import com.example.wew.Response.WeatherResponse;

import retrofit2.Call;

public class WeatherRepository {
    private WeatherApi weatherApi;
    private static final String API_KEY = "aeaade6476df5e91ad36ffd2414f6681"; // Replace with your actual API key

    public WeatherRepository(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    // Modified method to only accept city
    public Call<WeatherResponse> fetchWeather(String city) {
        return weatherApi.getWeather(city, API_KEY); // Call the API with the city and the hardcoded API key
    }
}
