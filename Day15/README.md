
관계형 데이터베이스 = 자료구조(표) + 입출력명령(`SQL`)
비관계형 데이터베이스 = 자료구조(표 말고 다른거) + 입출력명령(`SQL` 이외)

> 💡어째서 관계형 데이터베이스라는 말을 독점하는가? 
> 다른 형태의 데이터베이스에도 관계가 존재한다. 

-> 
* `Tabular Database`(테이블형 데이터베이스) 
* `Document Database` (문서형 데이터베이스)
* `Graph Database`(그래프형 데이터베이스)

CLOUD` -> X as a Service(XaaS)
* Hospital as a Service 
* Restaurant as a Service 
* Education as a Service 
* ...
* `Iaas`, `PaaS`, `SaaS`, `DBaaS`, `MLaaS`, `DLaas`m `AIaaS`...
> 📌
`()`: parenthesis
`{}`: brace, curly brackets
`[]`: bracket 
`<>`: angle bracket

CREATE OR REPLACE PACKAGE PACK1 


```PLSQL

GRANT EXECUTE 
ON MEP_TYPES_PACK
TO PUBLIC ; -- PUBLIC 으로 만들면 앞으로도 생길 유저들도 사용가능
```

```PLSQL
ACE01.EMP0TYPES -- 이름에 .을 찍고사용
```

`VIEW` : `NAMED SELECT`문문




# SELECT INTO 
```PLSQL
--전통적인 방법 - 1
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
VSAL EMP.SAL%TYPE;
BEGIN 
SELECT SAL INTO VSAL
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE(VSAL);
END;
/

--레코드를 이용하는 방법 - 2
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
EMP_REC EMP%ROWTYPE;
BEGIN 
SELECT * INTO EMP_REC
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE(EMP_REC.SAL);
END;
/

--커스텀 레코드를 만드는 방법 - 3 
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
TYPE R1 IS RECORD (
RNAME EMP.ENAME%TYPE, 
RSAL EMP.SAL%TYPE,
RJOB EMP.JOB%TYPE
);
REC R1;
BEGIN 
SELECT ENAME, SAL, JOB INTO REC.RNAME, REC.RSAL, REC.RJOB
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE(REC.RNAME);
DBMS_OUTPUT.PUT_LINE(REC.RSAL);
DBMS_OUTPUT.PUT_LINE(REC.RJOB);
END;
/


--패키지를 이용하는 방법 - 4
CREATE OR REPLACE PACKAGE EMP_TYPES_PACK 
IS
TYPE R1 IS RECORD (
RNAME EMP.ENAME%TYPE, 
RSAL EMP.SAL%TYPE,
RJOB EMP.JOB%TYPE
);
END;
-- 패키지에 만들어둔 RECORD 를 사용
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
REC_EMP EMP_TYPES_PACK.R1
BEGIN 
SELECT ENAME, SAL, JOB INTO REC_EMP
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE('이 름 : '||REC_EMP.RNAME||' , 연 봉 : '||REC_EMP.RSAL||', 직 업 : '||REC_EMP.RJOB);
END;
/
```

```
결과::
Procedure PROC1이(가) 컴파일되었습니다.
3000

Procedure PROC2이(가) 컴파일되었습니다.
3000

Procedure PROC3이(가) 컴파일되었습니다.
SCOTT
3000
ANALYST

Procedure PROC4이(가) 컴파일되었습니다.
이 름 : SCOTT , 연 봉 : 3000, 직 업 : ANALYST
```

```PLSQL
CREATE OR REPLACE PROCEDURE PROC1(P_DNUM EMP.DEPTNO%TYPE)
IS 
TYPE TAB1 IS TABLE OF EMP.SAL%TYPE INDEX BY PLS_INTEGER;
T1 TAB1;
BEGIN 
SELECT SAL BULK COLLECT INTO T1
FROM EMP 
WHERE DEPTNO = P_DNUM; 
FOR I IN T1.FIRST .. T1.LAST LOOP
DBMS_OUTPUT.PUT_LINE(T1(I));
END LOOP;
END;
/
EXEC PROC1(20);
```

```
결과:: 
800
2975
3000
1100
3000
```

```PLSQL
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
  exec proc1(20);
```


## 과제 
>💡 `ACE02` 유저로 접속해서 `PRODUCTS` 테이블 쿼리하는 CASE 6개 만드세요

```PLSQL
-- 1 전통적인 방법
CREATE OR REPLACE PROCEDURE MY_PROC1(P_ID PRODUCTS.PRODUCT_ID%TYPE) IS
    V_PRODUCT_NAME PRODUCTS.PRODUCT_NAME%TYPE;
    V_DESCRIPTION PRODUCTS.DESCRIPTION%TYPE;

BEGIN 
    SELECT PRODUCT_NAME, DESCRIPTION INTO V_PRODUCT_NAME, V_DESCRIPTION
    FROM PRODUCTS
    WHERE PRODUCT_ID = P_ID;
    DBMS_OUTPUT.PUT_LINE('제품번호 : '||P_ID||', 제품이름 : '||V_PRODUCT_NAME||', 제품상세 : '||V_DESCRIPTION);
END;
/
EXEC MY_PROC1(249);
```

```PLSQL
-- 2 레코드를 이용하는 방법
CREATE OR REPLACE PROCEDURE MY_PROC2(P_ID PRODUCTS.PRODUCT_ID%TYPE) IS
    PRODUCT_REC PRODUCTS%ROWTYPE;
BEGIN 
    SELECT * INTO PRODUCT_REC
    FROM PRODUCTS
    WHERE PRODUCT_ID = P_ID;
    DBMS_OUTPUT.PUT_LINE('제품번호 : '||P_ID||', 제품이름 : '||PRODUCT_REC.PRODUCT_NAME||', 제품상세 : '||PRODUCT_REC.DESCRIPTION);
END;
/
EXEC MY_PROC2(249);
```

```PLSQL
-- 3 커스텀 레코드를 이용하는 방법
CREATE OR REPLACE PROCEDURE MY_PROC3(P_ID PRODUCTS.PRODUCT_ID%TYPE) IS
    TYPE REC IS RECORD(
        PRODUCT_NAME PRODUCTS.PRODUCT_NAME%TYPE,
        DESCRIPTION PRODUCTS.DESCRIPTION%TYPE
    );
    PRODUCT_REC REC;
BEGIN 
    SELECT PRODUCT_NAME, DESCRIPTION  INTO PRODUCT_REC.PRODUCT_NAME, PRODUCT_REC.DESCRIPTION
    FROM PRODUCTS
    WHERE PRODUCT_ID = P_ID;
    DBMS_OUTPUT.PUT_LINE('제품번호 : '||P_ID||', 제품이름 : '||PRODUCT_REC.PRODUCT_NAME||', 제품상세 : '||PRODUCT_REC.DESCRIPTION);
END;
/
EXEC MY_PROC3(249);
```

```PLSQL
-- 4 패키지를 이용하는 방법
CREATE OR REPLACE PACKAGE MY_PRODUCTS_PACK IS 
    TYPE REC IS RECORD(
        PRODUCT_NAME PRODUCTS.PRODUCT_NAME%TYPE,
        DESCRIPTION PRODUCTS.DESCRIPTION%TYPE
    );
END;
/
--패키지 생성
CREATE OR REPLACE PROCEDURE MY_PROC4(P_ID PRODUCTS.PRODUCT_ID%TYPE) IS
    PRODUCT_REC MY_PRODUCTS_PACK.REC;
BEGIN 
    SELECT PRODUCT_NAME, DESCRIPTION  INTO PRODUCT_REC.PRODUCT_NAME, PRODUCT_REC.DESCRIPTION
    FROM PRODUCTS
    WHERE PRODUCT_ID = P_ID;
    DBMS_OUTPUT.PUT_LINE('제품번호 : '||P_ID||', 제품이름 : '||PRODUCT_REC.PRODUCT_NAME||', 제품상세 : '||PRODUCT_REC.DESCRIPTION);
END;
/
--프로시저 생성
EXEC MY_PROC4(249);
```

```PLSQL
-- 5 테이블을 이용하는 방법
CREATE OR REPLACE PROCEDURE MY_PROC5(P_CATEGORY_ID PRODUCTS.CATEGORY_ID%TYPE) IS
    TYPE TAB IS TABLE OF PRODUCTS.PRODUCT_ID%TYPE 
    INDEX BY PLS_INTEGER;
PRODUCTS_TAB TAB;

BEGIN 
    SELECT PRODUCT_ID BULK COLLECT INTO PRODUCTS_TAB
    FROM PRODUCTS
    WHERE CATEGORY_ID = P_CATEGORY_ID;

    FOR IDX IN PRODUCTS_TAB.FIRST .. PRODUCTS_TAB.LAST LOOP
        DBMS_OUTPUT.PUT_LINE('카테고리 : '||P_CATEGORY_ID||', 제품번호 : '||PRODUCTS_TAB(IDX));
    END LOOP;
END;
/
EXEC MY_PROC5(4); -- 4번 카테고리에 속하는 제품번호 ~~
```

```PLSQL
-- 6 레코드와 테이블을 함께 이용하는 방법
CREATE OR REPLACE PROCEDURE MY_PROC6(P_CATEGORY_ID PRODUCTS.CATEGORY_ID%TYPE) IS
    TYPE REC IS RECORD(
        PRODUCT_ID PRODUCTS.PRODUCT_ID%TYPE,
        PRODUCT_NAME PRODUCTS.PRODUCT_NAME%TYPE,
        DESCRIPTION PRODUCTS.DESCRIPTION%TYPE
    );
    TYPE TAB IS TABLE OF REC
    INDEX BY PLS_INTEGER;
PRODUCTS_TAB TAB;

BEGIN 

    SELECT PRODUCT_ID ,PRODUCT_NAME, DESCRIPTION BULK COLLECT INTO PRODUCTS_TAB
    FROM PRODUCTS
    WHERE CATEGORY_ID = P_CATEGORY_ID;
    DBMS_OUTPUT.PUT_LINE('====='||P_CATEGORY_ID||'번 카테고리에 속한 제품군=====');

    FOR IDX IN PRODUCTS_TAB.FIRST .. PRODUCTS_TAB.LAST LOOP
        DBMS_OUTPUT.PUT_LINE('제품번호 : '||PRODUCTS_TAB(IDX).PRODUCT_ID||
        ', 제품이름 : '||PRODUCTS_TAB(IDX).PRODUCT_NAME||
        ', 제품상세 : '||PRODUCTS_TAB(IDX).DESCRIPTION);
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
END;
/
EXEC MY_PROC6(4);
```

`PL/SQL`의 VALUE -> READ ONLY


## PL/SQL 프로시저 구문

```PLSQL
CREATE [OR REPLACE ] PROCEDURE procedure_name (parameter_list)     
IS
    [declaration statements]

BEGIN

[execution statements]

EXCEPTION

[exception handler]

END [procedure_name ];
```


### PL/SQL 프로시저 헤더

프로시저는 해당 이름과 선택적 매개 변수 목록을 지정하는 헤더로 시작합니다.

각 매개변수는 , 또는 모드일 수 있습니다. 매개 변수 mode는 매개 변수를 읽거나 쓸 수 있는지 여부를 지정합니다.`IN``OUT``INOUT`

**`IN`**

매개 변수는 읽기 전용입니다. 프로시저 내에서 매개 변수를 참조할 수 있지만 해당 값을 변경할 수는 없습니다. Oracle은 기본 모드로 사용합니다. 즉, 매개 변수에 대한 모드를 명시적으로 지정하지 않으면 Oracle에서 해당 모드를 사용합니다.`IN``IN``IN``IN`

**`OUT`**

매개 변수를 쓸 수 있습니다. 일반적으로 매개 변수에 대해 반환된 값을 설정하고 호출 프로그램에 반환합니다. 프로시저는 매개 변수에 대해 제공하는 값을 무시합니다.`OUT``OUT``OUT`

**`INOUT`**

매개 변수는 읽고 쓸 수 있습니다. 프로시저를 읽고 수정할 수 있습니다.`INOUT`

이 옵션을 사용하면 현재 프로시저를 새 코드로 덮어쓸 수 있습니다.`OR REPLACE`

```PLSQL

CREATE OR REPLACE PROCEDURE PROC1 
(
P_EMPNO IN EMP.EMPNO%TYPE,
P_SAWON OUT EMP%ROWTYPE
);
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
```


### PL/SQL 프로시저 본문

[익명 블록](https://www.oracletutorial.com/plsql-tutorial/plsql-anonymous-block/)과 마찬가지로 프로시저 본문은 세 부분으로 구성됩니다. 실행 파트는 필수이지만 선언적 파트와 예외 처리 파트는 선택 사항입니다. 실행 파트에는 하나 이상의 실행 문이 포함되어야 합니다.


포멀파라미터(in,out) : 프로시저에 선언됨
엑추얼파라미터 : 대입값

| 파라미터 모드      | 형식 매개 변수                                                                                                                                                                                                                                                                                                                                         | 실제 매개 변수                                                                        | 참조로 통과 되었습니까?                                                    |
| :----------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------ | :--------------------------------------------------------------- |
| 안으로          | 형식 매개변수는 상수처럼 작동합니다: 서브프로그램이 시작될 때 해당 값은 실제 매개변수 또는 디폴트 값의 값이며 서브프로그램은 이 값을 변경할 수 없습니다.                                                                                                                                                                                                                                                          | 실제 매개 변수는 상수, 초기화된 변수, 리터럴 또는 식일 수 있습니다.                                        | 실제 매개 변수는 참조로 전달됩니다.                                             |
| 아웃           | 형식 매개 변수는 해당 형식의 기본값으로 초기화됩니다. 유형의 기본값은 기본값이 아닌 레코드 유형을 제외하고입니다([예 8-16](https://docs.oracle.com/en/database/oracle/oracle-database/19/lnpls/plsql-subprograms.html#GUID-518B8827-26CC-4734-B799-ACB038185638__CHDJEJHG) 참조). `NULL``NULL`<br><br>서브프로그램이 시작될 때, 형식 매개변수는 실제 매개변수의 값에 관계없이 초기값을 갖습니다. Oracle은 서브프로그램에서 형식 매개변수에 값을 지정할 것을 권장합니다. | 형식 매개 변수 유형의 기본값이 인 경우, 실제 매개 변수는 데이터 형식이 로 정의되지 않은 변수여야 합니다. `NULL``NOT``NULL` | 기본적으로 실제 매개 변수는 값으로 전달됩니다. 를 지정하면 참조로 전달될 수 있습니다. `NOCOPY`       |
| 인 아웃(IN OUT) | 형식 매개변수는 초기화된 변수처럼 작동합니다: 서브프로그램이 시작될 때, 그 값은 실제 매개변수의 값입니다. Oracle은 서브프로그램에서 해당 값을 업데이트할 것을 권장합니다.                                                                                                                                                                                                                                              | 실제 매개 변수는 변수여야 합니다(일반적으로 문자열 버퍼 또는 숫자 누산기).                                     | 기본적으로 실제 매개 변수는 값으로 전달됩니다(양방향으로). 를 지정하면 참조로 전달될 수 있습니다.`NOCOPY` |


out 형태로 받는걸 권장하는이유 -> 마이바티스나 jpa 사용시 함수로 받게 되면 어려움이 있다. 

### `PL/SQL` TABLE 리턴
### 사용자 정의 데이터 타입
```PLSQL
CREATE OR REPLACE PACKAGE PACKAGE_EMP_TYPES 
IS TYPE EMP_SAL_TAB_TYPE IS TABLE OF EMP.SAL%TYPE 
INDEX BY PLS_INTEGER;
END;
/
```


```PLSQL 
   create or replace procedure proc1
    (p_deptno  in  emp.deptno%type, 
     p_sal_tab out pack_emp_types.emp_sal_tab_type)
    is
    begin
      select sal bulk collect into p_sal_tab
      from emp
      where deptno = p_deptno;
    end;
    /

    show errors

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
```

```PLSQL
    create or replace function func1
    (p_deptno in emp.deptno%type) 
     return pack_emp_types.emp_sal_tab_type
    is
      p_sal_tab pack_emp_types.emp_sal_tab_type;
    begin
      select sal bulk collect into p_sal_tab
      from emp
      where deptno = p_deptno;

      return p_sal_tab;
    end;
    /

    show errors

    create or replace procedure function_test_drive(p_deptno emp.deptno%type)
    is
      sal_tab pack_emp_types.emp_sal_tab_type;
    begin
      sal_tab := func1(p_deptno);

      for i in sal_tab.first .. sal_tab.last loop
        dbms_output.put_line(sal_tab(i));
      end loop;
    end;
    /

    exec function_test_drive(30)
```

```PLSQL
CREATE OR REPLACE PROCEDURE PROC1 (
	P_DEPTNO IN EMP.DEPTNO%TYPE
	
) IS
BEGIN 
SELECT EMPNO, ENAME, SAL, JOB BULK COLLECT INTO 
FROM EMP 
WHERE DEPTNO = P_DEPTNO;
END;
/
```


# JDBC INTRODUCTION
THE JDBC API IS A JAVA API


  - Establishing a connection
  - Create a statement
  - Execute the query
  - Process the ResultSet object
  - Close the connection


* Statement 
* PreparedStatement 
* CallableStatement 

# Executing Queries
  .executeUpdate(sql) : Return int, Normally for DML like INSERT, UPDATE, DELETE, MERGE
  .executeQuery(sql)  : Return ResultSet, Run SELECT query and return a ResultSet
  .execute(sql)       : Return boolean, Normally for DDL like CREATE or DROP
  .executeBatch()     : Return int[], Run SQL commands as a batch


# JDBC를 이용한 데이터베이스 연동 순서

  - 프로젝트 생성

  - 프로젝트에 lib 폴더 생성

  - ojdbc8.jar(jdbc driver) 파일 복사해서 lib 폴더에 붙여넣기

    C:\app\사용자명\product\21c\dbhomeXE\jdbc\lib\ojdbc8.jar

    -> jdbc driver? DBMS를 만든 회사에서 만든 translator!

  - 프로젝트의 library에 ojdbc8.jar 파일 추가

  - 프로젝트의 Java Build Path 속성 설정

# JDBC OCI DRIVER 
 
 - JDBC OCI Driver 사용시 connect-time load balancing 및 connect-time failover를 손쉽게 구현할 수 있음
  - JDBC OCI Driver 사용을 위해 tnsnames.ora 파일에 Network alias 추가
  
> C:\Users\user> cd C:\app\사용자명\product\21c\dbhomeXE\network\admin
  C:\app\KOSA\product\21c\homes\OraDB21Home1\network\admin\sqlnet.ora> notepad tnsnames.ora

아래와 같이 Network alias를 추가하세요
```bash
mydb =
  (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))
    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1522))
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = xepdb1)
    )
  )
```




## JdbcConnection.java
```java
package com.kosa;  
  
import java.sql.DatabaseMetaData;  
import java.sql.SQLException;  
import java.sql.Connection;  
  
import oracle.jdbc.pool.OracleDataSource;  
  
/**  
 * packageName    : com.kosa * fileName       : JdbcConnection * author         : Yeong-Huns * date           : 2024-04-16 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-16        Yeong-Huns       최초 생성  
 */  
public class JdbcConnection {  
    @SuppressWarnings("unused")  
    public static void main(String args[]) throws SQLException {  
  
        OracleDataSource ods = new OracleDataSource();  
  
        /* Thin driver */  
  
        // 1        ods.setURL("jdbc:oracle:thin:@localhost:1521/xepdb1");  
        ods.setUser("ace01");  
        ods.setPassword("me");  
        Connection conn1 = ods.getConnection();  
  
        DatabaseMetaData meta = conn1.getMetaData();  
        System.out.println("JDBC driver version is " + meta.getDriverVersion());  
        System.out.println("1성공");  
        // 2  
        ods.setURL("jdbc:oracle:thin:ace01/me@localhost:1521/xepdb1");  
        Connection conn2 = ods.getConnection();  
        System.out.println("2성공");  
        /* Oracle Call Interface (OCI) driver */  
  
        // 3        ods.setURL("jdbc:oracle:oci8:@mydb");  
        ods.setUser("ace01");  
        ods.setPassword("me");  
        Connection conn3 = ods.getConnection();  
        System.out.println("3성공");  
  
        // 4  
        ods.setURL("jdbc:oracle:oci8:ace01/me@mydb");  
        Connection conn4 = ods.getConnection();  
        System.out.println("4성공");  
  
  
        /* 설정 파일 + 싱글턴 패턴 활용 접속 */        Connection conn5 = DBConnection.getConnection();  
  
  
    }  
  
}
```

## Oracle.properties
```ini
driver=oracle.jdbc.driver.OracleDriver  
url=jdbc:oracle:thin:@localhost:1521/xepdb1  
user=ace01  
password=me
```

## DBConnection.java
```java
package com.kosa;  
  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.Reader;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.util.Properties;  
  
/**  
 * packageName    : com.kosa * fileName       : DBConnection * author         : Yeong-Huns * date           : 2024-04-16 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-16        Yeong-Huns       최초 생성  
 */  
public class DBConnection {  
    private static Connection conn;  
  
    private DBConnection() {  
    }  
    static {  
        // 환경설정 파일을 읽어오기 위한 객체 생성  
        Properties properties  = new Properties();  
        Reader reader;  
        try {  
            reader = new FileReader("lib/oracle.properties");  // 읽어올 파일 지정  
            properties.load(reader);                           // 설정 파일 로딩하기  
        } catch (FileNotFoundException e1) {  
            System.out.println("예외: 지정한 파일을 찾을수없습니다 :" + e1.getMessage());  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        String driverName = properties.getProperty("driver");  
        String url = properties.getProperty("url");  
        String user = properties.getProperty("user");  
        String pwd = properties.getProperty("password");  
  
        try {  
            Class.forName(driverName);  
            conn = DriverManager.getConnection(url, user, pwd);  
            System.out.println("connection success");  
            System.out.println("DB커넥션 성공!");  
        } catch (ClassNotFoundException e) {  
            System.out.println("예외: 드라이버로드 실패 :" + e.getMessage());  
            e.printStackTrace();  
        } catch (SQLException e) {  
            System.out.println("예외: connection fail :" + e.getMessage());  
            e.printStackTrace();  
        }  
    }  
  
    public static Connection getConnection() {  
        return conn;  
    }  
}
```

## JdbcTestSelect.java
```java
package com.kosa;  
  
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
/**  
 * packageName    : com.kosa * fileName       : JdbcTestSelect * author         : Yeong-Huns * date           : 2024-04-16 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-16        Yeong-Huns       최초 생성  
 */  
public class JdbcTestSelect {  
    public static void main(String args[]) throws SQLException {  
  
        Connection conn = null;  
        Statement stmt = null;  
        ResultSet rset = null;  
  
        conn = DBConnection.getConnection();  
  
        try {  
            stmt = conn.createStatement();  
            rset = stmt.executeQuery("SELECT employee_id, first_name FROM employees");  
  
            while (rset.next()) {  
                System.out.print(rset.getInt(1) + " ");  
                System.out.println(rset.getString(2));  
            }  
        }  
  
        finally {  
            if (rset != null)  
                rset.close();  
            if (stmt != null)  
                stmt.close();  
            if (conn != null)  
                conn.close();  
        }  
    }  
}
```

## JdbcTestInsert.java
> 📌먼저 hd 유저로 접속해서 테이블을 생성!

```sql
drop table t1 cascade constraints purge;
create table t1 as select employee_id, first_name from employees;
```

```java
package com.kosa;  
  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
/**  
 * packageName    : com.kosa * fileName       : JdbcTestInsert * author         : Yeong-Huns * date           : 2024-04-16 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-16        Yeong-Huns       최초 생성  
 */  
public class JdbcTestInsert {  
    public static void main(String args[]) throws SQLException {  
  
        Connection conn = null;  
        Statement stmt = null;  
        PreparedStatement pstmt = null;  
  
        conn = DBConnection.getConnection();  
        conn.setAutoCommit(false);  
  
        try {  
            pstmt = conn.prepareStatement("insert into t1 (EMPLOYEE_ID, FIRST_NAME) values (?, ?)");  
            stmt = conn.createStatement();  
  
            pstmt.setInt(1, 1500);  
            pstmt.setString(2, "LESLIE");  
            pstmt.execute();  
            conn.commit(); // COMMIT 하지않으면 확정안됨  
            //stmt.executeUpdate("truncate table t1");  
  
            pstmt.setInt(1, 507);  
            pstmt.setString(2, "MARSHA");  
            pstmt.execute();  
            conn.commit();  
            //stmt.executeUpdate("truncate table t1");  
  
            System.out.println("입력 성공!");  
        } finally {  
  
            if (pstmt != null)  
                pstmt.close();  
        }  
    }  
}
```

# DAO, VO(DTO) 활용하기 
```sql
 drop table member cascade constraints purge;

  create table member
  (id     varchar2(10) primary key,
   name   varchar2(10),
   height number(5),
   weight number(5),
   age    number(5));

  insert into member values('001', 'Peter', 175, 67, 24);
  insert into member values('002', 'Diana', 188, 78, 31);
  insert into member values('003', 'Jennifer', 165, 48, 17);
  insert into member values('004', 'Bruce', 177, 78, 23);

  commit;

```

## MemberVO
```java
package com.kosa;  
  
import lombok.Data;  
import lombok.Getter;  
import lombok.Setter;  
  
/**  
 * packageName    : com.kosa * fileName       : MemberVO * author         : Yeong-Huns * date           : 2024-04-16 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-16        Yeong-Huns       최초 생성  
 */  
@Data  
@Getter  
public class MemberVO {  
    private String id;  
    private String name;  
    private int height;  
    private int weight;  
    private int age;  
}
```

## MemberDAO
```java
package com.kosa;  
  
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.Statement;  
import java.util.ArrayList;  
  
/**  
 * packageName    : com.kosa * fileName       : MemberDAO * author         : Yeong-Huns * date           : 2024-04-16 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-16        Yeong-Huns       최초 생성  
 */  
public class MemberDAO {  
    private Connection conn = DBConnection.getConnection();  
    private Statement stmt;  
    private ResultSet rs;  
  
    public ArrayList<MemberVO> list() {  
        ArrayList<MemberVO> list = new ArrayList<MemberVO>();  
        try {  
            String query = "select * from member";  
            System.out.println(query);  
            stmt = conn.createStatement();  
            rs = stmt.executeQuery(query);  
  
            while (rs.next()) {  
                String id = rs.getString("id");  
                String name = rs.getString("name");  
                int height = rs.getInt("height");  
                int weight = rs.getInt("weight");  
                int age = rs.getInt("age");  
  
                MemberVO data = new MemberVO();  
  
                data.setId(id);  
                data.setName(name);  
                data.setHeight(height);  
                data.setWeight(weight);  
                data.setAge(age);  
  
                list.add(data);  
            }  
            rs.close();  
            stmt.close();  
            conn.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return list;  
    }  
  
  
}
```

## MemberTest.java
```java
package com.kosa;  
  
import java.util.ArrayList;  
  
/**  
 * packageName    : com.kosa * fileName       : MemberTest * author         : Yeong-Huns * date           : 2024-04-16 * description    : * =========================================================== * DATE              AUTHOR             NOTE * ----------------------------------------------------------- * 2024-04-16        Yeong-Huns       최초 생성  
 */  
public class MemberTest {  
    public static void main(String[] args) {  
        MemberDAO dao = new MemberDAO();  
        ArrayList<MemberVO> list = dao.list();  
  
        for (int i = 0; i < list.size(); i++) {  
            MemberVO data = (MemberVO) list.get(i);  
            String id = data.getId();  
            String name = data.getName();  
            int height = data.getHeight();  
            int weight = data.getWeight();  
            int age = data.getAge();  
  
            System.out.println("아이디는>>" + id +  
                    " 이름은>>" + name +  
                    " 키는>>" + height +  
                    " 몸무게는>>" + weight +  
                    " 나이는>>" + age);  
        }  
    }  
}
```