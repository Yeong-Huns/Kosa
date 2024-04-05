package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * packageName    : calculator
 * fileName       : calcDriver
 * author         : Yeong-Huns
 * date           : 2024-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04        Yeong-Huns       최초 생성
 */
public class calcDriver {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(
                Calculator.PULS.getResult(4,8),
                Calculator.MINUS.getResult(9,3),
                Calculator.MULTIPLY.getResult(4, 9),
                Calculator.DIVIDE.getResult(8, 4));
        for(int a: list){
            System.out.println(a);
        }
        int[] array = {1,2,3,4,5};


    }
}
