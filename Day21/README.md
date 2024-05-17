
----------------------------
> Section 5. Grouping data 
----------------------------

# Aggregate Functions(집계 함수, 복수행 함수, 그룹 함수) 사용 패턴
* [Aggregate Functions(Oracle docs)](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Aggregate-Functions.html#GUID-62BE676B-AF18-4E63-BD14-25206FEA0848)


```sql
select sal, sal, sal
  from emp;
```

```bash
   SAL        SAL        SAL
---------- ---------- ----------
       800        800        800
      1600       1600       1600
      1250       1250       1250
      2975       2975       2975
      1250       1250       1250
      2850       2850       2850
      2450       2450       2450
      3000       3000       3000
      5000       5000       5000
      1500       1500       1500
      1100       1100       1100
       950        950        950
      3000       3000       3000
      1300       1300       1300
```

```sql
  select sum(sal) "급여 총합", 
         to_char(avg(sal), '999999.99') "급여 평균",
         to_char(stddev(sal), '999999.99') "급여 표준편차"
  from emp;
```

```bash
 급여 총합 급여 평균            급여 표준편차
---------- -------------------- --------------------
     29025    2073.21              1182.50
```

```sql
  select deptno, 
         sum(sal) "급여 총합", 
         to_char(avg(sal), '999999.99') "급여 평균",
         to_char(stddev(sal), '999999.99') "급여 표준편차"
  from emp
  group by deptno
  order by deptno;
```

```bash
 DEPTNO  급여 총합 급여 평균            급여 표준편차
---------- ---------- -------------------- --------------------
        10       8750    2916.67              1893.63
        20      10875    2175.00              1123.33
        30       9400    1566.67               668.33
```

```sql
  select deptno, 
         job,
         sum(sal) "급여 총합", 
         to_char(avg(sal), '999999.99') "급여 평균",
         to_char(stddev(sal), '999999.99') "급여 표준편차"
  from emp
  group by deptno, job
  order by deptno, job;
```

```bash
  DEPTNO JOB         급여 총합 급여 평균            급여 표준편차
---------- ---------- ---------- -------------------- --------------------
        10 CLERK            1300    1300.00                  .00
        10 MANAGER          2450    2450.00                  .00
        10 PRESIDENT        5000    5000.00                  .00
        20 ANALYST          6000    3000.00                  .00
        20 CLERK            1900     950.00               212.13
        20 MANAGER          2975    2975.00                  .00
        30 CLERK             950     950.00                  .00
        30 MANAGER          2850    2850.00                  .00
        30 SALESMAN         5600    1400.00               177.95

9 행이 선택되었습니다.
```

```sql
  select /* 가공한 결과에 의한 GROUP BY */
         to_char(hiredate, 'Q') as quarter,
         count(*) "인원수", 
         sum(sal) "급여 총합", 
         to_char(avg(sal), '999999.99') "급여 평균",
         to_char(stddev(sal), '999999.99') "급여 표준편차"
  from emp
  group by to_char(hiredate, 'Q') 
  order by quarter;
```

> 💡 분기별 집계

```bash
QU     인원수  급여 총합 급여 평균            급여 표준편차
-- ---------- ---------- -------------------- --------------------
1           4       5250    1312.50               209.66
2           3       8275    2758.33               274.24
3           2       2750    1375.00               176.78
4           5      12750    2550.00              1734.21
```




# ★★ 중요 규칙 ★★
> 📌 ★★ 중요 규칙 ★★
> 복수행 함수로 감싼 컬럼 이외의 모든 컬럼은 반드시
> group by절에 나타나야 함!! (단, literal은 예외임)

```sql
select empno, ename, sal, sum(sal), avg(sal)
from emp;
```

문법 에러!

```bash
select empno, ename, sal, sum(sal), avg(sal)
       *
1행에 오류:
ORA-00937: 단일 그룹의 그룹 함수가 아닙니다
```

	↓↓

```sql
select empno, ename, sal, sum(sal), avg(sal)
from emp
group by empno, ename, sal;
```

문법 에러는 해결되었지만 원하는 결과가 아님!

```bash
  EMPNO ENAME                       SAL   SUM(SAL)   AVG(SAL)
---------- -------------------- ---------- ---------- ----------
      7369 SMITH                       800        800        800
      7499 ALLEN                      1600       1600       1600
      7521 WARD                       1250       1250       1250
      7566 JONES                      2975       2975       2975
      7654 MARTIN                     1250       1250       1250
      7698 BLAKE                      2850       2850       2850
      7782 CLARK                      2450       2450       2450
      7788 SCOTT                      3000       3000       3000
      7839 KING                       5000       5000       5000
      7844 TURNER                     1500       1500       1500
      7876 ADAMS                      1100       1100       1100
      7900 JAMES                       950        950        950
      7902 FORD                       3000       3000       3000
      7934 MILLER                     1300       1300       1300
```

    ↓↓

```sql
select empno, ename, sal, 
         (select sum(sal) from emp) sum_sal,
         to_char((select avg(sal) from emp), '99999.99') avg_sal
from emp
group by empno, ename, sal;
```

적절한 SQL 실력이 있으면 문법 에러를 피해서 충분히 원하는 결과를 만들 수 있음 

```bash
  EMPNO ENAME                       SAL    SUM_SAL AVG_SAL
---------- -------------------- ---------- ---------- ------------------
      7369 SMITH                       800      29025   2073.21
      7499 ALLEN                      1600      29025   2073.21
      7521 WARD                       1250      29025   2073.21
      7566 JONES                      2975      29025   2073.21
      7654 MARTIN                     1250      29025   2073.21
      7698 BLAKE                      2850      29025   2073.21
      7782 CLARK                      2450      29025   2073.21
      7788 SCOTT                      3000      29025   2073.21
      7839 KING                       5000      29025   2073.21
      7844 TURNER                     1500      29025   2073.21
      7876 ADAMS                      1100      29025   2073.21
      7900 JAMES                       950      29025   2073.21
      7902 FORD                       3000      29025   2073.21
      7934 MILLER                     1300      29025   2073.21
```

# HAVING vs WHERE
```sql
select deptno, sum(sal)
  from emp
  where deptno != 20
  group by deptno;
```

```bash
 DEPTNO   SUM(SAL)
---------- ----------
        30       9400
        10       8750
```

```sql
select deptno, sum(sal)
  from emp
  group by deptno
  having deptno != 20;
```

```bash
   DEPTNO   SUM(SAL)
---------- ----------
        30       9400
        10       8750
```

> 💡 `having` 문법?
> -> 일단 where 에 넣어보고 안되면 having을 사용하자 

    ----

```sql
select deptno, sum(sal)
from emp
where sum(sal) <= 10000   /* ORA-00934: 그룹 함수는 허가되지 않습니다 */
group by deptno;
```

```bash
where sum(sal) <= 10000   /* ORA-00934: 그룹 함수는 허가되지 않습니다 */
      *
3행에 오류:
ORA-00934: 그룹 함수는 허가되지 않습니다
```

```sql
select deptno, sum(sal)
from emp
group by deptno
having sum(sal) <= 10000;
```

```bash
 DEPTNO   SUM(SAL)
---------- ----------
        30       9400
        10       8750
```

> 💡 `where` 절에선 사용할 수 없다면 `having` 을 사용해보자.


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


# Set 연산자

>📌 `set`
> - union all : 합집합(중복허용) 
> - union     : 합집합(중복제거) 
> - intersect : 교집합(중복제거)
> - minus     : 차집합(중복제거)
  
```java
A = {1, 1, 1, 2, 2, 3, 3, 3}
B = {3, 3, 4, 4, 4, 4, 5, 5}

A union all B = {1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5}
A union     B = {1, 2, 3, 4, 5}
A intersect B = {3}
A minus     B = {1, 2}
```

### 요구사항
> 📌 두 쿼리의 컬럼 갯수와 데이터 타입이 반드시 일치해야 함

```sql
select empno, ename, sal from emp
union all
select employee_id, last_name, salary from employees;
```

```sql
select empno, ename, sal from emp
union all
select employee_id, first_name||' '||last_name, salary from employees;
```

```sql
select empno, '-' as first_name, ename as last_name, sal from emp
union all
select employee_id, first_name, last_name, salary from employees;
```


## 문제.근무하는 사원이 있는 부서 번호
```sql
select deptno from dept
intersect
select deptno from emp;
```

## 문제.근무하는 사원이 없는 부서 번호
```sql
select deptno from dept
minus
select deptno from emp;
```


## 문제.부하 직원이 있는 사원
```sql
select empno from emp
intersect
select mgr from emp;
```
  
## 문제.부하 직원이 없는 사원
```sql
select empno from emp
minus
select mgr from emp;
```
  
## 문제.빠진 번호 찾기
```sql
drop table t1 purge;

create table t1
as
select level as no
from dual
connect by level <= 100;
  
delete from t1
where no in (select round(dbms_random.value * 100)
from dual
connect by level <= 5);

select * from t1;

select level as no
from dual
connect by level <= 100
minus
select no from t1;
```
  


---
 >Hierarchical Query
---

  - [Hierarchical Queries(Oracle-Docs)](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Hierarchical-Queries.html)
  - [Hierarchical Queries in Oracle(Oracle Base)](https://oracle-base.com/articles/misc/hierarchical-queries)

# 계층 질의 구문 이해

  select *        -- 6 
  from 재료집합   -- 1
  where           -- 4
  start with      -- 2
  connect by      -- 3
  group by        -- 5
  having          -- 7
  order by        -- 8


# 기본 예제

```sql
select /* top-down */
         level, lpad(empno, length(empno)*level, ' ') as empno, 
         ename, sal, job, mgr, deptno, CONNECT_BY_ISLEAF AS leaf
  from emp e
  start with empno = 7839
  connect by prior empno = mgr;

  select /* bottom-up */
         level, lpad(empno, length(empno)*level, ' ') as empno, 
         ename, sal, job, mgr, deptno
  from emp
  start with empno = 7369
  connect by empno = prior mgr;
```



# Pseudocolumns

* [수도컬럼이란?](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Pseudocolumns.html) 
📌 수도컬럼은 테이블의 컬럼들 처럼 기능하는데 실제로 저장된 것은 아니다. 
select 를 할 순 있지만 dml은 할 순 없습니다. 
수도 컬럼은 argument 가 없는 함수와 비슷합니다. 
argument 가 없는 함수들은 각 row 마다 같은 value를 return 하는데,
수도 컬럼은 각 행마다 다른 결과를 return 합니다.

```sql
SQL> select user, sysdate from emp;

SQL> select rownum, user, sysdate from emp;

SQL> select rowid, rownum, user, sysdate from emp;
```

```bash
USER                           SYSDATE
------------------------------ --------
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25
ACE01                          24/04/25

  ROWNUM USER                           SYSDATE
---------- ------------------------------ --------
         1 ACE01                          24/04/25
         2 ACE01                          24/04/25
         3 ACE01                          24/04/25
         4 ACE01                          24/04/25
         5 ACE01                          24/04/25
         6 ACE01                          24/04/25
         7 ACE01                          24/04/25
         8 ACE01                          24/04/25
         9 ACE01                          24/04/25
        10 ACE01                          24/04/25
        11 ACE01                          24/04/25
        12 ACE01                          24/04/25
        13 ACE01                          24/04/25
        14 ACE01                          24/04/25


ROWID                  ROWNUM USER                           SYSDATE
------------------ ---------- ------------------------------ --------
AAAShIAAMAAAAIdAAA          1 ACE01                          24/04/25
AAAShIAAMAAAAIdAAB          2 ACE01                          24/04/25
AAAShIAAMAAAAIdAAC          3 ACE01                          24/04/25
AAAShIAAMAAAAIdAAD          4 ACE01                          24/04/25
AAAShIAAMAAAAIdAAE          5 ACE01                          24/04/25
AAAShIAAMAAAAIdAAF          6 ACE01                          24/04/25
AAAShIAAMAAAAIdAAG          7 ACE01                          24/04/25
AAAShIAAMAAAAIdAAH          8 ACE01                          24/04/25
AAAShIAAMAAAAIdAAI          9 ACE01                          24/04/25
AAAShIAAMAAAAIdAAJ         10 ACE01                          24/04/25
AAAShIAAMAAAAIdAAK         11 ACE01                          24/04/25
AAAShIAAMAAAAIdAAL         12 ACE01                          24/04/25
AAAShIAAMAAAAIdAAM         13 ACE01                          24/04/25
AAAShIAAMAAAAIdAAN         14 ACE01                          24/04/25

```


# Filtering

```sql
select level, lpad(empno, length(empno)*level, ' ') as empno, 
ename, sal, job, mgr, deptno, CONNECT_BY_ISLEAF AS leaf
from emp e
where level in (1, 2)
start with empno = 7839
connect by prior empno = mgr;

select level, lpad(empno, length(empno)*level, ' ') as empno, 
ename, sal, job, mgr, deptno, CONNECT_BY_ISLEAF AS leaf
from emp e
where CONNECT_BY_ISLEAF = 1
start with empno = 7839
connect by prior empno = mgr;
```

  


# order SIBLINGS by 예제
```sql
select level, lpad(empno, length(empno)*level, ' ') as empno, 
ename, sal, job, mgr, deptno
from emp e
start with empno = 7839
connect by prior empno = mgr
order SIBLINGS by sal desc;
```
  


# sys_connect_by_path 예제
```sql
select level, lpad(empno, length(empno)*level, ' ') as empno, 
ename, ltrim(sys_connect_by_path(ename, '/'), '/') as ret
from emp e
start with empno = 7839
connect by prior empno = mgr
order SIBLINGS by sal desc;
```
  

# 계층 질의가 사용된 아래 패턴을 이해하도록 합시다
```sql
select level as no
from dual
connect by level <= 10;
```

```sql
select level,dummy 
from dual 
connect by dummy = dummy
and level < 10;


select count(level) from dual connect by level <= 1000000;

create table t3 
as
select level as no, 
round(dbms_random.value*1000)as price
from dual 
connect by level <= 1000000;

commit;
```


  -> https://blog.naver.com/orapybubu/40067517566


---
>SQL for Aggregation in Data Warehouses
---

# Rollup

* [SQL for Aggregation in Data Warehouses(Oracle Docs)](https://docs.oracle.com/en/database/oracle/oracle-database/18/dwhsg/sql-aggregation-data-warehouses.html#GUID-CA29BB5D-90F5-4DCC-A0B4-99738E9C95D5)

> 📌 `Rollup`
> * `Extension`
> * 컬럼의 개수가 n개면 n+1 가지 종류의 결과 생성
> * 컬럼의 나열 순서가 중요함

```sql
select deptno, job, sum(sal)
from emp
group by deptno, job;

select deptno, job, sum(sal)
from emp
group by ROLLUP(deptno, job);
order by 1, 2
```

```bash
    DEPTNO JOB                  SUM(SAL)
---------- ------------------ ----------
        10 CLERK                    1300
        10 MANAGER                  2450
        10 PRESIDENT                5000
        10                          8750
        20 ANALYST                  6000
        20 CLERK                    1900
        20 MANAGER                  2975
        20                         10875
        30 CLERK                     950
        30 MANAGER                  2850
        30 SALESMAN                 5600
        30                          9400
                                   29025
```

```sql

select deptno, job, sum(sal)
from emp
group by GROUPING SETS((deptno, job), (deptno), ());

  cf. select deptno, decode(deptno, null, 'TOTAL', decode(job, null, 'DEPT_SUM', job)) as job, sum(sal)
from emp
group by ROLLUP(deptno, job);
```
  
