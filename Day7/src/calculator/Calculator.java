package calculator;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * packageName    : calculator
 * fileName       : Calculator
 * author         : Yeong-Huns
 * date           : 2024-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04        Yeong-Huns       최초 생성
 */
public enum Calculator {
    PULS("덧셈", (a,b)-> a + b),
    MINUS("뺄셈", (a,b)-> a - b),
    MULTIPLY("곱셈", (a,b)-> a * b),
    DIVIDE("나눗셈", (a,b)-> a / b);

    private final String name;
    private final BiFunction<Integer,Integer,Integer> function ;

    Calculator(String name, BiFunction<Integer, Integer, Integer> function) {
        this.name = name;
        this.function = function;
    }

    public int getResult(int num1, int num2){
        return this.function.apply(num1, num2);
    }
}
