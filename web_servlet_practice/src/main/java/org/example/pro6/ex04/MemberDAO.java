package org.example.pro6.ex04;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : org.example.pro6.ex04
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
    private Connection con;
    private PreparedStatement ps;
    private DataSource dataFactory;
    public MemberDAO() {
        try{
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) env.lookup("jdbc/oracle");
            System.out.println("JDBC Data Sources JNDI Resource Factory");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<MemberVO> listMembers(){
        List<MemberVO> list = new ArrayList<MemberVO>();
        try{
            con = dataFactory.getConnection();
            String sql = "select * from t_member";
            System.out.println("prepareStatement"+sql);
            ps = con.prepareStatement(sql);
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
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void addMember(MemberVO memberVO) {
        try {
            con = dataFactory.getConnection();
            String id = memberVO.getId();
            String pwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();
            String query = "insert into t_member";
            query += " (id,pwd,name,email)";
            query += " values(?,?,?,?)";
            System.out.println("prepareStatememt: " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, pwd);
            ps.setString(3, name);
            ps.setString(4, email);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }//end try
    }//end class

    //삭제
    public void delMember(String id) {
        try {
            con = dataFactory.getConnection();
            String query = "delete from t_member" + " where id=?";
            System.out.println("prepareStatememt:" + query);
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }//end try
    }//end dell..
}
