package com.kosa.strategy;

import com.kosa.strategy.category.detail.Apple;
import com.kosa.strategy.category.detail.Apple1;
import com.kosa.strategy.category.detail.Apple2;
import com.kosa.strategy.filter.AppleFilter;
import com.kosa.strategy.filter.GreenColorAppleFilter;
import com.kosa.strategy.filter.OverWeightAppleFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : apple
 * fileName       : AppleDriver
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class AppleDriver {
    public static void main(String[] args) {

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple1("맛좋은 사과", "GREEN", 40));
        appleList.add(new Apple2("달콤한 사과", "RED", 90));

        //appleList.forEach(Apple::getStatus); 전체 사과 출력
        filterApples(appleList, new GreenColorAppleFilter()).forEach(Apple::getStatus); // 녹색 사과 필터링
        filterApples(appleList, new OverWeightAppleFilter()).forEach(Apple::getStatus); // 무게 80이상 사과 필터링
    }
    public static List<Apple> filterApples(List<Apple> appleList, AppleFilter filter){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: appleList){
            if(filter.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
