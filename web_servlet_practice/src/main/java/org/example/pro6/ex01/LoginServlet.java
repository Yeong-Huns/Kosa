package org.example.pro6.ex01;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        System.out.println("Servlet init");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ensure request encoding is set to UTF-8
        request.setCharacterEncoding("UTF-8");

        // Ensure response content type and encoding is set to UTF-8
        response.setContentType("text/html; charset=UTF-8");

        // Ensure PrintWriter uses UTF-8 encoding
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        // Log the user information to the console
        System.out.println("아이디 : " + userId);
        System.out.println("비밀번호 : " + userPw);

        // Generate the HTML response
        out.print("<html>");
        out.print("<head><meta charset='UTF-8'></head>");
        out.print("<body>");
        if (userId != null && !userId.isEmpty()) {
            out.print("<h1>" + userId + "님! 로그인 하셨습니다.<h1>");
        } else {
            out.print("아이디를 입력하세요!!!");
            out.print("<br>");
            out.print("<a href='http://localhost:8080/login'> 로그인 창으로 이동</a>");
        }
        out.print("</body>");
        out.print("</html>");
    }

    public void destroy() {
    }
}