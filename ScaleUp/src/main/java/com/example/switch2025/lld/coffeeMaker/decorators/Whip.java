package com.example.switch2025.lld.coffeeMaker.decorators;

import com.example.switch2025.lld.coffeeMaker.concreteComponents.Coffee;

public class Whip extends CondimentDecorator {

    public Whip(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 34;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + "Whip";
    }
}
