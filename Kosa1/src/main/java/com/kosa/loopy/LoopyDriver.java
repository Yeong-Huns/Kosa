package com.kosa.loopy;

public class LoopyDriver {
    public static void main(String[] args) {
        Printer.beforeTheLoop();
        int numberRepetitions = InputScanner.Input("반복할 횟수를 지정해주세요.");
        Printer.loop(numberRepetitions);
        Printer.afterLoop();
    }
}

