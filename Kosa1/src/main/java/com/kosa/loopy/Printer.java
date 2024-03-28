package com.kosa.loopy;

public class Printer {

    private static int value = 0;
    public static void beforeTheLoop(){
        System.out.println("Before the Loop");
    }
    public static void loop(int numberRepetitions){
        while (value < numberRepetitions){
           System.out.println("In the Loop");
           System.out.println("X : " + value);
           value += 1;
        }
    }
    public static void afterLoop(){
        System.out.println("This is after Loop : " + value);
    }
}
