package com.example.switch2025.lld.weatherStation;

import com.example.switch2025.lld.weatherStation.observers.Observer;

import java.util.ArrayList;
import java.util.List;

// This is the subject
public class WeatherData implements Subject {

    private final List<Observer> observers;
    private long pressure;
    private float temperature;
    private float humidity;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public long getPressure() {
        return pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setMeasurements(long pressure, float temperature, float humidity) {
        this.pressure = pressure;
        this.temperature = temperature;
        this.humidity = humidity;

        measurementsChanged();
    }

    public void measurementsChanged() {
        notifyObservers();
    }
}
