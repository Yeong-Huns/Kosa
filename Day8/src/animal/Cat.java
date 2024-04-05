package animal;

/**
 * packageName    : animal
 * fileName       : Cat
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public class Cat extends Feline{

    private String name;
    private int age;

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void makeNoise() {

    }

    public void eat() {

    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void attack() {

    }
}
