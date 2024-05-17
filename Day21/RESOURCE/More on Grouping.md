
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
  

# Cube

  ~ 컬럼의 개수가 n개면 2^n 가지 종류의 결과 생성
  ~ 컬럼의 나열 순서가 중요하지 않음

  select deptno, job, sum(sal)
  from emp
  group by CUBE(deptno, job);

  select deptno, job, sum(sal)
  from emp
  group by GROUPING SETS((deptno, job), (deptno), (job), ());


# 집계 기준 컬럼이 3개일 경우

  drop table emp2 purge;

  create table emp2
  as
  select  empno, ename, sal, job, deptno, decode(mod(empno, 2), 0, 'M', 'W') as gender
  from emp e
  union all
  select  empno, ename, sal+100, job, deptno, decode(mod(empno, 2), 0, 'W', 'M') as gender
  from emp e;

  select * from emp2;

    --

  select deptno, job, gender, sum(sal)
  from emp2
  group by ROLLUP(deptno, job, gender)
  order by 1, 2, 3;

  select deptno, job, gender, sum(sal)
  from emp2
  group by GROUPING SETS((deptno, job, gender), (deptno, job), (deptno), ())
  order by 1, 2, 3;

    --

  select deptno, job, gender, sum(sal)
  from emp2
  group by CUBE(deptno, job, gender)
  order by 1, 2, 3;

  select deptno, job, gender, sum(sal)
  from emp2
  group by GROUPING SETS((deptno, job, gender), (deptno, job), (deptno, gender), (job, gender), (deptno), (job), (gender), ())
  order by 1, 2, 3;

  select deptno, job, gender, sum(sal)
  from emp2
  group by GROUPING SETS((deptno, job, gender), (job, gender), ())
  order by 1, 2, 3;


# GROUPING 함수

  drop table emp3 purge;

  create table emp3 as select * from emp;

  update emp3 set job = null where rownum = 1;

  select * from emp3;

  => grouping 함수를 사용하지 않을 경우 아래와 같이 질의 결과가 이상해집니다.

  select deptno, job, sum(sal) as sum_sal
  from emp3
  group by ROLLUP(deptno, job);

  select *
  from (select deptno, job, sum(sal) as sum_sal
        from emp3
        group by ROLLUP(deptno, job))
  where deptno is not null
  and job is null;

  => grouping 함수를 사용하면 이렇게 달라집니다.

  select deptno, job, sum(sal) as sum_sal, 
         grouping(deptno) as g_deptno, 
         grouping(job)    as g_job
  from emp3
  group by ROLLUP(deptno, job);

  select deptno, job, sum_sal
  from (select deptno, job, sum(sal) as sum_sal, 
               grouping(deptno) as g_deptno, 
               grouping(job)    as g_job
        from emp3
        group by ROLLUP(deptno, job))
  where g_deptno = 0
  and   g_job    = 1;

  select deptno, job, sum_sal
  from (select deptno, job, sum(sal) as sum_sal, 
               grouping(deptno) as g_deptno, 
               grouping(job)    as g_job
        from emp3
        group by ROLLUP(deptno, job))
  where g_deptno = 0
  and   g_job    in (0, 1);


# Composite column

  select deptno, job, gender, sum(sal)
  from emp2
  group by deptno, job, gender;

  select deptno, job, gender, sum(sal)
  from emp2
  group by rollup(deptno, job, gender);

    --> deptno, job, gender
    --> deptno, job
    --> deptno
    --> ()

  select deptno, job, gender, sum(sal)
  from emp2
  group by rollup(deptno, (job, gender));

    --> deptno, job, gender
    --> deptno
    --> ()

  select deptno, job, gender, sum(sal)
  from emp2
  group by rollup((deptno, job), gender);

    --> deptno, job, gender
    --> deptno, job
    --> ()

  select deptno, job, gender, sum(sal)
  from emp2
  group by rollup((deptno, job, gender));

    --> deptno, job, gender
    --> ()


# Concatenated Groupings

  select deptno, job, gender, sum(sal)
  from emp2
  group by deptno, rollup(job), cube(gender);

           deptno    job         gender
                     ()          ()

           --> deptno, job, gender
           --> deptno, job
           --> deptno, gender
           --> deptno

  select deptno, job, gender, sum(sal)
  from emp2
  group by GROUPING SETS((deptno, job, gender), (deptno, job), (deptno, gender), (deptno));
  
  >> Concatenated Groupings가 필요한 이유!

     http://me2.do/5HCJeu6h

     GROUP BY ROLLUP(calendar_year, calendar_quarter_desc, calendar_month_desc),
              ROLLUP(country_region, country_subregion, countries.country_iso_code, cust_state_province, cust_city),
              ROLLUP(prod_category_desc, prod_subcategory_desc, prod_name)

     GROUP BY GROUPING SETS((calendar_year, calendar_quarter_desc, calendar_month_desc, country_region, country_subregion, countries.country_iso_code, cust_state_province, cust_city, prod_category_desc, prod_subcategory_desc, prod_name),
                            (calendar_year, calendar_quarter_desc, calendar_month_desc, country_region, country_subregion, countries.country_iso_code, cust_state_province, cust_city, prod_category_desc, prod_subcategory_desc),
                             ....)


