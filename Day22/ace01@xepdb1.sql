--2024 04 26

drop table emp3 purge;

create table emp3 as select * from emp;

update emp3 set job = null where rownum = 1;

select deptno, job, sum(sal) as sum_sal
from emp3
group by ROLLUP(deptno, job);

--

select deptno, job, sum(sal) as sum_sal
from emp3
group by Rollup(deptno, job);

select *
from (select deptno, job, sum(sal) as sum_sal
from emp3
group by ROLLUP(deptno, job))
where deptno is not null 
and job is null;

select deptno, job, sum(sal) as sum_sal,
grouping(deptno) as g_deptno,
grouping(job) as g_job
from emp3
group by rollup(deptno, job);


select *
from(select deptno, job, sum(sal) as sum_sal, 
grouping(deptno) as g_deptno,
grouping(job) as g_job,
grouping_id(deptno, job) as g_deptno_job
from emp3
group by CUBE(deptno, job))
where g_deptno_job in (0, 3);


select deptno, job, sum(sal) as sum_sal, 
grouping(deptno) as g_deptno, 
grouping(job) as g_job
from emp3
group by rollup(deptno, job);

select deptno, job, sum_sal
from(select deptno, job, sum(sal) as sum_sal, 
grouping(deptno) as g_deptno, 
grouping(job) as g_job
from emp3
group by rollup(deptno, job))
where g_deptno = 0
and g_job = 1;



--- # Composite column

drop table emp2 purge;

create table emp2
as
select  empno, ename, sal, job, deptno, decode(mod(empno, 2), 0, 'M', 'W') as gender
from emp e
union all
select  empno, ename, sal+100, job, deptno, decode(mod(empno, 2), 0, 'W', 'M') as gender
from emp e;

select * from emp2;


select deptno, job, gender, sum(sal)
from emp2
group by deptno, job, gender;




--analytic function
select deptno, empno, ename, sal, 
rank() over(order by sal desc) as rank 
from emp
order by rank;


select deptno, empno, ename, sal, comm
from emp 
order by nvl(comm, -1) desc;
-- 커미션값이 존재하면 커미션값으로, 존재하지않으면 -1

select deptno, empno, ename, sal, comm
from emp ;

select deptno, empno, ename, sal
from emp
order by sal desc;
-- 급여로 내림차순 정렬

select deptno, empno, ename, sal,
rank()over(order by sal desc)
from emp;
--rank() : 같은 순위가 여러명이면 직전 등수 + 같은 순위의 인원수 

select deptno, empno, ename, sal, 
rank()over(order by sal desc),
dense_rank()over(order by sal desc)
from emp;
--dense_rank() : 같은 순위가 여러명이더라도 직전의 등수에서 +1

select deptno, empno, ename, sal, 
rank()over(order by sal desc) as rank,
dense_rank()over(order by sal desc) as dense_rank,
row_number()over(order by sal desc) as row_number
from emp;
--rownum은 동점자가 있어도 그냥 1씩 증가


select deptno, empno, ename, sal, 
rank() over(partition by deptno order by sal desc) as rank,
dense_rank() over(partition by deptno order by sal desc) as dense_rank,
row_Number() over(partition by deptno order by sal desc) as ROW_number
from emp;
-- 부서안에서 랭크를 정한다.

--Insert 

drop table t1 purge;

create table t1
as
select * from dept
where 1 = 2;

truncate table t1;

select * from t1;

alter table t1 drop column deptno;
--컬럼하나 삭제

alter table t1 add(deptno number(4) GENERATED as IDENTITY);
--자동으로 숫자가 증가하는 컬럼 추가

select * from t1;

insert into t1 values('ACCOUNTING', 'NEW YORK', 10);
-- 자동생성컬럼에 값을 줄 수 없다.
insert into t1 values('ACCOUNTING', 'NEW YORK', null);
-- 자동생성컬럼에 값을 줄 수 없다.
insert into t1(dname, loc) values('ACCOUNTING', 'NEW YORK');

truncate table t1;

alter table t1 drop column deptno;
--deptno 절삭
alter table t1 add(deptno number(4) generated as identity start with 10 INCREMENT by 10);

insert into t1(dname, loc)
select dname, loc from dept;


select * from t1;


--데이터 무결성
drop table t1 purge;
show recyclebin;
purge recyclebin;

drop table t_books purge;

drop table t_emp purge;
drop table t_dept purge;

create table t_dept(
deptno number(2),
dname varchar2(30 char),
loc varchar2(30 char));

-- dept 번호를 무결성을 가지게 하고싶다.
insert into t_dept values (10, 'HR', 'SEOUL'); -- 성공
insert into t_dept values (10, 'MARKETING', 'SEOUL'); -- 성공해서 문제!

commit;

--서로 다른부서는 다른 번호를 가지게 하고 싶은데 같은 값을 가지게 된다...

select * from t_dept;

create table t_emp(
empno number(4),
ename varchar2(30 byte),
sal number(10, 2),
hphone varchar2(11),
hiredate date, 
deptno number(2)
);

drop table t_emp purge;
drop table t_dept purge;

create table t_dept(
deptno number(2) primary key, /*not null + unique*/
dname varchar2(30 char) not null,
loc varchar2(30 char) not null
);

insert into t_dept values (10, 'HR', 'SEOUL'); -- 성공
insert into t_dept values (10, 'MARKET', 'SEOUL'); --실패
insert into t_dept values (null, 'HR', 'SEOUL'); -- 실패  
insert into t_dept values (20, 'MARKET', 'SEOUL'); --성공

commit;


create table t_dept_2(
deptno number(2) unique, /*unique*/
dname varchar2(30 char) not null,
loc varchar2(30 char) not null
);

insert into t_dept_2 values (10, 'HR', 'SEOUL'); -- 성공
insert into t_dept_2 values (10, 'MARKET', 'SEOUL'); --실패
insert into t_dept_2 values (null, 'HR', 'SEOUL'); -- 성공
insert into t_dept_2 values (20, 'MARKET', 'SEOUL'); --성공
insert into t_dept_2 values (null, 'MARKET', 'SEOUL'); --성공

--unique 는 null 을 막지않는다 
-- 이미 null 이 들어와있어도 다른 null을 넣을 수 있다
--null = null -> 알수없음 이기때문


select * from t_dept;

create table t_emp(
empno number(4) primary key,
ename varchar2(30) not null, 
sal number(10, 2) not null check(sal >= 300),
hphone varchar2(11) not null unique,
hiredate date, 
deptno number(2) references t_dept(deptno)
);

insert into t_emp
select empno, ename, sal, rownum, hiredate, deptno
from emp
where deptno in(10,20);

select * from t_emp;

select * from user_tables      where table_name in ('T_DEPT', 'T_EMP');
select * from user_constraints where table_name in ('T_DEPT', 'T_EMP');
select * from user_indexes     where table_name in ('T_DEPT', 'T_EMP');

drop table t_emp purge;
drop table t_dept purge;

create table t_dept
(deptno number(2)         constraint t_dept_deptno_pk primary key,
dname  varchar2(30 char) constraint t_dept_dname_nn  not null,
loc    varchar2(30 char) constraint t_dept_loc_nn    not null
);

insert into t_dept values (10, 'HR', 'SEOUL');           -- 성공
insert into t_dept values (10, 'MARKETING', 'SEOUL');    -- 실패
insert into t_dept values (null, 'MARKETING', 'SEOUL');  -- 실패
insert into t_dept values (20, 'MARKETING', 'SEOUL');    -- 성공

create table t_emp
(empno number(4)        constraint t_emp_empno_pk  primary key,           
ename    varchar2(30)  constraint t_emp_ename_nn  not null,
sal      number(10, 2) constraint t_emp_sal_nn    not null
constraint t_emp_sal_ck    check(sal >= 300),
hphone   varchar2(11)  constraint t_emp_hphone_nn not null
constraint t_emp_hphone_uk unique,
hiredate date,
deptno   number(2)     constraint t_emp_deptno_fk references t_dept(deptno)
);

--first/last functions
select 
employee_id, 
salary,
department_id, 
first_value(salary) over (partition by department_id order by salary)
as first_salary, 
last_value(salary) over (partition by department_id order by salary) 
as last_salary
from employees;


insert into t_emp
select empno, ename, sal, rownum, hiredate, deptno
from emp
where deptno in (10, 20);


drop table t_emp purge;
drop table t_dept purge;

create table t_dept(
deptno number(2) constraint t_dept_deptno_pk primary key, 
dname varchar2(30 char) constraint t_deptno_nn not null,
loc varchar2(30 char) constraint t_dept_loc_nn not null 
);

insert into t_dept values(10, 'HR', 'SEOUL'); --성공
insert into t_dept values(10, 'marketing', 'SEOUL'); --실패
insert into t_dept values(null, 'marketing', 'SEOUL'); --실패
insert into t_dept values(20, 'marketing', 'SEOUL'); --성공

commit;

select * from t_dept;

create table t_emp(
empno number(4) constraint t_emp_empno_pk primary key,
ename varchar2(30) constraint t_emp_ename_nn not null,
sal number(10, 2) 
constraint t_emp_sal_nn not null
constraint t_emp_sal_ck check(sal >= 300),
hphone varchar2(11) constraint t_emp_hphone_nn not null 
constraint t_emp_hphone_uk unique,
hiredate date,
deptno number(2) constraint t_emp_deptno_fk references t_dept(deptno)
);

insert into t_emp 
select empno, ename, sal, rownum, hiredate, deptno
from emp
where deptno in (10, 20);

select * from t_emp;

select * from user_tables where table_name in ('T_DEPT', 'T_EMP');
select * from user_constraints where table_name in ('T_DEPT', 'T_EMP');
select * from user_indexes where table_name in ('T_DEPT','T_EMP');


drop table t_emp purge ;
drop table t_dept purge;


--- Identity Columns 
insert into identity_test_

