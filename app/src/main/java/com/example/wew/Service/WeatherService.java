package com.example.wew.Service;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.wew.Retrofit.WeatherApi;
import com.example.wew.Response.WeatherResponse;

public class WeatherService {
    private static final String BASE_URL = "https://home.openweathermap.org/";
    private static final String API_KEY = "aeaade6476df5e91ad36ffd2414f6681"; // Replace with your actual API key

    private WeatherApi weatherApi;

    public WeatherService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
    }

    public Call<WeatherResponse> getWeather(String city) {
        return weatherApi.getWeather(city, API_KEY);
    }
}