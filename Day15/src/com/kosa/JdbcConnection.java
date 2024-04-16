package com.kosa;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.pool.OracleDataSource;

/**
 * packageName    : com.kosa
 * fileName       : JdbcConnection
 * author         : Yeong-Huns
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        Yeong-Huns       최초 생성
 */
public class JdbcConnection {
    @SuppressWarnings("unused")
    public static void main(String args[]) throws SQLException {

        OracleDataSource ods = new OracleDataSource();

        /* Thin driver */

        // 1
        ods.setURL("jdbc:oracle:thin:@localhost:1521/xepdb1");
        ods.setUser("ace01");
        ods.setPassword("me");
        Connection conn1 = ods.getConnection();

        DatabaseMetaData meta = conn1.getMetaData();
        System.out.println("JDBC driver version is " + meta.getDriverVersion());
        System.out.println("1성공");
        // 2
        ods.setURL("jdbc:oracle:thin:ace01/me@localhost:1521/xepdb1");
        Connection conn2 = ods.getConnection();
        System.out.println("2성공");
        /* Oracle Call Interface (OCI) driver */

        // 3
        ods.setURL("jdbc:oracle:oci8:@mydb");
        ods.setUser("ace01");
        ods.setPassword("me");
        Connection conn3 = ods.getConnection();
        System.out.println("3성공");
        //jdbc:oracle:oci8:ace100/me@localhost:1521/xepdb1
        // 4
        ods.setURL("jdbc:oracle:oci8:ace01/me@mydb");
        Connection conn4 = ods.getConnection();
        System.out.println("4성공");


        /* 설정 파일 + 싱글턴 패턴 활용 접속 */
        Connection conn5 = DBConnection.getConnection();


    }

}
