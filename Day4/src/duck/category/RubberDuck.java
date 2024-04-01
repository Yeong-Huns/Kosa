package duck.category;

import duck.behavior.fly.FlyNoWay;
import duck.behavior.quack.Squeak;

/**
 * packageName    : duck
 * fileName       : RubberDuck
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class RubberDuck extends Duck{
    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("고무오리처럼 보인다.");
    }
}
