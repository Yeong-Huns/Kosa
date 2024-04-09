package MyCollections.hashMap;

import java.util.*;
import java.util.stream.Stream;

/**
 * packageName    : MyCollections.hashMap
 * fileName       : Driver
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
        int numberOfCustomer = (int)(Math.random()*50+100);
        String[] nameList = {"Kim", "Park", "Lee", "Choi"};
        List<String> list = new ArrayList<>();
        for(int i = 0; i < numberOfCustomer; i++){
            list.add(nameList[(int)(Math.random()*4)]); // list 안에 kim, park, Lee 랜덤 저장
        }

        HashMap<String, Integer> hashMap = new HashMap<>();
        for(String str : list){
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
            // -> 해시맵에 키가 이미 존재하면 value += 1 || 0(default) += 1
        }
        // 빈도수를 해시맵에 키 : 밸류 형태로 저장

        List<Map.Entry<String, Integer>> sortedlist = new ArrayList<>(hashMap.entrySet());
        sortedlist.stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(i->System.out.println("방문한 손님중 가장 많은 성씨는 " + i.getKey() + "입니다.\n"));
        //가장 많이 나온 성씨를 출력(최빈값)

        sortedlist.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        //과연 그럴까? 내림차순 정렬로 확인

        sortedlist.forEach(i-> System.out.println(i.getKey() + " " + i.getValue()));
    }
}
