package springFW.ex08.mybatis.hr.dao;

import org.apache.ibatis.annotations.Param;
import springFW.ex08.mybatis.hr.model.EmpVO;

import java.util.List;
import java.util.Map;

/**
 * packageName    : springFW.ex05.jdbc01
 * fileName       : IEmpRepository
 * author         : Yeong-Huns
 * date           : 2024-05-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30        Yeong-Huns       최초 생성
 */
public interface IEmpRepository {
    int getEmpCount();
    int getEmpCount(@Param("deptId") int deptId);
    int getEmpCount(@Param("jobId") String jogId);
    int getEmpCount(@Param("jobId") String jogId, @Param("deptId") int deptId);

    int getEmpCountByDeptId(@Param("deptList") int[] deptList);

    List<EmpVO> getEmpList();

    EmpVO getEmpInfo(int empId);

    void updateEmp(EmpVO emp);
    void insertEmp(EmpVO emp);
    void deleteJobHistory(int empId);
    int deleteEmp(@Param("empId") int empId, @Param("email") String email);

    List<Map<String, Object>> getAllDeptId();
    List<Map<String, Object>> getAllJobId();
    List<Map<String, Object>> getAllManagerId();



}
