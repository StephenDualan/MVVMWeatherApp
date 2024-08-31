package com.example.wew.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.wew.Model.WeatherViewModel;
import com.example.wew.Repository.WeatherRepository;

public class WeatherViewModelFactory implements ViewModelProvider.Factory {
    private final WeatherRepository repository;

    public WeatherViewModelFactory(WeatherRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WeatherViewModel.class)) {
            return (T) new WeatherViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
