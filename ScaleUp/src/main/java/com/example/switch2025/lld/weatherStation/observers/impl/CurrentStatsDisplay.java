package com.example.switch2025.lld.weatherStation.observers.impl;

import com.example.switch2025.lld.weatherStation.WeatherData;
import com.example.switch2025.lld.weatherStation.observers.Display;
import com.example.switch2025.lld.weatherStation.observers.Observer;

public class CurrentStatsDisplay implements Observer, Display {

    private final WeatherData weatherData;
    private long pressure;
    private float temperature;
    private float humidity;

    public CurrentStatsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        // rather than subject pushing the state, we can pull out the required state using Subject object
        this.pressure = weatherData.getPressure();
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();

        display();
    }

    @Override
    public void display() {
        System.out.println("Current Weather Station Statistics:" +
                " Pressure: " + pressure + ", Temperature: " + temperature + ", Humidity: " + humidity);
    }
}
