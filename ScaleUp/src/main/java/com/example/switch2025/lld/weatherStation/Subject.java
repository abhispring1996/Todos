package com.example.switch2025.lld.weatherStation;

import com.example.switch2025.lld.weatherStation.observers.Observer;

public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
