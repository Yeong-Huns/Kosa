package nestedClass;

/**
 * packageName    : nestedClass
 * fileName       : OuterClass
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class OuterClass {
    class InnerClass{

    }
    static class StaticNestedClass{
        //void accessMembers(OuterClass outerClass)
    }

    public static void main(String[] args) {
        System.out.println("Inner Class");
        System.out.println("=================");
        OuterClass outerClass = new OuterClass();

    }
}
