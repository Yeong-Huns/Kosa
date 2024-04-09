package MyStream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09        Yeong-Huns       최초 생성
 */
public class Main {
    public static void main(String[] args) {

        int numberOfCustomer = (int) (Math.random() * 50 + 50);
        String[] nameList = {"Kim", "Park", "Lee", "Choi"};

        new ArrayList<>(IntStream.rangeClosed(1, numberOfCustomer)    //1부터 numberOfCustomer 까지 순환
                .mapToObj(i -> nameList[(int)(Math.random()*numberOfCustomer) % nameList.length])  //
                .collect(Collectors.groupingBy(obj -> obj))
                .entrySet())
                .stream()
                .sorted(Comparator.comparingInt(a -> a.getValue().size()))
                .forEach(a->System.out.println(a.getKey() + " " + a.getValue().size()));


        /*
                new ArrayList<>(IntStream.range(0, numberOfCustomer)
                        .mapToObj(i -> nameList[i % nameList.length])
                        .collect(Collectors.groupingBy(obj -> obj))
                        .entrySet())
                        .stream()
                        .max((a1,a2) -> a1.getValue().size() - a2.getValue().size())
                        .ifPresentOrElse(i->System.out.println(i.getKey() + " " + i.getValue().size()), ()->System.out.println("Not Found"));
*/


    }

}
