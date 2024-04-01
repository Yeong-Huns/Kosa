package animal;

/**
 * packageName    : animal
 * fileName       : Hippo
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Hippo extends Animal{
    @Override
    public void makeNoise() {
        System.out.println("하마가 운다.");
    }

    @Override
    public void eat() {
        System.out.println("하마가 먹는다.");
    }

    @Override
    public void attack() {

    }
}
