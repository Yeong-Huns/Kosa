package animal;

/**
 * packageName    : animal
 * fileName       : Tiger
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Tiger extends Feline{
    @Override
    public void makeNoise() {
        System.out.println("호랑이가 운다.");
    }

    @Override
    public void attack() {

    }

    @Override
    public void eat() {
        System.out.println("호랑이가 고기를 먹는다.");
    }
}
