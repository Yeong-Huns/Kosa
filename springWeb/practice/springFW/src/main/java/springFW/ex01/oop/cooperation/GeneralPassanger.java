package springFW.ex01.oop.cooperation;

/**
 * packageName    : springFW.ex01.oop.cooperation
 * fileName       : GeneralPassanger
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class GeneralPassanger implements Passanger{
    private String name = "";
    private int money = 0;


    public GeneralPassanger(int money) {
        this.money = money;
    }

    public GeneralPassanger(String name, int money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public void withdraw(int amount) {
        // TODO Auto-generated method stub
        this.money -= amount;
    }

    public void deposit(int amuont) {
        this.money += amuont;
    }


    @Override
    public void showInfo() {
        // TODO Auto-generated method stub
        System.out.println(this.name + "의 소지금은 " + this.money + "입니다. ");
    }
}
