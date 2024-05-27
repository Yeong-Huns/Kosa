package springFW.ex01.oop.cooperation;

/**
 * packageName    : springFW.ex01.oop.cooperation
 * fileName       : TransTest
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class TransTest {
    public static void main(String[] args) {
        GeneralPassanger pass1 = new GeneralPassanger(10000);
        pass1.showInfo();

        GeneralPassanger pass2 = new GeneralPassanger("승객2", 10000);
        pass2.showInfo();


        Bus bus100 = new Bus(100);
        bus100.showInfo();

        Bus bus200 = new Bus(200);
        bus200.showInfo();

        bus100.getIn(pass1);
        bus100.getOut(pass1);

        bus200.getIn(pass1);
        bus200.getOut(pass1);

        bus100.getIn(pass2);
        bus100.getOut(pass2);

        Student std = new Student("학생1", 20000);
        std.showInfo();
        bus200.getIn(std);
    }
}
