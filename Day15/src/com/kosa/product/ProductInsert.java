package com.kosa.product;

import com.kosa.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * packageName    : com.kosa.product
 * fileName       : ProductInsert
 * author         : Yeong-Huns
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        Yeong-Huns       최초 생성
 */
public class ProductInsert {
    public static void main(String args[]) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;

        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        try {
            pstmt = conn.prepareStatement("insert into PRODUCT (PROD_CODE, PROD_NAME) values (?, ?)");
            stmt = conn.createStatement();

            pstmt.setString(1, "NAME7");
            pstmt.setString(2, "LESLIE");
            pstmt.execute();
            conn.commit(); // COMMIT 하지않으면 확정안됨
            //stmt.executeUpdate("truncate table t1");

            pstmt.setString(1, "NAME8");
            pstmt.setString(2, "MARSHA");
            pstmt.execute();
            conn.commit();
            //stmt.executeUpdate("truncate table t1");

            System.out.println("입력 성공!");
        } finally {

            if (pstmt != null)
                pstmt.close();
        }

    }
}
