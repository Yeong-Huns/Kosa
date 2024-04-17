CREATE OR REPLACE PROCEDURE PROC1 
(
P_EMPNO IN EMP.EMPNO%TYPE,
P_SAWON OUT NOCOPY EMP%ROWTYPE
)
IS
BEGIN
SELECT * INTO P_SAWON 
FROM EMP
WHERE EMPNO = P_EMPNO; 
END; 
/

DECLARE
REC EMP%ROWTYPE;
BEGIN 
PROC1(7788, REC); -- 서버에서 날아갈땐 EXEC 필요없음
DBMS_OUTPUT.PUT_LINE(REC.EMPNO||REC.ENAME);
END;
/


--함수



---
CREATE OR REPLACE PACKAGE PACKAGE_EMP_TYPES 
IS TYPE EMP_SAL_TAB_TYPE IS TABLE OF EMP.SAL%TYPE 
INDEX BY PLS_INTEGER;
END;
/

CREATE OR REPLACE PROCEDURE PROC1(
P_DNUM IN EMP.DEPTNO%TYPE,
P_SAL_TAB OUT PACKAGE_EMP_TYPES.EMP_SAL_TAB_TYPE
) IS
BEGIN
SELECT SAL BULK COLLECT INTO P_SAL_TAB
FROM EMP
WHERE DEPTNO = P_DNUM;
END;
/

create or replace procedure procedure_test_drive(p_deptno emp.deptno%type)
    is
      sal_tab pack_emp_types.emp_sal_tab_type;
    begin
      proc1(p_deptno, sal_tab);

      for i in sal_tab.first .. sal_tab.last loop
        dbms_output.put_line(sal_tab(i));
      end loop;
    end;
    /

    exec procedure_test_drive(30)
    
    --(2) 함수

    create or replace function func1
    (p_deptno in emp.deptno%type)
     return pack_emp_types.empno_ename_sal_job_tab_type
    is
      empno_ename_sal_job_tab pack_emp_types.empno_ename_sal_job_tab_type;
    begin
      select empno, ename, sal, job bulk collect into empno_ename_sal_job_tab
      from emp
      where deptno = p_deptno;

      return empno_ename_sal_job_tab;
    end;
    /

    show errors

    create or replace procedure function_test_drive(p_deptno emp.deptno%type)
    is
      empno_ename_sal_job_tab pack_emp_types.empno_ename_sal_job_tab_type;
    begin
      empno_ename_sal_job_tab := func1(p_deptno);

      for i in empno_ename_sal_job_tab.first .. empno_ename_sal_job_tab.last loop
        dbms_output.put_line(empno_ename_sal_job_tab(i).empno);
        dbms_output.put_line(empno_ename_sal_job_tab(i).ename);
        dbms_output.put_line(empno_ename_sal_job_tab(i).sal);
        dbms_output.put_line(empno_ename_sal_job_tab(i).job);
        dbms_output.put_line('---------');
      end loop;
    end;
    /

    exec function_test_drive(30)


