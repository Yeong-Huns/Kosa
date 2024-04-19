create or replace function number_of_days_worked(
    p_employee_id in employees.employee_id%type -- SQL 타입
  )
    return number -- 
  is 
    v_days number;
  begin
    select ceil(sysdate - hire_date) into v_days
    from employees
    where employee_id = p_employee_id;   

    return v_days;
  end;
  /
  
  select employee_id, number_of_days_worked(employee_id) "근속일수"
  from employees;

  exec dbms_output.put_line(number_of_days_worked(101))
  
  SELECT * FROM EMPLOYEES;
  
CREATE OR REPLACE FUNCTION FUN1(
P_EMPLOYEE_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE
) RETURN NUMBER 
IS 
V_DAYS NUMBER;
BEGIN
SELECT CEIL(SYSDATE - HIRE_DATE) INTO V_DAYS
FROM EMPLOYEES
WHERE employee_id = P_EMPLOYEE_ID;

RETURN V_DAYS;
END;
/

SELECT * FROM EMPLOYEES;


EXEC FUN1(101);  
-- 이건 안됨

EXEC DBMS_OUTPUT.PUT_LINE(FUN1(101));
-- 이렇게 사용해야함 

SELECT EMPLOYEE_ID, FUN1(EMPLOYEE_ID) "근속 일수"
FROM EMPLOYEES;
-- 이렇게 SQL 내부에서도 사용가능


create or replace package p
  is
    procedure p (a number);
    procedure p (a varchar2);
    procedure p (a date);
  end;
  /
  
  create or replace package body p
  is
    procedure p (a number)
    is 
    begin
      dbms_output.put_line('숫자');
      dbms_output.put_line(a);
    end;

    procedure p (a varchar2)
    is 
    begin
      dbms_output.put_line('문자');
      dbms_output.put_line(a);
    end;

    procedure p (a date)
    is 
    begin
      dbms_output.put_line('날짜');
      dbms_output.put_line(a);
    end;
  end;
  /
  
  exec p.p(100)
exec p.p('Korea')
exec p.p(sysdate)



create or replace package pack1
  is 
    v1 number;                           -- public variable
    procedure setV2(a number);              -- public subprogram
    function getV2 return number;           -- public subprogram
  end;
  /
  
  create or replace package body pack1
  is
    v2 number;                           -- private variable

    function message(a varchar2)            -- private subprogram
      return varchar2
    is
      function text                         -- local subprogram
      return varchar2
      is
      begin
        return ' 수행';
      end;
    begin
      return a||text;
    end;   

procedure setV2(a number)               -- public subprogram
    is
      v3 number := 0;                    -- local variable
    begin
      p.p(message('setV2'));
      v2 := a + v3;
    end;

    function getV2 return number         -- public subprogram
    is
    begin
      p.p(message('getV2'));
      return v2;
    end;
  end;
  /
  
  exec pack1.v1 := 100
exec p.p(pack1.v1)

exec pack1.v2 := 300        -- 에러
exec pack1.setV2(300)
exec p.p(pack1.getV2())


    UPDATE departments d
    SET total_sal=(SELECT SUM(salary)
                   FROM employees d
                   WHERE e.department_id = d.department_id);
                   
                   
                   
DROP TABLE T1 PURGE;

CREATE TABLE T1
AS 
SELECT * FROM EMP;

DROP TABLE T1_SAL_AUDIT PURGE;

CREATE TABLE T1_SAL_AUDIT(
EMPNO NUMBER(4),
UPDATE_USER VARCHAR2(10),
UPDATE_DATE DATE,
OLD_SAL NUMBER(7,2),
NEW_SAL NUMBER(7,2)
);



CREATE OR REPLACE TRIGGER T1_SAL_AUDIT_TRI
AFTER UPDATE OF SAL ON T1
FOR EACH ROW 
BEGIN
INSERT INTO T1_SAL_AUDIT(EMPNO, UPDATE_USER, UPDATE_DATE, OLD_SAL, NEW_SAL) 
VALUES(:NEW.EMPNO, USER, SYSDATE, :OLD.SAL, :NEW.SAL);
END;
/



SELECT * FROM EMP;

--나의 예제



--트래킹 대상
DROP TABLE MY_TRIGGER_TAB PURGE;
SELECT * FROM EMP;

CREATE TABLE MY_TRIGGER_TAB
AS 
SELECT * FROM EMP;

--TRACKING CREATE
DROP TABLE TRACKING_CREATED_HISTORY PURGE;

CREATE TABLE TRACKING_CREATED_HISTORY(
ID NUMBER GENERATED AS IDENTITY,
EMPNO NUMBER(4),
CREATED_AT DATE,
CREATED_BY VARCHAR(20),
PC_NAME VARCHAR2(20), 
IP_ADDRESS VARCHAR2(20),
REGION VARCHAR2(20),
PRIMARY KEY(ID)
);

SELECT * FROM TRACKING_CREATED_HISTORY;
--TRACKING UPDATE
DROP TABLE TRACKING_UPDATED_HISTORY PURGE;

CREATE TABLE TRACKING_UPDATED_HISTORY(
ID NUMBER GENERATED AS IDENTITY,
EMPNO NUMBER(4),
UPDATED_AT DATE,
UPDATED_BY VARCHAR(20),
PC_NAME VARCHAR2(20), 
IP_ADDRESS VARCHAR2(20),
REGION VARCHAR2(20),
PRIMARY KEY(ID)
);

--TRACKING DELETED
DROP TABLE TRACKING_DELETED_HISTORY PURGE;

CREATE TABLE TRACKING_DELETED_HISTORY(
ID NUMBER GENERATED AS IDENTITY,
EMPNO NUMBER(4),
DELETED_AT DATE,
DELETED_BY VARCHAR(20),
PC_NAME VARCHAR2(20), 
IP_ADDRESS VARCHAR2(20),
REGION VARCHAR2(20),
PRIMARY KEY(ID)
);



--SET TRIGGER
CREATE OR REPLACE TRIGGER MY_TRIGGER
BEFORE INSERT OR UPDATE OR DELETE ON MY_TRIGGER_TAB
FOR EACH ROW
BEGIN

IF INSERTING THEN 
INSERT INTO 
TRACKING_CREATED_HISTORY(
EMPNO,
CREATED_AT,
CREATED_BY,
PC_NAME, 
IP_ADDRESS,
REGION
) 
VALUES(
:NEW.EMPNO,
SYSDATE,
SYS_CONTEXT('USERENV', 'OS_USER'),
SYS_CONTEXT('USERENV', 'HOST'),
SYS_CONTEXT('USERENV', 'IP_ADDRESS'),
SYS_CONTEXT('USERENV', 'NLS_TERRITORY')
);
END IF;

IF UPDATING THEN
INSERT INTO 
TRACKING_UPDATED_HISTORY(
EMPNO,
UPDATED_AT,
UPDATED_BY,
PC_NAME, 
IP_ADDRESS,
REGION
) 
VALUES(
:NEW.EMPNO,
SYSDATE,
SYS_CONTEXT('USERENV', 'OS_USER'),
SYS_CONTEXT('USERENV', 'HOST'),
SYS_CONTEXT('USERENV', 'IP_ADDRESS'),
SYS_CONTEXT('USERENV', 'NLS_TERRITORY')
);
END IF;

IF DELETING THEN
INSERT INTO 
TRACKING_DELETED_HISTORY(
EMPNO,
DELETED_AT,
DELETED_BY,
PC_NAME, 
IP_ADDRESS,
REGION
) 
VALUES(
:OLD.EMPNO,
SYSDATE,
SYS_CONTEXT('USERENV', 'OS_USER'),
SYS_CONTEXT('USERENV', 'HOST'),
SYS_CONTEXT('USERENV', 'IP_ADDRESS'),
SYS_CONTEXT('USERENV', 'NLS_TERRITORY')
);
END IF;


END;
/


--테스트

-- 삽입 테스트
INSERT INTO my_trigger_tab (empno, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(8000, 'TEST', 'FISHER', '8000', SYSDATE, 2000, 500, 50);

-- 삽입 내역 테이블 조회
SELECT * FROM TRACKING_CREATED_HISTORY;


-- 수정 테스트
UPDATE my_trigger_tab SET job = 'NONE' WHERE EMPNO=8000;
-- 수정 내역 테이블 조회
SELECT * FROM TRACKING_UPDATED_HISTORY;

--삭제 테스트
DELETE FROM my_trigger_tab WHERE EMPNO=8000;
--삭제 내역 테이블 조회
SELECT * FROM TRACKING_DELETED_HISTORY;

--테스트 종료

