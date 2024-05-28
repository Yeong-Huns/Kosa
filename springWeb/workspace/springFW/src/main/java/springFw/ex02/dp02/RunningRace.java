package springFw.ex02.dp02;

/**
 * packageName    : springFW.ex02.dp02
 * fileName       : RunningRace
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public abstract class RunningRace {
    private void ready(){
        System.out.println("준비!!");
    }

    protected abstract void run();

    private void finish(){
        System.out.println("결승선");
    }

    public void race(){
        ready();
        run();
        finish();
    }

}
