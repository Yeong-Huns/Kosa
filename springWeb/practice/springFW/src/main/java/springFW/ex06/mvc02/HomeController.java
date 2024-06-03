package springFW.ex06.mvc02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * packageName    : springFW.ex06.mvc02
 * fileName       : HomeController
 * author         : Yeong-Huns
 * date           : 2024-06-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-03        Yeong-Huns       최초 생성
 */
@Controller
public class HomeController {
    @RequestMapping(value="/")
    public String home(Locale locale, Model model) { //MVC에 많이 씀(안써도 되는데 일반적으로 사용)

        model.addAttribute("serverTime", "서버시간");
        return "home";
    }
}
