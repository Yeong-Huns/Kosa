

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
