package animal;

/**
 * packageName    : animal
 * fileName       : Dog
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Dog extends Canine implements Pet{
    @Override
    public void makeNoise() {
        System.out.println("강아지가 왈왈 짖는다.");
    }

    @Override
    public void eat() {
        System.out.println("강아지가 사료를 먹는다.");
    }

    @Override
    public void beFriendly() {

    }

    @Override
    public void play() {

    }

    @Override
    public void attack() {

    }
}
