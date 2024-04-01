package animal;

/**
 * packageName    : animal
 * fileName       : Canine
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public abstract class Canine extends Animal{
    @Override
    public void roam() {
        System.out.println("갯과 동물이 돌아다닌다.");
    }
}
