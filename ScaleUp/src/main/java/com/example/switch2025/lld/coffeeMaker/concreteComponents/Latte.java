package com.example.switch2025.lld.coffeeMaker.concreteComponents;

public class Latte extends Coffee {

    public Latte() {
        this.description = "Latte";
    }

    @Override
    public double getCost() {
        return 30;
    }
}
