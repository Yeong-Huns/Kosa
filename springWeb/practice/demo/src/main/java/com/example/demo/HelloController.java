package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class HelloController {
    @GetMapping("/")
    public @ResponseBody  String index(){
        return "welcome home";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name, Model model ){
        String greetings = "Hello " + name + "!";
        model.addAttribute("greetings", greetings);
        return "hello";
    }


    @GetMapping("/temp")
    public String temp(){
        return "temp";
    }
}
