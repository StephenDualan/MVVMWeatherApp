package com.example.wew.Retrofit;

import com.example.wew.Response.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/weather") // Adjust endpoint as needed
    Call<WeatherResponse> getWeather(
            @Query("q") String city,
            @Query("appid") String apiKey // Make sure this matches your API requirements
    );
}
