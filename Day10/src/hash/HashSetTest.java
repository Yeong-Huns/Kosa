package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : hash
 * fileName       : HashSetTest
 * author         : Yeong-Huns
 * date           : 2024-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09        Yeong-Huns       최초 생성
 */
public class HashSetTest {
    public static void main(String[] args) {
        Person person1 = new Person("제이크", 30);
        Person person2 = new Person("제이크", 30);

        System.out.println("person1 = " + System.identityHashCode(person1));
        System.out.println("person2 = " + System.identityHashCode(person2));
        System.out.println(person1 == person2);

        Set<Person> personSet = new HashSet<>();

        personSet.add(person1);
        personSet.add(person2);

        int size = personSet.size();
        System.out.println("size = " + size);
    }
}
