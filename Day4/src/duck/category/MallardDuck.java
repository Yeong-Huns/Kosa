package duck.category;

import duck.behavior.fly.FlyWithWings;
import duck.behavior.quack.Quack;

/**
 * packageName    : duck
 * fileName       : MallardDuck
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class MallardDuck extends Duck{
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        //천둥오리
        System.out.println("천둥오리처럼 보인다.");
    }
}
