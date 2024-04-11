package exGeneric;

/**
 * packageName    : MyGeneric
 * fileName       : BoxTest
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class BoxTest {
    public static void main(String[] args) {
        Box<Person> box = new Box<>();

        box.setData(new Person("홍길동"));
        System.out.println(box.getData());

        box.setData(new Student("김복순", 2));
        System.out.println(box.getData());

        box.setData(new College("대조영",2,4));
        System.out.println(box.getData());
    }
}
