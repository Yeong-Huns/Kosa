package com.kosa.bicycle;

public class Bicycle {
    private int cadance = 0;
    private int speed = 0;
    private int gear = 1;

    public void changeCadance(int value) {
        this.cadance = value;
    }
    public void speedUp(int inc) {
        this.speed += inc;
    }
    public void changeGear(int value) {
        this.gear = value;
    }
    public void printStatus() {
        System.out.println("cadance : " + cadance + " speed : " + speed + " gear : " + gear);
    }

}
