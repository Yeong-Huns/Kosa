package duck.category;

import duck.behavior.fly.FlyWithWings;
import duck.behavior.quack.Quack;

/**
 * packageName    : duck
 * fileName       : RedheadDuck
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class RedheadDuck extends Duck{
    public RedheadDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("아메리카흰죽지? 처럼보인다.");
    }
}
