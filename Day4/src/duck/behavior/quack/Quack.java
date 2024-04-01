package duck.behavior.quack;

/**
 * packageName    : duck
 * fileName       : Quack
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Quack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("꿱꿱");
    }
}
