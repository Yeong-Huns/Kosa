package com.kosa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * packageName    : com.kosa
 * fileName       : JdbcTestSelect
 * author         : Yeong-Huns
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        Yeong-Huns       최초 생성
 */
public class JdbcTestSelect {
    public static void main(String args[]) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        conn = DBConnection.getConnection();

        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT employee_id, first_name FROM employees");

            while (rset.next()) {
                System.out.print(rset.getInt(1) + " ");
                System.out.println(rset.getString(2));
            }
        }

        finally {
            if (rset != null)
                rset.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }
}
