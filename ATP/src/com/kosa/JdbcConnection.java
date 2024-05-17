package com.kosa;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : com.kosa.JdbcConnection
 * author         : Yeong-Huns
 * date           : 2024-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30        Yeong-Huns       최초 생성
 */
public class JdbcConnection {
    @SuppressWarnings("unused")
    public static void main(String args[]) throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        /* 설정 파일 + 싱글턴 패턴 활용 접속 */
        Connection conn5 = DBConnection.getConnection();
    }

}