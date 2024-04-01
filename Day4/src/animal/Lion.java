package animal;

/**
 * packageName    : animal
 * fileName       : Lion
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Lion extends Feline{
    @Override
    public void makeNoise() {
        System.out.println("사자가 어흥 운다.");
    }

    @Override
    public void eat() {
        System.out.println("사자가 먹는다.");
    }

    @Override
    public void attack() {

    }
}
