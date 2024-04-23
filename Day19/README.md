
# Distinct

  https://www.oracletutorial.com/oracle-basics/oracle-select-distinct/

```SQL
select unique   deptno from emp;
select distinct deptno from emp;
select distinct deptno, job from emp order by 1, 2;
```
* `row-major format`(OLTP에 적합) vs `column-major format`(OLAP, DW, DSS, BI)
* `sort unique` vs `hash unique`

```SQL
SELECT DISTINCT DEPTNO, JOB FROM EMP;
```

```plsql
SELECT UNIQUE DEPTNO FROM EMP;
SELECT DISTINCT DEPTNO, JOB FROM EMP;
SET AUTOTRACE ON;
SET AUTOTRACE OFF;
```

```BASH
---------------------------------------------------------------------------
| Id  | Operation          | Name | Rows  | Bytes | Cost (%CPU)| Time     |
---------------------------------------------------------------------------
|   0 | SELECT STATEMENT   |      |    11 |   121 |     4  (25)| 00:00:01 |
|   1 |  HASH UNIQUE       |      |    11 |   121 |     4  (25)| 00:00:01 |
|   2 |   TABLE ACCESS FULL| EMP  |    14 |   154 |     3   (0)| 00:00:01 |
---------------------------------------------------------------------------
```

## Introduction to Oracle SELECT DISTINCT statement
```SQL
SELECT DISTINCT column_1
FROM table;
```


# Where절의 기능
> 💡 `FALSE` 부터 

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
> 	`NULL` = `NULL` -> 항상 `NULL`

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

# And 

  https://www.oracletutorial.com/oracle-basics/oracle-and/

> 📌 `Null` 이해
> -> unknown, unavailable, inapplicable, 비어있는, 미결정, ...
> -> 가능하면 null을 허용하지 않는 것이 유리함

> 📌 `NULL` 비교
> -> ≠ 0
> -> ≠ space
> 
> -> =  null    -> 결과는 Null
> -> 산술연산   -> 결과는 Null  -> 극복하기 위해 nvl, nvl2, ...
> -> 논리연산   -> 진리표를 기억할 것
   
* And 진리표 이해 : F > N > T

| AND   | TRUE  | FALSE | NULL  |
| ----- | ----- | ----- | ----- |
| TRUE  | TRUE  | FALSE | NULL  |
| FALSE | FALSE | FALSE | FALSE |
| NULL  | NULL  | FALSE | NULL  |

```SQL
--ex) 키가 180cm이상 이고 성이 김씨?
if __________ and _________ and _______ then
실행문;
end if;

from 테이블
where __________ and _________ and _______ 
```

# Or

  https://www.oracletutorial.com/oracle-basics/oracle-or/

  - Or 진리표 이해 : T > N > F

| OR    | TRUE | FALSE | NULL |
| ----- | ---- | ----- | ---- |
| TRUE  | TRUE | TRUE  | TRUE |
| FALSE | TRUE | FALSE | NULL |
| NULL  | TRUE | NULL  | NULL |



```SQL
--ex) 키가 180cm이상 이거나 성이 김씨?
if __________ or _________ or _______ then
실행문;
end if;

from 테이블
where __________ or _________ or _______ 
```



# FETCH 

  - [Oracle TUTORIAL](https://www.oracletutorial.com/oracle-basics/oracle-fetch/)
  - [ORACLE DOCS](https://oracle-base.com/articles/12c/row-limiting-clause-for-top-n-queries-12cr1)
  - [ORACLE BASE](https://docs.oracle.com/en/database/oracle/oracle-database/21/sqlrf/SELECT.html#GUID-CFA006CA-6FF1-4972-821E-6996142A51C6__BABBADDD)

## pagenation 
```SQL
-- 1 2 3 4 
SELECT val
FROM   rownum_order_test
ORDER BY val
OFFSET (1 - 1) * 10 ROWS FETCH NEXT 10 ROWS ONLY;
```

### Create and populate a test table.
```SQL
DROP TABLE rownum_order_test;

CREATE TABLE rownum_order_test (
  val  NUMBER
);

INSERT ALL
  INTO rownum_order_test
  INTO rownum_order_test
SELECT level
FROM   dual
CONNECT BY level <= 10;

COMMIT;
```

```SQL
SELECT val
FROM   rownum_order_test
ORDER BY val;
```

```BASH
  VAL
----------
         1
         1
         2
         2
         3
         3
         4
         4
         5
         5
         6
         6
         7
         7
         8
         8
         9
         9
        10
        10

20 행이 선택되었습니다.
```

### Top-N Queries
> 📌 `CASE 1`
> SIMPLE USE

```SQL
SELECT val
FROM   rownum_order_test
ORDER BY val DESC
FETCH FIRST 5 ROWS ONLY;
```

```BASH
       VAL
----------
        10
        10
         9
         9
         8
```

> 📌 `CASE 2`
> Using the `WITH TIES`

```SQL
SELECT VAL
FROM ROWNUM_ORDER_TEST
ORDER BY VAL DESC
FETCH FIRST 5 ROWS WITH TIES;
```

```BASH
  VAL
----------
        10
        10
         9
         9
         8
         8

6 행이 선택되었습니다.
```

> 💡 `WITH TIES`  : 동률(같은 값)은 갯수에 포함하지 않는다.

> 📌 `CASE 3`
> Limit by percentage of rows

```SQL
SELECT VAL
FROM ROWNUM_ORDER_TEST
ORDER BY VAL
FETCH FIRST 20 PERCENT ROWS ONLY;
```

```BASH
 VAL
----------
         1
         1
         2
         2
```

> 💡 `20 PERCENT ROWS ONLY` : 20개 데이터의 20퍼센트 -> 4개 출력

> 📌 `CASE 4`
> With the row limiting

```SQL
SELECT val
FROM   rownum_order_test
ORDER BY val
OFFSET 4 ROWS FETCH NEXT 4 ROWS ONLY;
```

```BASH
       VAL
----------
         3
         3
         4
         4
```

> 💡 `OFFSET 4 ROWS` : 처음에 나타날 4개의 ROWS 를 제외하고 보여준다.

> 📌 `CASE 5`
> combined with a `FETCH` using a `PERCENT`.

```SQL
SELECT val
FROM   rownum_order_test
ORDER BY val
OFFSET 4 ROWS FETCH NEXT 20 PERCENT ROWS ONLY;
```

```BASH
  VAL
----------
         3
         3
         4
         4
```

> 💡 `OFFSET` 을 이용해서 첫 4행을 자르고, 4개(`20 PERCENT ROWS`)의 행을 표시한다
# IN
```SQL
select * from emp where mgr = 7698;
select * from emp where mgr = 7566;
select * from emp where mgr = 7782;
--따로따로 결과 SELECT
```

    ↓↓

```SQL
select * from emp where mgr = 7698
union all
select * from emp where mgr = 7566
union all
select * from emp where mgr = 7782;
--UNION ALL 
```

    ↓↓

```SQL
select * from emp where mgr = 7698 or mgr = 7566 or mgr = 7782;
--이거랑
select * from emp where mgr in (7698, 7566, 7782);
--이거랑 동일
```

```BASH
EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7499 ALLEN                SALESMAN                 7698 81/02/20       1600        300         30
      7521 WARD                 SALESMAN                 7698 81/02/22       1250        500         30
      7654 MARTIN               SALESMAN                 7698 81/09/28       1250       1400         30
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7844 TURNER               SALESMAN                 7698 81/09/08       1500          0         30
      7900 JAMES                CLERK                    7698 81/12/03        950                    30
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
      7934 MILLER               CLERK                    7782 82/01/23       1300                    10
```

> 💡 `IN`을 사용하면 `OR` 로 변환되는 짧은 시간이 필요

# BETWEEN

```SQL
select * from emp where sal >= 1500 and sal <= 2500;
--이거랑
select * from emp where sal between 1500 and 2500;
--이거랑 동일
```

> 💡 마찬가지로 `Query Transformation` 에 시간이 걸린다.
## Query Transformation

> 💡 쿼리를 `DBMS`가 좀 더 나은방식으로 바꿔서 수행

* [The Logical Optimizer(yes24)](https://www.yes24.com/Product/Goods/3796929)
* [10053 트레이스](http://www.gurubee.net/lecture/3987)

```sql
SELECT * FROM EMP 
WHERE SAL > (SELECT SAL FROM EMP WHERE EMPNO = 7788);

SELECT E.*
FROM EMP E, EMP S 
WHERE S.EMPNO = 7788
AND E.SAL > S.SAL;
```


# LIKE

  * wild card : %(zero or many), _(only one)
```SQL
select * from emp where ename like '_____';
-- 5글자
select * from emp where ename like 'S%';
-- 'S'로 시작하는 데이터
```

```SQL
drop table t1 purge;

create table t1 (name varchar2(30));

insert into t1 values('AAA');
insert into t1 values('ABA');
insert into t1 values('ACA');
insert into t1 values('A_A');

commit;

```

```SQL
select * from t1 where name like '%A_A%';
```

```BASH
NAME
------------------------------------------------------------
AAA
ABA
ACA
A_A
```

```SQL
select * from t1 where name like '%A!_A%' escape '!';
```

```BASH
NAME
------------------------------------------------------------
A_A
```

> 💡 `ESCAPE 문자`는 꼭 `!`만 되는게 아니다. 

---

# 데이터가 움직이느냐, 로직이 움직이느냐
> 📌
>  "데이터가 움직이느냐, 로직이 움직이느냐"는 데이터 처리와 시스템 설계에 대한 중요한
>  고려 사항입니다. 이 질문은 시스템의 효율성과 성능에 대한 근본적인 접근 방식을 나타냅니다.

## 1. 데이터가 움직이는 경우 (Data Movement):

- 이 접근 방식에서는 데이터가 네트워크를 통해 다른 시스템이나 애플리케이션으로 전송됩니다.
* 데이터를 중앙 집중식 서버나 클라우드 기반 서비스로 이동시켜 처리합니다.
* 대규모 데이터 센터에서 강력한 계산 리소스를 활용해 분석이나 처리를 수행합니다.
* 단점은 대량의 데이터 이동으로 인한 네트워크 대역폭 사용과 지연 시간이 발생할 수 있다는 것입니다.

## 2. 로직이 움직이는 경우 (Logic Movement):

    - 이 경우, 처리 로직(코드)가 데이터가 저장된 위치로 이동하여 실행됩니다.
    - 예를 들어, 데이터베이스 서버 내에 저장 프로시저를 사용하거나, 분산 데이터 처리 시스템에서 로컬 노드에서 실행되는 맵리듀스(MapReduce) 작업과 같은 것이 이에 해당합니다.
    - 이 방식은 데이터 이동을 최소화하여 네트워크 부하를 줄이고 처리 속도를 향상시키는 이점이 있습니다.

  시스템이나 애플리케이션의 특정 요구 사항에 따라, 데이터 이동과 로직 이동 사이에서 균형을 찾아야 합니다. 예를 들어, 보안이 중요한 애플리케이션의 경우, 데이터를 이동시키기보다는 보안된 환경 내에서 로직을 실행하는 것이 더 바람직할 수 있습니다. 반면, 클라우드 기반 애플리케이션에서는 강력한 클라우드 리소스를 활용하기 위해 데이터를 클라우드로 이동시킬 수 있습니다.

  이러한 결정은 데이터의 민감도, 처리해야 할 데이터의 양, 네트워크 대역폭, 지연 시간, 비용 등 다양한 요인을 고려하여 결정되어야 합니다.

---
# JOIN 
  * [Oracle basics](https://www.oracletutorial.com/oracle-basics/)

# Join이란?
  - from절에 재료 집합이 more than one!

FROM 결정 -> 하나의 집합  ->  Where 결정 -> 추후 가공

```SQL
SET SERVEROUTPUT ON;
select * from emp, dept;
-- 56건의 단일 테이블로 생각하기 
```

```SQL
begin
for d in (select * from dept) loop
for e in (select * from emp) loop
p.p(e.empno||' : '||d.deptno);
end loop;
end loop;
end;
/

select * from emp, dept, salgrade;

begin
for s in (select * from salgrade) loop
for d in (select * from dept) loop
for e in (select * from emp) loop
p.p(e.empno||' : '||d.deptno||' : '||s.grade);
end loop;
end loop;
end loop;
end;
/
  
SELECT LEVEL AS NO FROM DUAL CONNECT BY LEVEL <= 100;
  
  
WITH MYTAB AS (SELECT LEVEL AS NO 
FROM DUAL 
CONNECT BY LEVEL <=100)
SELECT COUNT(*) 
FROM MYTAB A, MYTAB B , MYTAB C;
  
```

```SQL
SELECT * FROM EMP, DEPT;
--56건의 데이터 
```

```SQL
SELECT GRADE, COUNT(*), SUM(SAL), AVG(SAL)
FROM (SELECT EMPNO, SAL, GRADE FROM EMP, SALGRADE 
WHERE SAL >= LOSAL AND SAL <= HISAL)
GROUP BY GRADE
ORDER BY GRADE;
```

```SQL
SELECT EMPNO, SAL, GRADE FROM EMP E, SALGRADE S 
WHERE E.SAL >= S.LOSAL AND E.SAL <= S.HISAL;
```

# Join 관련 용어
```SQL
select e.empno, 
         e.job, 
         e.deptno as emp_deptno, 
         d.deptno as dept_deptno, 
         d.dname
  from emp e, dept d              -- Join Statement
  where e.deptno = d.deptno       -- Join predicate
  and d.deptno = 30               
  -- Non-join predicate(Single-row predicate)
  and e.sal >= 1500               -- Non-join predicate
  order by 1;
```

> 💡`Non-join predicate(Single-row predicate)` : `Primary Key` 
  

# Join syntax

## Oracle sytax(Oracle 표준)

### Equi join
```sql
select e.empno, e.ename, e.sal, d.loc
from emp e, dept d
where e.deptno = d.deptno 
order by 1;
```

> 💡 부등호가 `=` -> Equi join 
> emp와 dept 테이블에서 동일 deptno 를 가진 데이터

### Non-equi join
```sql
select *
from emp e, salgrade s
where e.sal >= s.losal and e.sal <= s.hisal
order by 1;
```
> 💡 부등호가 `=` 이 아님 -> `Non-equi join`
> `EMP`테이블의 `SAL`컬럼의 값이 
> `SALGRADE`테이블의 LOSAL, HISAL 사이 
### Self join
```sql
select w.*, m.empno, m.ename
from emp w, emp m
where w.mgr = m.empno
order by 1;
--13개
```


> 💡 `Self Join` : `from 절`에 같은 테이블이 2번 나온다.
> from 절에 같은 테이블의 인스턴스가 2번이상 나온다.

> 💡 `Inner Join ?`
> Join 조건에 맞는 결과만 나오는 걸 `Inner Join`이라고 한다.

### Outer join
```sql
select w.*, m.empno, m.ename
from emp w, emp m
where w.mgr  = m.empno (+)
order by 1;
```

> 💡 `Join` 조건에 맞지 않는 결과도 나오는 걸 `Outer Join`이라고 한다.
## ANSI syntax(ANSI 표준)

### Cross Join
```sql
--ANSI 표준
select *
from emp e CROSS JOIN dept d;

--오라클식
-> select *
from emp e, dept d;
```

> 💡 행렬 곱 과 비슷하다 하여 `CROSS JOIN` 이라 한다.

   
### Natural Join
```sql
--ANSI 표준
SELECT E.EMPNO, E.ENAME, E.SAL, D.LOC
FROM EMP E 
NATURAL JOIN DEPT D;

--오라클식
-> select e.empno, e.ename, e.sal, d.loc
from emp e, dept d
where e.deptno = d.deptno 
```

> 💡 조인 대상 테이블들의 같은 이름 컬럼 전부로 equi join을 수행하는 것
> `EMP` 테이블과 `DEPT` 테이블의 동일한 이름 컬럼`(DEPTNO)`로 `Equi Join` 수행

### Natural join의 문제점 

> 💡 상품 테이블의 `name`과 가구 테이블의 `name`이 같을까? 

```sql
--같은 이름 컬럼 "전부"를 조인 조건에 사용하는 것!!
--ANSI 표준
select *
from departments d NATURAL JOIN employees e;

--오라클식
-> select *
from departments d, employees e
where d.department_id = e.department_id
and d.manager_id = e.manager_id;
```

> 💡 같은 이름의 컬럼 `DEPARTMENT_ID`, `MANAGER_ID` 둘 다 붙어버린다.

### Natural join의 문제점을 해결하는 방법
> 📌 (1) Join Using
```sql
--(1) Join Using
select *
from departments d JOIN employees e 
USING (department_id);
```

> 📌 (2) Join On  
```sql
--(2) Join On  
select *
from departments d JOIN employees e 
ON (d.department_id = e.department_id);
```

> 📌 (3) Join Using
> 주어진 컬럼들로 equi join을 수행하는 것
```sql
select empno, deptno
from emp e JOIN dept d
USING (deptno);
```

> 📌 Join On
```sql
select e.empno, e.ename, e.sal, d.loc
from emp e JOIN dept d
ON e.deptno = d.deptno 
order by 1;
```

> 💡 `FROM` 절 왼쪽엔 좀 더 크기가 큰 테이블이 오는 것이 좋다.
> `ORDER BY 1` : 가장 첫 번째 오는 컬럼(`E.EMPNO`)를 기준으로 `ORDER BY`


```SQL
select E.EMPNO, E.ENAME, E.SAL, S.GRADE
from emp e JOIN salgrade s
ON e.sal >= s.losal and e.sal <= s.hisal
order by S.GRADE;
```

> 💡 `EMP`, `SALGRADE` 테이블을 가져와서
> `SALGRADE` 의 `LOSAL` 컬럼과 `HISAL` 컬럼 사이의 `SAL` 값을 가지는 사원의 번호, 이름
> 급여, 등급을 출력한다.

```SQL
--SELF JOIN?
select w.*, m.empno, m.ename
from emp w JOIN emp m
ON w.mgr = m.empno
order by 1;
```

> 💡 `EMP` 테이블의 `MGR`에 해당하는 사번을 가지는 사원의 번호, 이름 추가 출력

```SQL
select a.a, a.b, sum(b.b) 누적합
from t1 a JOIN t1 b
ON a.a >= b.a
group by a.a, a.b
order by a.a;
```
### 상세 분석
```SQL
SELECT A.A AS AA, A.B AS AB
FROM T1 A JOIN T1 B
ON 1=1
ORDER BY 1;
```

```BASH
결과::
   AA         AB
---------- ----------
      7369        800
      7369        800
      7369        800
      7499       1600
      7499       1600
      7499       1600
      7521       1250
      7521       1250
      7521       1250

9 행이 선택되었습니다.
```

```SQL
SELECT A.A AS AA, A.B AS AB
FROM T1 A JOIN T1 B
ON A.A >= B.A
ORDER BY 1;
```

```BASH
  AA         AB
---------- ----------
      7369        800
      7499       1600
      7499       1600
      7521       1250
      7521       1250
      7521       1250

6 행이 선택되었습니다.
```

> 💡 A값 (7369, 7499, 7521) 

```SQL
SELECT A.A AS AA, A.B AS AB
FROM T1 A JOIN T1 B
ON A.A >= B.A
GROUP BY A.A, A.B
ORDER BY 1;
```

```BASH
  AA         AB
---------- ----------
      7369        800
      7499       1600
      7521       1250
```

> 💡 AA 와 AB 가 일치하는 값` GROUP BY`

```SQL
SELECT A.A AS AA, A.B AS AB, SUM(B.B) AS 합계
FROM T1 A JOIN T1 B
ON A.A >= B.A
GROUP BY A.A , A.B
ORDER BY 1;
```

```BASH
   AA         AB       합계
---------- ---------- ----------
      7369        800        800
      7499       1600       2400
      7521       1250       3650
```

> 💡 `JOIN ON` 구문에서 A.A >= B.A 선언에 의해 
> 7369 : 1 -> 800
> 7499 : 2 -> 800, 1600
> 7521 : 3 -> 800, 1250, 1600

---


```SQL
select e.empno,
e.sal, 
s.empno as scott_empno,
s.sal as scott_sal
from emp e JOIN emp s
ON e.sal >= s.sal 
where s.empno = 7788 
and e.empno != 7788;
```

> 💡 자기 자신과 비교하지 않기 위해 `AND E.EMPNO != 7788`

```BASH
EMPNO        SAL SCOTT_EMPNO  SCOTT_SAL
---------- ---------- ----------- ----------
      7839       5000        7788       3000
      7902       3000        7788       3000
```


> 📌 `LEFT,RIGTH,FULL Outer`
```sql
select w.*, m.empno, m.ename
from emp w LEFT JOIN emp m
ON w.mgr  = m.empno
order by 1;      --left join 필수 (outer)
select *
from employees e LEFT JOIN departments d
ON e.department_id = d.department_id
order by 1;

-> select *
from employees e, departments d
where e.department_id = d.department_id (+)
order by 1;

select *
from employees e RIGHT JOIN departments d
ON e.department_id = d.department_id
order by 1;

-> select *
from employees e, departments d
where e.department_id (+) = d.department_id 
order by 1;

select *
from employees e FULL JOIN departments d
ON e.department_id = d.department_id
order by 1;

-> select *
from employees e, departments d
where e.department_id (+) = d.department_id (+)   /* 이 문법은 허락되지 않음 */
order by 1;
```

### 나의 예제
```SQL
-- JOIN USING
SELECT DEPARTMENT_ID AS 부서ID,
DEPARTMENT_NAME AS 부서이름,
EMPLOYEE_ID AS 사번,
FIRST_NAME||' '||LAST_NAME AS 사원명
FROM DEPARTMENTS D JOIN EMPLOYEES E
USING (DEPARTMENT_ID);
```

> 💡 `JOIN USING`의 열 부분은 식별자(`D OR E와 같은`)를 가질 수 없음!

```SQL
-- JOIN ON
SELECT D.DEPARTMENT_ID AS 부서ID,
D.DEPARTMENT_NAME AS 부서이름,
E.EMPLOYEE_ID AS 사번,
E.FIRST_NAME||' '||E.LAST_NAME AS 사원명
FROM DEPARTMENTS D JOIN EMPLOYEES E
ON (D.DEPARTMENT_ID = E.DEPARTMENT_ID);
```

```
--JOIN USING

```
# Join 관련 문제

## CASE 1
> 📌 100건짜리 테이블 3개를 조인하면 
> 1,000,000건이 되는 것을 확인하는 쿼리문을 작성해보세요

```SQL
with mytab as (select level as no
                   from dual
                   connect by level <= 100)
select count(*)
from mytab t1,  mytab t2, mytab t3;
```

```BASH
 COUNT(*)
----------
   1000000
```

## CASE 2
> 📌 각 사원의 사번, 이름, 급여 및 근무지역을 쿼리하세요

```SQL
select *
from emp, dept
order by 1;

select *
from emp, dept
where emp.deptno = dept.deptno
order by 1;

select *
from emp, dept
where emp.deptno = dept.deptno and emp.sal >= 1500
order by 1;

select emp.empno, emp.ename, emp.sal, dept.loc
from emp, dept
where emp.deptno = dept.deptno and emp.sal >= 1500
order by 1;

select e.empno, e.ename, e.sal, d.loc
from emp e, dept d
where e.deptno = d.deptno and e.sal >= 1500
order by 1;
```

## CASE 3
> 📌 각 사원의 사번, 이름, 급여 및 급여등급을 쿼리하세요 

```SQL
select *
from emp e, salgrade s
order by 1;

select *
from emp e, salgrade s
where e.sal >= s.losal and e.sal <= s.hisal
order by 1;

select e.empno, e.ename, e.sal, s.grade
from emp e, salgrade s
where e.sal >= s.losal and e.sal <= s.hisal
order by 1;
  
select e.empno, e.ename, e.sal, s.grade
from emp e, salgrade s
where e.sal between s.losal and s.hisal
order by 1;
 
select grade, count(*)
from (select e.empno, e.ename, e.sal, s.grade
from emp e, salgrade s
where e.sal between s.losal and s.hisal)
group by grade
order by grade;
```

## CASE 4
> 📌 7788 사원의 급여이상을 받는 사원을 쿼리하세요

```BASH
empno   sal  scott_empno scott_sal
-------------------------------------------
7839  5000         7788      3000
7902  3000         7788      3000
```

```SQL
select *
from emp e, emp s
where s.empno = 7788;
    
select *
from emp e, emp s
where s.empno = 7788
and e.sal >= s.sal ;

select e.empno,
e.sal, 
s.empno as scott_empno,
s.sal as scott_sal
from emp e, emp s
where s.empno = 7788 
and e.sal >= s.sal 
and e.empno != 7788;
```

## 누적합 구하기
```BASH
         A          B     누적합
---------- ---------- ----------
      7369        800        800
      7499       1600       2400
      7521       1250       3650
```

```SQL
select *
from t1 a, t1 b
order by 1;

select *
from t1 a, t1 b
where a.a >= b.a
order by 1;

select a.a, a.b, sum(b.b) 누적합
from t1 a, t1 b
where a.a >= b.a
group by a.a, a.b
order by a.a;
```
   


# Cartesian product는 항상 나쁘다??!!

## 복제 

### 컬럼 복제
```SQL
select sum(sal), avg(sal), max(sal), min(sal) 
from emp;
```

### 행 복제
```SQL
select *
from emp, dept
order by dept.deptno;
```
    

### SET 연산자 vs Cartesian product
```SQL
select deptno, job, sum(sal)
from emp
group by deptno, job
union all
select deptno, null, sum(sal)
from emp
group by deptno
union all
select null, null, sum(sal)
from emp
order by 1, 2;
```

> 💡 `LINE BY LINE`
> `01행~03행` : 부서번호와 JOB으로 GROUP BY 같은 부서번호와 직업이면 급여의 합을 표시
> -> 같은 부서의 같은 직업의 급여의 합을 나타낸다.
> `05행~07행`: `UNION ALL`을 하기 위해 `NULL`을 넣어서 컬럼의 갯수를 맞춰준다.
> -> 같은 부서의 급여의 합을 나타낸다.
> `09행~11행`: `EMP` 테이블의 급여의 합을 나타낸다.

## Cartesian product 활용


```SQL
select * 
    from (select deptno, job, sum(sal) as sum_sal
          from emp
          group by deptno, job) e,
         (select level as no
          from dual
          connect by level <= 3) n;

    select decode(n.no, 1, e.deptno,
                        2, e.deptno) as deptno, 
           e.job, e.sum_sal, n.no
    from (select deptno, job, sum(sal) as sum_sal
          from emp
          group by deptno, job) e,
         (select level as no
          from dual
          connect by level <= 3) n;

    select decode(n.no, 1, e.deptno,
                        2, e.deptno) as deptno, 
           e.job, e.sum_sal, n.no
    from (select deptno, job, sum(sal) as sum_sal
          from emp
          group by deptno, job) e,
         (select level as no
          from dual
          connect by level <= 3) n;

    select decode(n.no, 1, e.deptno, 2, e.deptno) as deptno, 
           decode(n.no, 1, e.job) as job, 
           sum(e.sum_sal) as sum_sal
    from (select deptno, job, sum(sal) as sum_sal
          from emp
          group by deptno, job) e,
         (select level as no
          from dual
          connect by level <= 3) n
    group by decode(n.no, 1, e.deptno, 2, e.deptno),
             decode(n.no, 1, e.job)
    order by 1, 2;

    select decode(n.no, 1, e.deptno, 2, e.deptno) as deptno, 
           decode(n.no, 1, e.job) as job, 
           sum(e.sum_sal) as sum_sal
    from (select deptno, job, sum(sal) as sum_sal
          from emp
          group by deptno, job) e
          CROSS JOIN
         (select level as no
          from dual
          connect by level <= 3) n
    group by decode(n.no, 1, e.deptno, 2, e.deptno),
             decode(n.no, 1, e.job)
order by 1, 2;
--`CROSS JOIN`
-- CROSS JOIN 을 사용해서 의도를 명확하게 나타냄
```


---