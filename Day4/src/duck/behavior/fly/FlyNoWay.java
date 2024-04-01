package duck.behavior.fly;

/**
 * packageName    : duck
 * fileName       : FlyNoWay
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("오리는 날 수 없다");
    }
}
