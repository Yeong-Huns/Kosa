


> 💡 `SYSTEM` 유저를 이용한 작업
> * 계정명 : 
> * 암호 : `보안` -> 불편하게 만드는 게 보안
> * 권한 : 최소 권한의 원칙
> * 공간사용 : 최소 공간의 원칙
> * 프로파일 : 

### 사용자 정보 조회 명령문

```PLSQL
--계정확인
SELECT * 
FORM DBA_USERS
ORDER BY ACCOUNT_STATUS DESC, USERNAME;
```

### 데이터베이스에 있는 데이터 파일에 대한 정보 제공

```PLSQL
--시스템 유저로 접속
--공간 확인
SELECT * 
FROM DBA_DATA_FILES;
```

### 권한확인

```PLSQL
--권한확인
SELECT DISTINCT PRIVILEGE
FROM DBA_SYS_PRIVS
ORDER BY 1;
```

### ROLES 확인

```PLSQL
--그룹별로 역할을 나누어 권한 관리
SELECT * FROM DBA_ROLES;
```

### DBA_PROFILE 확인

```PLSQL
--
SELECT * FROM
DBA_PROFILES
ORDER BY profile, RESOURCE_TYPE;
```

```PLSQL
결과::
DEFAULT	CONNECT_TIME	KERNEL	UNLIMITED
DEFAULT	PRIVATE_SGA	KERNEL	UNLIMITED
DEFAULT	LOGICAL_READS_PER_CALL	KERNEL	UNLIMITED
DEFAULT	LOGICAL_READS_PER_SESSION	KERNEL	UNLIMITED
DEFAULT	CPU_PER_CALL	KERNEL	UNLIMITED
DEFAULT	CPU_PER_SESSION	KERNEL	UNLIMITED
DEFAULT	SESSIONS_PER_USER	KERNEL	UNLIMITED
DEFAULT	COMPOSITE_LIMIT	KERNEL	UNLIMITED
DEFAULT	IDLE_TIME	KERNEL	UNLIMITED
DEFAULT	PASSWORD_REUSE_TIME	PASSWORD	UNLIMITED
DEFAULT	PASSWORD_LIFE_TIME	PASSWORD	180
DEFAULT	PASSWORD_VERIFY_FUNCTION	PASSWORD	NULL
DEFAULT	INACTIVE_ACCOUNT_TIME	PASSWORD	365
DEFAULT	PASSWORD_REUSE_MAX	PASSWORD	UNLIMITED
DEFAULT	PASSWORD_GRACE_TIME	PASSWORD	7
DEFAULT	FAILED_LOGIN_ATTEMPTS	PASSWORD	10
DEFAULT	PASSWORD_LOCK_TIME	PASSWORD	1
DEFAULT	PASSWORD_ROLLOVER_TIME	PASSWORD	0
```


---

# User, Privilege, Role, Profile

## `SYSTEM USER` 

```PLSQL
--(필요시)테이블 스페이스 생성
SELECT NAME FROM V$DATAFILE;
CREATE TABLESPACE MYTBS
DATAFILE 'C:\APP\KOSA\PRODUCT\21C\ORADATA\XE\XEPDB1\MYTBS01.DBF' SIZE 100M;



DECLARE 
V_PATH VARCHAR2(100); 
BEGIN
SELECT SUBSTR(NAME, 1, INSTR(NAME, '\', -1) ) AS PATH
INTO V_PATH
FROM V$DATAFILE
WHERE NAME LIKE '%SYSTEM01%';
DBMS_OUTPUT.PUT_LINE(V_PATH);
EXECUTE IMMEDIATE 'CREATE TABLESPACE MYTBS DATAFILE'''||V_PATH||'MYTBS01.DBF'' SIZE 100M';
END;
/

```

```PLSQL
--(2)필요시 ROLE 생성
DROP ROLE DEV_ROLE;
CREATE ROLE DEV_ROLE;
GRANT CREATE SESSION, CREATE TABLE 
TO DEV_ROLE;
```

```PLSQL
-- 권한(SELECT) 수여 
GRANT SELECT 
ON ACE01.EMP
TO DEV_ROLE;
```

```PLSQL
-- (4)필요시 PROFILE 생성
CREATE PROFILE DEV_PROFILE LIMIT
FAILED_LOGIN_ATTEMPTS 3
SESSIONS_PER_USER 3;
-- 사용자가 동시에 생성할 수 있는 세션(session)의 최대 수를 제한
```

* [PROFILE 참조](https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/CREATE-PROFILE.html)


```PLSQL
-- USER 생성
    CREATE USER TIME
    IDENTIFIED BY INOUT
    DEFAULT TABLESPACE USERS
    QUOTA 5M ON USERS
    QUOTA UNLIMITED ON MYTBS
    PROFILE DEV_PROFILE
    PASSWORD EXPIRE; -- 비밀번호 만기옵션
    
    -- TIME 계정에 DEV_ROLE 부여
    GRANT DEV_ROLE
    TO TIME;
```

## `TIME USER`
```SQL
--SQLPLUS 접속
TIME/INOUT@LOCALHOST:1521/XEPDB1;

--비밀번호가 만기되었습니다.
--새로운 비밀번호 입력

SHOW USER ;
CREATE TABLE T1 (NO NUMBER);
INSERT INTO T1 VALUES(1000);
INSERT INTO T1 VALUES(2000);
COMMIT;
```

```PLSQL
--다른 유저에게 권한부여
GRANT SELECT, INSERT 
ON T1 
TO ACE02, ACE03; 

SELECT * FROM TAB;
```

> 💡 `T1` 테이블에 대한 권한을 `ACE02`, `ACE03` 유저에게 준다.

## `ACE02 USER`

```PLSQL
-- ACE02 유저로 접속
show user ;
select tname from tab; 
select * from time.t1;
```

```PLSQL
결과::
1000
2000
```


# Getting Started with Oracle Database
* [Getting Started](https://www.oracletutorial.com/getting-started/)
* [Download Oracle Sample Database](https://www.oracletutorial.com/getting-started/oracle-sample-database/)
* [Create Oracle Sample Database](https://www.oracletutorial.com/getting-started/create-oracle-sample-database-for-practice/)
* [Connect To Oracle Database Server](https://www.oracletutorial.com/getting-started/connect-to-oracle-database/)

# 관계형 데이터베이스란 -> Tabular 데이터베이스

  
  > 📌 `relational database`
  > 자료구조(표) + 입출력 명령(SQL) : Oracle, MySQL, PostgreSQL, ...

> 📌 `non-relational database`
> 자료구조(표 및 이외) + 입출력 명령(SQL 및 이외) : MongoDB, Cassandra, Redis, ...

* [DB-Engines Ranking](https://db-engines.com/en/ranking)


---

# Oracle Basics
* [Oracle Basics](https://www.oracletutorial.com/oracle-basics/)



```plsql
--테이블 롤백 정보
select * from dba_rollback_segs;
```

---
# Understanding SQL concepts 

 > 📌 SQL(QUERY Language)
 > * `QUERY` : A query is a question, especially one 
 >           that you ask an organization, publication, or expert.
 > * 질의 : 전문가에게 물어봄
 > * 집합적 사고 : SQL, R, NumPy, pandas, ...



> 📌 [sql](https://en.wikipedia.org/wiki/SQL)
> *  History	
> * Standardization history
> * Syntax
> * Procedural extensions

### SQL 분류
| 분류       | 종류                                                                              |
| -------- | ------------------------------------------------------------------------------- |
| `D(S)DL` | CREATE, ALTER, DROP, RENAME, TRUNCATE, COMMENT                                  |
| `DML`    | INSERT, UPDATE, DELETE, MERGE, SELECT                                           |
| `TCL`    | COMMIT, ROLLBACK, SAVEPOINT -> Transaction <br>: DML 문장의 집합 or DDL 하나 or DCL 하나 |
| `DCL`    | GRANT, REVOKE                                                                   |

*  [Graphic Syntax Diagrams](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Graphic-Syntax-Diagrams.html#GUID-D22097D5-1E7A-4A17-862A-F0084732B3CE) 

* parallel_clause
![](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/img/parallel_clause.gif)

* physical_attributes_clause
![](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/img/physical_attributes_clause.gif)

---

  
  - DELETE, TRUNCATE, DROP 비교 (feat. PURGE)

| 종류                  | storage       | rollback |
| ------------------- | ------------- | -------- |
| DELETE FROM EMP;    | 공간 반납 없음      | O        |
| TRUNCATE TABLE EMP; | 최초 공간만 남기고 반납 | X        |
| DROP TABLE EMP;     | 몽땅 반납         | X        |

```plsql
PURGE RECYCLEBIN --
DROP TABLE EMP PURGE;
```

```PLSQL
--휴지통 조회
SHOW RECYCLEBIN;

--휴지통 내부 테이블 조회
SELECT * FROM "BIN$WNQ7EjJPSmuv0l7ix/PTGQ==$0"; 

--잘못 DROP 한걸 복구함 
FLASHBACK TABLE "BIN$WNQ7EjJPSmuv0l7ix/PTGQ==$0" TO BEFORE DROP; 
```

---

## DDL 활용 예제
```PLSQL
DROP TABLE BREADS PURGE;

CREATE TABLE BREADS       /* 제과제빵 목록 테이블 */
(NO   NUMBER(2),          /* 번호 */
NAME VARCHAR2(30 CHAR));  /* 이름 */

COMMENT ON TABLE  BREADS       IS '제과제빵 목록 테이블';
COMMENT ON COLUMN BREADS.NO    IS '번호';
COMMENT ON COLUMN BREADS.NAME  IS '이름';
    
SELECT * FROM USER_TAB_COMMENTS;
--테이블 코멘트
SELECT * FROM USER_COL_COMMENTS; 
--컬럼 코멘트


INSERT INTO BREADS VALUES (1, '가토쇼콜라');
INSERT INTO BREADS VALUES (2, '맘모스빵');
INSERT INTO BREADS VALUES (3, '모카번');

COMMIT;

ALTER TABLE BREADS ADD (PRICE NUMBER(10, 2));
--정수 8자리 소수점 2자리 -> 전체 10자리
ALTER TABLE BREADS ADD (BAKER VARCHAR2(30 CHAR));
--30글자 지정

SELECT * FROM BREADS;

UPDATE BREADS
SET  PRICE = 10000, BAKER = 'Dorothy'
WHERE NO = 1;

UPDATE BREADS
SET  PRICE = 15000, BAKER = 'Edward'
WHERE NO = 2;
--UPDATE

SELECT * FROM BREADS;

RENAME BREADS TO T_BREADS;
--테이블 이름 변경

SELECT * FROM T_BREADS;

TRUNCATE TABLE T_BREADS;

SELECT * FROM T_BREADS;
```


---


# DML, TCL, Read Consistency, Lock, Deadlock 예제

   

## (1) DML, TCL 이해 
 
```PLSQL
drop table books purge;

SELECT * FROM BOOKS;

create table books
(isbn varchar2(10));

insert into books values('1');
insert into books values('2');
insert into books values('3');

savepoint sp1;

insert into books values('4');
insert into books values('5');

savepoint sp2;
--여기서부터
insert into books values('6');
insert into books values('7');
insert into books values('8');
-- 이 부분이 롤백됨  
rollback to sp2;

insert into books values('9');
insert into books values('10');

commit;

select * from books;
```

```PLSQL
결과::
1
2
3
4
5
9
10
```

    
## (2) 읽기 일관성(read consistency)
 

> 📌 A 유저 `DELETE` -> A 유저 SELECT -> 삭제된 상태로 SELECT 
> B 유저 SELECT -> 아직 COMMIT 된것도 아니니 삭제된 데이터를 합쳐서 원본처럼 보여줌
> 

### ACE02 
```PLSQL
---
drop table members purge;

--MEMBERS 테이블 생성
create table members
(no number(4),
name varchar2(10 char));

--ACE01 에게 권한부여
grant all
on members
to ACE01;

--
insert into members
values (1001, '석수');

insert into members
values (1002, '장안');
-- 커밋 전 SELECT
select * from members;

commit;
```

### ACE01 
```PLSQL
--
select * from ACE02.members;  -- 권한이 없어서 에러

select * from ACE02.members;  -- DDL 명령과 함께 commit  + 접근 권한 있음 

select * from ACE02.members;  -- 질의 결과 없음 

select * from ACE02.members;  -- 질의 결과 확인됨

COMMIT;
```


## (3) 잠금(LOCK)
### ACE02
```PLSQL
--LOCK
update members
set name = '탄산수'
where no = 1001;

select * from members;

rollback; --롤백하자마자  오른쪽 LOCK 해제
```

### ACE01 
```PLSQL
--LOCK
select * from ACE02.members; 

update ACE02.members
set name = '삼다수'
where no = 1001;
--WAITING......

SELECT * FROM ACE02.MEMBERS;

COMMIT;
```


## (4) 교착 상태(DEADLOCK)
### ACE02 

```PLSQL
-- DEADLOCK
UPDATE MEMBERS
SET NAME = '양배추'
WHERE NO = 1001;

UPDATE MEMBERS
SET NAME = '나이키'
WHERE NO = 1002;
--WAITING!

/*
명령의 45 행에서 시작하는 중 오류 발생 -
UPDATE MEMBERS
SET NAME = '나이키'
WHERE NO = 1002
오류 발생 명령행: 46 열: 5
오류 보고 -
SQL 오류: ORA-00060: 자원 대기중 교착 상태가 검출되었습니다
*/

REVOKE ALL
ON MEMBER
FROM ACE02;
-- REVOKE 로 물려줌(양)
```

### ACE01 
```PLSQL
--DEADLOCK
UPDATE ACE02.MEMBERS
SET NAME = '유연수'
WHERE NO = 1002;

UPDATE ACE02.MEMBERS
SET NAME = '안유진'
WHERE NO = 1001;
--WAITING!

-- REVOKE 후 교착 해제

COMMIT;
```

---
# Querying data
> 💡 `SELECT` 매우 중요

# Section 1. [Querying data](https://www.oracletutorial.com/oracle-basics/oracle-select/)

## SELECT문의 기능
> 📌 `SELECT`
> 검색, 조회, 질의, ...
> 틀린건 아니지만 아쉬운 답변
> -> 원하는 집합(결과)을 정의(묘사)하는 언어 

```PLSQL
select * 
from emp;
--저장된 그대로의 집합을 묘사(정의)

select empno, ename, sal, job 
from emp;
--

select sal, sal, sal, sal /* 컬럼 복제 */
from emp;

select sum(sal), avg(sal), max(sal), min(sal) 
from emp;

select empno, sal, sal*1.1 as whatif 
from emp;
```

> 📌 원하는 집합을 묘사하는 방법은 무한하다

```PLSQL
select empno, sal
from emp;

select empno, sal
from (select empno, sal
from emp);

select empno, sal
from (select *
from emp);
  
select empno, sal
from emp
group by empno, sal;

select e1.empno, e2.sal
from (select empno, ename from emp) e1,
(select empno, sal from emp) e2
where e1.empno = e2.empno;
```

> 📌 `SELECT`문을 작성하거나 해석하는 권장 순서(절대 실행 순서 아님!!)

```PLSQL
select   컬럼, 함수, 연산식, 별명, ...  [mandatory]   -- 4
from     재료집합, ...                 [mandatory]   -- 1
where    조건, ...                                   -- 2
group by 컬럼, 함수, 연산식, ...                      -- 3
having   컬럼, 함수, 연산식, ...                      -- 5
order by 컬럼, 함수, 연산식, ...;                     -- 6

select deptno, count(*), sum(sal)
from emp
where sal > 1000 
group by deptno 
having sum(sal) <= 30000 
order by sum(sal) desc;
```

  
# 가공
## 연산
> 📌 연산
> 산술연산 : +, -, *, / 
> 연결연산 : ||
> 논리연산
> 기타

## 함수

> 📌 함수
> * `Built-in function`
> 	* 단일행 함수
> 	* 복수행 함수
> * `User-defined function`(PL/SQL)
> 	* 단일행 함수
> 	* 복수행 함수

```PLSQL
select empno, ename, sal
  from emp;

  select empno, ename ||'('|| job||')' as SAWON, sal, sal*1.25 as upsal
  from emp;

  select empno, ename, initcap(ename)
  from emp;
  --INITCAP 첫글자만 대문자

  create or replace function tax(a number) return number
  is
  begin
return a * 0.031;
end;
/

select empno, sal, tax(sal) 
from emp;
```
  


# column alias
```PLSQL
select empno     alias1, --비권
empno as  alias2, --권장
empno    "alais3", -- 
empno as "alais3" -- 과함
from emp;
--2, 3번 권장
```


# literal
```PLSQL
select empno, sal, '원'
from emp
where sal >= 1500;
-- 이건 쉽게 이해가는데 
```

```PLSQL
--결과
     EMPNO        SAL '원'
---------- ---------- ------
      7499       1600 원
      7566       2975 원
      7698       2850 원
      7782       2450 원
      7788       3000 원
      7839       5000 원
      7844       1500 원
      7902       3000 원
```

```PLSQL
select empno "사번", sal || '원' "급여(\)"
from emp
where sal >= 1500;
```

```SQL
--결과
	사번 급여(\)
---------- ----------
      7499 1600원
      7566 2975원
      7698 2850원
      7782 2450원
      7788 3000원
      7839 5000원
      7844 1500원
      7902 3000원
```

> 💡 내 생각
> `EMPNO` 의 칼럼명 : `""(쌍 따옴표)`를 사용해서 `사번`이 컬럼명이 될것이다.
> `SAL`의 컬럼명 : "급여\" 가 컬럼명, 결과값은 `SAL+'원'` 


---

# Sorting data 

---------------------------
> Section 2. Sorting data <
---------------------------

## order by절 

  https://www.oracletutorial.com/oracle-basics/oracle-order-by/

  (1) 오름차순
```PLSQL
select empno, ename, sal as salary from emp order by sal    asc;
select empno, ename, sal as salary from emp order by salary asc;
select empno, ename, sal as salary from emp order by 3      asc;
```
   

  (2) 내림차순
```PLSQL
select empno, ename, sal as salary from emp order by sal    desc;
select empno, ename, sal as salary from emp order by salary desc;
select empno, ename, sal as salary from emp order by 3      desc;
```
   

  (3) Null이 있을 경우
```PLSQL
select empno, ename, comm from emp order by comm asc;
select empno, ename, comm from emp order by comm asc nulls first;
-- NULL 값이 먼저옴
```

```LUA
--결과
 EMPNO ENAME                      COMM
---------- -------------------- ----------
      7369 SMITH
      7782 CLARK
      7902 FORD
      7900 JAMES
      7876 ADAMS
      7566 JONES
      7698 BLAKE
      7934 MILLER
      7788 SCOTT
      7839 KING
      7844 TURNER                        0

     EMPNO ENAME                      COMM
---------- -------------------- ----------
      7499 ALLEN                       300
      7521 WARD                        500
      7654 MARTIN                     1400
```

```PLSQL
select empno, ename, comm from emp order by comm desc;
select empno, ename, comm from emp order by comm desc nulls last;
```

```LUA
--결과
     EMPNO ENAME                      COMM
---------- -------------------- ----------
      7654 MARTIN                     1400
      7521 WARD                        500
      7499 ALLEN                       300
      7844 TURNER                        0
      7788 SCOTT
      7839 KING
      7876 ADAMS
      7900 JAMES
      7902 FORD
      7934 MILLER
      7698 BLAKE

     EMPNO ENAME                      COMM
---------- -------------------- ----------
      7566 JONES
      7369 SMITH
      7782 CLARK
```
  
> 💡 `ORDER BY` 
> `ORDER BY`는 엄밀히 말하면 `SORT`라곤 할 수 없다. 
> 단지 정렬된 데이터를 원한다는 뜻 -> 오라클이 `SORT`를 할수도, 안할수도 있음!
  

  (4) select list에 없는 컬럼으로 정렬 가능
```PLSQL
select empno, ename, job from emp order by sal desc;
```

  (5) 두 개 이상의 기준으로 정렬
```PLSQL
select empno, ename, sal, deptno from emp order by deptno asc, sal desc;
```
  

  (6) 가공한 결과에 의한 order by

### STEP 1

```PLSQL
select empno, ename, substr(ename, -1, 1) 
from emp;
```

> 💡 `ENAME` 맨 뒤에서 한글자를 자른다.

```LUA
  EMPNO ENAME                SUBSTR(ENAME, -1, 1)
---------- -------------------- --------
      7369 SMITH                H
      7499 ALLEN                N
      7521 WARD                 D
      7566 JONES                S
      7654 MARTIN               N
      7698 BLAKE                E
      7782 CLARK                K
      7788 SCOTT                T
      7839 KING                 G
      7844 TURNER               R
      7876 ADAMS                S
      7900 JAMES                S
      7902 FORD                 D
      7934 MILLER               R
```

### STEP 2

```PLSQL
select empno, ename, substr(ename, -1, 1) 
from emp 
order by substr(ename, -1, 1);
```

> 💡 자른 글자를 기준으로 `ODER BY`한다.

```LUA
EMPNO ENAME                SUBSTR(E
---------- -------------------- --------
      7902 FORD                 D
      7521 WARD                 D
      7698 BLAKE                E
      7839 KING                 G
      7369 SMITH                H
      7782 CLARK                K
      7499 ALLEN                N
      7654 MARTIN               N
      7934 MILLER               R
      7844 TURNER               R
      7566 JONES                S
      7876 ADAMS                S
      7900 JAMES                S
      7788 SCOTT                T
```

### STEP 3

```PLSQL
select empno, ename                       
from emp 
order by substr(ename, -1, 1);
```

> 💡 (`SUBSTR(ENAME, -1, 1)`)을 리스트에 표시하지 않고 정렬 기준으로만 사용한다.

```LUA
  EMPNO ENAME
---------- --------------------
      7902 FORD
      7521 WARD
      7698 BLAKE
      7839 KING
      7369 SMITH
      7782 CLARK
      7499 ALLEN
      7654 MARTIN
      7934 MILLER
      7844 TURNER
      7566 JONES
      7876 ADAMS
      7900 JAMES
      7788 SCOTT
```

### STEP 4

```PLSQL
select empno, hiredate, to_char(hiredate, 'MM') 
from emp;
```

> 💡 `TO_CHAR` : 날짜나 숫자 등의 값을 문자열로 변환하는 함수이다.

```LUA
 EMPNO HIREDATE TO_C
---------- -------- ----
      7369 80/12/17 12
      7499 81/02/20 02
      7521 81/02/22 02
      7566 81/04/02 04
      7654 81/09/28 09
      7698 81/05/01 05
      7782 81/06/09 06
      7788 82/12/09 12
      7839 81/11/17 11
      7844 81/09/08 09
      7876 83/01/12 01
      7900 81/12/03 12
      7902 81/12/03 12
      7934 82/01/23 01
```

### STEP 5

```PLSQL
select empno, hiredate, to_char(hiredate, 'MM') 
from emp 
order by to_char(hiredate, 'MM');
```

> 💡 문자열로 변환한 월을 기준으로 정렬한다.

```LUA
     EMPNO HIREDATE TO_C
---------- -------- ----
      7876 83/01/12 01
      7934 82/01/23 01
      7499 81/02/20 02
      7521 81/02/22 02
      7566 81/04/02 04
      7698 81/05/01 05
      7782 81/06/09 06
      7844 81/09/08 09
      7654 81/09/28 09
      7839 81/11/17 11
      7788 82/12/09 12
      7369 80/12/17 12
      7900 81/12/03 12
      7902 81/12/03 12
```

### STEP 6

```PLSQL
select empno, hiredate                          
from emp 
order by to_char(hiredate, 'MM'), hiredate;
```

> 💡 `SELECT`절에서 `TO_CAHR`를 감춘다.
> 월로 정렬하되, 그 안에서 연도가 빠른 값이 먼저나오게 정렬한다.


```BASH
 EMPNO HIREDATE
---------- --------
      7934 82/01/23
      7876 83/01/12
      7499 81/02/20
      7521 81/02/22
      7566 81/04/02
      7698 81/05/01
      7782 81/06/09
      7844 81/09/08
      7654 81/09/28
      7839 81/11/17
      7369 80/12/17
      7900 81/12/03
      7902 81/12/03
      7788 82/12/09
```
  

---
# Filtering Data 
# Where절의 기능

  https://www.oracletutorial.com/oracle-basics/oracle-where/

  > 📌 `WHERE`
> 조건을 나열하는 곳 ...
> 틀린건 아니지만 아쉬운 답변
> -> 후보행(Candidate row)을 검증해서 True, False, Null을 리턴하는 절인데,
    Where절이 True를 리턴해야 후보행이 리턴된다
  
(1) where절 원리 이해

```SQL
select empno, ename, sal, sal*1.1 as whatif 
from emp
where deptno = 30;
```   

> 💡 `EMPNO`, `ENAME`, `SAL`, `SQL*1.1(이름 : WHATIF)`
> 부서번호가 30인 사람 `SELECT`

```BASH
결과::
 EMPNO ENAME                       SAL     WHATIF
---------- -------------------- ---------- ----------
      7499 ALLEN                      1600       1760
      7521 WARD                       1250       1375
      7654 MARTIN                     1250       1375
      7698 BLAKE                      2850       3135
      7844 TURNER                     1500       1650
      7900 JAMES                       950       1045
```

(2) 조건이 2개 이상인 경우

```sql
select empno, ename, sal, sal*1.1 as whatif 
from emp
where deptno = 30 and sal < 1500;
```

> 💡 `EMPNO`, `ENAME`, `SAL`, `SQL*1.1(이름 : WHATIF)`
> 부서번호가 30이고 급여가 `1500` 이하인사람 `SELECT`

```BASH
결과::
  EMPNO ENAME                       SAL     WHATIF
---------- -------------------- ---------- ----------
      7521 WARD                       1250       1375
      7654 MARTIN                     1250       1375
      7900 JAMES                       950       1045
```

(3) 컬럼명 = 컬럼명 예제

```SQL
select * from emp where empno = empno;
```

```BASH
결과::
EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7369 SMITH                CLERK                    7902 80/12/17        800                    20
      7499 ALLEN                SALESMAN                 7698 81/02/20       1600        300         30
      7521 WARD                 SALESMAN                 7698 81/02/22       1250        500         30
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7654 MARTIN               SALESMAN                 7698 81/09/28       1250       1400         30
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7782 CLARK                MANAGER                  7839 81/06/09       2450                    10
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7839 KING                 PRESIDENT                     81/11/17       5000                    10
      7844 TURNER               SALESMAN                 7698 81/09/08       1500          0         30
      7876 ADAMS                CLERK                    7788 83/01/12       1100                    20
      7900 JAMES                CLERK                    7698 81/12/03        950                    30
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
      7934 MILLER               CLERK                    7782 82/01/23       1300                    10
```


```SQL
select * from emp where comm = comm;
```

```BASH
결과::
   EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7499 ALLEN                SALESMAN                 7698 81/02/20       1600        300         30
      7521 WARD                 SALESMAN                 7698 81/02/22       1250        500         30
      7654 MARTIN               SALESMAN                 7698 81/09/28       1250       1400         30
      7844 TURNER               SALESMAN                 7698 81/09/08       1500          0         30
```

(4) 항상 false인 조건을 사용한 예제

```SQL
drop table sawon purge;

create table sawon
as 
select * from emp 
where 1 = 2;

select * from sawon;
```

```BASH
결과::
선택된 레코드가 없습니다.
```

> 💡 무조건 `FALSE` 를 반환한다. 

(5) is null 연산자

```SQL
select * from emp where comm = null;    -- 엉터리
select * from emp where comm is null;   -- 제대로
```

> 💡 `= NULL` : `SQL`에서는 `NULL`값을 직접적으로 `=` 연산자로 비교할 수 없다.
> 	`IS NULL` : `SQL`에서는 `NULL`값을 비교할 때 `IS NULL`조건을 사용해야 한다.

```BASH
::= NULL;
선택된 레코드가 없습니다.
::IS NULL;
     EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7369 SMITH                CLERK                    7902 80/12/17        800                    20
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7782 CLARK                MANAGER                  7839 81/06/09       2450                    10
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7839 KING                 PRESIDENT                     81/11/17       5000                    10
      7876 ADAMS                CLERK                    7788 83/01/12       1100                    20
      7900 JAMES                CLERK                    7698 81/12/03        950                    30
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
      7934 MILLER               CLERK                    7782 82/01/23       1300                    10
```

```SQL
select * from emp where sal >= 1500 and comm = null;   -- 엉터리
select * from emp where sal >= 1500 and comm is null;  -- 제대로
```


> 💡 마찬가지로, `SQL`에서는 `NULL`값은 `=`연산자로 비교할 수 없다.

```BASH
::= NULL;
선택된 레코드가 없습니다.
:: IS NULL;
     EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7782 CLARK                MANAGER                  7839 81/06/09       2450                    10
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7839 KING                 PRESIDENT                     81/11/17       5000                    10
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
```

(6) 가공한 결과를 사용한 where절

```SQL
--사원이름이 5글자인 사원?
select empno, ename, ename
from EMP;
```

```BASH
 EMPNO ENAME                ENAME
---------- -------------------- --------------------
      7369 SMITH                SMITH
      7499 ALLEN                ALLEN
      7521 WARD                 WARD
      7566 JONES                JONES
      7654 MARTIN               MARTIN
      7698 BLAKE                BLAKE
      7782 CLARK                CLARK
      7788 SCOTT                SCOTT
      7839 KING                 KING
      7844 TURNER               TURNER
      7876 ADAMS                ADAMS
      7900 JAMES                JAMES
      7902 FORD                 FORD
      7934 MILLER               MILLER
```

```SQL
select empno, ename, length(ename)
from emp;
```

```BASH
     EMPNO ENAME                LENGTH(ENAME)
---------- -------------------- -------------
      7369 SMITH                            5
      7499 ALLEN                            5
      7521 WARD                             4
      7566 JONES                            5
      7654 MARTIN                           6
      7698 BLAKE                            5
      7782 CLARK                            5
      7788 SCOTT                            5
      7839 KING                             4
      7844 TURNER                           6
      7876 ADAMS                            5
      7900 JAMES                            5
      7902 FORD                             4
      7934 MILLER                           6
```

```SQL
select empno, ename, length(ename)
from emp
where length(ename) = 5;
```

```BASH
  EMPNO ENAME                LENGTH(ENAME)
---------- -------------------- -------------
      7369 SMITH                            5
      7499 ALLEN                            5
      7566 JONES                            5
      7698 BLAKE                            5
      7782 CLARK                            5
      7788 SCOTT                            5
      7876 ADAMS                            5
      7900 JAMES                            5
```

(7) not 사용하는 예제

```SQL
SELECT *
FROM EMP 
WHERE NOT(DEPTNO=10 AND SAL >= 500);
```

```BASH
 EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7369 SMITH                CLERK                    7902 80/12/17        800                    20
      7499 ALLEN                SALESMAN                 7698 81/02/20       1600        300         30
      7521 WARD                 SALESMAN                 7698 81/02/22       1250        500         30
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7654 MARTIN               SALESMAN                 7698 81/09/28       1250       1400         30
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7844 TURNER               SALESMAN                 7698 81/09/08       1500          0         30
      7876 ADAMS                CLERK                    7788 83/01/12       1100                    20
      7900 JAMES                CLERK                    7698 81/12/03        950                    30
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
```

> 💡 부서번호가 10이고 `SAL`이 500 이상이면 포함하지 않겠다는 뜻.
