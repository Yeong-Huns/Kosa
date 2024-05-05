package main.java.kosa.myapp;

import main.java.kosa.myapp.ui.frames.MainFrame;
import main.java.kosa.myapp.util.dataBaseConnection.DBConnection;
import oracle.jdbc.pool.OracleDataSource;

import java.awt.*;
import java.sql.Connection;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    OracleDataSource ods = new OracleDataSource();
                    /* 설정 파일 + 싱글턴 패턴 활용 접속 */
                    Connection conn5 = DBConnection.getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}