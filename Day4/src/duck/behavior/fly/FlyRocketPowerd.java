package duck.behavior.fly;

/**
 * packageName    : duck.behavior.fly
 * fileName       : FlyRocketPowerd
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class FlyRocketPowerd implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("로켓의 힘으로 날아오른다.");
    }
}
