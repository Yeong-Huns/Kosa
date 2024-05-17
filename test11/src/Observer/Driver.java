package Observer;

import java.util.Arrays;

/**
 * packageName    : Observer
 * fileName       : Driver
 * author         : Yeong-Huns
 * date           : 2024-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10        Yeong-Huns       최초 생성
 */
public class Driver {
    public static void main(String[] args) {
        Creator creator = new Creator();
        Common c1 = new Common("park");
        Common c2 = new Common("kim");
        Common c3 = new Common("lee");
        Common c4 = new Common("son");
        Common c5 = new Common("john");
        Common c6 = new Common("sick");

        creator.subscribe(c1);
        creator.subscribe(c2);
        creator.subscribe(c3);
        creator.upload("좋댓구알");
    }
}
