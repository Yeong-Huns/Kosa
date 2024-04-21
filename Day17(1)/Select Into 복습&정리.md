  
# Case 1
```plsql
create or replace procedure proc1(p_empno emp.empno%type)
  is
    v_sal emp.sal%type;
  begin
    select sal into v_sal
    from emp
    where empno = p_empno;

    dbms_output.put_line(v_sal);
  end;
  /

  exec proc1(7788)
```

> 💡 실행 전 예상
> **나의 예상** :  `empno`를 받아서 해당 `empno`를 가진 사원의 `sal`을 출력하는 프로시저

## 안보고 따라하기

```PLSQL
SELECT * FROM EMP;

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE PRO1 (P_EMPNO EMP.EMPNO%TYPE) 
IS
V_SAL EMP.SAL%TYPE;
BEGIN 
SELECT SAL INTO V_SAL
FROM EMP
WHERE EMPNO = P_EMPNO;
DBMS_OUTPUT.PUT_LINE('급여 : '||V_SAL);
END;
/

EXEC PRO1(7788);
```

```PLSQL
결과::
급여 : 3000
```

## 나의 예제
```PLSQL

CREATE OR REPLACE PROCEDURE PRO1(P_EMPNO EMP.EMPNO%TYPE) 
IS
V_ENAME EMP.ENAME%TYPE;
V_JOB EMP.JOB%TYPE;
V_SAL EMP.SAL%TYPE;
V_DEPTNO EMP.DEPTNO%TYPE;
BEGIN 
SELECT ENAME, JOB, SAL, DEPTNO
INTO V_ENAME, V_JOB, V_SAL, V_DEPTNO
FROM EMP
WHERE EMPNO = P_EMPNO;
DBMS_OUTPUT.PUT_LINE('사번 : '||P_EMPNO||', 이름 : '||V_ENAME||
', 직업 : '||V_JOB||', 급여 : '||V_SAL||', 부서 번호 : '||V_DEPTNO);
END;
/

EXEC PRO1(7788);
```

```PLSQL
Procedure PRO1이(가) 컴파일되었습니다.
사번 : 7788, 이름 : SCOTT, 직업 : ANALYST, 급여 : 3000, 부서 번호 : 20
```

<BR>

---
# Case 2
```plsql
create or replace procedure proc1(p_empno emp.empno%type)
  is
    rec_emp emp%rowtype;
  begin
    select * into rec_emp
    from emp
    where empno = p_empno;

    dbms_output.put_line(rec_emp.ename);
    dbms_output.put_line(rec_emp.sal); 
    dbms_output.put_line(rec_emp.ename||', '||rec_emp.sal);
  end;
  /

  exec proc1(7788)
```
  
> 💡 실행 전 예상
> `EMPNO`로 조회, 해당 결과를 `REC_EMP`에 담아 출력

## 안보고 따라하기
```PLSQL
-- CASE 02
CREATE OR REPLACE PROCEDURE PRO2(P_EMPNO EMP.EMPNO%TYPE) 
IS
REC_EMP EMP%ROWTYPE;
BEGIN 
SELECT * INTO REC_EMP
FROM EMP
WHERE EMPNO = P_EMPNO; 
DBMS_OUTPUT.PUT_LINE(REC_EMP.ENAME);
DBMS_OUTPUT.PUT_LINE(REC_EMP.SAL);
DBMS_OUTPUT.PUT_LINE(REC_EMP.ENAME||', '||REC_EMP.SAL);
END;
/

EXEC PRO2(7788);

```

```PLSQL
Procedure PRO2이(가) 컴파일되었습니다.
SCOTT
3000
SCOTT, 3000

```


## 나의 예제
```PLSQL

CREATE OR REPLACE PROCEDURE PRO2(P_EMPNO EMP.EMPNO%TYPE) 
IS
TYPE REC_EMP IS RECORD(
V_ENAME EMP.ENAME%TYPE,
V_SAL   EMP.SAL%TYPE,
V_DEPTNO EMP.DEPTNO%TYPE
);
REC REC_EMP;
BEGIN 
SELECT ENAME, SAL, DEPTNO 
INTO REC.V_ENAME, REC.V_SAL, REC.V_DEPTNO
FROM EMP
WHERE EMPNO = P_EMPNO;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC.V_ENAME||', 급여 : '||REC.V_SAL||
', 부서번호 : '||REC.V_DEPTNO);
END;
/


EXEC PRO2(7788);
```

```PLSQL
Procedure PRO2이(가) 컴파일되었습니다.
이름 : SCOTT, 급여 : 3000, 부서번호 : 20
```

> 💡 어려웠던 부분
> * 아직 반복 숙달이 덜 된 것 같다. 
> * 커스텀 `RECORD` 사용 시, `09행` 처럼 따로 `변수명 : 커스텀레코드명` 선언을 해줘야
> * 사용가능. 
> * 문법에 익숙해질 필요가 있음.


<BR>

---

# Case 3
```plsql
create or replace procedure proc1(p_empno emp.empno%type)
  is
    TYPE emp_record_type IS RECORD
    (ename emp.ename%type,
     sal   emp.sal%type,
     job   emp.job%type);
    
     rec_emp emp_record_type;
  begin
    select ename, sal, job into rec_emp
    from emp
    where empno = p_empno;

    dbms_output.put_line(rec_emp.ename);
    dbms_output.put_line(rec_emp.sal); 
  end;
  /

  exec proc1(7788)

    ↓↓

  create or replace package emp_types_pack
  is
    TYPE emp_record_type IS RECORD
    (ename emp.ename%type,
     sal   emp.sal%type,
     job   emp.job%type);
  end;
  /

  grant execute
  on emp_types_pack
  to public;

  create or replace procedure proc1(p_empno emp.empno%type)
  is
     rec_emp emp_types_pack.emp_record_type;
  begin
    select ename, sal, job into rec_emp
    from emp
    where empno = p_empno;

    dbms_output.put_line(rec_emp.ename);
    dbms_output.put_line(rec_emp.sal); 
  end;
  /

  exec proc1(7788)

    ↓↓

  create or replace view v1
  as
  select ename, sal, job 
  from emp;

  create or replace procedure proc1(p_empno emp.empno%type)
  is
    rec_emp v1%rowtype;
  begin
    select ename, sal, job into rec_emp
    from emp
    where empno = p_empno;

    dbms_output.put_line(rec_emp.ename);
    dbms_output.put_line(rec_emp.sal); 
    dbms_output.put_line(rec_emp.ename||', '||rec_emp.sal);
  end;
  /

  exec proc1(7788)
```
  
> 💡 실행 전 예상
> `01행~18행` : `STEP02`의 나의 예제와 동일하다. 
> `23행~30행`: 커스텀 레코드 선언을 패키지에 담았다. 
> `32행~34행` : 만든 패키지의 실행권한을 `PUBLIC` 으로 변경
> `36행~47행` : 커스텀 레코드를 패키지를 사용하여 귀찮은 타입 작성없이 사용.
> `53행~72행`: `VIEW`를 생성해서 원하는 컬럼만 담는 커스텀 레코드를 마치 
> `행 레코드(EMP%ROWTYPE)` 를 선언하는것 만큼 편리하게 선언

## 안보고 따라하기
### 1. 커스텀레코드

```PLSQL
--STEP03(커스텀레코드)
CREATE OR REPLACE PROCEDURE PRO3 (P_EMPNO EMP.EMPNO%TYPE)
IS
TYPE REC IS RECORD(
ENAME EMP.ENAME%TYPE,
SAL EMP.SAL%TYPE,
JOB EMP.JOB%TYPE
);
REC_EMP REC;
BEGIN
SELECT ENAME, SAL, JOB 
INTO REC_EMP.ENAME, REC_EMP.SAL, REC_EMP.JOB 
FROM EMP
WHERE EMPNO = P_EMPNO;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC_EMP.ENAME||', 급여 : '||REC_EMP.SAL||', 직업 : '||REC_EMP.JOB);
END;
/

EXEC PRO3(7788);
```

```PLSQL
-- 결과
이름 : SCOTT, 급여 : 3000, 직업 : ANALYST
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

### 2. 패키지
```PLSQL
--STEP03(패키지)
CREATE OR REPLACE PACKAGE REC_EMP_PACK
IS
TYPE REC IS RECORD(
ENAME EMP.ENAME%TYPE,
SAL EMP.SAL%TYPE,
JOB EMP.JOB%TYPE
);
END;
/
CREATE OR REPLACE PROCEDURE PRO3 (P_EMPNO EMP.EMPNO%TYPE)
IS
REC_EMP REC_EMP_PACK.REC;
BEGIN 
SELECT ENAME, SAL, JOB 
INTO REC_EMP.ENAME, REC_EMP.SAL, REC_EMP.JOB 
FROM EMP
WHERE EMPNO = P_EMPNO;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC_EMP.ENAME||', 급여 : '||REC_EMP.SAL||', 직업 : '||REC_EMP.JOB);
END;
/

EXEC PRO3(7788);
```

```PLSQL
이름 : SCOTT, 급여 : 3000, 직업 : ANALYST
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

### 3. VIEW
```PLSQL

--SETP03(VIEW)
CREATE OR REPLACE VIEW V1
AS
SELECT ENAME, SAL, JOB 
FROM EMP;
/
CREATE OR REPLACE PROCEDURE PRO3(P_EMPNO EMP.EMPNO%TYPE)
IS 
REC_EMP V1%ROWTYPE;
BEGIN 
SELECT ENAME, SAL, JOB 
INTO REC_EMP.ENAME, REC_EMP.SAL, REC_EMP.JOB 
FROM EMP
WHERE EMPNO = P_EMPNO;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC_EMP.ENAME||', 급여 : '||REC_EMP.SAL||', 직업 : '||REC_EMP.JOB);
END;
/

EXEC PRO3(7788);
```

```PLSQL
이름 : SCOTT, 급여 : 3000, 직업 : ANALYST
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

> 💡 `VIEW`는 `AS` 써야한다.


## 나의 예제
### 1. 커스텀 레코드를 사용하는 방법
```PLSQL
--MY03 커스텀레코드
SELECT * FROM EMPLOYEES;
CREATE OR REPLACE PROCEDURE PRO3(P_EMPLOYEE_ID EMPLOYEES.EMPLOYEE_ID%TYPE)
IS
TYPE REC IS RECORD(
FIRST_NAME EMPLOYEES.FIRST_NAME%TYPE,
LAST_NAME EMPLOYEES.LAST_NAME%TYPE,
EMAIL EMPLOYEES.EMAIL%TYPE,
SALARY EMPLOYEES.SALARY%TYPE
);
REC_EMP REC;
BEGIN
SELECT FIRST_NAME, LAST_NAME, EMAIL, SALARY 
INTO REC_EMP.FIRST_NAME, REC_EMP.LAST_NAME, REC_EMP.EMAIL, REC_EMP.SALARY
FROM EMPLOYEES
WHERE EMPLOYEE_ID = P_EMPLOYEE_ID;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC_EMP.FIRST_NAME||' '||REC_EMP.LAST_NAME||', 이메일 : '||REC_EMP.EMAIL||', 급여 : '||REC_EMP.SALARY);
END;
/
EXEC PRO3(149);
```

```PLSQL
이름 : Eleni Zlotkey, 이메일 : EZLOTKEY, 급여 : 10500
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

### 2. 패키지를 사용하는 방법
```PLSQL
--MY03 패키지
CREATE OR REPLACE PACKAGE EMPLOYEES_PACK 
IS
TYPE REC IS RECORD(
FIRST_NAME EMPLOYEES.FIRST_NAME%TYPE,
LAST_NAME EMPLOYEES.LAST_NAME%TYPE,
EMAIL EMPLOYEES.EMAIL%TYPE,
SALARY EMPLOYEES.SALARY%TYPE
);
END;
/
CREATE OR REPLACE PROCEDURE PRO3(P_EMPLOYEE_ID EMPLOYEES.EMPLOYEE_ID%TYPE)
IS
REC_EMP EMPLOYEES_PACK.REC;
BEGIN
SELECT FIRST_NAME, LAST_NAME, EMAIL, SALARY 
INTO REC_EMP.FIRST_NAME, REC_EMP.LAST_NAME, REC_EMP.EMAIL, REC_EMP.SALARY
FROM EMPLOYEES
WHERE EMPLOYEE_ID = P_EMPLOYEE_ID;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC_EMP.FIRST_NAME||' '||REC_EMP.LAST_NAME||', 이메일 : '||REC_EMP.EMAIL||', 급여 : '||REC_EMP.SALARY);
END;
/
EXEC PRO3(149);
```

```PLSQL
이름 : Eleni Zlotkey, 이메일 : EZLOTKEY, 급여 : 10500
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

### 3. `VIEW`를 사용하는 방법
```PLSQL
--MY03 VIEW 활용
CREATE OR REPLACE VIEW V1 
AS
SELECT FIRST_NAME, LAST_NAME, EMAIL, SALARY 
FROM EMPLOYEES;
/
CREATE OR REPLACE PROCEDURE PRO3(P_EMPLOYEE_ID EMPLOYEES.EMPLOYEE_ID%TYPE)
IS
REC_EMP V1%ROWTYPE;
BEGIN
SELECT FIRST_NAME, LAST_NAME, EMAIL, SALARY 
INTO REC_EMP.FIRST_NAME, REC_EMP.LAST_NAME, REC_EMP.EMAIL, REC_EMP.SALARY
FROM EMPLOYEES
WHERE EMPLOYEE_ID = P_EMPLOYEE_ID;
DBMS_OUTPUT.PUT_LINE('이름 : '||REC_EMP.FIRST_NAME||' '||REC_EMP.LAST_NAME||', 이메일 : '||REC_EMP.EMAIL||', 급여 : '||REC_EMP.SALARY);
END;
/
EXEC PRO3(149);
```

```PLSQL
이름 : Eleni Zlotkey, 이메일 : EZLOTKEY, 급여 : 10500
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

> 💡 어려웠던 부분
> VIEW 선언 시 `AS`, `END` XXX 
> IS 나  ;`세미클론` , `END`등의 문법이 헷갈리는 경우가 많은 듯 하다.
> 아직 충분히 사용해보지 못해서 그러는 듯 하다. 

<BR>

---

# Case 4
```plsql
create or replace procedure proc1(p_deptno emp.deptno%type)
  is
    TYPE emp_sal_table_type IS TABLE OF emp.sal%type 
      INDEX BY pls_integer;

    emp_sal_tab emp_sal_table_type;
  begin
    select sal BULK COLLECT INTO emp_sal_tab
    from emp
    where deptno = p_deptno;

    dbms_output.put_line(emp_sal_tab.first);
    dbms_output.put_line(emp_sal_tab.last);
    dbms_output.put_line('-----');

    for i in emp_sal_tab.first .. emp_sal_tab.last loop
      dbms_output.put_line(emp_sal_tab(i));
    end loop;
  end;
  /

  show errors

  exec proc1(20)
```
  
> 💡 실행 전 예상해보기 
> 테이블을 사용해서 여러 데이터를 조회한다. 
> 주어진 부서번호(`P_DEPTNO`)로 조회하여 해당하는 모든 데이터를 출력한다. 
> `FOR`문에서 `FIRST`, `LAST` 사용과 SELECT `BULK COLLECT` INTO 기억하기!!

## 안보고 따라하기
```PLSQL
--STEP04 
CREATE OR REPLACE PROCEDURE PRO4(P_DEPTNO EMP.DEPTNO%TYPE)
IS
TYPE TAB1 IS TABLE OF EMP.SAL%TYPE
INDEX BY PLS_INTEGER;
EMP_TAB TAB1;
BEGIN 
SELECT SAL BULK COLLECT INTO EMP_TAB
FROM EMP
WHERE DEPTNO = P_DEPTNO;
DBMS_OUTPUT.PUT_LINE(EMP_TAB.FIRST);
DBMS_OUTPUT.PUT_LINE(EMP_TAB.LAST);
FOR I IN EMP_TAB.FIRST .. EMP_TAB.LAST LOOP
DBMS_OUTPUT.PUT_LINE(EMP_TAB(I));
END LOOP;
END;
/
EXEC PRO4(20);
```

```PLSQL
1
5
800
2975
3000
1100
3000
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```
## 나의 예제

```PLSQL
CREATE OR REPLACE PROCEDURE PRO4 (P_DEPTNO EMP.DEPTNO%TYPE) 
IS
TYPE TAB1 IS TABLE OF EMP%ROWTYPE
INDEX BY PLS_INTEGER;
EMP_TAB TAB1;
BEGIN 
SELECT * BULK COLLECT INTO EMP_TAB
FROM EMP
WHERE DEPTNO = P_DEPTNO;
DBMS_OUTPUT.PUT_LINE(EMP_TAB.FIRST);
DBMS_OUTPUT.PUT_LINE(EMP_TAB.LAST);
FOR I IN EMP_TAB.FIRST .. EMP_TAB.LAST LOOP
DBMS_OUTPUT.PUT_LINE('사번 : '||EMP_TAB(I).EMPNO||', 이름 : '||EMP_TAB(I).ENAME||', 급여 : '||EMP_TAB(I).SAL);
END LOOP;
END;
/

EXEC PRO4(30);
```

```PLSQL
1
6
사번 : 7499, 이름 : ALLEN, 급여 : 1600
사번 : 7521, 이름 : WARD, 급여 : 1250
사번 : 7654, 이름 : MARTIN, 급여 : 1250
사번 : 7698, 이름 : BLAKE, 급여 : 2850
사번 : 7844, 이름 : TURNER, 급여 : 1500
사번 : 7900, 이름 : JAMES, 급여 : 950
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

> 📌 `BULK COLLECT`
> `BULK COLLECT`를 사용하지 않고 실행하면 `SELECT * INTO EMP_TAB` 부분에서 
> 컴파일 오류가 발생한다. 
> 그 이유는 `EMP_TAB`을 `SELECT INTO` 구문으로 바로 할당할 수 없기 때문이다. 
> `EMP_TAB`은 `PL/SQL`에서 정의한 타입이며 `BULK COLLECT`와 함께 사용되어야 한다.

> 💡 `TABLE` 에서 컬럼값을 가져올 때, 
> `TABLE명(INDEX).컬럼명`으로 가져와야한다.  
<BR>

---

# Case 5
```plsql
create or replace procedure proc1(p_deptno emp.deptno%type)
  is
    TYPE emp_table_type IS TABLE OF emp%rowtype
      INDEX BY pls_integer;

    emp_tab emp_table_type;
  begin
    select * BULK COLLECT INTO emp_tab
    from emp
    where deptno = p_deptno;

    for i in emp_tab.first .. emp_tab.last loop
      dbms_output.put_line(emp_tab(i).empno);
      dbms_output.put_line(emp_tab(i).ename);
      dbms_output.put_line('-----');
    end loop;
  end;
  /

  exec proc1(20)
```
  
> 💡 실행 전 예상
> `STEP04` 내가 만든 예제와 동일하다. 

## 안보고 따라 하기
```PLSQL
--STEP05
CREATE OR REPLACE PROCEDURE PRO5(P_DEPTNO EMP.DEPTNO%TYPE) 
IS 
TYPE TAB1 IS TABLE OF EMP%ROWTYPE
INDEX BY PLS_INTEGER;
EMP_TAB TAB1;
BEGIN 
SELECT * BULK COLLECT INTO EMP_TAB 
FROM EMP
WHERE DEPTNO = P_DEPTNO; 
DBMS_OUTPUT.PUT_LINE(EMP_TAB.LAST);
FOR I IN EMP_TAB.FIRST .. EMP_TAB.LAST LOOP
DBMS_OUTPUT.PUT_LINE(EMP_TAB(I).EMPNO||' : '||EMP_TAB(I).ENAME);
END LOOP;
END;
/
EXEC PRO5(20);
```

```PLSQL
5
7369 : SMITH
7566 : JONES
7788 : SCOTT
7876 : ADAMS
7902 : FORD
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

## 나의 예제
```PLSQL
CREATE OR REPLACE PACKAGE EMP_PACK IS
TYPE TAB1 IS TABLE OF EMP%ROWTYPE 
INDEX BY PLS_INTEGER;
END;
/
CREATE OR REPLACE PROCEDURE PRO5(P_DEPTNO EMP.DEPTNO%TYPE)
IS 
EMP_TAB EMP_PACK.TAB1;
BEGIN 
SELECT * BULK COLLECT INTO EMP_TAB 
FROM EMP
WHERE DEPTNO = P_DEPTNO; 
DBMS_OUTPUT.PUT_LINE(EMP_TAB.LAST);
FOR I IN EMP_TAB.FIRST .. EMP_TAB.LAST LOOP
DBMS_OUTPUT.PUT_LINE(EMP_TAB(I).EMPNO||' : '||EMP_TAB(I).ENAME);
END LOOP;
END;
/
EXEC PRO5(20);
```

```PLSQL
Package EMP_PACK이(가) 컴파일되었습니다.
5
7369 : SMITH
7566 : JONES
7788 : SCOTT
7876 : ADAMS
7902 : FORD
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

> 💡 `TABLE`선언부를 `PACKAGE`를 사용해서 재사용성을 높혔다.

<BR>

---

# Case 6
```plsql
create or replace procedure proc1(p_deptno emp.deptno%type)
  is
    TYPE emp_record_type IS RECORD
    (empno emp.empno%type,
     ename emp.ename%type,
     sal   emp.sal%type,
     job   emp.job%type);

    TYPE emp_table_type IS TABLE OF emp_record_type
      INDEX BY pls_integer;

    emp_tab emp_table_type;
  begin
    select empno, ename, sal, job BULK COLLECT INTO emp_tab
    from emp
    where deptno = p_deptno;

    for i in emp_tab.first .. emp_tab.last loop
      dbms_output.put_line(emp_tab(i).empno);
      dbms_output.put_line(emp_tab(i).ename);
      dbms_output.put_line('-----');
    end loop;
  end;
  /

  exec proc1(20)
```
  
> 💡 실행 전 예상
> `커스텀 레코드`를 만들고 그걸 `TABLE`로 만들어서 여러 결과를 담아서 출력하는 형태.

## 안보고 따라하기
```PLSQL
--STEOP06
CREATE OR REPLACE PROCEDURE PROC1(P_DEPTNO EMP.DEPTNO%TYPE)
IS
TYPE EMP_RECORD_TYPE IS RECORD
(EMPNO EMP.EMPNO%TYPE,
ENAME EMP.ENAME%TYPE,
SAL   EMP.SAL%TYPE,
JOB   EMP.JOB%TYPE);

TYPE EMP_TABLE_TYPE IS TABLE OF EMP_RECORD_TYPE
INDEX BY PLS_INTEGER;

EMP_TAB EMP_TABLE_TYPE;
BEGIN
SELECT EMPNO, ENAME, SAL, JOB BULK COLLECT INTO EMP_TAB
FROM EMP
WHERE DEPTNO = P_DEPTNO;

FOR I IN EMP_TAB.FIRST .. EMP_TAB.LAST LOOP
DBMS_OUTPUT.PUT_LINE(EMP_TAB(I).EMPNO);
DBMS_OUTPUT.PUT_LINE(EMP_TAB(I).ENAME);
DBMS_OUTPUT.PUT_LINE('-----');
END LOOP;
END;
/
EXEC PROC1(20);
```

```PLSQL
7369
SMITH
-----
7566
JONES
-----
7788
SCOTT
-----
7876
ADAMS
-----
7902
FORD
-----
```

## 나의 예제
```PLSQL
CREATE OR REPLACE PROCEDURE PRO6(P_DEPTNO EMP.DEPTNO%TYPE) 
IS
TYPE REC IS RECORD(
EMPNO EMP.EMPNO%TYPE,
ENAME EMP.ENAME%TYPE,
SAL EMP.SAL%TYPE
);

TYPE TAB1 IS TABLE OF REC 
INDEX BY PLS_INTEGER;
REC_TAB TAB1;

BEGIN

SELECT EMPNO, ENAME, SAL BULK COLLECT INTO REC_TAB
FROM EMP
WHERE DEPTNO = P_DEPTNO;

DBMS_OUTPUT.PUT_LINE(REC_TAB.LAST);
FOR I IN REC_TAB.FIRST .. REC_TAB.LAST LOOP
DBMS_OUTPUT.PUT_LINE('사번 : '||REC_TAB(I).EMPNO||', 이름 : '||REC_TAB(I).ENAME||', 급여 : '||REC_TAB(I).SAL);
END LOOP;
END;
/

```

```PLSQL
5
사번 : 7369, 이름 : SMITH, 급여 : 800
사번 : 7566, 이름 : JONES, 급여 : 2975
사번 : 7788, 이름 : SCOTT, 급여 : 3000
사번 : 7876, 이름 : ADAMS, 급여 : 1100
사번 : 7902, 이름 : FORD, 급여 : 3000
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

> 💡 아쉬운 점 
> 원래 예제를 만들 때, `REC`를 `VIEW` 로 만들고, `REC`를 `TABLE`로 만들어 보려 했지만 `PLS-00488: 'REC'은(는) 유형이어야 합니다.` 라는 오류 메세지와 함께 실패하였다. 
> 검색해보니, `PLS-00488`오류는 일치하지 않는 레코드, 인덱스가 반환되거나 전달되었을 때 발생하고, 주로 레코드나 인덱스가 잘못 정의되어 있거나, 유효하지 않은 데이터 타입이 사용되었을 때 발생한다는 걸 보니, 아쉽게도 지원하지 않는 듯 하다. 😭
