package duck.category;

import duck.behavior.fly.FlyNoWay;
import duck.behavior.fly.FlyWithWings;
import duck.behavior.quack.MuteQuack;

/**
 * packageName    : duck
 * fileName       : DecoyDuck
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class DecoyDuck extends Duck{
    public DecoyDuck() {
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("가짜 오리 처럼 보인다.");
    }
}
