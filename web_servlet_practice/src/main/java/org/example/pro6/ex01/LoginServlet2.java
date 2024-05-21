package org.example.pro6.ex01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * packageName    : org.example.pro6.ex01
 * fileName       : LoginServlet2
 * author         : Yeong-Huns
 * date           : 2024-05-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21        Yeong-Huns       최초 생성
 */
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
    public void init() throws ServletException {
        System.out.println("LoginServlet2 init 메서드 호출");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        String userId = request.getParameter("userId");
        String password = request.getParameter("userPw");

        String data = """
                <html>
                <body>
                <h1>
                아이디 : %s
                비밀번호 : %s
                </h1>
                </body>
                </html>
                """.formatted(userId, password);
        out.println(data);
    }

    @Override
    public void destroy() {
        System.out.println("destroy 메서드 호출");
    }
}
