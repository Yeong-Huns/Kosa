
-----------------------------
> Section 4. Joining tables <
-----------------------------

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