package mixedMessage;

/**
 * packageName    : mixedMessage
 * fileName       : mixedDiriver
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class mixedDiriver {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        A a2 = new C();
        // ===========================================================
        b.m1();
        c.m2();
        a.m3();
        System.out.println("===========================================");
        c.m1();
        c.m2();
        c.m3();
        System.out.println("===========================================");
        a.m1();
        b.m2();
        c.m3();
        System.out.println("===========================================");
        a2.m1();
        a2.m2();
        a2.m3();
        System.out.println("===========================================");

    }
}
