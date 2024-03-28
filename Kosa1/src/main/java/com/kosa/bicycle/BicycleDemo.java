package com.kosa.bicycle;

public class BicycleDemo {
    public static void main(String[] args) {
        Bicycle bike1 = new Bicycle();
        Bicycle bike2 = new Bicycle();

        bike1.changeCadance(50);
        bike1.printStatus();
    }
}
