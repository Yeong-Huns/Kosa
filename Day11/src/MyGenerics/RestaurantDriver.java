package MyGenerics;

import MyGenerics.beverages.Beverages;
import MyGenerics.beverages.Soda;
import MyGenerics.beverages.Wine;
import MyGenerics.food.ChineseFood;
import MyGenerics.food.Food;
import MyGenerics.food.JapaneseFood;
import MyGenerics.food.KoreanFood;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : MyGenerics
 * fileName       : RestaurantDriver
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class RestaurantDriver {
    public static void main(String[] args) {
        KoreanFood<String, Integer> koreanFood1 = new KoreanFood<String, Integer>("제육볶음","깻잎",6500);
        KoreanFood<String, String> koreanFood2 = new KoreanFood<>("백반","상추","오천원");
        KoreanFood<String, Integer> koreanFood3 = new KoreanFood<>("갈비탕", "김치",8000);
        ChineseFood<String, Integer> chineseFood1 = new ChineseFood<>("짜장면", "단무지", 6000);
        JapaneseFood<String,Integer,Boolean> japaneseFood = new JapaneseFood<>("연어초밥",8500,false);

        List<KoreanFood<String, Integer>> list = new ArrayList<>();
        list.add(koreanFood1);
        //list.add(koreanFood2); -> 타입 (String, Integer) 와 다르기 때문에 넣을 수 없음
        list.add(koreanFood3);

        List<Food<String,Integer>> foodList = new ArrayList<>(); // <String, Integer> 형식의 음식을 넣을 수 있는 리스트
        foodList.add(koreanFood1);
        foodList.add(koreanFood3);
        foodList.add(chineseFood1);
        foodList.add(japaneseFood);
        printFoodPrice(foodList); //-> 모든 음식의 값 출력
        System.out.println("==================");
        List<Beverages<String, Integer>> beveragesList = new ArrayList<>();
        beveragesList.add(new Wine<>("레드와인", 100000));
        beveragesList.add(new Wine<>("화이트와인", 150000));
        beveragesList.add(new Soda<>("콜라", 1500));
        printBeveragePrice(beveragesList); //-> 음료수값 출력

    }
    public static void printAnyPrice(List<? super KoreanFood<String, Integer>> list) {
        for (Object restaurant : list) {
            System.out.println(((Restaurant<String,Integer>)restaurant).getPrice());
        }
    }
    public static void printFoodPrice(List<? extends Food<String, Integer>> list) {
        for (Food<String, Integer> food : list) {
            System.out.println(food.getPrice());
        }
    }
    public static void printBeveragePrice(List<? extends Beverages<String, Integer>> list) {
        for (Beverages<String, Integer> food : list) {
            System.out.println(food.getPrice());
        }
    }
}




