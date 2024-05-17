package com.kosa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * packageName    : com.kosa
 * fileName       : DBConnect2
 * author         : Yeong-Huns
 * date           : 2024-04-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22        Yeong-Huns       최초 생성
 */
public class DBConnect2 {

    private static Connection conn;

    private DBConnect2() {
    }

    static {
        // 환경설정 파일을 읽어오기 위한 객체 생성
        Properties properties = new Properties();
        Reader reader;
        try {
            reader = new FileReader("lib/oracle.properties"); // 읽어올 파일 지정
            properties.load(reader); // 설정 파일 로딩하기
        } catch (FileNotFoundException e1) {
            System.out.println("예외: 지정한 파일을 찾을수없습니다 :" + e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driverName = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("password");

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("connection success");
        } catch (ClassNotFoundException e) {
            System.out.println("예외: 드라이버로드 실패 :" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("예외: connection fail :" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

}
