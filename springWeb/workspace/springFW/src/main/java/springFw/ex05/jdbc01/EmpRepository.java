package springFw.ex05.jdbc01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * packageName    : springFW.ex05.jdbc01
 * fileName       : EmpRepository
 * author         : Yeong-Huns
 * date           : 2024-05-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30        Yeong-Huns       최초 생성
 */

@Repository
public class EmpRepository implements IEmpRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private class EmpMapper implements RowMapper<EmpVO> {
        @Override
        public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmpVO empVO = new EmpVO();
            empVO.setEmployeeId(rs.getInt("employee_id"));
            empVO.setFirstName(rs.getString("first_name"));
            empVO.setLastName(rs.getString("last_name"));
            empVO.setEmail(rs.getString("email"));
            empVO.setPhoneNumber(rs.getString("phone_number"));
            empVO.setHireDate(rs.getDate("hire_date"));
            empVO.setJobId(rs.getString("job_id"));
            empVO.setSalary(rs.getDouble("salary"));
            empVO.setCommissionPct(rs.getDouble("commission_pct"));
            empVO.setManagerId(rs.getInt("manager_id"));
            empVO.setDepartmentId(rs.getInt("department_id"));
            return empVO;
        }
    }

    @Override
    public int getEmpCount() {
        String sql = "select count(*) from employees";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getEmpCount(int deptId) {
        String sql = "select count(*) from employees where department_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
    }

    @Override
    public List<EmpVO> getEmpList() {
        String sql = "select * from employees";
        return jdbcTemplate.query(sql, new EmpMapper());
    }

    @Override
    public EmpVO getEmpInfo(int empId) {
        String sql = "select * from employees where employee_id = ?";
        return jdbcTemplate.queryForObject(sql, new EmpMapper(), empId);
    }

    @Override
    public void updateEmp(EmpVO emp) {

    }

    @Override
    public void insertEmp(EmpVO emp) {

    }

    @Override
    public void deleteJobHistory(int empId) {

    }

    @Override
    public int deleteEmp(int empId, String email) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> getAllDeptId() {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getAllJobId() {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getAllManagerId() {
        return Collections.emptyList();
    }
}
