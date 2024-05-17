SELECT * FROM EMP, DEPT, SALGRADE;

SELECT 100*100 FROM DUAL;




SET SERVEROUTPUT ON;
select * from emp, dept;

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
  
  ---
  SELECT * FROM EMP, DEPT;
  -- 56RJSDM
  
  SELECT * FROM EMP, DEPT WHERE EMP.DEPTNO = DEPT.DEPTNO;
  -- 56건 단일테이블에서 부서번호가 같은 값을 구한다(14건)
  
   SELECT * FROM EMP, DEPT WHERE EMP.DEPTNO = DEPT.DEPTNO
   AND EMP.SAL>= 2000;
   -- 8건
   
   SELECT EMPNO, SAL, GRADE FROM EMP, SALGRADE 
   WHERE SAL >= LOSAL AND SAL <= HISAL
   ORDER BY GRADE;
   
   SELECT EMPNO, SAL, GRADE FROM EMP E, SALGRADE S 
   WHERE E.SAL >= S.LOSAL AND E.SAL <= S.HISAL;
   
   
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
  
  
  select w.*, m.empno, m.ename
from emp w, emp m
where w.mgr = m.empno
order by 1;


--SELF JOIN
SELECT W.EMPNO, W.ENAME, M.EMPNO, M.ENAME FROM EMP W , EMP M
WHERE W.MGR = M.EMPNO
ORDER BY 1;

--7499
SELECT E.EMPNO, E.ENAME, E.SAL, A.EMPNO, A.ENAME, A.SAL FROM EMP E, EMP A WHERE A.EMPNO = 7499 AND E.SAL > A.SAL;
-- SELF JOIN + NON EQUI JOIN

SELECT * FROM EMP W, EMP M WHERE W.MGR = M.EMPNO;


--outter join
select w.*, m.empno, m.ename
from emp w, emp m
where w.mgr  = m.empno (+)
order by 1;

--natural join
select * from emp e NATURAL JOIN dept d;

select * from departments d join employees e 
using (department_id);

select *
      from emp e JOIN salgrade s
                   ON e.sal >= s.losal and e.sal <= s.hisal
      order by 1;
      
select w.*, m.empno, m.ename
      from emp w LEFT outer JOIN emp m
                        ON w.mgr  = m.empno
      order by 1;
      
      
--left 

select *
from employees e FULL JOIN departments d
ON e.department_id = d.department_id
order by 1;

SELECT E.EMPNO, E.ENAME, E.SAL, D.LOC 
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
ORDER BY 1;

SELECT * 
FROM EMP E, SALGRADE S
WHERE E.SAL >= S.LOSAL AND E.SAL <= S.HISAL
ORDER BY 1;

SELECT W.*, M.EMPNO, M.ENAME
FROM EMP W, EMP M
WHERE W.MGR = M.EMPNO
ORDER BY 1;


SELECT W.*, M.EMPNO, M.ENAME 
FROM EMP W, EMP M 
WHERE W.MGR = M.EMPNO (+)
ORDER BY 1;


SELECT * 
FROM EMP E 
CROSS JOIN DEPT D;

SELECT * 
FROM EMP E, DEPT D;
--동일 결과

SELECT E.EMPNO, E.ENAME, E.SAL, D.LOC
FROM EMP E 
NATURAL JOIN DEPT D;
-- EMP 테이블과 DEPT 테이블의 같은 이름 컬럼 전부로 EQUL JOIN 수행

SELECT E.EMPNO, E.ENAME, E.SAL, D.LOC
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO;

select sum(sal), avg(sal), max(sal), min(sal) 
from emp;

select *
from emp, dept
order by dept.deptno;

select *
from emp, dept
order by EMP.EMPNO;

SELECT DEPTNO, SUM(SAL) FROM EMP GROUP BY DEPTNO;


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


select deptno, job, SAL
from emp;

select deptno, job, sum(sal)
from emp
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO;
-- 부서번호와 JOB으로 GROUP BY 
-- 같은 부서번호와 직업이면 급여의 합을 표시

select deptno, sum(sal)
from emp
group by deptno;

select null, null, sum(sal)
from emp
order by 1, 2;


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

SELECT DEPARTMENT_ID, DEPARTMENT_NAME, EMPLOYEE_ID, FIRST_NAME||LAST_NAME 
FROM DEPARTMENTS D 
NATURAL JOIN employees E;

SELECT DEPARTMENT_ID, DEPARTMENT_NAME, EMPLOYEE_ID, FIRST_NAME||LAST_NAME 
FROM DEPARTMENTS D JOIN EMPLOYEES E 
USING (DEPARTMENT_ID);

SELECT MANAGER_ID FROM DEPARTMENTS;
SELECT MANAGER_ID FROM EMPLOYEES;

SELECT d.DEPARTMENT_ID AS 부서ID,
DEPARTMENT_NAME AS 부서이름,
EMPLOYEE_ID AS 사번,
FIRST_NAME||' '||LAST_NAME AS 사원명
FROM DEPARTMENTS D JOIN EMPLOYEES E
USING (DEPARTMENT_ID);

SELECT D.DEPARTMENT_ID AS 부서ID,
D.DEPARTMENT_NAME AS 부서이름,
E.EMPLOYEE_ID AS 사번,
E.FIRST_NAME||' '||E.LAST_NAME AS 사원명
FROM DEPARTMENTS D JOIN EMPLOYEES E
ON (D.DEPARTMENT_ID = E.DEPARTMENT_ID);

select empno, deptno
from emp e JOIN dept d
USING (deptno);


SELECT * FROM EMP;
SELECT * FROM DEPT;


SELECT E.EMPNO, E.ENAME, E.SAL, D.LOC 
FROM EMP E JOIN DEPT D
ON E.DEPTNO = D.DEPTNO
ORDER BY 1;

SELECT * FROM SALGRADE;

select E.EMPNO, E.ENAME, E.SAL, S.GRADE
from emp e JOIN salgrade s
ON e.sal >= s.losal and e.sal <= s.hisal
order by S.GRADE;

SELECT W.*, M.EMPNO, M.ENAME 
FROM EMP W JOIN EMP M
ON W.MGR = M.EMPNO 
ORDER BY 1;
-- 사번 기준 정렬
--SELF JOIN 

SELECT * FROM T1;

SELECT A.A AS AA, A.B AS AB
FROM T1 A JOIN T1 B
ON 1=1
ORDER BY 1;


SELECT A.A AS AA, A.B AS AB, B.B AS BB
FROM T1 A JOIN T1 B
ON A.A >= B.A

ORDER BY 1;

SELECT A.A AS AA, A.B AS AB, SUM(B.B) AS 합계
FROM T1 A JOIN T1 B
ON A.A >= B.A
GROUP BY A.A , A.B
ORDER BY 1;

SELECT A.A AS AA, A.B AS AB
FROM T1 A JOIN T1 B
ON A.A >= B.A
GROUP BY A.A, A.B
ORDER BY 1;

select a.a, a.b, sum(b.b) 누적합
from t1 a JOIN t1 b
ON a.a >= b.a
group by a.a, a.b
order by a.a;

SELECT A.A, A.B, SUM(B.B) AS 누적합
FROM T1 A JOIN T1 B 
ON A.A >= B.A 
GROUP BY A.A, A.B
ORDER BY A.A;


select e.empno,
e.sal, 
s.empno as scott_empno,
s.sal as scott_sal
FROM EMP E JOIN EMP S
ON E.SAL >= S.SAL
WHERE S.EMPNO = 7788;

select e.empno,
e.sal, 
s.empno as scott_empno,
s.sal as scott_sal
from emp e JOIN emp s
ON e.sal >= s.sal 
where s.empno = 7788 
and e.empno != 7788;
--SELF JOIN 
--NOT EQUI
-- 자기 자신과 비교하지 않기 위해 
--AND E.EMPNO != 7788


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
    
select sum(sal), avg(sal), max(sal), min(sal) 
from emp;

select *
from emp, dept
order by dept.deptno;

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