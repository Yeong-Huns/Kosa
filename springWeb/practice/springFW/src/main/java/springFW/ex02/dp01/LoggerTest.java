package springFW.ex02.dp01;

/**
 * packageName    : springFW.ex02.dp01
 * fileName       : test
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class LoggerTest {
    public static void main(String[] args) {
        //LoggerService loggerService = new LoggerService(new LoggerConsole());
        LoggerService loggerService = new LoggerService(new LoggerFile());
        try {
            loggerService.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
