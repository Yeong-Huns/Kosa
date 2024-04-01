package duck.behavior.fly;

/**
 * packageName    : duck
 * fileName       : FlyWithWings
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("멋진 달이 될래요.");
    }
}
