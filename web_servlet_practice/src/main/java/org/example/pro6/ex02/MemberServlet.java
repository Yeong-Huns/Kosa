package org.example.pro6.ex02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : org.example.pro6.ex02
 * fileName       : MemberServlet
 * author         : Yeong-Huns
 * date           : 2024-05-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21        Yeong-Huns       최초 생성
 */
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        MemberDAO dao = new MemberDAO();
        List<MemberVO> list = dao.listMembers();
        out.print("""
                <html>
                <body>
                <table border=1>
                <tr align='center' bgcolor='lightgreen'>
                <td>아이디</td>
                <td>비밀번호</td>
                <td>이름</td>
                <td>이메일</td>
                <td>가입일</td>
                </tr>
                """);
        for(int i = 0; i<list.size(); i++){
            MemberVO member = list.get(i);

            String id = member.getId();
            String pwd = member.getPwd();
            String name = member.getName();
            String email = member.getEmail();
            LocalDate date = member.getJoinDate();

            out.print("""
                    <tr>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    </tr>
                    """.formatted(id,pwd,name,email,date));
        }
        out.println("</table></body></html>");
    }
}
