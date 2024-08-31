package com.example.wew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wew.Client.ApiClient;
import com.example.wew.Model.WeatherViewModel;
import com.example.wew.Repository.WeatherRepository;
import com.example.wew.Response.WeatherResponse;
import com.example.wew.Retrofit.WeatherApi;
import com.example.wew.View.WeatherViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel viewModel;
    private EditText cityEditText; // Input field for the city
    private Button searchButton; // Search button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        cityEditText = findViewById(R.id.city_edit_text);
        searchButton = findViewById(R.id.search_button);

        // Create an instance of WeatherRepository
        WeatherRepository repository = new WeatherRepository(ApiClient.getRetrofitInstance().create(WeatherApi.class));

        // Create the ViewModel using the factory
        WeatherViewModelFactory factory = new WeatherViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(WeatherViewModel.class);

        // Set up observer for weather data
        viewModel.getWeather().observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse response) {
                if (response != null) {
                    // Update UI with weather data
                    updateUI(response);
                } else {
                    // Handle error response
                    handleError();
                }
            }
        });

        // Set up search button click listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityEditText.getText().toString();
                if (!city.isEmpty()) {
                    viewModel.fetchWeather(city); // Fetch weather for the entered city
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUI(WeatherResponse response) {
        // Update your UI components with the weather data
        TextView temperatureTextView = findViewById(R.id.temperature_text_view);
        temperatureTextView.setText(String.format("Temperature: %.2f°C", response.getMain().getTemp()));

        // Update weather description
        if (response.getWeather() != null && response.getWeather().length > 0) {
            WeatherResponse.Weather weather = response.getWeather()[0]; // Get the first weather object
            TextView weatherDescriptionTextView = findViewById(R.id.weather_description_text_view);
            weatherDescriptionTextView.setText(weather.getDescription());
        }

        // Update humidity
        TextView humidityTextView = findViewById(R.id.humidity_text_view);
        humidityTextView.setText(String.format("Humidity: %d%%", response.getMain().getHumidity()));

        // Update wind speed
        TextView windSpeedTextView = findViewById(R.id.wind_speed_text_view);
        windSpeedTextView.setText(String.format("Wind Speed: %.2f m/s", response.getWind().getSpeed()));
    }

    private void handleError() {
        // Handle error response properly, e.g., display an error message
        Toast.makeText(MainActivity.this, "Error fetching weather data", Toast.LENGTH_SHORT).show();
    }
}
