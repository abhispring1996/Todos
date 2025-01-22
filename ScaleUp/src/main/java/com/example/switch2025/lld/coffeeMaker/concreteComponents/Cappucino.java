package com.example.switch2025.lld.coffeeMaker.concreteComponents;

public class Cappucino extends Coffee {

    public Cappucino() {
        description = "Cappucino";
    }

    @Override
    public double getCost() {
        return 15.5;
    }
}
