package MyCollections.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * packageName    : MyCollections
 * fileName       : ArrayList
 * author         : Yeong-Huns
 * date           : 2024-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09        Yeong-Huns       최초 생성
 */
public class Driver {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple(Color.GREEN, 80, 3000),
                new Apple(Color.GREEN, 40, 2000),
                new Apple(Color.RED, 50, 2100),
                new Apple(Color.RED, 60, 2200),
                new Apple(Color.GREEN, 70, 2300),
                new Apple(Color.RED, 80, 2400),
                new Apple(Color.GREEN, 35, 2900),
                new Apple(Color.RED, 44, 2800),
                new Apple(Color.GREEN, 58, 2700));

        appleList.sort(Comparator.comparing(Apple::getPrice));  // 사과의 가격으로 sort
        System.out.println("가격 정렬 결과를 출력합니다 : ");
        appleList.forEach(Apple::printApple);
        System.out.println("================================");
        appleList.sort((i1, i2) -> i1.getWeight() - i2.getWeight()); // 사과의 무게로 sort (Comparator)
        System.out.println("무게 정렬 결과를 출력합니다 : ");
        appleList.forEach(Apple::printApple);
    }

}
