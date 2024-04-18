--명시적 커서
CREATE OR REPLACE PROCEDURE PROC1(P_DEPTNO EMP.DEPTNO%TYPE)
IS
CURSOR E_CUR IS
SELECT EMPNO, ENAME, SAL, JOB, DEPTNO 
FROM EMP 
WHERE DEPTNO = P_DEPTNO
ORDER BY SAL DESC;

EMP_REC E_CUR%ROWTYPE;

BEGIN
--NULL; -- 커서 선언만 했기때문에 
OPEN E_CUR;  -- ACTIVE SET 
LOOP
FETCH E_CUR INTO EMP_REC;
EXIT WHEN E_CUR%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(EMP_REC.EMPNO||', '||', '||EMP_REC.ENAME||', '||EMP_REC.SAL);
END LOOP;
CLOSE E_CUR;
END;
/

EXEC PROC1(20); 

SELECT * FROM EMP;

CREATE OR REPLACE PROCEDURE PRO2 (PNUM EMP.EMPNO%TYPE) IS
CURSOR CUR IS 
SELECT ENAME, JOB, SAL, DEPTNO 
FROM EMP 
WHERE EMPNO = PNUM
ORDER BY SAL asc;

REC CUR%ROWTYPE;

BEGIN 
OPEN CUR;
LOOP
FETCH CUR INTO REC;
EXIT WHEN CUR%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC.ENAME||', 직업 : '||REC.JOB||', 급여 : '||REC.SAL||', 부서번호 : '||REC.DEPTNO);
END LOOP;
CLOSE CUR;
END;
/

EXEC PRO2(7788);

SELECT * FROM EMP;


CREATE OR REPLACE PROCEDURE PRO1(P_DNUM EMP.DEPTNO%TYPE)
IS
CURSOR C1 
IS
SELECT EMPNO, ENAME, SAL 
FROM EMP 
WHERE DEPTNO = P_DNUM
ORDER BY SAL ASC;
BEGIN 
FOR I IN C1 LOOP
DBMS_OUTPUT.PUT_LINE(I.EMPNO||' : '||I.ENAME||' : '||I.SAL);
END LOOP;
END;
/

EXEC PRO1(30);

CREATE OR REPLACE PROCEDURE PRO2(D_NUM EMP.DEPTNO%TYPE) IS
BEGIN 
FOR I IN (SELECT EMPNO ,
ENAME,
SAL
FROM EMP WHERE DEPTNO=D_NUM ORDER BY SAL ASC) LOOP
DBMS_OUTPUT.PUT_LINE(I.EMPNO||' : '||I.ENAME||' : '||I.SAL);
END LOOP;
END;
/
EXEC PRO2(30);


EXEC P1(30);



CREATE OR REPLACE PROCEDURE P1(P_DEPTNO EMP.DEPTNO%TYPE, P_SAL EMP.SAL%TYPE)
IS
CURSOR C1
IS
SELECT * 
FROM EMP
WHERE DEPTNO = P_DEPTNO
    AND SAL >= P_SAL
ORDER BY SAL DESC;
    BEGIN
        FOR I IN C1(10, 1000) LOOP
            DBMS_OUTPUT.PUT_LINE(I_EMPNO||' : '||I.SAL);
            end loop;
        
        DBMS_OUTPUT.PUT_LINE('---');
        
        FOR J IN C1(30, 1500) LOOP
            DBMS_OUTPUT.PUT_LINE(J_EMPNO||' : '||J.SAL);
            
            end loop;
    end;
/


 create or replace procedure sp_strong_type_cursor_variable
  is
    type emp_record_type is record
    (employee_id employees.employee_id%type,
     last_name   employees.last_name%type,
     salary      employees.salary  %type);

    type emp_cursor_type is ref cursor
      return emp_record_type;

    v_empcur emp_cursor_type;   /* 강한 타입의 커서 변수 */
    v_emprec emp_record_type;
  begin
    /* 첫번째 SQL문에 대해 커서 변수를 OPEN */
    open v_empcur
    for
      select employee_id, last_name, salary 
      from employees 
      where department_id = 10;

    loop
      fetch v_empcur into v_emprec ;
      exit when v_empcur%notfound ;
      dbms_output.put_line('employee_id='||v_emprec.employee_id||', last_name='||v_emprec.last_name||', salary='||v_emprec.salary);
    end loop ;

    close v_empcur ;
  
    dbms_output.put_line('--------------') ;
  
    /* 두번째 SQL문에 대해 커서 변수를 OPEN */
    open v_empcur
    for
      select employee_id, last_name, salary+nvl(commission_pct,0)
      from employees
      where department_id = 20;

    loop
      fetch v_empcur into v_emprec ;
      exit when v_empcur%notfound ;
      dbms_output.put_line('employee_id='||v_emprec.employee_id||', last_name='||v_emprec.last_name||', salary='||v_emprec.salary);
    end loop ;
    close v_empcur ;
  end;
  /
  EXEC sp_strong_type_cursor_variable();
  
  -----
  create or replace procedure sp_cusor_variable_parameter
  is
    type emp_rec is record
    (employee_id employees.employee_id%type,
     last_name   employees.last_name%type);

    type emp_cursor_type is ref cursor 
      return emp_rec; 

    v_empcur emp_cursor_type;  /* 강한 타입의 커서 변수 */
  
    procedure print_emp        /* 로컬 서브프로그램 */
    (a_empcur in emp_cursor_type)
    is   
      v_emprec emp_rec;
    begin
      loop
        fetch a_empcur into v_emprec ;
        exit when a_empcur%notfound;
        dbms_output.put_line('employee_id=' ||v_emprec.employee_id||', last_name=' || v_emprec.last_name);
      end loop;
    end;
  begin
    open v_empcur 
    for
      select employee_id, last_name
      from employees;

    print_emp(v_empcur);

    close v_empcur;
  end;
  /
SELECT * FROM MEMBER;
DELETE MEMBER WHERE ID = 4;
DESC MEMBER;
INSERT INTO MEMBER (ID, NAME, HEIGHT, WEIGHT, AGE)  VALUES ('004', 'BRAIN', 177, 83, 30);


---팝업

DROP TABLE CUSTOMER;

CREATE TABLE CUSTOMER(
ID NUMBER GENERATED AS IDENTITY, 
NAME VARCHAR2(10),
EXPENSE NUMBER,
REG_DATE DATE,
PRIMARY KEY(ID)
);

INSERT INTO CUSTOMER(NAME, EXPENSE, REG_DATE) VALUES ('홍길동', 1000, SYSDATE);

UPDATE customer SET EXPENSE = 2000 WHERE ID = 1;


SELECT COUNT(*) FROM CUSTOMER WHERE ID=2;

CREATE OR REPLACE PROCEDURE TEST1(
P_ID CUSTOMER.ID%TYPE,
P_CURSOR OUT SYS_REFCURSOR
) IS
BEGIN 
OPEN P_CURSOR FOR
SELECT *
FROM CUSTOMER
WHERE ID = P_ID;
END;
/

CREATE OR REPLACE
PROCEDURE FIND_ALL(
P_CURSOR OUT SYS_REFCURSOR 
) IS
BEGIN 
OPEN P_CURSOR FOR 
SELECT * FROM CUSTOMER;
END;
/

DECLARE 
V_CURSOR SYS_REFCURSOR; 
REC CUSTOMER%ROWTYPE;
P_ID CUSTOMER.ID%TYPE := 1;
BEGIN 
OPEN V_CURSOR FOR SELECT * FROM CUSTOMER WHERE ID = P_ID;
FETCH V_CURSOR INTO REC; 
DBMS_OUTPUT.PUT_LINE(REC.ID||' : '||REC.NAME||' : '||REC.EXPENSE);
CLOSE V_CURSOR;
END;
/


----
CREATE OR REPLACE PACKAGE CUSTOMER_PACK 
IS 
PROCEDURE REGISTER(
P_NAME CUSTOMER.NAME%TYPE, 
P_EXPENSE CUSTOMER.EXPENSE%TYPE
);
PROCEDURE UPDATE_EXPENSE(
P_ID CUSTOMER.ID%TYPE,
P_EXPENSE CUSTOMER.EXPENSE%TYPE 
);
PROCEDURE DELETE(
P_ID CUSTOMER.ID%TYPE
);
PROCEDURE FIND_ALL(
P_CURSOR OUT SYS_REFCURSOR 
);
PROCEDURE FIND_BY_ID(
P_ID CUSTOMER.ID%TYPE,
P_CURSOR OUT SYS_REFCURSOR
);
FUNCTION IS_EXISTS(
P_ID CUSTOMER.ID%TYPE
)RETURN NUMBER;

END;
/


----
CREATE OR REPLACE PACKAGE BODY CUSTOMER_PACK 
IS 
FUNCTION EXPENSE_CHECK(
MONEY CUSTOMER.EXPENSE%TYPE
)RETURN BOOLEAN 
IS 
BEGIN 
IF MONEY < 0 THEN 
RETURN FALSE;
ELSE 
RETURN TRUE;
END IF;
END;

PROCEDURE REGISTER (
P_NAME CUSTOMER.NAME%TYPE, 
P_EXPENSE CUSTOMER.EXPENSE%TYPE
) IS 
BEGIN 
IF EXPENSE_CHECK(P_EXPENSE) THEN 
INSERT INTO CUSTOMER(NAME, EXPENSE, REG_DATE)
VALUES (P_NAME, P_EXPENSE, SYSDATE);
ELSE DBMS_OUTPUT.PUT_LINE('금액은 음수가 될 수 없습니다.');
END IF;
COMMIT;
END; 


PROCEDURE UPDATE_EXPENSE(
P_ID CUSTOMER.ID%TYPE,
P_EXPENSE CUSTOMER.EXPENSE%TYPE 
) IS
BEGIN 
IF EXPENSE_CHECK(P_EXPENSE) THEN 
UPDATE CUSTOMER 
SET EXPENSE = P_EXPENSE
WHERE ID = P_ID;
ELSE DBMS_OUTPUT.PUT_LINE('금액은 음수가 될 수 없습니다.');
END IF;
COMMIT; 
END;

PROCEDURE DELETE(
P_ID CUSTOMER.ID%TYPE
) IS
BEGIN 
DELETE FROM CUSTOMER 
WHERE ID = P_ID;
COMMIT;
END;

PROCEDURE FIND_ALL(
P_CURSOR OUT SYS_REFCURSOR 
) IS
BEGIN 
OPEN P_CURSOR FOR 
SELECT * FROM CUSTOMER
ORDER BY REG_DATE;
END;

PROCEDURE FIND_BY_ID(
P_ID CUSTOMER.ID%TYPE,
P_CURSOR OUT SYS_REFCURSOR
) IS
BEGIN 
OPEN P_CURSOR FOR
SELECT * 
FROM CUSTOMER
WHERE ID = P_ID;
END;

FUNCTION IS_EXISTS(
P_ID CUSTOMER.ID%TYPE
)RETURN NUMBER
IS RS NUMBER(1);
BEGIN 
SELECT 1 INTO RS
FROM CUSTOMER 
WHERE ID = P_ID;
RETURN RS;
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN 0;
END;

END;
/
---

TRUNCATE TABLE CUSTOMER;

SELECT * FROM CUSTOMER;

-- 등록기능
EXEC customer_pack.register('손기정',1000);
EXEC customer_pack.register('바나나',2000);
EXEC customer_pack.register('홍길동',3000);
EXEC customer_pack.register('류호정',4000);
EXEC customer_pack.register('사만다',5000);
EXEC customer_pack.register('이기억',6000);
EXEC customer_pack.register('공혁준',7000);
--전체조회
DECLARE 
CUR SYS_REFCURSOR;
REC CUSTOMER%ROWTYPE;
BEGIN
customer_pack.find_all(CUR);
LOOP
FETCH CUR INTO REC;
EXIT WHEN CUR%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('ID : '||REC.ID||', 이름: '||REC.NAME||', 사용 금액: '||REC.EXPENSE||', 가입일 : '||REC.REG_DATE);
END LOOP;
END;
/
--단일조회(FindById)
DECLARE 
V_ID CUSTOMER.ID%TYPE := 2;
CUR SYS_REFCURSOR;
REC CUSTOMER%ROWTYPE;
BEGIN
IF CUSTOMER_PACK.is_exists(V_ID) = 1 THEN 
customer_pack.find_by_id(V_ID,CUR);
FETCH CUR INTO REC;
DBMS_OUTPUT.PUT_LINE('ID : '||REC.ID||', 이름: '||REC.NAME||', 사용 금액: '||REC.EXPENSE||', 가입일 : '||REC.REG_DATE);
END IF;
END;
/
-- 존재여부 확인 
EXEC DBMS_OUTPUT.PUT_LINE(CUSTOMER_PACK.is_exists(1));

-- 삭제
DECLARE 
V_ID CUSTOMER.ID%TYPE := 2;
BEGIN 
IF CUSTOMER_PACK.is_exists(V_ID) = 1 THEN 
CUSTOMER_PACK.DELETE(V_ID);
DBMS_OUTPUT.PUT_LINE(V_ID||' 번 유저 삭제');
END IF;
END;
/

---팝업퀴즈 종료

SELECT * FROM employees;

CREATE OR REPLACE PROCEDURE P1(A NUMBER) IS
V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN
SELECT SALARY INTO V_SALARY
FROM EMPLOYEES
WHERE DEPARTMENT_ID = A;

DBMS_OUTPUT.PUT_LINE(V_SALARY);
END;
/
EXEC P1(90);


create or replace procedure p1(a number)
       is
         CURSOR nemam
         IS
           select salary
           from employees
           where department_id = a; 
       begin
         open nemam;
         /* active set */
       end;
       /

       exec p1(50);
       exec p1(51);
       
       --- 커서의 액티브 셋 예제
       
       
       
-- 암시적 커서 속성 사용 예제       
drop table sawon purge;

  create table sawon 
  as 
  select * from employees; 
  -- EMPLOYEES 테이블 복제 

  begin
    update sawon
    set salary = salary * 1.3
    where department_id = 50;
-- DEPARTMENT_ID 가 50인 경우 SALARY를 1.3배로 업데이트하세요 
    if sql%rowcount = 0 then
      dbms_output.put_line('수정된 행이 없습니다');
    else 
      dbms_output.put_line(sql%rowcount||'행이 수정되었습니다');
    end if;
-- 묵시적 커서를 사용해서 UPDATE 한 ROWCOUNT를 출력한다.  
    delete from sawon
    where department_id = 20;
--부서번호가 20이면 삭제
    if sql%rowcount = 0 then
      dbms_output.put_line('삭제된 행이 없습니다');
    else 
      dbms_output.put_line(sql%rowcount||'행이 삭제되었습니다');
    end if;
  end;
  /
  
  --- 암시적 커서의 속성 (SQL%ROWCOUNT)
  
  --- 명시적 커서 예제
  create or replace procedure p1(a employees.department_id%type)
  is
    CURSOR emp_cursor -- EMP _CURSOR (명시적 커서 선언)
    IS
      select employee_id, last_name, salary, job_id, hire_date  
      from employees
      where department_id = a
      order by salary desc;
      
-- 이거 실행한 쿼리문의 결과를 담아둠 
-- 전달받은 부서 ID 와 일치하는 결과(사원번호, 라스트네임, 급여 , 직업아이디, 고용날짜)
    
    r emp_cursor%rowtype;  -- 결과를 담은 커서를 조금씩 잘라서 출력하기위한 레코드 
  
  begin
    if not(emp_cursor%isopen) then 
    -- CURSOR%ISOPEN : 커서가 열려있으면 TRUE 반환 BUT, 지금은 IF NOT THEN 이니 
    -- 커서가 닫혀있으면 커서를 OPEN 하겠다는 뜻
      OPEN emp_cursor;  /* 서버 내부에 active set이 생성 */ 
    end if;

    loop
      FETCH emp_cursor INTO r; -- 하나의 행씩 잘라서 R(레코드에) 담는다. 
      exit when emp_cursor%notfound; -- 명시적 커서의 속성 (더이상 결과를 찾지못할경우 루프문 종료)
      dbms_output.put_line(r.employee_id||' '||r.salary||' '||r.job_id);
    end loop;

    CLOSE emp_cursor;
  end;
  /

  exec p1(10)
  exec p1(50)   

    ↓↓

  /* Cursor for loop */
  create or replace procedure p1(a employees.department_id%type)
  is
    CURSOR emp_cursor
    IS
      select employee_id, last_name, salary, job_id, hire_date 
      from employees
      where department_id = a
      order by salary desc;
  begin
    for r in emp_cursor loop  /* open, fetch */
      dbms_output.put_line(r.employee_id||' '||r.salary||' '||r.job_id);
    end loop;                 /* close */
  end;
  /

  exec p1(10)
  exec p1(50) 

    ↓↓

  /* 서브쿼리를 이용한 Cursor for loop */
  create or replace procedure p1(a employees.department_id%type)
  is
  begin
    for r in (select employee_id, last_name, salary, job_id, hire_date 
              from employees
              where department_id = a
              order by salary desc) loop
      dbms_output.put_line(r.employee_id||' '||r.salary||' '||r.job_id);
    end loop;
  end;
  /

  exec p1(10)
  exec p1(50) 
       
       
create or replace procedure p1
    is
begin
    for d in (select * from departments order by department_id) loop -- 묵시적 커서예제 
            dbms_output.put_line(d.department_id); -- 부서의 ID 를 출력한다
            for e in (select * from employees where department_id = d.department_id order by salary desc) loop -- 부서의 아이디와 동일한 EMPLOYEES 출력 
                    dbms_output.put_line(e.employee_id||', '||e.last_name);
                end loop;
            dbms_output.put_line('-----------');
        end loop;
end;
/

SELECT * FROM DEPT;
SELECT * FROM EMP;

CREATE OR REPLACE PROCEDURE P1
    IS
BEGIN 
FOR I IN (SELECT * FROM DEPT ORDER BY DEPTNO) LOOP 
DBMS_OUTPUT.PUT_LINE('부서 번호 : '||I.DEPTNO);
FOR J IN (SELECT * FROM EMP WHERE DEPTNO = I.DEPTNO) LOOP
DBMS_OUTPUT.PUT_LINE('사번 : '||J.EMPNO||', 사원 이름: '||J.ENAME);
END LOOP; 
DBMS_OUTPUT.PUT_LINE('====');
END LOOP;
END;
/

EXEC P1;

--



---강한 타입의 커서 변수
create or replace procedure sp_strong_type_cursor_variable
  is
    type emp_record_type is record
    (employee_id employees.employee_id%type,
     last_name   employees.last_name%type,
     salary      employees.salary  %type);

    type emp_cursor_type is ref cursor
      return emp_record_type;

    v_empcur emp_cursor_type;   /* 강한 타입의 커서 변수 */
    v_emprec emp_record_type;
  begin
    /* 첫번째 SQL문에 대해 커서 변수를 OPEN */
    open v_empcur
    for
      select employee_id, last_name, salary -- ID, NAME ,SALARY
      from employees 
      where department_id = 10;

    loop
      fetch v_empcur into v_emprec ;
      exit when v_empcur%notfound ;
      dbms_output.put_line('employee_id='||v_emprec.employee_id||', last_name='||v_emprec.last_name||', salary='||v_emprec.salary);
    end loop ;

    close v_empcur ;
  
    dbms_output.put_line('--------------') ;
  
    /* 두번째 SQL문에 대해 커서 변수를 OPEN */
    open v_empcur
    for
      select employee_id, last_name, salary+nvl(commission_pct,0)
      from employees
      where department_id = 20;

    loop
      fetch v_empcur into v_emprec ;
      exit when v_empcur%notfound ;
      dbms_output.put_line('employee_id='||v_emprec.employee_id||', last_name='||v_emprec.last_name||', salary='||v_emprec.salary);
    end loop ;
    close v_empcur ;
  end;
  /

  exec sp_strong_type_cursor_variable
  
CREATE OR REPLACE PROCEDURE A1 IS
type EMP_RECORD_TYPE is record
    (employee_id employees.employee_id%type,
     last_name   employees.last_name%type,
     salary      employees.salary%type);

TYPE M_CUR IS REF CURSOR 
RETURN EMP_RECORD_TYPE;

V_CUR M_CUR;
EMP_REC EMP_RECORD_TYPE;

BEGIN 

OPEN V_CUR FOR 

SELECT EMPLOYEE_ID, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 10;

LOOP
FETCH V_CUR INTO EMP_REC;
EXIT WHEN V_CUR%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(EMP_REC.EMPLOYEE_ID);
END LOOP;
CLOSE V_CUR;

OPEN V_CUR FOR
SELECT EMPLOYEE_ID, LAST_NAME, SALARY+NVL(COMMISSION_PCT, 0)
-- NVL 함수 : 첫번째 열이 NULL 인 경우  두번째 값으로 대체한다. 
FROM EMPLOYEES 
WHERE DEPARTMENT_ID = 20; 

LOOP 
FETCH V_CUR INTO EMP_REC; 
EXIT WHEN V_CUR%NOTFOUND; 
DBMS_OUTPUT.PUT_LINE('employee_id='||EMP_REC.employee_id||', last_name='||EMP_REC.last_name||', salary='||EMP_REC.salary);
END LOOP;
CLOSE V_CUR;
END;
/
SHOW ERRORS;

EXEC A1;

SELECT * FROM EMPLOYEES;

-- 강타입 예제 1 끝

--- 강타입 예제 2 시작
create or replace procedure EX1
    is
    type REC1 is record(
    employee_id employees.employee_id%type,
    last_name   employees.last_name%type
    );
    -- 사번, 이름 저장 레코드
    type CUR1 is ref cursor
    return REC1;
    -- 사번, 이름 저장 레코드를 반환하는 REF 커서
    CUR CUR1; 
    
    --내부 프로시저 시작
    procedure print_emp(C in CUR1) is
    REC REC1;
    begin
    loop
    fetch C into REC;
    exit when C%notfound;
    dbms_output.put_line('employee_id=' ||REC.employee_id||', last_name=' || REC.last_name);
    end loop;
    end;
   --내부 프로시저 종료
   begin
   open CUR
   for
   select employee_id, last_name
   from employees;

   print_emp(CUR);

   close CUR;
end;
/
SHOW ERRORS;
exec sp_cusor_variable_parameter

-- 강타입 예제 2 끝


--- 약타입 예제 시작
create or replace procedure sp_weak_type_cursor_variable is
v_cursor   sys_refcursor; -- 강타입과 다르게 반환 타입이 없다
v_selector char;
v_department_id   number;
  
procedure open_cursor(
a_cursor   in out sys_refcursor, 
a_selector in     char,
a_department_id   in     number
) IS
BEGIN
IF a_selector = 'E' THEN
OPEN a_cursor FOR select * from employees  where department_id = a_department_id;
ELSE
open a_cursor for select * from departments where department_id = a_department_id;
END IF ;
END;
-- CHAR 타입의 입력값을 받아서 E 이면 EMPLOYEESE 를 찾는 쿼리 결과가 커서에 담긴다.
-- 그렇지 않으면 DEPARTMENTS 를 찾는 쿼리 결과가 커서에 담긴다.
procedure PRINT_CURSOR
(A_CURSOR   in out sys_refcursor,
A_SELECTOR in     char)
IS
V_EMPREC  EMPLOYEES%ROWTYPE;
V_DEPTREC DEPARTMENTS%ROWTYPE;
begin
if A_SELECTOR = 'E' then -- 셀렉터가 E 였을 경우
loop
fetch A_CURSOR into V_EMPREC;   --EMP 레코드에 담음
exit when A_CURSOR%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('employee_id='||V_EMPREC.EMPLOYEE_ID||', last_name='||V_EMPREC.LAST_NAME||', job_id='||V_EMPREC.JOB_ID  ||', salary='  ||V_EMPREC.SALARY);
end loop;
else
loop
fetch A_CURSOR into V_DEPTREC;  -- DEPT 테이블에 담음
exit when A_CURSOR%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('department_id='||V_DEPTREC.DEPARTMENT_ID||', DEPARTMENT_NAME='||V_DEPTREC.DEPARTMENT_NAME||', LOCATION_ID=' ||V_DEPTREC.LOCATION_ID);
end loop;
end if;
end;
begin
-- DEPT 테이블 출력
V_SELECTOR := 'D';
V_DEPARTMENT_ID   := 10;
OPEN_CURSOR (V_CURSOR, V_SELECTOR, V_DEPARTMENT_ID);  -- 커서 OPEN
PRINT_CURSOR(V_CURSOR, V_SELECTOR);            -- 커서 출력
close V_CURSOR;
  
DBMS_OUTPUT.PUT_LINE('----');

-- EMP 테이블 출력
V_SELECTOR := 'E';
V_DEPARTMENT_ID   := 10;
OPEN_CURSOR (V_CURSOR, V_SELECTOR, V_DEPARTMENT_ID);  -- 커서 OPEN
PRINT_CURSOR(V_CURSOR, V_SELECTOR);            -- 커서 출력
close V_CURSOR;
end;
/

exec SP_WEAK_TYPE_CURSOR_VARIABLE
---