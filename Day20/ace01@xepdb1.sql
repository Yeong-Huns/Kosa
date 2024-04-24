--04 월 24일

set serveroutput on;

select e.employee_id, e.first_name||' '||e.last_name as name, d.department_name
from employees e join departments d
on e.department_id = d.department_id
order by 1
fetch first 11 rows only;

--dual
select ltrim ('xyxyxYxy', 'xy')
from dual;
--x 또는 y

select ltrim ('98e102', '[0-9]')
from dual;

select round(15.193, 1) as Round 
from dual;

select * from dual;

select empno, ename, 100 from emp where job = 'SALESMAN';

select '2 x '||level||' = '||level * 2 "2단"
from dual
connect by level <= 9;

select level, level*2, level*5, level*10 from dual connect by level <= 10;

drop table t1 purge;
create table t1 (col varchar2(30));
insert into t1 values ('myname@naver.com');

commit;
select substr(col, 1, instr(col, '@') - 1) as ret1,
substr(col, instr(col, '@') + 1) as ret2
from t1;

select ltrim('sses', 's') from dual;

select ltrim(rtrim('ses', 's'), 's')from dual;
select trim(both 's' from 'ses') from dual;

select ltrim('xyxYxy', 'xy') from dual;
select trim(leading 'xy' from 'xyxYxy') from dual;

select empno, sal, replace(sal, '0', '*') as ret1, 
lpad(replace(sal, '0', '*'), 6, ' ') as ret2
from emp;

select replace('JAMES', 'MES', 'NE') as ret
from dual;

select translate('my phone number : 010-1234-5555','0123456789',
'**********')
from dual;

--rpad
select empno, sal, sal
from emp;

select empno, sal, ceil(sal/100)
from emp;

select empno, sal, '*', ceil(sal/100)
from emp;

select empno, sal, rpad('*', ceil(sal/100), '*') as bar
from emp;

select ename, 
substr(ename, 1, 1)||'*'||substr(ename, -1, 1) as ret1,
substr(ename, 1, 1)||rpad('*', length(ename) - 2, '*')||substr(ename, 1, 1) as ret2
from emp;

select substr('abcdefg',3) from dual;

select ename,
substr(ename, 1, 1)||'*'||substr(ename, -1, 1) as ret1,
substr(ename, 1, 1)||translate(substr(lower(ename), 3), 'abcdefghijklmnopqrstuvwxyz','**************************')||
substr(ename, -1, 1) as ret2
from emp;

select round(45.995, 2) a,
round(45.995, -1) b,
round(55445.995, -1)c
from dual;

select trunc(45.995, 2) a,     -- 45.99
trunc(45.995, -2) b,    -- 0
trunc(55445.995, -2) c  -- 55400
from dual;

select sysdate from dual;

alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';
select sysdate from dual;  

alter session set nls_language = 'AMERICAN';
alter session set nls_date_format = 'DD-MON-YY';

alter session set nls_language = 'korean';
alter session set nls_date_format = 'DD-MON-YY';



select sysdate from dual;

select empno, sysdate - hiredate from emp;

select ceil((sysdate - to_date('01-JAN-1972', 'DD-MON-YYYY'))/7) as weeks
from dual;

select ceil((sysdate - to_date('01-JAN-1972', 'DD-MON-YYYY'))/7) as weeks
from dual;

select sysdate, sysdate + 100, sysdate + 1000, sysdate + 10000
from dual;

alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';

select sysdate, 
sysdate + 5/24 + 36/(24*60) + 51/(24*60*60) "5시간 36분 51초 뒤",
sysdate + interval '5:36:51' hour to second "5시간 36분 51초 뒤"
from dual;	

select empno, hiredate, sysdate, ceil(sysdate - hiredate), ceil(months_between(sysdate, hiredate))
from emp;

select next_day(sysdate, 'MON')
from dual;

select last_day('11-FEB-23')
from dual;


alter session set nls_language = 'AMERICAN';
alter session set nls_date_format= 'DD-MON-YY';

select empno, hiredate, to_char(hiredate, 'YYYY YYYY YYYY') from emp;
select empno, hiredate, to_char(hiredate, 'YYYY Q MM WW W DDD DD D Day') from emp;

select empno, hiredate, to_char(hiredate, 'Year Month Day DAY day') from emp;
select empno, hiredate, to_char(hiredate, 'Year Month Day DAY day') from emp;

alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';
select sysdate, to_char(sysdate, 'sssss') from dual;

alter session set time_zone = '+03:00';
select localtimestamp, to_char(localtimestamp, 'sssss') from dual;

select to_char(sysdate, 'YYYY')||'년 '||to_char(sysdate, 'MM')||'월 '||to_char(sysdate, 'DD')||'일' as ret1,
to_char(sysdate, 'YYYY"년" MM"월" DD"일"') as ret2
from dual;

-- to_char

select to_char(sysdate, 'YYYY"년" MM"월" DD"일"') as date from dual;
select to_char(hiredate, 'YYYY Month DD HH24:MM:SS') as ret1,
to_char(hiredate, 'fmYYYY Month DD fmHH24:MM:SS') as ret2   /* fm = fill mode */
from emp;

select empno, hiredate, to_char(hiredate, 'Year Month Day DAY day') from 
emp;

select to_char(hiredate, 'YYYY Month DD HH24:MM:SS') as ret1,
to_char(hiredate, 'fmYYYY Month DD fmHH24:MM:SS') as ret2
from emp;   /* fm = fill mode */

select empno, hiredate, to_char(hiredate, 'DD Ddsp Ddth Ddspth Ddthsp')
from emp;

alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';
select sysdate, to_char(sysdate, 'sssss') from dual;

alter session set time_zone = '+09:00';
select localtimestamp, to_char(localtimestamp, 'sssss') from dual;

select to_char(sysdate, 'YYYY')||'년 '||to_char(sysdate, 'MM')||'월'||
to_char(sysdate, 'DD')||'일' as ret1,
to_char(sysdate, 'YYYY"년" MM"월" DD"일"') as ret2
from dual;

select to_char(sysdate, 'YYYY"년" MM"월" DD"일"') "ret2"
from dual;

select sal, 
to_char(sal, '$9,999,999.99') as ret1,
to_char(sal, 'L0,000,000.00') as ret2
from emp;
--9 : 있으면 보여주고 없으면 비워라
--0 : 없으면 0으로 채우고 있으면 보여줘라
--
alter session set nls_territory = 'america';
alter session set nls_territory = 'korea';


select empno, ename, sal, nvl(comm, 0) + 100 from emp;
select empno, ename , sal,  nvl2(comm, comm, 0) + 100 from emp;

select empno, ename, comm, 
nvl(to_char(comm), '없음') as ret1,
nvl2(comm, 'comm 있음', 'comm 없음') as ret2
from emp;


select empno, ename, sal, comm,
sal*12 + nvl(comm, 0)
from emp;

select empno, ename, sal, comm,
sal*12 + nvl(comm, 0)
from emp;

select empno, ename, sal, comm, 
nvl2(comm, sal*12+comm, sal*12) as annual_salary,  
nvl2(comm, 'sal*12+comm', 'sal*12') as gubun
from emp;

select empno, sal, '' whatif, deptno
from emp;

select /* decode 함수 */
empno, sal, decode(deptno, 10, sal*1.1, 20, sal*1.2, sal) as whatif, deptno 
from emp;


select /* simple case 표현식 */
empno, sal, case deptno when 10 then sal*1.1 
when 20 then sal*1.2 
else         sal 
end as whatif, deptno 
from emp;

select /* searched case 표현식 */
empno, sal, case when deptno = 10 then sal*1.1
when deptno = 20 then sal*1.2
else sal
end as whatif, deptno 
from emp;

select empno, sal, case deptno when 10 then sal*1.1
when 20 then sal*1.2 
else sal
end as whatif, deptno
from emp;


select empno, ename, job, decode(job, 'ANALYST' , '청계산'
                                          , 'MANAGER' , '청계산'
                                          , 'CLERK'   , '한라산'
                                          , 'SALESMAN', '한라산'
                                          , 'PRESIDENT', '백두산'
                                          , '어딘가') as place
      from emp;
      
      
select sal, sal, sal, sal, sal, to_char(hiredate, 'YYYY') from emp;

select sal, sal, sal, sal, sal, year
from(select sal, to_char(hiredate, 'YYYY') as year from emp)
;



select job,
sum(sal) as total,
sum(decode(year, '1983', sal)) as y1983, 
sum(decode(year, '1982', sal)) as y1982, 
sum(decode(year, '1981', sal)) as y1981, 
sum(decode(year, '1983', null, '1982', null, '1981', null, sal)) as previous
from (select job, sal, to_char(hiredate, 'YYYY') as year from emp)
group by job
order by job;
--group by 




select job, 
nvl(sum(sal), 0) as total, 
nvl(sum(decode(year , '1983', sal)), 0) as y1983, 
nvl(sum(decode(year , '1982', sal)), 0) as y1982, 
nvl(sum(decode(year , '1981', sal)), 0) as y1981, 
nvl(sum(decode(year , '1983', null, '1982', null, '1981', null, sal)), 0) as previous
from(select job, sal, to_char(hiredate, 'YYYY') as year from emp)
group by job
order by job;
--nvl 활용


-- 입사자수
select empno, deptno, to_char(hiredate, 'Q') as quarter
from emp;
-- Q : 분기

select empno, deptno, quarter, quarter, quarter, quarter 
from (select empno, deptno, to_char(hiredate, 'Q') as quarter from emp);

select deptno, 
decode(quarter, '1', 1) as q1, 
decode(quarter, '2', 1) as q2, 
decode(quarter, '3', 1) as q3,
decode(quarter, '4', 1) as q4
from (select deptno, to_char(hiredate, 'Q') as quarter from emp);

select 
count(decode(quarter, '1', 1)) as q1, 
count(decode(quarter, '2', 1)) as q2, 
count(decode(quarter, '3', 1)) as q3,
count(decode(quarter, '4', 1)) as q4
from (select to_char(hiredate, 'Q') as quarter from emp);

select deptno,
count(decode(quarter, '1', 1)) as q1, 
count(decode(quarter, '2', 1)) as q2, 
count(decode(quarter, '3', 1)) as q3,
count(decode(quarter, '4', 1)) as q4
from (select deptno, to_char(hiredate, 'Q') as quarter from emp)
group by deptno
order by deptno;


select 
e.deptno as deptno, 
e.job as job,
e.sum_sal, 
n.no
from 
(select deptno, job, sum(sal) as sum_sal from emp group by deptno, job) e,
(select level as no from dual connect by level <= 3) n
order by n.no, e.deptno;

select 
decode(n.no, 1, e.deptno, 2, e.deptno) as deptno,
decode(n.no, 1, e.job) as job,
sum(e.sum_sal) as sum_sal 
from 
(select deptno, job, sum(sal) as sum_sal from emp group by deptno, job) e,
(select level as no from dual connect by level <= 3) n
group by decode(n.no, 1, e.deptno, 2, e.deptno), decode(n.no, 1, e.job);



 select decode(n.no, 1, e.deptno, 2, e.deptno) as deptno, 
e.job, e.sum_sal, n.no from (select deptno, job, sum(sal) as sum_sal
          from emp
          group by deptno, job) e,
         (select level as no
          from dual
          connect by level <= 3) n;
          
select decode(n.no, 1, e.deptno, 2, e.deptno) as deptno, 
decode(n.no, 1, e.job) as job, 
sum(e.sum_sal) as sum_sal
from 
(select deptno, job, sum(sal) as sum_sal from emp group by deptno, job) e,
(select level as no from dual connect by level <= 3) n
group by decode(n.no, 1, e.deptno, 2, e.deptno), decode(n.no, 1, e.job) 
order by 1, 2;
          

select 
decode(n.no, 1, e.deptno, 2, e.deptno) as deptno,
decode(n.no, 1, e.job) as job,
sum(e.sum_sal) as sum_sal 
from 
(select deptno, job, sum(sal) as sum_sal from emp group by deptno, job) e,
(select level as no from dual connect by level <= 3) n
group by decode(n.no, 1, e.deptno, 2, e.deptno), decode(n.no, 1, e.job)
order by 1,2 ;


---복습

--dual

select ltrim('xyxyxYxy', 'xy')
from dual;
-- ltrim -> xy 를 지우는게아니라 x or y 임!

select 45 * 56 from emp;

select 45 * 56 from dual;
-- 

select '9 x '||level||' = '||9*level as "9단"
from dual 
connect by level <= 9;

select  
'3 x '||level||' = '||level*3 "3단", 
'5 x '||level||' = '||level*5 "5단", 
'7 x '||level||' = '||level*7 "7단"
from dual 
connect by level < 10;

--
/*
Character : upper, lower, initcap, substr, length, replace, translate, ltrim, rtrim, trim, lpad, rpad,
*/

--instr
select instr('my name is unknown', 'm') from dual;
--m 의 인덱스를 찾는다
select instr('my name is unknown', 'm', 3) from dual;
--m 의 인덱스를 찾는데, 3번 위치부터 찾는다
select instr('my name is unknown', 'm', 1, 2) from dual;
--m 의 인덱스를 1부터 찾는데, 2번째로 일치하는 m의 위치를 출력한다.

--substr + instr
select substr(col, 1, instr(col, '@') - 1) as ret1,
substr(col, instr(col, '@')+ 1)as ret2 
from t1;
-- col의 맨 처음부터 "@"전까지 as ret1
-- col의 @ 바로 뒤부터 끝까지 as ret2


select ltrim('SSES', 'S')from dual;

select trim(leading 'S' from 'SSES') from dual;
--leading : 앞에나오는 s 제거 

select rtrim('SES', 'S') from dual;
select trim(TRAILING 'S' from 'SES') from dual;

select ltrim(rtrim('SES', 'S'), 'S') from dual;
select trim(both 'S' from 'SES') from dual;

select ltrim('xyxyxYxy', 'xy') from dual;
--Yxy
select trim(leading 'xy' from 'xyxyxYxy') from dual;

select empno, sal, replace(sal, '0', '*')as ret1,
lpad(replace(sal, '0', '*'), 6, ' ')as ret2
from emp;

select replace('JAMES', 'MES', 'NE') as ret
from dual;

--translate
select translate('my phone number : 010-1234-5555', '0123456789', '**********')
from dual;

--rpad (bar Chart)
select empno, sal, sal
from emp;
-- sal 복제

select empno, sal, ceil(sal/100)
from emp;
-- ceil -> 소수점 값이 있다면 무조건 올려서 정수 반환
-- sal 을 100으로 나누고, 올림하여 반환

select empno, sal, '*', ceil(sal/100)
from emp;

select empno, sal, rpad('*', ceil(sal/100), '*') as bar 
from emp;

drop table t1 purge;
create table t1(col varchar2(30));


insert into t1 values ('A1023');
insert into t1 values ('98e023');
insert into t1 values ('7821');
insert into t1 values ('1232221');
    
commit;

select col, ltrim(col, '0123456789') as "isNUll?"
from t1 ;

select col
from t1
where ltrim(col, '0123456789') is null;

select col, upper(col), lower(col) from t1;

select col 
from t1
where upper(col) = lower(col);

select substr(ename, 1, 1)||'*'||substr(ename, -1, 1) as name
from emp;

select substr(ename, 1, 1)||rpad('*', length(ename)-2, '*')||substr(ename, -1, 1) as name
from emp;

select ename,
substr(ename, 1, 1)||'*'||substr(ename, -1, 1) as ret1,
substr(ename, 1, 1)||translate(substr(lower(ename), 3), 'abcdefghijklmnopqrstuvwxyz','**************************')||substr(ename, -1, 1) as ret2
from emp;

select round(45.995, 2) a,
round(45.995, -2) b, 
round(55445.995, -2) c
from dual;
--소수점 기준 거꾸로

alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';

select sysdate from dual;

select ceil(sysdate - to_date('1994-oct-07', 'YYYY-MON-DD')) as Days from dual;

select empno, sal, '' whatif, deptno
from emp;

select 
empno, sal, decode(deptno, 10, sal*1.1, 20, sal*1.2, sal) as whatif, deptno 
from emp;
-- deptno(부서번호)가 10 이면 월급*1.1 / 20면 월급*1.2 / 나머진 그대로


select /* decode 함수 */
empno, sal, decode(trunc(sal/2000), 0, sal*0.01, 1, sal*0.02, sal*0.03) as tax
from emp;


select 
empno, sal, case trunc(sal/2000) 
when 0 then sal*0.01
when 1 then sal*0.02
else        sal*0.03
end as tax
from emp;

select sal, sal, sal, sal, deptno
from emp;

select sal,
decode(deptno, 10, sal) "10",
decode(deptno, 20, sal) "20",
decode(deptno, 30, sal) "30",
deptno
from emp
order by deptno;

select 
sum(sal) "total",
sum(decode(deptno, 10, sal)) "D_10",
sum(decode(deptno, 20, sal)) "D_20",
sum(decode(deptno, 30, sal)) "D_30"
from emp;


select job, 
sum(sal) "total",
sum(decode(deptno, 10, sal)) "D_10",
sum(decode(deptno, 20, sal)) "D_20",
sum(decode(deptno, 30, sal)) "D_30"
from emp
group by job
order by job;



select job, 
nvl(sum(sal), 0) "total",
nvl(sum(decode(deptno, 10, sal)), 0) "D_10",
nvl(sum(decode(deptno, 20, sal)), 0) "D_20",
nvl(sum(decode(deptno, 30, sal)), 0) "D_30"
from emp
group by job
order by job;

select empno, deptno, to_char(hiredate, 'Q') as quarter
from emp;
--Q: 분기
select empno, deptno, quarter, quarter, quarter, quarter
from (select empno, deptno, to_char(hiredate, 'Q') as quarter
from emp);

select deptno, decode(quarter, '1', 1) as q1, quarter, quarter, quarter
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp);

select deptno, 
decode(quarter, '1', 1) as q1, 
decode(quarter, '2', 1) as q2, 
decode(quarter, '3', 1) as q3, 
decode(quarter, '4', 1) as q4
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp);

select count(decode(quarter, '1', 1)) as q1, 
count(decode(quarter, '2', 1)) as q2, 
count(decode(quarter, '3', 1)) as q3, 
count(decode(quarter, '4', 1)) as q4
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp);

select deptno,
count(decode(quarter, '1', 1)) as "1분기", 
count(decode(quarter, '2', 1)) as "2분기", 
count(decode(quarter, '3', 1)) as "3분기", 
count(decode(quarter, '4', 1)) as "4분기"
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp)
group by deptno
order by deptno;
