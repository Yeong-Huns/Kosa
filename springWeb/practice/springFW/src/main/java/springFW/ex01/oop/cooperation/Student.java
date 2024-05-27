package springFW.ex01.oop.cooperation;

/**
 * packageName    : springFW.ex01.oop.cooperation
 * fileName       : Student
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class Student extends GeneralPassanger{

    private int money = 0;;

    public Student(int money) {
        super(money);
        this.money = money;
    }

    public Student(String name, int money) {
        super(name, money);
        // TODO Auto-generated constructor stub
        this.money = money;
    }

    @Override
    public void deposit(int amount) {
        // TODO Auto-generated method stub
        this.money += amount + (amount*0.2);
    }





}
