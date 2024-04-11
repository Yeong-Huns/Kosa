import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Main {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("apple", "banana", "apple", "mango", "apple", "apple", "mango", "strawberry");

        list.stream()
                .distinct()
                .filter(i -> i.length() < 8)
                .peek(a -> System.out.println())
                .filter(s -> s.startsWith("a"))
                .peek(System.out::println)
                .forEach(i -> System.out.println());
    }
}