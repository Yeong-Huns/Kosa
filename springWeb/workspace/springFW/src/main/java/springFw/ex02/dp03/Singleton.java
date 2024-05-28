package springFw.ex02.dp03;

/**
 * packageName    : springFW.ex02.dp03
 * fileName       : Singleton
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class Singleton {
    private static Singleton instance;
    private int count = 0;

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    private Singleton() {}

    public void showCount(){
        count++;
        System.out.println("count : " +count);
    }
}
