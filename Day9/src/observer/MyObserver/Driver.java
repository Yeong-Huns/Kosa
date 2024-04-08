package observer.MyObserver;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : observer.MyObserver
 * fileName       : Driver
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class Driver {
    public static void main(String[] args) {
        Celebrity elonMusk = new Celebrity("Elon Musk");

        Arrays.asList(new Common("Park"),
                new Common("Lee"),
                new Common("John"))
                .forEach(elonMusk::follow);

        elonMusk.newPost("화성 갈끄니까~~~");
    }
}
