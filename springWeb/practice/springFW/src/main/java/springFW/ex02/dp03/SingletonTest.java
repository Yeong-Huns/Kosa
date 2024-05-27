package springFW.ex02.dp03;

/**
 * packageName    : springFW.ex02.dp03
 * fileName       : SingletonTest
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.showCount();

        Singleton singleton1 = Singleton.getInstance();
        singleton1.showCount();
    }
}
