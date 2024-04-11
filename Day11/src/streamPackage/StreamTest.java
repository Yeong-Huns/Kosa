package streamPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * packageName    : streamPackage
 * fileName       : StreamTest
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "orange", "banana");
        for (String result : fruits) {
            System.out.println(result);
        }
        //stream 을 사용할 경우
        List<String> fruits2 = Arrays.asList("apple", "orange", "banana");
        Stream<String> fruits2stream = fruits2.stream();
        fruits2stream.forEach(System.out::println);
    }
}
