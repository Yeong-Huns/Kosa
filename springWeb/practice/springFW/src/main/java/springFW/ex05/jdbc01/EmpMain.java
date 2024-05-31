package springFW.ex05.jdbc01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Date;

/**
 * packageName    : springFW.ex05.jdbc01
 * fileName       : EmpMain
 * author         : Yeong-Huns
 * date           : 2024-05-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30        Yeong-Huns       최초 생성
 */
public class EmpMain {
    public static void main(String[] args) {
        AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:config/jdbc01/application-config.xml");

        IEmpService empService = context.getBean(IEmpService.class);
        System.out.println("-- 사원수 --");
        System.out.println(empService.getEmpCount());
        System.out.println(empService.getEmpCount(50));

        System.out.println("-- 사원리스트 --");
        System.out.println(empService.getEmpList());

        System.out.println("-- 200 사원 검색 --");
        System.out.println(empService.getEmpInfo(200));

        System.out.println("-- 회원 정보 수정");
        EmpVO emp = new EmpVO();
                emp.setFirstName("김");
                emp.setLastName("영훈");
                emp.setEmail("vaa@naver.com");
                emp.setPhoneNumber("1010133");
                emp.setHireDate(new Date(System.currentTimeMillis()));
                emp.setJobId("ST_CLERK");
                emp.setSalary(100.0);
                emp.setCommissionPct(0.5);
                emp.setManagerId(100);
                emp.setDepartmentId(50);
                emp.setEmployeeId(200);
        empService.updateEmp(emp);
        System.out.println(empService.getEmpInfo(200));


        System.out.println("-- 사원번호 210번 등록/확인 --");
        EmpVO newEmp = new EmpVO();
        newEmp.setFirstName("홍");
        newEmp.setLastName("진호");
        newEmp.setEmail("vaa123@naver.com");
        newEmp.setPhoneNumber("312");
        newEmp.setHireDate(new Date(System.currentTimeMillis()));
        newEmp.setJobId("ST_CLERK");
        newEmp.setSalary(11);
        newEmp.setCommissionPct(0.5);
        newEmp.setManagerId(100);
        newEmp.setDepartmentId(50);
        newEmp.setEmployeeId(210);
        empService.insertEmp(newEmp);
        System.out.println(empService.getEmpInfo(210));

        System.out.println("-- 사원번호가 210번인 사원의 급여를 10% 인상/확인 --");
        EmpVO searchEmp = empService.getEmpInfo(210);
        searchEmp.setSalary(searchEmp.getSalary()*1.1);
        empService.updateEmp(searchEmp);
        System.out.println(empService.getEmpInfo(210));

        System.out.println("-- 사원번호가 210번 사원 삭제/확인. --");
        System.out.println(empService.deleteEmp(210, "vaa123@naver.com") == 1 ? "삭제완료" : "삭제실패");



        System.out.println("-- 모든 부서 출력 --");
        System.out.println(empService.getAllDeptId());


        System.out.println("-- 직무 데이터 출력 --");
        System.out.println(empService.getAllJobId());

        System.out.println("-- 매니저번호, 이름 출력 --");
        System.out.println(empService.getAllManagerId());
    }
}
