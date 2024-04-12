SHOW CON_NAME;
SHOW USER;

DROP TABLE T1 PURGE;
CREATE TABLE T1(NO NUMBER);

select * from tab;

purge RECYCLEBIN;
select * from emp;
-- 사원번호를 주면 연봉을 계산하는 함수 (PLSQL)

CREATE OR REPLACE FUNCTION uf_emp_annual_salary(amutype emp.empno%TYPE)RETURN number
IS 
    v_annual_sal number;
BEGIN
    SELECT SAL*12 + NVL(comm, 0) INTO v_annual_sal
    FROM EMP
    WHERE EMPNO = amutype;
    RETURN v_annual_sal;
END;
/
SHOW ERRORS;


    
SELECT
    empno,
    initcap(ename) AS ename,
    sal,
    comm,
    uf_emp_annual_salary(empno) AS annual_salary
FROM emp;


SELECT
    "A1"."EMPNO"                                 "EMPNO",
    initcap("A1"."ENAME")                        "ENAME",
    "A1"."SAL"                                   "SAL",
    "A1"."COMM"                                  "COMM",
    "ACE01"."UF_EMP_ANNUAL_SALARY"("A1"."EMPNO") "ANNUAL_SALARY"
FROM
    "ACE01"."EMP" "A1";
    
    
CREATE OR REPLACE PROCEDURE up_emp_to_string(p_empno IN emp.empno%TYPE)
IS
    v_empno emp.empno%TYPE;
    v_ename emp.ename%TYPE;
    v_sal emp.sal%TYPE;
BEGIN
    SELECT empno, ename, sal
    INTO v_empno, v_ename, v_sal
    FROM emp
    WHERE empno = p_empno;
    
    DBMS_OUTPUT.PUT_LINE(v_empno ||' , '||v_ename ||' , ' ||v_sal);
END;
/
SHOW ERRORS;

SET SERVEROUTPUT ON;

EXECUTE up_emp_to_string(7788);

--ddd

    create or replace procedure up_emp_to_string2
  (p_empno in  emp.empno%type,
   p_ename out emp.ename%type,
   p_sal   out emp.sal%type)
  is
  begin
    select ename, sal
    into   p_ename, p_sal 
    from emp
    where empno = p_empno;
  end;
  /

  show errors
 
  set serveroutput on
  
  declare
    v_ename emp.ename%type;
    v_sal   emp.sal%type;
  begin
    up_emp_to_string2(7788, v_ename, v_sal);
    dbms_output.put_line(v_ename);
    dbms_output.put_line(v_sal);
  end;
  /


--dd

begin 
    for i in 1..10 loop
    dbms_output.put_line('Hello World');
    end loop;
end;
/
create or replace procedure gugudan(dan number)
is 
begin
    for i in 1..9 loop
    dbms_output.put_line(dan||' * '||i||' = '|| dan * i);
    end loop;
end;
/
show errors;
execute gugudan(3);

select * from user_source;


