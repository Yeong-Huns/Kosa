--2024.04.25
set SERVEROUTPUT on;

select sal, sal, sal from emp;

select 
sum(sal) "급여 총합",
to_char(avg(sal), '999999.99') "급여 평균",
to_char(stddev(sal), '999999.99') "급여 표준편차"
from emp;

select 
deptno, 
sum(sal) "급여 총합",
to_char(avg(sal), '999999.99') "급여 평균",
to_char(stddev(sal), '000000.00') "급여 표준편차"
from emp
group by deptno
order by deptno;

select 
deptno,
job, 
sum(sal) "급여 총합",
to_char(avg(sal), '000000.00') "급평",
to_char(stddev(sal), '999999.99') "급표"
from emp
group by deptno, job
order by deptno, job;

select 
to_char(hiredate, 'Q') "분기",
count(*) "인원수",
sum(sal) "합",
to_char(avg(sal), '999999.99') "급평",
to_char(stddev(sal), '999999.99') "급표"
from emp
group by to_char(hiredate, 'Q')
order by "분기";

--중요 규칙
select empno, ename, sal, sum(sal), avg(sal) from emp;
--단일 그룹의 그룹 함수가 아닙니다.

select empno, ename, sal, sum(sal), avg(sal)
from emp
group by empno, ename, sal;
-- 원하는 결과가 아님

select empno, ename, sal,
(select sum(sal) from emp) "sum_sal",
to_char((select avg(sal) from emp), '999999.99') "emp"
from emp
group by empno, ename, sal;

select deptno, sum(sal)
from emp
where deptno != 20
group by deptno;

select deptno, sum(sal)
from emp
where sum(sal) <= 10000   /* ORA-00934: 그룹 함수는 허가되지 않습니다 */
group by deptno;

select deptno, sum(sal)
from emp
group by deptno
having sum(sal) <= 10000;


-- subquery
drop table t1 purge;

create table t1
as 
select empno, ename, sal 
from emp
where 1 = 2;
--의도적으로 false 을 사용해서 테이블 구조만 복사

select * from t1;

insert into t1
select employee_id, last_name, salary
from employees 
where employee_id < 200
and lower(last_name) like '%e%';
-- e가 포함된 문자

select * from t1;

update t1
set sal = case when sal > (select avg(sal) from t1) then sal*1.1
else sal*1.2
end;

commit;

select * from t1;

delete from t1
where 
sal < (select sal from t1 where empno = 104);

commit;

select * from t1;

select empno, sal, sal*10
from emp e 
where 
sal*10 > (select sal from t1 where empno = 102);

select * from emp
where 
job = (select job from emp where empno = 7788);

select * from emp 
where 
sal > (select avg(sal) from emp);

select * from dept 
where 
deptno in (select deptno from emp);

-- 연산자 사용예제

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

select * from t1 where col > all(select col from t2);

select * from t1 
where 
col > all(select col from t2);
-- 2개 이상의 결과 (col from t2);
select * from t1
where 
col > (select max(col) from t2);
-- t2의 최대값을 구해서 최대값만으로 비교

select * from t1 
where 
col < all(select col from t2);
-- 전부보다 작은것? t2의 제일 작은값보다 작은것

select * from t1
where 
col < (select min(col) from t2);

select * from t1
where
col > any(select col from t2);
--t2 col 아무거나보다 큰값 (제일 작은값 보다 크면 ok)

select * from t1
where 
col > (select min(col) from t2);

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


select * from t1
where 
col1 in (select col1 from t2)
and col2 in (select col2 from t2);
--t2 col1 전부 and t2 col2 전부

select * from t1
where 
(col1, col2) 
in (select col1, col2 from t2);
--t2 의 col1, col2 컬럼과 완벽히 동일한 컬럼 찾기

-- inline view (from 절의 서브쿼리)
select 
deptno as ss,
round(avg(sal),2) "avg_sal"
from emp
group by deptno;
-- 부서별 평균월급 구하기

select * from emp;

select * 
from 
emp e,
(select 
deptno as ss, 
round(avg(sal), 2) as avg_sal
from emp
group by deptno) a
order by 1;



select empno, ename, sal from emp
union all 
select employee_id, last_name, salary from employees ;

select empno, ename, sal from emp
union all 
select employee_id,  first_name||' '||last_name, salary from employees ;

select empno, '-' as first_name, ename as last_name, sal from emp
union all 
select employee_id,  first_name, last_name, salary from employees ;


drop table t1 purge;

create table t1
as
select level as no
from dual 
connect by level <= 100;

commit;

select round(dbms_random.value*100)
from dual
connect by level <= 5;

select empno, ename, 
hiredate - (select max(hiredate) from emp)
from emp;

select 
empno, 
ename, 
hiredate,
(select max(hiredate) from emp)
from emp;

select empno, ename,
abs(hiredate - (select max(hiredate) from emp))
from emp;

select deptno, empno, ename, sal, 
(select sum(sal) from emp where deptno = e.deptno) as dept_sum_sal
from emp e
order by deptno, sal desc;


select deptno, empno, ename, sal,
round(sal/dept_sum_sal, 2) "통계",
dept_sum_sal
from(select deptno, empno, ename, sal, 
(select sum(sal) from emp 
where deptno = e.deptno) as dept_sum_sal
from emp e)
order by deptno, sal desc;

select deptno, empno, ename, sal, 
round(sal/dept_sum_sal, 2) "통계 좀 하는 분 이걸 뭐라고 할까요?", 
dept_sum_sal
from (select deptno, empno, ename, sal, 
(select sum(sal) from emp where deptno = e.deptno) as dept_sum_sal
from emp e)
order by deptno, sal desc;

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




select 
e.empno, 
e.sal,
a.deptno,
to_char(a.avg_sal, '9999.99') "avg_sal"
from emp e;



/*
Top Down
*/
select level, emp.*
from emp 
start with empno = 7839
connect by prior empno = mgr
order by level;

select
level,
lpad(empno, length(empno)*level, ' ') as empno,
ename, sal, job, mgr, deptno, Connect_by_isLeaf as leaf
from emp e
start with empno = 7839
connect by prior empno = mgr;

select
level,
lpad(empno, length(empno)*level, ' ') as empno,
ename, sal, job, mgr, deptno, Connect_by_isLeaf as leaf
from emp e
start with empno = 7839
connect by prior empno = mgr;

select
level,
lpad(empno, length(empno)*level, ' ') as empno,
ename, sal, job, mgr, deptno, Connect_by_isLeaf as leaf
from emp e
start with empno = 7839
connect by prior empno = mgr
order siblings by sal desc;


--filtering

select 
level, 
lpad(empno, length(empno)*level, ' ')as empno, 
ename, sal, job, mgr, deptno, CONNECT_BY_ISLEAF as leaf
from emp e
where level in (1,2)
start with empno = 7839
connect by prior empno = mgr;

--order siblings by

select 
level, 
lpad(empno, length(empno)*level, ' ') as empno, 
ename, sal, job, mgr, deptno
from emp e 
start with empno = 7839 
connect by prior empno = mgr
order by sal desc;
-- 이러면 종속관계가 다 깨짐

select 
level, 
lpad(empno, length(empno)*level, ' ') as empno, 
ename, sal, job, mgr, deptno
from emp e 
start with empno = 7839 
connect by prior empno = mgr
order siblings by sal desc;
--종속관계를 유지하며 급여별로 정렬



select 
level,
lpad(empno, length(empno)*level, ' ')as empno, 
ename, 
sys_connect_by_path(ename, '/') as ret
from emp e 
start with empno = 7839
connect by prior empno = mgr 
order siblings by sal desc;
--sys_connect_by_path 를 사용하여 계층 간에 '/'를 넣어 구분하였다.

select 
level, 
lpad(empno, length(empno)*level, ' ') as empno ,
ename, ltrim(sys_connect_by_path(ename, '/'), '/') as ret
from emp e
start with empno = 7839 
connect by prior empno = mgr
order siblings by sal desc;
--ltrim을 사용하여  맨앞의 '/' 를 지워준다.



select 
level, 
lpad(empno, length(empno)*level, ' ')as empno, 
ename, sal, job, mgr, deptno, CONNECT_BY_ISLEAF as leaf
from emp e
where CONNECT_BY_ISLEAF = 1
start with empno = 7839
connect by prior empno = mgr;

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

system/oracle@localhost:1521/xepdb1;
conn ace01/me@localhost:1521/xepdb1;

--rollup

select deptno , job, sum(sal)
from emp
group by ROLLUP(deptno, job);


select deptno, job, sum(sal)
from emp
group by GROUPING SETS((deptno, job),(deptno),())
order by 1, 3;


select * from emp;

-- Grouping function
Drop table emp3 purge;






create or replace view v1 as 
select empno, ename, job from emp;

declare 
rec1 v1%rowtype;
rec1 r1;
begin
select empno, ename into r1
from emp
where empno = 7369;
DBMS_output.put_line(r1.empno, r1.ename);
end;
/