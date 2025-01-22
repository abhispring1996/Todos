package com.example.switch2025.lld.coffeeMaker.decorators;

import com.example.switch2025.lld.coffeeMaker.concreteComponents.Coffee;

public class Mocha extends CondimentDecorator {
    public Mocha(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 3.5;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + "Mocha";
    }
}
