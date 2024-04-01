package duck;

import duck.behavior.quack.Squeak;
import duck.category.DecoyDuck;
import duck.category.Duck;
import duck.category.MallardDuck;
import duck.category.RubberDuck;
import mixedMessage.A;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : duck
 * fileName       : DuckDriver
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class DuckDriver {
    public static void main(String[] args) {
        //Duck mallardDuck = new MallardDuck();
        //mallardDuck.performQuack();
        //mallardDuck.performFly();
        List<Duck> duckList = new ArrayList<>();
        duckList.add(new MallardDuck());
        duckList.add(new RubberDuck());
        duckList.add(new DecoyDuck());
        duckList.forEach(Duck::display);
        duckList.forEach(Duck::performQuack);
        duckList.forEach(Duck::performFly);

        //mallardDuck.setQuackBehavior(new Squeak());
        //mallardDuck.performQuack();


    }
}
