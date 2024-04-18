>[!info]- 복습
> # Data Types
> - scalar : character, number, data, boolean
> - Composite : PL/SQL Table, PL/SQL Record
> - Reference : re cursor
> - LOB : CLOB, BLOB, BFILE
>   
> # PL/SQL Language Fundamental

```plsql
select into
--반환값없으면 런타임 에러
from
```

### EXCEPTION
`no_data_found` : 반환 데이터가 없음
`too_many_rows` : 한 건의 데이터가 조회되어야하는데, 결과가 2개 이상임

```plsql
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
```

### 나의 예제
```PLSQL
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
```

---
### CURSOR FOR LOOP
```PLSQL
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
```

# 📌 명시적 커서 

> 📌 Cursor
> A `cursor` is a name or handle to a specific private SQL area.
> `커서`는 특정 `priavate SQL area`를 다루는 `이름` 또는 `핸들`이다.  


---

  ## [Oracle Memory Architecture](https://docs.oracle.com/en/database/oracle/oracle-database/12.2/cncpt/memory-architecture.html#GUID-913335DF-050A-479A-A653-68A064DCCA41)    
### Cursor의 종류
| 커서                | RETURN | METHOD                                |
| ----------------- | ------ | ------------------------------------- |
| `Implicit CURSOR` | 한 건    | select, insert, update, delete, merge |
| `Explicit CURSOR` | 두 건 이상 | select                                |
```PLSQL
create or replace procedure p1(a number)
       is
         v_salary number;
       begin
         select salary into v_salary
         from employees
         where department_id = a; 
       end;
       /

EXEC P1(50) 
-- 에러 : ORA-01422: 실제 인출은 요구된 것보다 많은 수의 행을 추출합니다.
EXEC p1(51)
-- 에러 : ORA-01403: 데이터를 찾을 수 없습니다.
```

```PLSQL
create or replace procedure p1(a number)
       is
         CURSOR nemam
         IS
           select salary
           from employees
           where department_id = a; 
       begin
         open nemam;   /* active set */
       end;
       /

       exec p1(50)
       exec p1(51)
```
       
> 💡 `CURSOR`의 ACTIVE SET(액티브 셋) 이란?
> 커서가 실행될 때 생성되는 결과 집합을 가르킨다. 
> 커서가 쿼리를 실행하면 결과 집합이 액티브 셋에 저장되며, 
> 이를 통해 프로그램에서 결과를 반복적으로 검색할 수 있다. 
> 
> 일반적으로 엑티브 셋은 커서가 열려 있는 동안에만 유지된다. 
> 커서가 닫히면 액티브 셋도 사라지게 된다. 

### Cursor 속성
| 커서종류                          | 속성                                                             |
| ----------------------------- | -------------------------------------------------------------- |
| `implicit cursor`<br>* 암묵적 커서 | * sql%isopen<br>* sql%found<br>* sql%notfoud<br>* sql%rowcount |
| `explicit cursor`<br>* 명시적 커서 | * 커서명%isopen<br>* 커서명%found<br>* 커서명%notfoud<br>* 커서명%rowcount |

# implicit cursor 예제
  
## 암시적 커서 속성 사용 예제

```PLSQL
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
```

 > 💡암시적 커서의 속성을 사용하여 몇 개의 행이 영향을 받았는지 표시


---
# Explicit cursor 예제 : declare -> open -> fetch -> close

```PLSQL
  --- 명시적 커서 예제
  create or replace procedure p1(a employees.department_id%type)
  is
    CURSOR emp_cursor -- EMP _CURSOR (명시적 커서 선언)
    IS
      select employee_id, last_name, salary, job_id, hire_date  
      from employees
      where department_id = a
      order by salary desc;    
      
    r emp_cursor%rowtype; 
  
  begin
    if not(emp_cursor%isopen) then 
    
      OPEN emp_cursor;  /* 서버 내부에 active set이 생성 */ 
    end if;

    loop
      FETCH emp_cursor INTO r;  
      exit when emp_cursor%notfound; 
      dbms_output.put_line(r.employee_id||' '||r.salary||' '||r.job_id);
    end loop;

    CLOSE emp_cursor;
  end;
  /

  exec p1(10)
  exec p1(50)   
```

> 💡 LINE BY LINE
> **04행** : `06행~09행` 실행한 쿼리문의 결과를 담아둠
> **10행** : 결과를 담은 커서를 조금씩 잘라서 출력하기위한 레코드  
> **14행** : `CURSOR%ISOPEN` : 커서가 열려있으면 TRUE 반환 
> **16행** : 지정한 쿼리가 실행되고, 결과가 담김
> **20행** : `FETCH`를 통해 하나의 행씩 잘라서 레코드에 담아 출력
> **21행** : 명시적 커서의 속성(`커서이름%NOTFOUND`)을 이용하여 결과가 없을 경우
> 루프문을 빠져나온다. 
> **25행** : 커서를 닫는다.  (결과 집합을 닫는다.)

> ### 📌 커서의 실행순서
> 1. `CURSOR`를 `OPEN`하여 쿼리를 실행한다. 
> 2. `FETCH`문을 사용하여 결과 집합에서 한 번에 하나의 행을 가져온다. 
> 3. 필요한 작업을 수행하고, 더 이상의 행이 없을 때까지 `FETCH`를 반복한다. 
> 4. `CURSOR`를 `CLOSE`하여 결과 집합을 닫는다.

### 예제 연습

```PLSQL
DECLARE
    CURSOR employee_cursor IS
        SELECT employee_name FROM employees;
    employee_name employees.employee_name%TYPE; -- 하나의 행을 담을 레코드
BEGIN
    OPEN employee_cursor; -- 쿼리 실행 
    LOOP
        FETCH employee_cursor INTO employee_name; -- FETCH 하나의 행 담음
        EXIT WHEN employee_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Employee Name: ' || employee_name);
    END LOOP;
    CLOSE employee_cursor; --결과의 집합을 닫음
END;
```

# Cursor for loop 예제 하나 더
```PLSQL
create or replace procedure p1
  is
  begin
    for d in (select * from departments order by department_id) loop
      dbms_output.put_line(d.department_id);
      for e in (select * from employees where department_id = d.department_id order by salary desc) loop
        dbms_output.put_line(e.employee_id||', '||e.last_name);
      end loop;
      dbms_output.put_line('-----------');
    end loop;
  end;
  /

  exec p1
```

> 💡 `묵시적 커서` 를 사용한 `이중 FOR`문 예
  
### 예제 연습
```PLSQL
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
```

> 💡 부서번호로 사원을 출력하는 예제

---
# 매개변수를 사용하는 커서

## Cursor variable

  * [Cursor Variables](https://docs.oracle.com/en/database/oracle/oracle-database/18/lnpls/static-sql.html#GUID-4A6E054A-4002-418D-A1CA-DE849CD7E6D5)
  > 📌 커서 변수
    (1) 강한 타입의 커서 변수는 반환형만 일치하면 어떤 SELECT문도 OPEN 할 수 있다
    (2) 약한 타입의 커서 변수는 반환형이 서로 다른 쿼리에 대해서도 사용할 수 있다
    (3) 커서 변수는 서브프로그램의 매개변수로 사용할 수 있다

  - [Enhancing the Application: Advanced JDBC Features](https://docs.oracle.com/database/121/TDPJD/addfunc.htm#TDPJD206 )

## 강한 타입의 커서 변수
>  📌`강한 타입의 커서 변수`는 반환되는 칼럼의 개수와 타입만 일치하면
> 어떤 `SELECT` 문에 대해서도 OPEN 가능하다.

> 💡 `PL/SQL`에서 일반적으로 `CURSOR`를 이용하여 쿼리를 실행하려면 먼저 `CURSOR`를 
> 열어야한다. `CURSOR`를 열 때 사용되는 구문은 `OPEN FOR`이다 .
> `OPEN FOR`문은 `CURSOR`를 선언하고 쿼리를 할당하는 데 사용된다.

### 이해를 위한 예제 만들어보기
```PLSQL
--일반적인 방법
DECLARE
    CURSOR cursor_name IS
        SELECT column1, column2, ... FROM table_name WHERE condition;
BEGIN
    OPEN cursor_name;
    -- 이후 작업
END;
```

```PLSQL
DECLARE 
	type EMP_RECORD_TYPE is record
    (employee_id employees.employee_id%type,
     last_name   employees.last_name%type,
     salary      employees.salary%type);

TYPE M_CUR IS REF CURSOR 
RETURN EMP_RECORD_TYPE;

REC1 EMP_RECORD_TYPE;
CUR M_CUR;

BEGIN 
OPEN CUR FOR 
SELECT EMPLOYEE_ID, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 10; ...
```

> 📌 커서 선언 후 열기 & 강타입은 인자의 갯수와 TYPE 만 일치하면 
> 다시 `OPEN` 하여 사용가능

```PLSQL
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

  exec sp_strong_type_cursor_variable
```
  
>💡 `NVL`
>**NVL 함수** : 첫번째 열이 NULL 인 경우  두번째 값으로 대체한다. 


> 📌 `REM 커서 변수`는 서브 프로그램의 매개 변수로 사용할 수 있다.


```PLSQL
--- 강타입 예제 2 시작
create or replace procedure sp_cusor_variable_parameter
    is
    type emp_rec is record(
    employee_id employees.employee_id%type,
    last_name   employees.last_name%type
    );
    -- 사번, 이름 저장 레코드
    type emp_cursor_type is ref cursor
    return emp_rec;
    -- 사번, 이름 저장 레코드를 반환하는 REF 커서
    v_empcur emp_cursor_type; 
    
    --내부 프로시저
    procedure print_emp(a_empcur in emp_cursor_type) is
    v_emprec emp_rec;
    begin
    loop
    fetch a_empcur into v_emprec ;
    exit when a_empcur%notfound;
    dbms_output.put_line('employee_id=' ||v_emprec.employee_id||', last_name=' || v_emprec.last_name);
    end loop;
    end;
   --내부 프로시저 끝
   begin
   open v_empcur
   for
   select employee_id, last_name
   from employees;

   print_emp(v_empcur);

   close v_empcur;
end;
/

exec sp_cusor_variable_parameter
```

> 💡 LINE BY LINE
> **02행~12행** : 사번, 이름을 담는 레코드와 , 그 레코드를 `RETURN` 하는 `REF CURSOR`
> **14행~24행** : `EMP_CURSOR_TYPE`을 받아서 LOOP를 돌며 그 결과를 한줄씩 뽑아먹는 
> 내부 프로시저를 만든다.
> **25행** : 이제 CURSUR 가 실행할 쿼리를 선언해준다. 그 뒤 내부 프로시저를 돌린다. 

### 예제 연습
```PLSQL
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
    
    --내부 프로시저
    procedure print_emp(C in CUR1) is
    REC REC1;
    begin
    loop
    fetch C into REC;
    exit when C%notfound;
    dbms_output.put_line('employee_id=' ||REC.employee_id||', last_name=' || REC.last_name);
    end loop;
    end;
   --내부 프로시저 끝
   begin
   open CUR -- 커서 오픈
   for
   select employee_id, last_name
   from employees; -- 이거 결과 담음

   print_emp(CUR); -- 내부 프로시저 돌림

   close CUR; -- 닫음
end;
/
SHOW ERRORS;
```

---
# 약한 타입의 커서 변수

>  📌 약한 타입의 커서 변수는 반환형이 서로 다른 쿼리에 대해서도 사용할 수 있다

```PLSQL

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
```

> 💡 LINE BY LINE
> **03행~06행** : 강타입 예제들과 다르게 커서가 반환하는 값을 정해두지 않았다. 
> 또한, CHAR를 입력받고 반환값을 다르게 하기 위해 V_SELECETOR를 만들어 주었다.
> **08행~19행** : `OPEN CURSOR` 메소드 -> 셀렉터와 부서 아이디를 받고, 
> * 셀렉터가 E 일경우 커서를 열고 부서 아이디와 일치하는 근무자 조회 결과를 커서에 담는다.
> * 그렇지 않으면, 부서 아이디와 일치하는 부서 조회결과를 커서에 담는다.
> **22행~42행** : 셀렉터 타입에따라 담을 레코드를 다르게 만들어 출력한다. 
  
  
---
# 나의 커서 예제

# 자바부분!

## CustomerJDBCRepository
```JAVA
package com.kosa.Customer.repository;  
  
import com.kosa.Customer.connection.DBConnection;  
import com.kosa.Customer.domain.Customer;  
import oracle.jdbc.OracleTypes;  
  
import java.sql.*;  
import java.time.LocalDate;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Optional;  
  
/**  
 * packageName    : com.kosa.Customer.repository * fileName       : CustomerRepository * author         : Yeong-Huns * date           : 2024-04-18 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-18        Yeong-Huns       최초 생성  
 */  
public class CustomerJDBCRepository {  
    private static CustomerJDBCRepository instance = null;  
    private CustomerJDBCRepository() {}  
    public static CustomerJDBCRepository getInstance() {  
        if (instance == null) {  
            instance = new CustomerJDBCRepository();  
        }  
        return instance;  
    }  
  
  
    private  Connection conn = DBConnection.getConnection();  
    private Statement stmt;  
    private ResultSet rs;  
  
    public void Register(Customer customer) {  
        String callStatement = "{ call CUSTOMER_PACK.REGISTER(?, ?) }";  
        try {  
            CallableStatement callableStatement = conn.prepareCall(callStatement);  
            callableStatement.setString(1, customer.getName());  
            callableStatement.setLong(2, customer.getExpense());  
            callableStatement.executeUpdate();  
            System.out.println(customer.getName() + "님의 가입요청이 정상 처리 되었습니다.");  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        }    }  
  
    public List<Customer> customerList() {  
        List<Customer> customerList = new ArrayList<Customer>();  
        String callStatement = "{ call CUSTOMER_PACK.FIND_ALL(?) }";  
        try {  
            CallableStatement callableStatement = conn.prepareCall(callStatement);  
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);  
            callableStatement.execute();  
  
            ResultSet rs = (ResultSet) callableStatement.getObject(1);  
  
            while (rs.next()) {  
                long id = rs.getLong(1);  
                String name = rs.getString(2);  
                long expense = rs.getLong(3);  
                LocalDate registrationDate = rs.getDate(4).toLocalDate();  
                customerList.add(new Customer(id, name, expense, registrationDate));  
            }  
  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        }        return customerList;  
    }  
  
    public void UpdateExpense(Customer customer){  
        String callStatement = "{ call CUSTOMER_PACK.UPDATE_EXPENSE(?, ?) }";  
        try{  
            CallableStatement callableStatement = conn.prepareCall(callStatement);  
            callableStatement.setLong(1, customer.getId());  
            callableStatement.setLong(2, customer.getExpense());  
            callableStatement.executeUpdate();  
            System.out.println(customer.getName() + "님의 사용금액이 업데이트 되었습니다.");  
        }catch(SQLException e){  
            e.printStackTrace();  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally {  
        }    }  
    public void delete(Customer customer){  
        String callStatement = "{ call CUSTOMER_PACK.DELETE(?) }";  
        try{  
            CallableStatement callableStatement = conn.prepareCall(callStatement);  
            callableStatement.setLong(1, customer.getId());  
            callableStatement.execute();  
            System.out.println(customer.getName() + "님의 계정이 정상적으로 삭제되었습니다.");  
        }catch(SQLException e){  
            e.printStackTrace();  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally {  
        }    }  
    public Optional<Customer> findById(long RequestId){  
        Optional<Customer> customerOptional = Optional.empty();  
        String callStatement = "{ call CUSTOMER_PACK.FIND_BY_ID(?, ?) }";  
        try{  
            CallableStatement callableStatement = conn.prepareCall(callStatement);  
            callableStatement.setLong(1, RequestId);  
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);  
            callableStatement.execute();  
            System.out.println("계정 조회 성공.");  
            ResultSet rs = (ResultSet) callableStatement.getObject(2);  
            while (rs.next()) {  
                long id = rs.getLong(1);  
                String name = rs.getString(2);  
                long expense = rs.getLong(3);  
                LocalDate registrationDate = rs.getDate(4).toLocalDate();  
                customerOptional = Optional.of(new Customer(id, name, expense, registrationDate));  
            }  
        }catch(SQLException e){  
            e.printStackTrace();  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally {  
        }        return customerOptional;  
    }  
}
```

## Customer
```java
package com.kosa.Customer.domain;  
  
import java.time.LocalDate;  
  
/**  
 * packageName    : com.kosa.Employee.domain * fileName       : Employee * author         : Yeong-Huns * date           : 2024-04-18 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-18        Yeong-Huns       최초 생성  
 */  
  
public class Customer {  
    private long id;  
    private String name;  
    private long expense;  
    private LocalDate registrationDate;  
  
  
    public Customer(String name, long expense) {  
        this.name = name;  
        this.expense = expense;  
    }  
  
  
    public Customer(long id, String name, long expense, LocalDate registrationDate) {  
        this.id = id;  
        this.name = name;  
        this.expense = expense;  
        this.registrationDate = registrationDate;  
    }  
    public void updateExpense(int money) {  
        this.expense += money;  
    }  
  
    public long getId() {  
        return id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public long getExpense() {  
        return expense;  
    }  
  
    public LocalDate getRegistrationDate() {  
        return registrationDate;  
    }  
  
    @Override  
    public String toString() {  
        return "Customer{" +  
                "id=" + id +  
                ", name='" + name + '\'' +  
                ", expense=" + expense +  
                ", registrationDate=" + registrationDate +  
                '}';  
    }  
}
```

## TEST
```java
package com.kosa.Customer.controller;  
  
import com.kosa.Customer.domain.Customer;  
import com.kosa.Customer.repository.CustomerJDBCRepository;  
  
import java.util.Arrays;  
  
/**  
 * packageName    : com.kosa.account.controller * fileName       : ddd * author         : Yeong-Huns * date           : 2024-04-17 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-17        Yeong-Huns       최초 생성  
 */  
public class ddd {  
  
    public static void main(String[] args) {  
        CustomerJDBCRepository repository = CustomerJDBCRepository.getInstance();  
  
        //repository.Register(new Customer("김영한", 2000));  
  
        //고객등록  
        Arrays.asList(  
                new Customer("손기정",1000),  
                new Customer("바나나",2000),  
                new Customer("홍길동",3000),  
                new Customer("류호정",4000),  
                new Customer("사만다",5000),  
                new Customer("이기억",6000),  
                new Customer("공혁준",7000)  
        ).forEach(repository::Register);  
  
        //전체조회  
        repository.customerList().forEach(System.out::println);  
  
        //단건조회  
        Customer customer = repository.findById(13).orElseThrow(IllegalArgumentException::new);  
        System.out.println(customer);  
        //조회한 유저 삭제  
        repository.delete(customer);  
  
  
    }  
}
```

# DB 부분!
## CUSTOMER_PACKAGE
```plsql
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
```

## CUSTOMER_PACKAGE_BODY
```PLSQL
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
```

## PROCEDURE TEST
```PLSQL
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

```

---
# EXCEPTION 
이름 있는 예외 : WHEN NAME THEN
이름 없는 예외 : WHEN OTHERS THEN , PUT THE NAME TO EXCEPTION THEN HANDLE

오라클의 에러는 반드시 번호가 있다. 
하지만 처리는 항상 이름을 통해서 이루어진다. 

### `EXCEPTION` 처리
```PLSQL 
INSERT INTO T1 VALUES (1000);
BEGIN 
DBMS_OUTPUT.PUT_LINE(100/0);
EXCEPTION 
WHEN ZERO_DIVIDE THEN 
NULL;
END;
INSERT INTO T1 VALUES(200);
SELECT * FROM T1; 
ROLLBACK;
```

```PLSQL
 create or replace procedure insert_t1(a number)
    is
      e_null exception;
      pragma exception_init(e_null, -1400); -- EXCEPTION 번호로 
      --CUSTOM EXCEPTINO 만
    begin
      insert into t1 values(a);
    exception
      when e_null then
        dbms_output.put_line('Null 값을 입력할 수 없습니다!');
    end;

```



```PLSQL
CREATE TABLE CUSTOMER(
ID NUMBER GENERATED AS IDENTITY PRIMARY KEY, 
NAME VARCHAR2(10),
EXPENSE NUMBER,
REG_DATE DATE
);
```

```plsql
CREATE OR REPLACE PACKAGE CUSTOMER_PACK 
IS 
PROCEDURE REGISTER(
P_NAME CUSTOMER.NAME%TYPE, 
P_EXPENSE CUSTOMER.EXPENSE%TYPE,
P_REG_DATE CUSTOMER.REG_DAT%TYPE
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
P_CURSOR OUT SYS_REFCURSOR
);
FUNCTION IS_EXISTS(
P_ID CUSTOMER.ID%TYPE
)RETURN NUMBER;

END;
/
```

```PLSQL
CREATE OR REPLACE PACKAGE BODY CUSTOMER_PACK 
IS 
FUNCTION EXPENSE_CHECK(
MONEY CUSTOMER.EXPENSE%TYPE;
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
P_EXPENSE CUSTOMER.EXPENSE%TYPE,
P_REG_DATE CUSTOMER.REG_DAT%TYPE
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
WHERE ID = P_ID
COMMIT;
END;

PROCEDURE FIND_ALL(
P_CURSOR OUT SYS_REFCURSOR 
) IS
BEGIN 
OPEN P_CURSOR FOR 
SELECT * FROM CUSTOMER;
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
)RETURN NUMBER;
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
```






```PLSQL
create or replace procedure  insert_t1(a number)
    is
    begin
      insert into t1 values(a);
    end;
    /

    exec insert_t1(1000);
    exec insert_t1(Null);

    select * from t1;

     

    create or replace procedure insert_t1(a number)
    is
      e_null exception;
      pragma exception_init(e_null, -1400);
    begin
      insert into t1 values(a);
    exception
      when e_null then
        dbms_output.put_line('Null 값을 입력할 수 없습니다!');
    end;
    /

    exec insert_t1(1000);
    exec insert_t1(Null);

    select * from t1;

   

    create or replace package pack_exceptions
    is
      e_null exception;
      pragma exception_init(e_null, -1400);
    end;
    /

    create or replace procedure insert_t1(a number)
    is
    begin
      insert into t1 values(a);
    exception
      when pack_exceptions.e_null then
        dbms_output.put_line('Null 값을 입력할 수 없습니다!');
    end;
    /

    exec insert_t1(1000);
    exec insert_t1(Null);

    select * from t1;


```

