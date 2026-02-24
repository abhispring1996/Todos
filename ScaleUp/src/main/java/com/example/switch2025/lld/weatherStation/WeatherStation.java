package com.example.switch2025.lld.weatherStation;

import com.example.switch2025.lld.weatherStation.observers.Display;
import com.example.switch2025.lld.weatherStation.observers.impl.CurrentStatsDisplay;

public class WeatherStation {

    public static void main(String[] args) {
        // creating the subject
        WeatherData weatherData = new WeatherData();
        // registering the observer
        Display currentStatsDisplay = new CurrentStatsDisplay(weatherData);

        weatherData.setMeasurements(123, 9.5f, 56.8f);
        weatherData.setMeasurements(55, 9.5f, 56.8f);
    }
}
