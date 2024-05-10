package main.java.kosa.myapp.util.dataBaseConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * packageName    : main.java.kosa.myapp.util.dataBaseConnection
 * fileName       : DBConnection
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
public class DBConnection {
    private static Connection conn;
    private static Properties properties = new Properties();

    private DBConnection() { }

    static {
        loadProperties();
        connectDB();
    }

    private static void loadProperties() {
        try (Reader reader = new FileReader("lib/oracle.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            System.out.println("Error loading properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void connectDB() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    return;  // 연결이 이미 열려 있으면 메서드 종료
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 연결 시도
        String driverName = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("password");
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("DB 접속완료!");  // 연결 성공 시 메시지 출력
        } catch (Exception e) {
            System.out.println("DB connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                connectDB();  // 연결을 시도하고 성공 메시지는 connectDB 내에서 출력
            }
        } catch (SQLException e) {
            System.out.println("Failed to check connection status: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}