package springFw.ex06.mvc01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : springFW.ex06.mvc01
 * fileName       : NullHandler
 * author         : Yeong-Huns
 * date           : 2024-05-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-31        Yeong-Huns       최초 생성
 */
public class NullHandler implements CommandHandler{
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("응답값 없음");
        return null;
    }
}
