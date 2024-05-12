package main.java.kosa.myapp.util.dataBaseConnection;

import main.java.kosa.myapp.dto.response.ResponseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
public class CallableStatementTemplate {
    private static CallableStatementTemplate callableStatement;
    private final Connection conn;

    public static CallableStatementTemplate getCallableStatement() {
        if (callableStatement == null) {
            callableStatement = new CallableStatementTemplate();
        }
         return callableStatement;
    }

    private CallableStatementTemplate() {
        this.conn = DBConnection.getConnection();
    }



    public ResponseEntity<Void> executeUpdate(String sql, PreparedStatementSetter pss) {
        try (CallableStatement cs = conn.prepareCall(sql)) {
            int lastIndex = getLastIndex(sql);
            pss.setValues(cs);
            cs.executeUpdate();
            int resultCode = cs.getInt(lastIndex-1);
            String resultMessage = cs.getString(lastIndex);
            return new ResponseEntity<Void>(resultCode, resultMessage);
        } catch (SQLException ex) {
            return new ResponseEntity<Void>(ex.getErrorCode(), ex.getMessage());
        } catch (RuntimeException ex) {
            return new ResponseEntity<Void>(400,ex.getMessage());
        }
    }

    public <T>ResponseEntity<List<T>> queryForMany(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper){
        try (CallableStatement cs = conn.prepareCall(sql)) {
            int lastIndex = getLastIndex(sql);
            pss.setValues(cs);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(lastIndex - 2);
            List<T> results = new ArrayList<>();

            int rowNum = 1;
            while (rs.next()) {
                results.add(rowMapper.mapRow(rs, rowNum));
                rowNum++;
            }
            int resultCode = cs.getInt(lastIndex-1);
            String resultMessage = cs.getString(lastIndex);
            return new ResponseEntity<>(results, resultCode, resultMessage);
        } catch (SQLException ex) {
            System.out.println("QueryForManyError  : { 오류코드 : " + ex.getErrorCode() + " , 오류 메세지 : " + ex.getMessage() + "}");
            return new ResponseEntity<>(ex.getErrorCode(),ex.getMessage());
        } catch (RuntimeException ex){
            return new ResponseEntity<>(400,ex.getMessage());
        }
    }

    public <T> ResponseEntity<T> queryForOne(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        try (CallableStatement cs = conn.prepareCall(sql)) {
            int lastIndex = getLastIndex(sql);
            pss.setValues(cs);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(lastIndex - 2);
            T result = null;

            boolean hasResult = rs.next();
            if (hasResult) {
                result = rowMapper.mapRow(rs, 1);
            }
            int resultCode = cs.getInt(lastIndex - 1);
            String resultMessage = cs.getString(lastIndex);
            // 결과가 두 번째 행을 가지고 있는지 확인합니다.
            if (hasResult && rs.next()) {
                return new ResponseEntity<>(result, 4, "쿼리 결과가 유니크 값을 반환하지 않습니다");
            }

            return new ResponseEntity<>(result, resultCode, resultMessage);
        } catch (SQLException ex) {
            return new ResponseEntity<>(null, ex.getErrorCode(), ex.getMessage());
        } catch (Exception ex) {
            return new ResponseEntity<>(null,500, "서버 내부 오류: " + ex.getMessage());
        }
    }


    private int getLastIndex(String sql){
        return (int)sql.chars().filter(e->e=='?').count();
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
