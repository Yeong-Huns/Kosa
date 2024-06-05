package springFW.ex05.jdbc01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "update employees set first_name=?, last_name=?, email=?, phone_number=?, hire_date=?, job_id=?, salary=?, commission_pct=?, manager_id=?, department_id=? where employee_id = ?";
        jdbcTemplate.update(sql,
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getPhoneNumber(),
                emp.getHireDate(),
                emp.getJobId(),
                emp.getSalary(),
                emp.getCommissionPct(),
                emp.getManagerId(),
                emp.getDepartmentId(),
                emp.getEmployeeId()
                );

    }

    @Override
    public void insertEmp(EmpVO emp) {
        String sql = "insert into employees(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) values (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                emp.getEmployeeId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getPhoneNumber(),
                emp.getHireDate(),
                emp.getJobId(),
                emp.getSalary(),
                emp.getCommissionPct(),
                emp.getManagerId(),
                emp.getDepartmentId()
                );
    }

    @Override
    public void deleteJobHistory(int empId) {
        String sql = "delete from job_history where employee_id = ?";
        jdbcTemplate.update(sql, empId);
    }

    @Override
    public int deleteEmp(int empId, String email) {
        String sql = "delete from employees where employee_id = ? and email = ?";
        return jdbcTemplate.update(sql, empId, email);
    }

    @Override
    public List<Map<String, Object>> getAllDeptId() {
        String sql = "select department_id as departmentId, department_name as departmentName from departments";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getAllJobId() {
        String sql = "select job_id as jobId, job_title as jobTitle from jobs";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getAllManagerId() {
        String sql = "select d.manager_id as managerId, e.first_name as firstName from departments d join employees e on d.manager_id = e.employee_id order by d.manager_id";
        return jdbcTemplate.queryForList(sql);
    }
}
