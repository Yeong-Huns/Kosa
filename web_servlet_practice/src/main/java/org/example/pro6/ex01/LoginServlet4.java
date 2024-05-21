package org.example.pro6.ex01;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * packageName    : org.example.pro6.ex01
 * fileName       : LoginServlet4
 * author         : Yeong-Huns
 * date           : 2024-05-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21        Yeong-Huns       최초 생성
 */
@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init메서드 호출 4");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req, resp);
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");
        System.out.println("아이디 : " + userId);
        System.out.println("비밀번호 : " + userPw);
    }

    @Override
    public void destroy() {
        System.out.println("destroy 메서드 호출!");
    }
}
