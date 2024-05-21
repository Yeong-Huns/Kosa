package org.example.pro6.ex02;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : org.example.pro6.ex02
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
    private final static String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
    private final static String user = "ace02";
    private final static String password = "me";
    private final static String driver = "oracle.jdbc.OracleDriver";
    private Connection con;
    private Statement st;

    public List<MemberVO> listMembers(){
        List<MemberVO> list = new ArrayList<MemberVO>();
        try{
            connDB();
            if (con == null || st == null) {
                System.out.println("Database connection or statement creation failed.");
                return list;
            }
            String sql = "select * from t_member";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
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
            st.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private void connDB(){
        try{
            Class.forName(driver);
            System.out.println("Connecting to database...");
            this.con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");
            this.st = con.createStatement();
            System.out.println("Statement created!");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Driver Load Fail");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Database connection or statement creation failed.");
        }
    }
}
