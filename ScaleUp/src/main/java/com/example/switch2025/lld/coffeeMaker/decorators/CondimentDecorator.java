package com.example.switch2025.lld.coffeeMaker.decorators;

import com.example.switch2025.lld.coffeeMaker.concreteComponents.Coffee;

public abstract class CondimentDecorator extends Coffee {

    // reference to the main concrete component
    protected Coffee coffee;

    protected CondimentDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public abstract double getCost();

    public abstract String getDescription();
}
