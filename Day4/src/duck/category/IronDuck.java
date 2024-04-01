package duck.category;

import duck.behavior.fly.FlyRocketPowerd;
import duck.behavior.quack.MuteQuack;

/**
 * packageName    : duck.category
 * fileName       : IronDuck
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class IronDuck extends Duck{
    public IronDuck() {
        flyBehavior = new FlyRocketPowerd();
        quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("철로 된 오리처럼 보인다.");
    }
}
