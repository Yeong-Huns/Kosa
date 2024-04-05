package animal;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : animal
 * fileName       : MethodTestDrive
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public class MethodTestDrive {
    public static void main(String[] args) {
        Dog d = new Dog("바둑이",2);
        System.out.println(d.toString());
        List<Animal> animalList = Arrays.asList(new Animal[]{new Dog("흰둥이",2),new Dog("흰둥이",2)});
        Animal d2 = new Cat();
        System.out.println(d2);
        System.out.println(d2.toString());
    }
}
