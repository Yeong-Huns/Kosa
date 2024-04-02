package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : generic
 * fileName       : TestDrive
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class TestDrive {
    public static void main(String[] args) {
        Pair<String, Integer> p1 = new OrderedPair<>("Even", 18);
        Pair<String, String> p2 = new OrderedPair<>("Hello", "world");

        p1.getKey();
        p1.getValue();
        p2.getKey();
        p2.getValue();

        /*
        List list = new ArrayList();
        list.add(100);
        list.add("korea");
        list.add(10L);
        list.add(3.14);
         */
        Pair<Long, String> Test1 = new OrderedPair<>(1L, "Generic");
        Pair<Long, String> Test2 = new OrderedPair<>(1L, "Generic");
        System.out.println(Util.compare(Test1, Test2));
    }
}
