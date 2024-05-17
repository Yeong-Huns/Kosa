import java.util.Arrays;
import java.util.List;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-23        Yeong-Huns       최초 생성
 */
public class Main {

    public static void main(String[] args) {

        String temp = "onetwothreefourfivesixseveneightnine";
        temp =temp.toUpperCase();
        System.out.println(temp);
        for(Number number : Number.values()){
            System.out.println(number);
            number.getNumber();

            int[] arrr = {1,2,3,4,5,6,7,8};
            List<Integer> list =  Arrays.stream(arrr).boxed().toList();
        }

    }
    public enum Number {
        ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);
        private int number;
        private Number(int num){
            number = num;
        }

        public int getNumber() {
            return number;
        }
    }
}
