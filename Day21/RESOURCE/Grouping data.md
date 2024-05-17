
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
  