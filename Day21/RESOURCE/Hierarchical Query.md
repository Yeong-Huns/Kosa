
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

