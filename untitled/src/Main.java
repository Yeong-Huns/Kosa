import java.util.Arrays;
import java.util.List;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("d");

        List<Object> list = Arrays.asList(new Object[]{new Dog()});
        //list.get(0).bark() -> 불가
        Object o = list.get(0);
        Dog dog = (Dog)o;
        dog.bark();

        //or

        if(o instanceof Dog){
            Dog d = (Dog)o;
        }

        Dog dog1 = (Dog) list.get(0);
        dog1.bark();



    }
}