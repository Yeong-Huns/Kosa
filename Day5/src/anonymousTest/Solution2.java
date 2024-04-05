package anonymousTest;

/**
 * packageName    : anonymousTest
 * fileName       : Driver2
 * author         : Yeong-Huns
 * date           : 2024-04-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-03        Yeong-Huns       최초 생성
 */
public class Solution2 {
    public interface Calc {
        int calc(int a, int b);
        default int getResult(int a, int b){
            return this.calc(a,b);
        };
    }
    public static void main(String[] args) {
        System.out.println(((Calc)Integer::sum).getResult(4,7));
    }
}
