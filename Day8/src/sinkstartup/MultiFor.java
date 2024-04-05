package sinkstartup;

/**
 * packageName    : sinkstartup
 * fileName       : MultiFor
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public class MultiFor {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {

            for (int j = 4; j > 2; j--) {
                System.out.println(i + " " + j);
            }

            if (i == 1) {
                i++;
            }
        }
    }

}
