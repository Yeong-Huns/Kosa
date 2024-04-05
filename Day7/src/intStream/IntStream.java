package intStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * packageName    : intStream
 * fileName       : IntStream
 * author         : Yeong-Huns
 * date           : 2024-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04        Yeong-Huns       최초 생성
 */
public class IntStream {
    public static void main(String[] args) {
        int x = 10;
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= x; i++){
            if(i % 2 == 1) list.add(i);
        }

        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

    }

}
