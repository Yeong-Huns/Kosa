package springFW.ex02.dp01;

/**
 * packageName    : springFW.ex02.dp01
 * fileName       : LoggerConsole
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class LoggerConsole implements LoggerInterface{
    @Override
    public void writeLog(String log) throws Exception {
        System.out.println("입력 전압: " + log);
    }
}
