package springFw.ex02.dp01;

import java.util.Scanner;

/**
 * packageName    : springFW.ex02.dp01
 * fileName       : LoggerService
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class LoggerService {
    private LoggerInterface logger;
    public LoggerService(LoggerInterface logger) {
        this.logger = logger;
    }

    public LoggerService() {
    }

    public void start() throws Exception{
        System.out.println("전압을 입력해주세요.!!");
        Scanner scanner = new Scanner(System.in);
        try {
            while(true){
                String v = scanner.next().toUpperCase();

                if("Q".equals(v)){
                    System.out.println("종료되었습니다.");
                    break;
                }

                System.out.println("입력 전압 : " + v);
            }
        }catch (Exception e) {
            throw new Exception("입력값에 문제가 있습니다.!!");
        }
        finally {
            scanner.close();
        }
    }
}
