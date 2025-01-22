package com.example.switch2025.lld.coffeeMaker.concreteComponents;

public abstract class Coffee {
    protected String description;

    public abstract double getCost();

    public String getDescription() {
        return "Coffee" + description;
    }
}
