package org.example.pro6.ex03;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * packageName    : org.example.pro6.ex03
 * fileName       : Jdbc_test
 * author         : Yeong-Huns
 * date           : 2024-05-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21        Yeong-Huns       최초 생성
 */
public class Jdbc_test {
    public static void main(String[] args) {
        Connection con;
        try{
            String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
            String user = "ace02";
            String password = "me";
            String driver = "oracle.jdbc.OracleDriver";
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to connect to database");
        }
    }
}
