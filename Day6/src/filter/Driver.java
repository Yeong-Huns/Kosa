package filter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : filter
 * fileName       : Driver
 * author         : Yeong-Huns
 * date           : 2024-04-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-03        Yeong-Huns       최초 생성
 */
public class Driver {
    public static void main(String[] args) {

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple(40, Color.GREEN, "충주사과"));
        appleList.add(new Apple(20, Color.RED, "대구사과"));
        appleList.add(new Apple(55, Color.RED, "대구사과"));
        appleList.add(new Apple(75, Color.GREEN, "의성사과"));
        appleList.add(new Apple(96, Color.RED, "대구사과"));
        appleList.add(new Apple(30, Color.RED, "대구사과"));
        appleList.add(new Apple(60, Color.RED, "의성사과"));
        appleList.add(new Apple(50, Color.GREEN, "충주사과"));

        class temp implements AppleFilter {
            @Override
            public boolean test(Apple apple) {
                return apple.getName().contains("대구");
            }
        }

        filterList(appleList, new temp()).forEach(Apple::printApple);  // 대구사과만 필터링해 출력

        filterList(appleList, new AppleFilter() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals(Color.GREEN);
            }
        }).forEach(Apple::printApple);  // 초록색 사과만 출력

        filterList(appleList, (a)-> a.getWeight() > 40).forEach(Apple::printApple); // 무게가 40 이상인 사과만 출력


    }
    public static List<Apple> filterList(List<Apple> list, AppleFilter filter){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: list){
            if(filter.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
