package com.kosa.doobee;

public class DooBee {
    public static void main(String[] args) {
        printDooBee(2);
        printLastDo();
    }
    static void printDooBee(int numberOfLoops) {
        int x = 0;
        while (x < numberOfLoops) {
            System.out.print("DooBee");
            x = x + 1;
        }
    }
    static void printLastDo() {
        System.out.print("Do");
    }
}
