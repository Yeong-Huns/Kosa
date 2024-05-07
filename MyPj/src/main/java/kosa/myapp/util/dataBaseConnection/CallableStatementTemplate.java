package main.java.kosa.myapp.util.dataBaseConnection;

import main.java.kosa.myapp.entity.response.ResponseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Void> execute(String sql, PreparedStatementSetter pss, int resultCodeIndex, int resultMessageIndex) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            pss.setValues(cs);
            cs.executeUpdate();
            int resultCode = cs.getInt(resultCodeIndex);
            String resultMessage = cs.getString(resultMessageIndex);
            return new ResponseEntity<Void>(resultCode, resultMessage);
        } catch (SQLException ex) {
            return new ResponseEntity<Void>(ex.getErrorCode(), ex.getMessage());
        }
    }

    public <T>ResponseEntity<List<T>> findList(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper, int resultCodeIndex, int resultMessageIndex){
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            pss.setValues(cs);
            ResultSet rs = cs.executeQuery();
            int rowNum = 0;
            List<T> results = new ArrayList<T>();
            while (rs.next()) {
                results.add(rowMapper.mapRow(rs, rowNum++));
            }
            int resultCode = cs.getInt(resultCodeIndex);
            String resultMessage = cs.getString(resultMessageIndex);
            return new ResponseEntity<>(results ,resultCode,resultMessage);
        } catch (SQLException ex) {
            return new ResponseEntity<>(ex.getErrorCode(),ex.getMessage());
        }
    }

    public <T>ResponseEntity<T> findOne(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper, int resultCodeIndex, int resultMessageIndex){
        try(Connection conn = DBConnection.getConnection();
            CallableStatement cs = conn.prepareCall(sql)){
            pss.setValues(cs);
            ResultSet rs = cs.executeQuery();
            T result = null;
            if(rs.next()){
                result = rowMapper.mapRow(rs, 0);
            }
            int resultCode = cs.getInt(resultCodeIndex);
            String resultMessage = cs.getString(resultMessageIndex);
            if(rs.next()){
                return new ResponseEntity<>(null, 4, "Query did not return a unique result");
            }
            return new ResponseEntity<>(result,resultCode,resultMessage);
        }
        catch (SQLException ex){
            return new ResponseEntity<>(ex.getErrorCode(),ex.getMessage());
        }

    }


    public <T> Optional<T> findById(String sql,
                                            PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            pss.setValues(cs);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return Optional.of(rowMapper.mapRow(rs, 0));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException("Database access error:", ex);
        }
    }

    public boolean isExists(String sql, PreparedStatementSetter pss) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            pss.setValues(cs);
            ResultSet rs = cs.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public <T> List<T> getQueryResult(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        List<T> results = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            pss.setValues(cs);
            ResultSet rs = cs.executeQuery();
            int rowNum = 0;
            while (rs.next()) {
                results.add(rowMapper.mapRow(rs, rowNum++));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return results;
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
