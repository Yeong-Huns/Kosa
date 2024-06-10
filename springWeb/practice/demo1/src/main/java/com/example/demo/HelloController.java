package com.example.demo;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * packageName    : com.example.demo
 * fileName       : HelloController
 * author         : Yeong-Huns
 * date           : 2024-06-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-10        Yeong-Huns       최초 생성
 */
@Controller
@Log4j2
public class HelloController {
    @GetMapping("/")
    public @ResponseBody  String index(){
        return "welcome home";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/temp")
    public String temp(Model model){
        model.addAttribute("message", "Welcome");
        return "temp";
    }

    @GetMapping("/temp2")
    public String temp(HttpSession session){
        log.info("임시 메소드 수행중 ....");
        session.setAttribute("message", "세션 메세지");
        return "temp";
    }
}
