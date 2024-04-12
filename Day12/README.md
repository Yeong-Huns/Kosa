# DBMS
>📌 **D**ata**B**ase**M**anagement**S**ervice(DBMS)

## Data 관련 용어 이해
* Data(복수) vs Datum(단수)

IT 란 ? 
-All about Data 

AI 를 다루는 문맥에서 `Data` 는 `경험`을 형상화한 무엇으로 생각해도 좋음

정보 -> 나에게 유의미한 데이터

데이터 : 원석
정보 : 원석을 가공한 무언가

### Data Modeling
*  챙겨두면 돈이 될 것 같은 데이터를 선별 정리하는 것 (데이터를 형상화 한 것)
-> 현실에 있는 수많은 데이터 중에 나에게 필요한 것 선별

IT : 데이터를 Infomation 으로 바꾸는데 필요한 모든 기술 ?
All about Data   

### Data Analysis 
* 자료 분석


### Data Type
* (자료 형) : 문자, 숫자, 숫자, 날짜, 이미지

### Data Structure (자료 구조)
* `Table`, `Tree`, `Data` ...
* `Data Base`,` Data Warehouse`, `Data Mart`, `Data Store`, `Date Lake`, `Data Dam`

`DataBase` : 가장 작은 데이터 모음 단위 - 데이터가 들어가 있는 기지

`Data Warehouse` : `DataBase`를 모아둠

`Data Mart` : 특정 정보만 모아두는 `DataBase`

`Data Store` : 그저 데이터를 보관하는 공간

`Data Lake` : 데이터를 모으되, 바로 이용할 수 없는 , 어느 정도의 가공이 필요한 데이터를 모아둠 

`Data Dam`  :  Data 로 댐처럼 발전한다는 의미로 만들어진 용어

# SQL Programming

## 목표 

`SQL` : DDL, DML, TCL, DCL
Object : Table, Index, View, Sequence, Snonym  
Tools : SQL Developer, SQL\*Plus

* [참고 영상](https://cafe.naver.com/gsinternet/2)

## 교재 
* [Oracle Database Tutorial](https://www.oracletutorial.com/)
* [Do it! 오라클로 배우는 데이터베이스 입문](https://www.yes24.com/Product/Goods/65849798)

## 실습 환경
 - [Live SQL](https://livesql.oracle.com/)

    ~ 로그인해서 사용하면 됨
    ~ 생성하는 테이블과 데이터 등은 로그아웃하면 사라짐
    ~ 문법 연습 용도로 적합함
    ~ 제공되는 학습자료도 훌륭함

* [Oracle Cloud](https://www.oracle.com/kr/cloud/free/)

    ~ 가입한 뒤 Database 간단하게 생성할 수 있음
    ~ 생성한 Database에 대해 약간의 설정을 더하면 됨


  - [Oracle XE](https://www.oracle.com/kr/database/technologies/appdev/xe.html)

   ~ 설치 전 확인사항 : https://leean-arc.tistory.com/2
   ~ 다운로드 및 설치 : https://0pen3r.tistory.com/147   -> sys 유저 암호는 oracle로 할 것!

|         | DMS                              | DBMS                      |
| ------- | -------------------------------- | ------------------------- |
| 뜻       | **Document**를 만들고 관리하기 위한 소프트 웨어 | **DB**를 만들고 관리하기 위한 소프트웨어 |
| 사용 난이도  | 쉽다.                              | 어렵다.                      |
| 유실시 리스크 | 데이터가 유실되면 아쉽다.                   | 회사가 망할 수 있다.              |
| 최종 결과물  | 파일                               | 파일                        |
| 난이도     | 쉽다.                              | 어렵다.                      |
>📌본질적으로 차이가 없다 

Database : ~.dbf , ~.ibd


Database Server = Database + Instance
		_ Datafile_ -System Global Area
		_redo log file _ s-Background Process
		-control file 



![[Database_Instance.excalidraw]]

오라클이 외부에서 접속하기 위해서 리스너를 만들었다.
외부에서 접속시 리스너를 통해 접속가능 

oracle 11g <-> oracle12C 이상 사용

listener


## SQL (Structured Query Language)
### Query? 
* A **query** is a question, expercially one that you ask an organization, publication, or Expert.
데이터베이스에 질의(묻고 응답을 받음)

* MongoDB Query language (MQL) 
* Cassabdra Qyery language (CQL)
등의 여러 Query Language 가 존재재

>📌 쿼리 종류는 많지만 가장 대중적인 건 SQL (Structured Query Language)


Structured : 잘 짜여진 

 

| 종류                                    | 설명                                                  |
| ------------------------------------- | --------------------------------------------------- |
| **DQL**(Data Query Language)          | RDBMS에 저장한 데이터를 원하는 방식으로 조회하는 명령어                   |
| **DML**(Data Manipulation Language)   | RDBMS 내 테이블의 데이터를 저장 , 수정, 삭제하는 명령어                 |
| **DDL**(Data Definition Language)     | RDBMS 내 데이터 관리를 위해 테이블을 포함한 여러 객체를 생성, 수정, 삭제하는 명령어 |
| **TCL**(Transaction Control Language) | 트랜잭션 데이터의 영구 저장, 취소 등과 관련된 명령어                      |
| **DCL**(Data Control Language)        | 데이터 사용 권한과 관련된 명령어                                  |


- SQL 분류

| 이름     | 기능                                             |
| ------ | ---------------------------------------------- |
| D(S)DL | CREATE, ALTER, DROP, RENAME, TRUNCATE, COMMENT |
| DML    | INSERT, UPDATE, DELETE, MERGE, SELECT          |
| TCL    | COMMIT, ROLLBACK, SAVEPOINT (Transaction)      |
| DCL    | GRANT, REVOKE                                  |

---

# 실습 환경설정
## Oracle XE
* [Oracle XE](https://www.oracle.com/kr/database/technologies/appdev/xe.html)
* [설치 전 확인사항](https://leean-arc.tistory.com/2)
* [다운로드 및 설치](https://0pen3r.tistory.com/147)

```sql
DROP TABLE BOOKS PURGE; -- 테이블 바로삭제

PURGE RECYCLEBIN; -- 휴지통 비우기

CREATE TABLE BOOKS --테이블 생성
(ID NUMBER(4),
AUTHOR VARCHAR2(30));

SELECT * FROM tab;

INSERT INTO BOOKS VALUES(1001, '강안수'); -- 테이블 INSERT
INSERT INTO BOOKS VALUES(1002, '이경훈');

SELECT * FROM BOOKS;-- 테이블 READ
```



## 설치 뒤 수행할 작업
1. 윈도우 서비스 창을 열어서 `Oracle`로 시작하는 서비스들을 모두 수동 시작으로 수정할 것

2. Oracle 서비스 관리용 스크립트 파일 작성 및 활용 테스트
```cmd
notepad xe_stop.bat

//메모장
net stop OracleServiceXE
net stop OracleOraDB21Home1TNSListener
// -> 오라클 서비스XE 종료 -> 오라클 리스너 종료
(저장)

notepad xe_start.bat

//메모장
net start OracleOraDB21Home1TNSListener 
net start OracleServiceXE 
//-> 오라클 리스너 실행 -> 오라클 서비스 실행 (종료의 역순)
(저장)

서버 종료 ->
xe_stop.bat
서버 실행 ->
xe_start.bat
```


```sql
user23@아무거나
사용자 이름 : user23
비밀번호 : 아무거나
클라우드 전자 지갑 
```

## Oracle XE 서버에 관리자로 접속해서 사용자생성 및 권한 부여

### SQL PLUS 를 이용할 경우

```markdown
  (1) SQL*Plus 이용하는 경우 : https://hec-ker.tistory.com/97

        C:\Users\user> where sqlplus

        C:\Users\user> sqlplus / as sysdba                            
        <- 절대 이렇게 접속하지 마세요. CDB에 접속하는 것입니다.
        SQL> show con_name
        SQL> exit

        C:\Users\user> sqlplus system/oracle                          
        <- 절대 이렇게 접속하지 마세요. CDB에 접속하는 것입니다.
        SQL> show con_name
        SQL> exit
 
        C:\Users\user> sqlplus system/oracle@localhost:1521/xepdb1    
        <- 반드시 이렇게 접속하세요. PDB에 접속하는 것입니다.

```

```sql
SHOW CON_NAME;
SHOW USER;
DROP USER ACE01 CASCADE;

CREATE USER ACE01
IDENTIFIED BY ME 
QUOTA 100M ON USERS;
-- 계정명 : ACE01 암호 : ME

GRANT CREATE SESSION
TO ACE01;

GRANT CREATE TABLE, CREATE PROCEDURE, CREATE VIEW
TO ACE01;
-- ACE01 에게 권한수여

EXIT;
```

### SQL Developer 이용하는 경우
![[Pasted image 20240412191846.png]]

![[Pasted image 20240412191859.png]]

```SQL
SHOW user;


select * from dba_users; -- 관리자 계정만 가능 (만든계정확인)

drop user ace02 cascade;

create user ace02
IDENTIFIED by me
QUOTA 100m ON users; 

GRANT CREATE SESSION, CREATE TABLE, CREATE PROCEDURE, CREATE VIEW TO ace02;

```

## Oracle XE 서버에 일반 사용자로 접속
```SQL
CONN ACE02/me@localhost:1521/xepdb1 -- 반드시 이렇게 접속

DROP TABLE T1 PURGE;
CREATE TABLE T1(NO NUMBER);

SELECT * FROM TAB; 
DESC T1
EXIT
```

---
# PL/SQL 
> 💡PL/SQL 은 SQL 만으로는 구현이 어렵거나 구현 불가능한 작업을 수행하기 위해 오라클에서 
> 제공하는 프로그래밍 언어이다. 
> 변수, 조건 처리, 반복 처리 등 다른 프로그래밍 언어에서도 제공하는 다양한 기능을 사용할 수 있다.

## PL/SQL 구조
### 블록이란? 
>📌 `PL/SQL` 은 데이터베이스 관련 특정 작업을 수행하는 명령어와 실행에 필요한 여러 요소를 정의하는 명령어 등으로 구성되며, 이러한 명령어를 모아 둔 `PL/SQL` 프로그램의 기본 단위를 `블록`이라 한다.

| 구성 키워드            | 필수/선택 | 설명                                     |
| ----------------- | ----- | -------------------------------------- |
| DECLARE(선언부)      | 선택    | 실행에 사용될 변수 , 상수, 커서 등을 선언              |
| BEGIN(실행부)        | 필수    | 조건문, 반복문 , SELECT, DML 함수 등을 정의        |
| EXCEPTION(예외 처리부) | 선택    | PL/SQL 실행 도중 발생하는 오류(예외상황)을 해결하는 문장 기술 |

```SQL
DECLARE 
[실행에 필요한 여러 요소 선언];
BEGIN 
[작업을 위해 실제 실행하는 명령어];
EXCEPTION 
[PL/SQL 수행 도중 발생하는 오류 처리];
END;
```

> 💡선언부(`DECLARE`)와 예외 처리부(`EXCEPTION`) 는 **생략 가능**하지만, 실행부(`BEGIN`)은 
> 반드시 존재해야 한다. 
> 필요에 따라 `PL/SQL` 블록 안에 다른 블록을 포함할 수 있다.
> 이를 **중첩 블록**(`Nested block`)이라 한다.

### Hello, PL/SQL 출력
> 💡`PL/SQL` 실행 결과를 화면에 출력하기 위해선 `SERVEROUTPUT` 환경 변수 값을 `ON`으로 
> 변경해 주어야한다. 
> `PUT_LINE`은 화면 출력을 위해 오라클에서 기본으로 제공하며, `DBMS_OUTPUT` 패키지에 속해 있다.
> 마지막엔 `/` 잊지말자.

```SQL
-- Hello World 출력하기 예제
SET SERVEROUTPUT ON; 
-- 실행 결과 화면에 출력 설정 ON

BEGIN 
	DBMS_OUTPUT.PUT_LINE('Hello, World!');
END;
/

-- 선언부, 예외처리부 생략
```

> 1. `PL/SQL` 블록을 구성하는 `DECLARE, BEGIN, EXCEPTION` 키워드에는 세미클론(;) 사용 x
> 2. `PL/SQL` 블록의 각 부분에서 실행해야 하는 문장 끝에는 세미클론(;) 사용
>    EX) DBMS_OUTPPUT.PUT_LINE('HELLO, PL/SQL');
> 3.  PL/SQL 문 내부에서 한 줄 주석(--)과 여러 줄 주석 (/*  ~ */)을 사용할 수 있다. 
>    이들 주석은 SQL 문 에서도 사용할 수 있다.
> 4. PL/SQL 작성을 마치고 실행하기 위해선 마지막에(/ )를 사용해야한다.

### PL/SQL 주석
| 종류      | 사용 기호            | 설명                             |
| ------- | ---------------- | ------------------------------ |
| 한 줄 주석  | `--[주석 처리 내용]`   | 현재 줄만 주석 처리된다.                 |
| 여러 줄 주석 | `/*[주석 처리 내용]*/` | `/* 에서 */`까지 여러 줄에 걸쳐 주석 처리된다. |
```PLSQL

DECLARE -- 선언부
V_EMPNO NUMBER(4) := 7788; -- 변수명 : V_EMPNO, 타입 : NUMBER(길이) = 7788;
V_ENAME VARCHAR2(10); -- 변수명 : V_ENAME, 타입 : VARCHAR2(길이);

/*
VARCHAR 와 VARCHAR2 의 차이가 뭐지? 
-> 현재는 완전 동일한 기능이지만 VARCHAR2 사용 강력권장!!!!  
WHY? -> VARCHAR 데이터 타입은 추후 ORACLE이 변경 예정이라함 
*/

BEGIN -- 실행부
    V_ENAME := 'SCOTT'; 
    -- V_NAME, 타입 : VARCHAR2(10) = 'SCOTT' 
    -- DBMS_OUTPUT.PUT_LINE('V_EMPNO: '|| V_EMPNO);
    DBMS_OUTPUT.PUT_LINE('V_ENAME: '|| V_ENAME);
END;
/

/*
결과 예상 : 선언부(DECLARE)에서 선언한 변수를 실행부(BEGIN) 에서 
        DBMS_OUTPUT.PUT_LINE() 으로 출력하는 구조
        V_EMPNO = 7788, V_ENAME = SCOTT(실행부에 지정)
        주석처리된 V_EMPNO 는 출력되지 않고 SCOTT만 출력될듯?
*/
```
### 결과
```SQL

V_ENAME: SCOTT


PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

> 💡`PL/SQL`에서 여러 명령어를 모아 둔 프로그램의 기본 단위를 `블록`이라 한다.
> 기본 `PL/SQL` `블록`은 실행에 사용할 변수 , 상수, 커서 등을 선언하는 `DECLARE`와 
> 조건문, 반복문, SELECT, DML, 함수 등 실제 수행할 기능부를 정의하는 `BEGIN` , 
> 그리고 `PL/SQL`으로 제작한 프로그램의 실행 도중 발생하는 오류를 처리하는 `EXCEPTION` 으로 
> 구성된다.
> 

## 변수와 상수
### 변수 선언과 값 대입하기
> 📌변수`Variable`는 데이터를 일시적으로 저장하는 요소로 이름과 저장할 자료형을 지정하여 
> 선언부`Declare`에서 작성한다. 선언부에서 작성한 변수는 실행부`BEGIN` 에서 활용한다.

### 형식
```SQL
(1)변수 이름 (2)자료형 (3):= (4)값 또는 값이 도출되는 여러 표현식;
```

| 번호  | 설명                                                                                            |
| --- | --------------------------------------------------------------------------------------------- |
| 1   | 데이터를 저장할 변수 이름을 지정한다. 이 변수 이름을 통해 저장한 데이터를 사용                                                 |
| 2   | 선언한 변수에 저장할 데이터의 자료형을 지정한다.                                                                   |
| 3   | 선언한 변수에 값을 할당하기 위해 :=  를 사용한다. 이 기호는 오른쪽 값을 <br>왼쪽에 대입하겠다는 뜻. 값을 할당하지 않고 변수 선언만 한다면 3,4 생략 가능 |
| 4   | 값 OR 값이 결과로 반환되는 표현식 지정 (변수에 지정한 자료형과 일치해야만 함)                                                |
### 변수 선언 및 변수 값 출력력

```PLsql
DECLARE 
    V_EMPNO NUMBER(4) := 4885;
    V_ENAME VARCHAR2(10);
BEGIN
    V_ENAME := 'SMART';
    DBMS_OUTPUT.PUT_LINE('V_EMPNO : '||V_EMPNO);
    DBMS_OUTPUT.PUT_LINE('V_ENAME : '||V_ENAME);
END;
/
```

```
:: 결과화면
V_EMPNO : 4885
V_ENAME : SMART

PL/SQL 프로시저가 성공적으로 완료되었습니다.
```


>💡 `DECLARE(선언부)`, `BEGIN(실행부)`는 세미클론(;) 사용하지 않음, 
>`||`연산자는 데이터 사이를 연결하여 출력할 때 사용

### 내가 이해한 내용

```JAVA
//선언부
int V_EMPNO = 4885;
String V_ENAME;
//실행부
V_ENAME = "SMART";
System.out.println(V_EMPNO);
System.out.println(V_ENAME);
//종료
```

### 상수 정의하기 
>📌 변수와 달리, 한번 정의한 값이 프로그램이 종료될 때까지 유지된다. 
>기존 변수 선언에서 `CONSTANT` 키워드를 추가하면 끝.

```SQL
변수 이름 CONSTANT 자료형 := 값 또는 EXPRESSION
```

### 상수에 값을 대입한 후 출력
```PLSQL
DECLARE 
    V_TAX CONSTANT NUMBER(1) := 3;
BEGIN
    DBMS_OUTPUT.PUT_LINE('V_TAX : '||V_TAX);
END;
/
```

```
::결과 화면
V_TAX : 3
```

### 내가 이해한 내용

```JAVA
private final int V_TAX = 3;
```

### 변수의 기본값 지정하기
> 📌`DEFAULT` 키워드는 변수에 저장할 기본값을 지정한다. 

```sql
변수 이름 자료형 DEFAULT 값 또는 EXPRESSION 
```
> 💡주의
> `CONSTANT`와 선언하는 위치가 다르다.
> 그리고 `:=` 대입 연산자를 사용하지 않는다.

```PLSQL
DECLARE
    V_DEPTNO NUMBER(2) DEFAULT 10;
BEGIN 
    DBMS_OUTPUT.PUT_LINE('V_DEPTNO : '||V_DEPTNO);
END;
/
```

```
V_DEPTNO : 10
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

### 변수에 NULL 값 저장 막기
```SQL
변수 이름 자료형 NOT NULL := 또는 DEFAULT 또는 EXPRESSION
```

>💡 `NOT NULL` 뒤에 올 수 있는 것들  `:=` OR `DEFAULT` OR `EXPRESSION`

```PLSQL
DECLARE 
    V_DEPNO NUMBER(2) NOT NULL DEFAULT 10;
BEGIN
    DBMS_OUTPUT.PUT_LINE('V_DEPNO : '||V_DEPNO);
END;
/
-- 이거 := 랑 DEFAULT 랑 똑같은거 아냐? 
-- 둘 다 같은 결과 확인완료
```

```SQL
V_DEPNO : 10
PL/SQL 프로시저가 성공적으로 완료되었습니다.
```

### 변수 이름 정하기 
 📌변수를 포함한 `PL/SQL` 문에서 지정하는 객체 이름을 `식별자` 라고 한다. 
 식별자에 이름을 붙이는 규칙은 다음과 같다.

> 1. 같은 블록 안에서 식별자는 고유해야 하며 중복될 수 없다. 
> 2. 대, 소문자를 구별하지 않는다.
> 3. 테이블 이름 붙이는 규칙과 같은 규칙을 따른다. 
> 	1. 이름은 문자로 시작해야 한다.(한글가능 BUT 숫자 불가)
> 	2. 이름은 30byte 이하여야 한다.(영어30, 한글15)
> 	3. 이름은 문자, 숫자, 특수문자 사용가능
> 	4. `SQL`키워드는 테이블 이름으로 사용 불가 
> 	   EX(`SELECT`, `FROM` 사용불가)

## 변수의 자료형
### 스칼라 
> 📌`스칼라형`은 숫자, 문자열 ,날짜 등과 같이 오라클에서 기본으로 정의해 놓은 자료형 
> -> 내부 구성 요소가 없는 단일 값

| 분류     | 자료형      | 설명                                                     |
| ------ | -------- | ------------------------------------------------------ |
| 숫자     | NUMBER   | 소수점을 포함할 수 있는 최대 38자리 숫자 데이터                           |
| 문자열    | CHAR     | 최대 32,767 바이트 고정 길이 문자열 데이터                            |
| 문자열    | VARCHAR2 | 최대 32,767 바이트 가변 길이 문자열 데이터                            |
| 날짜     | DATE     | 기원전 4712년 1월 1일부터 서기 9999년 12월 31일                     |
| 논리 데이터 | BOOLEAN  | `PL/SQL`에서만 사용할 수 있는 논리 자료형<br>`TRUE`, `FALSE`, `NULL` |
### 참조형
>📌참조형`Reference type` 은 오라클 데이터베이스에 존재하는 **특정 테이블 열의 자료형**이나 
>**하나의 행 구조**를 참조하는 자료형이다.  
>열을 참조할 때 `%TYPE`, 행을 참조할 때 `%ROWTYPE`을 사용한다. 
>`%TYPE`으로 선언한 변수는 지정한 테이블의 열과 완전히 같은 자료형이 된다.

```SQL
(1)변수 이름 (2)테이블 이름.열이름(3)%TYPE;
```

| 번호  | 설명                                                                                       |
| --- | ---------------------------------------------------------------------------------------- |
| 1   | 데이터가 저장될 변수의 이름을 지정한다. 변수 이름을 통해 저장된 데이터를 사용하게 된다.                                       |
| 2   | 특정 테이블에 속한 열의 이름을 명시한다. (1)변수는 명시된 테이블의 열과 같은 크기의 자료형이 지정된다.                             |
| 3   | 앞에서 지정한 테이블의 열과 같은 자료형 및 크기임을 명시한다.<br>이후 `:=` 또는 `DEFAULT` 키워드를 사용해서 값을 먼저 지정해 줄 수도 있다. |

### 참조형(열) 의 변수에 값을 대입한 후 출력하기
```PLSQL
DECLARE 
    V_DEPTNO DEPT.DEPTNO%TYPE := 50;
BEGIN
    DBMS_OUTPUT.PUT_LINE('V_DEPTNO : '||V_DEPTNO);
END;
/
SELECT * FROM DEPT;
```

>  💡지금 선언한 `V_DEPTNO` 는 `DEPT` 테이블의 `DEPTNO 열`과 같은 형식(`NUMBER`)이다!
>  형식이 일치(`NUMBER`)하는 값인 50을 값으로 지정하겠다!

### 행 구조 전체 참조
```PLSQL
변수 이름 테이블 이름 %ROWTYPE;
```

> 💡`%TYPE` 과 달리 저장할 값을 직접 지정할 수 없다. (`:=`, `DEFATUL`)

```PLSQL

DECLARE
    V_DEPT_ROW DEPT%ROWTYPE;
BEGIN
    SELECT DEPTNO, DNAME, LOC INTO V_DEPT_ROW 
    FROM DEPT
    WHERE DEPTNO = 40;
    DBMS_OUTPUT.PUT_LINE('DEPTNO : '||V_DEPT_ROW.DEPTNO);
    DBMS_OUTPUT.PUT_LINE('DNAME : '||V_DEPT_ROW.DNAME);
    DBMS_OUTPUT.PUT_LINE('LOC : '||V_DEPT_ROW.LOC);
END;
/

```

![[Pasted image 20240412215644.png]]
> `DEPT` 테이블의 구조

> 💡해설
>  1. `V_DEPT_ROW` 변수를 `DEPT` 테이블의 행 구조로 선언한다. 이제 `V_DEPT_ROW` 변수는 내부에
> `DEPTNO, DNAME, LOC` 필드를 가지게 된다!
> 2. `INTO V_DEPT_ROW` 를 사용하여 `DEPT` 테이블의 `SELECT` 문 결과를 `V_DEPT_ROW`에 대입한다!
>    // 이게 진짜 맞았네...
> 3. `V_DEPT_ROW`가 소유한 필드 개수 및 자료형과 `SELECT` 문 결과 열의 개수와 자료형은 같아야 한다.

