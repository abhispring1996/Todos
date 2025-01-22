package com.example.switch2025.lld.coffeeMaker;

import com.example.switch2025.lld.coffeeMaker.concreteComponents.Coffee;
import com.example.switch2025.lld.coffeeMaker.concreteComponents.Latte;
import com.example.switch2025.lld.coffeeMaker.decorators.Mocha;
import com.example.switch2025.lld.coffeeMaker.decorators.Whip;

public class CoffeeMaker {

    public static void main(String[] args) {
        Coffee coffee = new Latte();
        // decorating the coffee
        coffee = new Mocha(coffee);
        coffee = new Whip(coffee);

        System.out.println("The description: " + coffee.getDescription());
        System.out.println("The cost: " + coffee.getCost());
    }
}
