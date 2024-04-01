package animal;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : animal
 * fileName       : AnimalDriver
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class AnimalDriver {

    public static void main(String[] args) {

        List<Animal> list = new ArrayList<>();
        list.add(new Cat());
        list.add(new Dog());
        list.add(new Hippo());
        list.add(new Lion());
        list.add(new Tiger());
        list.add(new Wolf());
        list.add(new Wolf());

        for(Animal animal : list){
            animal.roam();
            animal.eat();
            animal.makeNoise();
            System.out.println();
        }
    }
}
