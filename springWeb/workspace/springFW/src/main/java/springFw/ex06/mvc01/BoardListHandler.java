package springFw.ex06.mvc01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : springFW.ex06.mvc01
 * fileName       : BoardListHandler
 * author         : Yeong-Huns
 * date           : 2024-05-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-31        Yeong-Huns       최초 생성
 */
public class BoardListHandler implements CommandHandler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("process : BoardListHandler");
        return "boardlist.jsp";
    }
}
