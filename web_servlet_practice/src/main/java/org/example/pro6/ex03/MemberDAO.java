package org.example.pro6.ex03;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : org.example.pro6.ex03
 * fileName       : MemberDAO
 * author         : Yeong-Huns
 * date           : 2024-05-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21        Yeong-Huns       최초 생성
 */
public class MemberDAO {
    private Connection conn;
    private PreparedStatement ps;
    private DataSource dataFactory;

    public MemberDAO() {
        try {
            Context cxt = new InitialContext();
            Context env = (Context) cxt.lookup("java:/comp/env");
            dataFactory = (DataSource) env.lookup("jdbc/oracle");
            System.out.println("DataFactory is " + dataFactory);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List listMembers(){
        List list = new ArrayList();
        try {
            conn = dataFactory.getConnection();
            String sql = "select * from t_member";
            System.out.println("prepareStatement"+sql);
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String email = rs.getString("email");
                LocalDate joinDate = rs.getDate("joinDate").toLocalDate();
                MemberVO vo = new MemberVO();
                vo.setId(id);
                vo.setPwd(pwd);
                vo.setName(name);
                vo.setEmail(email);
                vo.setJoinDate(joinDate);
                list.add(vo);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
