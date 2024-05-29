package springFw.ex04.aop01;

import java.util.Date;

/**
 * packageName    : springFW.ex04.aop01
 * fileName       : HelloLog
 * author         : Yeong-Huns
 * date           : 2024-05-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29        Yeong-Huns       최초 생성
 */
public class HelloLog {
    public static void log(){
        System.out.println(">>> Log : " + new Date());
    }
}
