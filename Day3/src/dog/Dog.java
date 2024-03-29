package dog;

/**
 * packageName    : dog
 * fileName       : Dog
 * author         : Yeong-Huns
 * date           : 2024-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29        Yeong-Huns       최초 생성
 */
public class Dog {
    /*
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.bark();
    }
    이렇게 해도 상관은 없지만 보통은 따로 main 클래스(드라이버 클래스)를 만든다.
    */

    public String name;

    public void bark(){
        System.out.println(name + " says 왈!");
    }
}
