package annonymousClass;

/**
 * packageName    : annonymousClass
 * fileName       : newTest
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class newTest {
    public final int value = 10;

    public void run(){
        int value = 5;
        System.out.println(this.value);
    }

    public static void main(String[] args) {
        newTest n = new newTest();
        n.run();    // -> 10
    }
}
