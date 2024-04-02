package appleFilter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * packageName    : appleFilter
 * fileName       : AppleDriver
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class AppleDriver {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple(40, Color.RED));
        appleList.add(new Apple(50,Color.GREEN));
        appleList.add(new Apple(70, Color.RED));
        appleList.add(new Apple(60, Color.GREEN));
        appleList.add(new Apple(70, Color.RED));
        appleList.add(new Apple(80, Color.RED));
        appleList.add(new Apple(90, Color.RED));
        appleList.add(new Apple(100, Color.GREEN));
        appleList.add(new Apple(85, Color.GREEN));

        Filter filter = new Filter();
        //filter.filteredList(appleList, new GreenAppleFilter()).forEach(Apple::getStatus);
        // 초록사과 필터링
        //filter.filteredList(appleList, new OverWeightFilter()).forEach(Apple::getStatus);
        // 무게 80이상 사과 필터링
        /*
        filter.filteredList(appleList, new AppleFilter() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals(Color.RED);
            }
        }).forEach(Apple::getStatus);*/
        // 빨간 사과 필터링
        filter.filteredList(appleList, (Apple apple)-> apple.getWeight() <80).forEach(Apple::getStatus);
        // 무게 80 아래 사과 필터링
        String str = "aya";
        int test1 = 2;


    }

}
