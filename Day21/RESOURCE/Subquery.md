# 서브쿼리(Subquery)란?

## 다른 SQL 문에 포함된 select!
```sql
drop table t1 purge; 

create table t1
as
select empno, ename, sal
from emp
where 1 = 2;
```

> 💡 의도적으로 `false` 을 사용해서 테이블 `구조`만 복사

```sql
select * from t1;

insert into t1
select employee_id, last_name, salary
from employees
where employee_id < 200 
and lower(last_name) like '%e%';

select * from t1;
```

```bash
 EMPNO ENAME                       SAL
---------- -------------------- ----------
       102 De Haan                   17000
       104 Ernst                      6000
       107 Lorentz                    4200
       142 Davies                     3100
       149 Zlotkey                   10500
       174 Abel                      11000
```

```sql
update t1
set sal = case when sal > (select avg(sal) from t1) then sal*1.1
else                                      sal*1.2
end;

select * from t1;
```

> 💡 평균보다 높은 월급`sal*1.1` , 그 외 `sal*1.2`

```bash
  EMPNO ENAME                       SAL
---------- -------------------- ----------
       102 De Haan                   18700
       104 Ernst                      7200
       107 Lorentz                    5040
       142 Davies                     3720
       149 Zlotkey                   11550
       174 Abel                      12100
```

```sql
delete from t1
where 
sal < (select sal from t1 where empno = 104);

select * from t1;
```

> 💡 `104`번 인 사람보다 월급이 낮으면 삭제

```bash
 EMPNO ENAME                       SAL
---------- -------------------- ----------
       102 De Haan                   18700
       104 Ernst                      7200
       149 Zlotkey                   11550
       174 Abel                      12100
```

```sql
select empno, sal, select empno, sal, sal*10
from emp e 
where 
sal*10 > (select sal from t1 where empno = 102);
```

> 💡 `월급*10` 을 했을 때 102번 사원의 월급보다 큰 결과만 출력

```bash
  EMPNO        SAL     SAL*10
---------- ---------- ----------
      7566       2975      29750
      7698       2850      28500
      7782       2450      24500
      7788       3000      30000
      7839       5000      50000
      7902       3000      30000
```


# 서브쿼리(Subquery) 문장의 유형

## 단일행 서브쿼리(Single row subquery)
```sql
select * from emp
where 
job = (select job from emp where empno = 7788);
```

> 💡 사번인 7788 인 사원과 동일한 직업을 가진 데이터 출력

```bash
EMPNO ENAME      JOB          MGR HIREDATE        SAL COMM     DEPTNO
----- ---------- ---------- ----- -------- ---------- ---- ----------
 7788 SCOTT      ANALYST     7566 82/12/09       3000              20
 7902 FORD       ANALYST     7566 81/12/03       3000              20
```

```sql
select * from emp 
where 
sal > (select avg(sal) from emp);
```

> 💡 `emp` 테이블의 평균 급여보다 많은 급여를 받는 사람 출력

```bash
EMPNO ENAME      JOB          MGR HIREDATE        SAL COMM     DEPTNO
----- ---------- ---------- ----- -------- ---------- ---- ----------
 7566 JONES      MANAGER     7839 81/04/02       2975              20
 7698 BLAKE      MANAGER     7839 81/05/01       2850              30
 7782 CLARK      MANAGER     7839 81/06/09       2450              10
 7788 SCOTT      ANALYST     7566 82/12/09       3000              20
 7839 KING       PRESIDENT        81/11/17       5000              10
 7902 FORD       ANALYST     7566 81/12/03       3000              20
```

## 복수행 서브쿼리(Multiple row subquery)

### in 연산자 사용 예제
```sql
select * from dept 
where 
deptno in (select deptno from emp);
```

> 💡 `dept`테이블에서 `emp` 테이블에 존재하는 부서번호를 가지고 있는 데이터를 출력

```bash
 DEPTNO DNAME                        LOC
---------- ---------------------------- --------------------------
        20 RESEARCH                     DALLAS
        30 SALES                        CHICAGO
        10 ACCOUNTING                   NEW YORK
```

### >, >=, <, <= 연산자 사용 예제
```sql
drop table t1 purge;
drop table t2 purge;

create table t1(col number);
create table t2(col number);

insert into t1 values(1000);
insert into t1 values(2000);
insert into t1 values(3000);

insert into t2 values(1500);
insert into t2 values(2500);

commit;

select * from t1;
select * from t2;
```

```bash
 SQL> select * from t1;

       COL
----------
      1000
      2000
      3000

SQL> select * from t2;

       COL
----------
      1500
      2500
```

```sql
select * from t1 
where 
col > all(select col from t2);
-- 2개 이상의 결과 (col from t2);

select * from t1
where 
col > (select max(col) from t2);
-- t2의 최대값을 구해서 최대값만으로 비교
```

```bash
    COL
----------
      3000
```

```sql
select * from t1 
where 
col < all(select col from t2);
-- 전부보다 작은것? t2의 제일 작은값보다 작은것
select * from t1
where 
col < (select min(col) from t2);
```

> 💡 `t2 col` 전부보다 작은값은 결국 t2의 최소값보다 작은것이다.

```sql
select * from t1
where
col > any(select * from t2);
--t2 col 아무거나보다 큰값 (제일 작은값 보다 크면 ok)

select * from t1
where 
col > (select min(col) from t2);
```

```bash
    COL
----------
      2000
      3000
```

```sql
select * from t1
where
col < any(select * from t2);
--t2 col 아무거나보다 작은값 (제일 큰값 보다 작으면 ok)

select * from t1
where 
col < (select max(col) from t2);
```

```bash
   COL
----------
      1000
      2000
```

## 복수행 복수열 서브쿼리(Multiple-Row Multiple-Column subquery) = Pairwise Subquery
```sql
drop table t1 purge;
drop table t2 purge;

create table t1(col1 varchar2(1), col2 number);
create table t2(col1 varchar2(1), col2 number);

insert into t1 values('A', 1000);
insert into t1 values('B', 1000);
insert into t1 values('A', 2000);
insert into t1 values('B', 2000);

insert into t2 values('A', 1000);
insert into t2 values('B', 2000);

commit;

select * from t1;
select * from t2;
```

```bash
SQL> select * from t1;

CO       COL2
-- ----------
A        1000
B        1000
A        2000
B        2000

SQL> select * from t2;

CO       COL2
-- ----------
A        1000
B        2000
```

```sql
select * from t1 /* Non-pairwise Subquery */
where 
col1 in (select col1 from t2)
and col2 in (select col2 from t2);
```

> 💡 `t2 col1` 전부 and `t2 col2` 전부

```bash
COL1       COL2
---- ----------
A        1000
A        2000
B        1000
B        2000
```

```sql
select * from t1 /* Pairwise Subquery */
where 
(col1, col2) 
in (select col1, col2 from t2);
```

> 💡 `t2` 의 `col1`, `col2` 컬럼과 완벽히 동일한 컬럼 찾기

```bash
COL1       COL2
---- ----------
A        1000
B        2000
```
## Inline view : from절의 서브쿼리

📌 문제.소속 부서의 평균 급여보다 많은 급여를 받는 사원
```sql
select 
deptno, 
round(avg(sal),2) "avg_sal"
from emp
group by deptno;
```

> 💡 `부서별 평균 월급`

```bash
 DEPTNO    avg_sal
---------- ----------
        20       2175
        30    1566.67
        10    2916.67
```

```sql
select * from 
emp e,
(select 
deptno, 
round(avg(sal), 2) as avg_sal
from emp
group by deptno) a
order by 1;
```

```bash
EMPNO ENAME  JOB         MGR HIREDATE   SAL   COMM DEPTNO DEPTNO    AVG_SAL
----- ------ --------- ----- -------- ----- ------ ------ ------ ----------
 7369 SMITH  CLERK      7902 80/12/17   800            20     20       2175
 7369 SMITH  CLERK      7902 80/12/17   800            20     30    1566.67
 7369 SMITH  CLERK      7902 80/12/17   800            20     10    2916.67
 7499 ALLEN  SALESMAN   7698 81/02/20  1600    300     30     20       2175
 7499 ALLEN  SALESMAN   7698 81/02/20  1600    300     30     30    1566.67
 7499 ALLEN  SALESMAN   7698 81/02/20  1600    300     30     10    2916.67
 7521 WARD   SALESMAN   7698 81/02/22  1250    500     30     20       2175
 7521 WARD   SALESMAN   7698 81/02/22  1250    500     30     30    1566.67
 7521 WARD   SALESMAN   7698 81/02/22  1250    500     30     10    2916.67
 7566 JONES  MANAGER    7839 81/04/02  2975            20     10    2916.67
 7566 JONES  MANAGER    7839 81/04/02  2975            20     30    1566.67
 7566 JONES  MANAGER    7839 81/04/02  2975            20     20       2175
 7654 MARTIN SALESMAN   7698 81/09/28  1250   1400     30     10    2916.67
 7654 MARTIN SALESMAN   7698 81/09/28  1250   1400     30     30    1566.67
 7654 MARTIN SALESMAN   7698 81/09/28  1250   1400     30     20       2175
 7698 BLAKE  MANAGER    7839 81/05/01  2850            30     10    2916.67
 7698 BLAKE  MANAGER    7839 81/05/01  2850            30     20       2175
 7698 BLAKE  MANAGER    7839 81/05/01  2850            30     30    1566.67
 7782 CLARK  MANAGER    7839 81/06/09  2450            10     20       2175
 7782 CLARK  MANAGER    7839 81/06/09  2450            10     30    1566.67
 7782 CLARK  MANAGER    7839 81/06/09  2450            10     10    2916.67
 7788 SCOTT  ANALYST    7566 82/12/09  3000            20     30    1566.67
 7788 SCOTT  ANALYST    7566 82/12/09  3000            20     10    2916.67
 7788 SCOTT  ANALYST    7566 82/12/09  3000            20     20       2175
 7839 KING   PRESIDENT       81/11/17  5000            10     20       2175
 7839 KING   PRESIDENT       81/11/17  5000            10     30    1566.67
 7839 KING   PRESIDENT       81/11/17  5000            10     10    2916.67
 7844 TURNER SALESMAN   7698 81/09/08  1500      0     30     30    1566.67
 7844 TURNER SALESMAN   7698 81/09/08  1500      0     30     20       2175
 7844 TURNER SALESMAN   7698 81/09/08  1500      0     30     10    2916.67
 7876 ADAMS  CLERK      7788 83/01/12  1100            20     20       2175
 7876 ADAMS  CLERK      7788 83/01/12  1100            20     10    2916.67
 7876 ADAMS  CLERK      7788 83/01/12  1100            20     30    1566.67
 7900 JAMES  CLERK      7698 81/12/03   950            30     30    1566.67
 7900 JAMES  CLERK      7698 81/12/03   950            30     20       2175
 7900 JAMES  CLERK      7698 81/12/03   950            30     10    2916.67
 7902 FORD   ANALYST    7566 81/12/03  3000            20     20       2175
 7902 FORD   ANALYST    7566 81/12/03  3000            20     10    2916.67
 7902 FORD   ANALYST    7566 81/12/03  3000            20     30    1566.67
 7934 MILLER CLERK      7782 82/01/23  1300            10     30    1566.67
 7934 MILLER CLERK      7782 82/01/23  1300            10     10    2916.67
 7934 MILLER CLERK      7782 82/01/23  1300            10     20       2175
```

> 💡 `join` -> 

```sql
select e.empno, e.sal, a.deptno, to_char(a.avg_sal, '9999.99') as avg_sal
from emp e,
(select deptno, avg(sal) as avg_sal
from emp
group by deptno) a
where e.deptno = a.deptno
and e.sal > a.avg_sal
order by 1;
```

## Scalar subquery

### 한 건 리턴하는 평범한 서브쿼리
### group by를 제외한 모든 절에 사용될 수 있음

📌 문제.나의 입사일자와 마지막 입사자의 입사일자의 차이를 쿼리하시요
```sql
select 
empno, 
ename, 
hiredate,
(select max(hiredate) from emp)
from emp;
```

```bash
EMPNO ENAME      HIREDATE (SELECTM
----- ---------- -------- --------
 7369 SMITH      80/12/17 83/01/12
 7499 ALLEN      81/02/20 83/01/12
 7521 WARD       81/02/22 83/01/12
 7566 JONES      81/04/02 83/01/12
 7654 MARTIN     81/09/28 83/01/12
 7698 BLAKE      81/05/01 83/01/12
 7782 CLARK      81/06/09 83/01/12
 7788 SCOTT      82/12/09 83/01/12
 7839 KING       81/11/17 83/01/12
 7844 TURNER     81/09/08 83/01/12
 7876 ADAMS      83/01/12 83/01/12
 7900 JAMES      81/12/03 83/01/12
 7902 FORD       81/12/03 83/01/12
 7934 MILLER     82/01/23 83/01/12

14 행이 선택되었습니다.
```

```sql
select empno, ename, 
hiredate - (select max(hiredate) from emp)
from emp;
```

```bash
EMPNO ENAME      HIREDATE-(SELECTMAX(HIREDATE)FROMEMP)
----- ---------- -------------------------------------
 7369 SMITH                                       -756
 7499 ALLEN                                       -691
 7521 WARD                                        -689
 7566 JONES                                       -650
 7654 MARTIN                                      -471
 7698 BLAKE                                       -621
 7782 CLARK                                       -582
 7788 SCOTT                                        -34
 7839 KING                                        -421
 7844 TURNER                                      -491
 7876 ADAMS                                          0
 7900 JAMES                                       -405
 7902 FORD                                        -405
 7934 MILLER                                      -354

14 행이 선택되었습니다.

```

```sql
select empno, ename,
abs(hiredate - (select max(hiredate) from emp))
from emp;
```

```bash
EMPNO ENAME      ABS(HIREDATE-(SELECTMAX(HIREDATE)FROMEMP))
----- ---------- ------------------------------------------
 7369 SMITH                                             756
 7499 ALLEN                                             691
 7521 WARD                                              689
 7566 JONES                                             650
 7654 MARTIN                                            471
 7698 BLAKE                                             621
 7782 CLARK                                             582
 7788 SCOTT                                              34
 7839 KING                                              421
 7844 TURNER                                            491
 7876 ADAMS                                               0
 7900 JAMES                                             405
 7902 FORD                                              405
 7934 MILLER                                            354
```

📌 문제.사원의 사번, 이름, 급여와 사원이 속한 부서의 급여합을 포함해서 쿼리하세요.
```sql
select deptno, empno, ename, sal, 
(select sum(sal) from emp where deptno = e.deptno) as dept_sum_sal
from emp e
order by deptno, sal desc;
```

```bash
DEPTNO EMPNO ENAME          SAL DEPT_SUM_SAL
------ ----- ---------- ------- ------------
    10  7839 KING          5000         8750
    10  7782 CLARK         2450         8750
    10  7934 MILLER        1300         8750
    20  7788 SCOTT         3000        10875
    20  7902 FORD          3000        10875
    20  7566 JONES         2975        10875
    20  7876 ADAMS         1100        10875
    20  7369 SMITH          800        10875
    30  7698 BLAKE         2850         9400
    30  7499 ALLEN         1600         9400
    30  7844 TURNER        1500         9400
    30  7521 WARD          1250         9400
    30  7654 MARTIN        1250         9400
    30  7900 JAMES          950         9400

14 행이 선택되었습니다.
```

```sql
select deptno, empno, ename, sal,
round(sal/dept_sum_sal, 2) "통계",
dept_sum_sal
from(select deptno, empno, ename, sal, 
(select sum(sal) from emp 
where deptno = e.deptno) as dept_sum_sal
from emp e)
order by deptno, sal desc;
```

```bash
DEPTNO EMPNO ENAME          SAL       통계 DEPT_SUM_SAL
------ ----- ---------- ------- ---------- ------------
    10  7839 KING          5000        .57         8750
    10  7782 CLARK         2450        .28         8750
    10  7934 MILLER        1300        .15         8750
    20  7788 SCOTT         3000        .28        10875
    20  7902 FORD          3000        .28        10875
    20  7566 JONES         2975        .27        10875
    20  7876 ADAMS         1100         .1        10875
    20  7369 SMITH          800        .07        10875
    30  7698 BLAKE         2850         .3         9400
    30  7499 ALLEN         1600        .17         9400
    30  7844 TURNER        1500        .16         9400
    30  7521 WARD          1250        .13         9400
    30  7654 MARTIN        1250        .13         9400
    30  7900 JAMES          950         .1         9400

14 행이 선택되었습니다.
```
## Subquery factoring = with clause
```sql
with a as 
(select 
deptno, 
avg(sal) as avg_sal from emp group by deptno)
select e.empno, e.sal, a.deptno, 
to_char(a.avg_sal, '9999.99') as avg_sal
from emp e, a
where e.deptno = a.deptno
and e.sal > a.avg_sal
order by 1;
```

```bash
EMPNO     SAL DEPTNO AVG_SAL
----- ------- ------ ----------------
 7499    1600     30  1566.67
 7566    2975     20  2175.00
 7698    2850     30  1566.67
 7788    3000     20  2175.00
 7839    5000     10  2916.67
 7902    3000     20  2175.00
```

```sql
with a as (select avg(sal) avg_sal
from emp),
b as (select sum(sal) sum_sal
from emp)
select e.empno, e.ename, e.sal, round(a.avg_sal) avg_sal, b.sum_sal
from emp e, a, b;
```
  
 

## Correlated subquery

* [SQL Correlated Subqueries(geeksforgeeks)](https://www.geeksforgeeks.org/sql-correlated-subqueries/)
![](https://media.geeksforgeeks.org/wp-content/cdn-uploads/SQL_Correlated_Subqueries.png)


📌 문제.소속 부서의 평균 급여보다 많은 급여를 받는 사원
```sql
select *
from emp e
where sal > (select avg(sal)
from emp
where deptno = e.deptno);
```



📌 문제.근무하는 사원이 5명이상인 부서
```sql
select *
from dept d
where 5 <= (select count(*)
from emp
where deptno = d.deptno);
```
  


📌 문제.근무하는 사원이 있는 부서
```sql
select *
from dept d
where exists (select 'x'
from emp
where deptno = d.deptno);
```

> 💡 `Exists` : 하나라도 확인되면 바로 return
>  exist 서브쿼리의 select 는 아무거나 적어도 OK
>  이걸로 save 만들어도 될거같다 