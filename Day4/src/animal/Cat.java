package animal;

/**
 * packageName    : animal
 * fileName       : Cat
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Cat extends Feline{
    @Override
    public void makeNoise() {
        System.out.println("고양이가 야옹 운다.");
    }

    @Override
    public void eat() {
        System.out.println("고양이가 생선을 먹는다.");
    }

    @Override
    public void attack() {

    }
}
