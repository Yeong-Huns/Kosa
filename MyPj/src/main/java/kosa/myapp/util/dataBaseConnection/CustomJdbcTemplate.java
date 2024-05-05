package main.java.kosa.myapp.util.dataBaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * packageName    : main.java.kosa.myapp.util.dataBaseConnection
 * fileName       : CustomJdbcTemplate
 * author         : Yeong-Huns
 * date           : 2024-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-05        Yeong-Huns       최초 생성
 */
public class CustomJdbcTemplate {

    public boolean create(String sqlQuery, PreparedStatementSetter pss) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sqlQuery)) {
            pss.setValues(cs);
            int affectedRows = cs.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FunctionalInterface
    public interface PreparedStatementSetter {
        void setValues(CallableStatement cs) throws SQLException;
    }

    @FunctionalInterface
    public interface RowMapper<T> {
        T mapRow(ResultSet rs, int rowNum) throws SQLException;
    }
}
