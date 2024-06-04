package springFW.ex06.mvc02;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : springFW.ex06.mvc02
 * fileName       : PageViewLogIntercepter
 * author         : Yeong-Huns
 * date           : 2024-06-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-04        Yeong-Huns       최초 생성
 */
public class PageViewLogInterceptor implements HandlerInterceptor {
    //Controller 를 기준으로 1. 컨트롤러 전  2. 컨트롤러 후(뷰이전) 3. 뷰를 실행한 이후
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        //return HandlerInterceptor.super.preHandle(request, response, handler);

        //조건문 작성
        //1. 저장할 DB(NoSQL주로 사용) 2.저장할 대상
        //타임스탬프(pk) 페이지명 요청 문자열(URL) ip 상태(HttpStatus)
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("컨트롤러 실행 완료");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("뷰가 실행됨.");
    }

}
