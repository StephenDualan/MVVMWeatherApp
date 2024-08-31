package com.example.wew.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wew.Repository.WeatherRepository;
import com.example.wew.Response.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository repository;
    private MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();

    public WeatherViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    public void fetchWeather(String city) {
        Call<WeatherResponse> call = repository.fetchWeather(city); // Get the Call object

        call.enqueue(new Callback<WeatherResponse>() { // Use enqueue to perform the network call asynchronously
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    weatherData.setValue(response.body());
                } else {
                    weatherData.setValue(null); // Handle error
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherData.setValue(null); // Handle failure
            }
        });
    }

    public LiveData<WeatherResponse> getWeather() {
        return weatherData;
    }
}
