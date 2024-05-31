package springFW.ex06.mvc01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : springFW.ex06.mvc01
 * fileName       : BoardInsertHandler
 * author         : Yeong-Huns
 * date           : 2024-05-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-31        Yeong-Huns       최초 생성
 */
public class BoardInsertHandler implements CommandHandler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("process : BoardInsertHandler");
        return "redirect:/boardList.do";
    }
}
