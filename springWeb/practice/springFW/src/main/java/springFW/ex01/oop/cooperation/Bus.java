package springFW.ex01.oop.cooperation;

/**
 * packageName    : springFW.ex01.oop.cooperation
 * fileName       : Bus
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class Bus {
    private int busNumber = 0;
    private int passengerCount = 0;
    private int money = 0;

    public Bus(int busNumber) {
        this.busNumber = busNumber;
        this.passengerCount = 0;
        this.money = 0;
    }

    public void getIn(Passanger pass) {
        ++this.passengerCount;
        this.money += 1000;
        pass.withdraw(1000);

        System.out.println("버스 승차");
        pass.showInfo();
        showInfo();
    }

    public void getIn(Student student) {
        ++this.passengerCount;
        this.money += 800;
        student.withdraw(800);

        System.out.println("학생 버스 승차");
        student.showInfo();
        showInfo();
    }

    public void showInfo() {
        System.out.println("버스" + this.busNumber + "번의 승객은 "
                + this.passengerCount + "명이고, 수입은 " + this.money + "입니다.");
    }

    public void getOut(Passanger pass) {
        --this.passengerCount;
        System.out.println("버스 하차");
        showInfo();
    }
}