package sinkstartup;

/**
 * packageName    : sinkstartup
 * fileName       : Output
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public class Output {
    public static void main(String[] args) {
        Output output = new Output();
        output.go();
    }

    void go() {
        int value = 7;
        for (int i = 1; i < 8; i++) {
            value++;
            if (i > 4) {
                System.out.print(++value + " ");
            }
            if (value > 14) {
                System.out.println(" i = " + i);
                break;
            }
        }
    }

}
