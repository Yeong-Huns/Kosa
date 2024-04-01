package animal;

/**
 * packageName    : animal
 * fileName       : Animal
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public abstract class Animal {
    String picture;
    String food;
    String hunger;
    String boundaries;
    String location;

    public void makeNoise(){
        System.out.println("동물이 짖는다.");
    }
    public void eat(){
        System.out.println("동물이 먹는다.");
    }
    public void sleep(){
        System.out.println("동물이 잔다.");
    }
    public void roam(){
        System.out.println("동물이 돌아다닌다.");
    }

    public abstract void attack();

}
