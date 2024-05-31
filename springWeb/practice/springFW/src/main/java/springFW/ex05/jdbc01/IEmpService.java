package springFW.ex05.jdbc01;

import java.util.List;
import java.util.Map;

/**
 * packageName    : springFW.ex05.jdbc01
 * fileName       : IEmpService
 * author         : Yeong-Huns
 * date           : 2024-05-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30        Yeong-Huns       최초 생성
 */
public interface IEmpService {
    int getEmpCount();
    int getEmpCount(int deptId);

    List<EmpVO> getEmpList();

    EmpVO getEmpInfo(int empId);

    void updateEmp(EmpVO emp);
    void insertEmp(EmpVO emp);
    int deleteEmp(int empId, String email);

    List<Map<String, Object>> getAllDeptId();
    List<Map<String, Object>> getAllJobId();
    List<Map<String, Object>> getAllManagerId();
}
