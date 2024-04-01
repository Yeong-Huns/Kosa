package animal;

/**
 * packageName    : animal
 * fileName       : Snake
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Snake extends Animal{
    @Override
    public void roam() {
        System.out.println("샤샤샥 움직인다.");
    }

    @Override
    public void eat() {
        System.out.println("나무를 타고 새의 알을 먹는다.");
    }

    @Override
    public void attack() {

    }

    @Override
    public void makeNoise() {
        System.out.println("쉬쉬쉭 소리를 낸다.");
    }
}
