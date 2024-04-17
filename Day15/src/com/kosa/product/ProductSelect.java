package com.kosa.product;

import com.kosa.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * packageName    : com.kosa.product
 * fileName       : ProductSelect
 * author         : Yeong-Huns
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        Yeong-Huns       최초 생성
 */
public class ProductSelect {
    public static void main(String args[]) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        conn = DBConnection.getConnection();

        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT PROD_CODE, PROD_NAME FROM PRODUCT");

            while (rset.next()) {
                System.out.print(rset.getString(1) + " ");
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
