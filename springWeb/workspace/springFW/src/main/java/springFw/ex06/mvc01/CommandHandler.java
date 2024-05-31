package springFw.ex06.mvc01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : springFW.ex06.mvc01
 * fileName       : CommandHandler
 * author         : Yeong-Huns
 * date           : 2024-05-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-31        Yeong-Huns       최초 생성
 */
public interface CommandHandler { //커맨드가 왔을때 핸들링하기 위한 커맨드 핸들러
    String process(HttpServletRequest request, HttpServletResponse response);

}
