package main.java.kosa.myapp.repository.member;

import main.java.kosa.myapp.util.dataBaseConnection.DBConnection;

import java.lang.reflect.Member;
import java.sql.*;

/**
 * packageName    : main.java.kosa.myapp.repository
 * fileName       : MemberDAO
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
public class MemberDAOImpl implements MemberDAO {
    private final Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public MemberDAOImpl(Connection conn) {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void insertMember(Member member) {
        String sql = " call MEMBER_INFO_PACKAGE.fetch_member_info(?, ?, ?, ?, ?, ?)";
        try(CallableStatement cstmt = conn.prepareCall(sql)){
            cstmt.registerOutParameter(1, Types.INTEGER);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
