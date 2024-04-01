package animal;

/**
 * packageName    : animal
 * fileName       : Wolf
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Wolf extends Canine{
    @Override
    public void makeNoise() {
        System.out.println("늑대가 하울링한다.");
    }

    @Override
    public void attack() {

    }

    @Override
    public void eat() {
        System.out.println("늑대가 고기를 먹는다.");
    }
}
