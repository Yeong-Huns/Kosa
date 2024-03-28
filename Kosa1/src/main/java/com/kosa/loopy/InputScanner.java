package com.kosa.loopy;

import java.util.Scanner;

public class InputScanner {
    public static int Input(String message){
        System.out.println(message);
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        scan.close();
        return input;
    }
}
