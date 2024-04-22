
---
>Section 3. Filtering data 
---

# Distinct

  https://www.oracletutorial.com/oracle-basics/oracle-select-distinct/

  select unique   deptno from emp;
  select distinct deptno from emp;

  select distinct deptno, job from emp order by 1, 2;

  ** row-major format(OLTP에 적합) vs column-major format(OLAP, DW, DSS, BI)

  ** sort unique vs hash unique


# Where절의 기능

  https://www.oracletutorial.com/oracle-basics/oracle-where/

  > 📌 `WHERE`
> 조건을 나열하는 곳 ...
> 틀린건 아니지만 아쉬운 답변
> -> 후보행(Candidate row)을 검증해서 True, False, Null을 리턴하는 절인데,
    Where절이 True를 리턴해야 후보행이 리턴된다
  
(1) where절 원리 이해

```SQL
select empno, ename, sal, sal*1.1 as whatif 
from emp
where deptno = 30;
```   

> 💡 `EMPNO`, `ENAME`, `SAL`, `SQL*1.1(이름 : WHATIF)`
> 부서번호가 30인 사람 `SELECT`

```BASH
결과::
 EMPNO ENAME                       SAL     WHATIF
---------- -------------------- ---------- ----------
      7499 ALLEN                      1600       1760
      7521 WARD                       1250       1375
      7654 MARTIN                     1250       1375
      7698 BLAKE                      2850       3135
      7844 TURNER                     1500       1650
      7900 JAMES                       950       1045
```

(2) 조건이 2개 이상인 경우

```sql
select empno, ename, sal, sal*1.1 as whatif 
from emp
where deptno = 30 and sal < 1500;
```

> 💡 `EMPNO`, `ENAME`, `SAL`, `SQL*1.1(이름 : WHATIF)`
> 부서번호가 30이고 급여가 `1500` 이하인사람 `SELECT`

```BASH
결과::
  EMPNO ENAME                       SAL     WHATIF
---------- -------------------- ---------- ----------
      7521 WARD                       1250       1375
      7654 MARTIN                     1250       1375
      7900 JAMES                       950       1045
```

(3) 컬럼명 = 컬럼명 예제

```SQL
select * from emp where empno = empno;
```

```BASH
결과::
EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7369 SMITH                CLERK                    7902 80/12/17        800                    20
      7499 ALLEN                SALESMAN                 7698 81/02/20       1600        300         30
      7521 WARD                 SALESMAN                 7698 81/02/22       1250        500         30
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7654 MARTIN               SALESMAN                 7698 81/09/28       1250       1400         30
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7782 CLARK                MANAGER                  7839 81/06/09       2450                    10
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7839 KING                 PRESIDENT                     81/11/17       5000                    10
      7844 TURNER               SALESMAN                 7698 81/09/08       1500          0         30
      7876 ADAMS                CLERK                    7788 83/01/12       1100                    20
      7900 JAMES                CLERK                    7698 81/12/03        950                    30
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
      7934 MILLER               CLERK                    7782 82/01/23       1300                    10
```


```SQL
select * from emp where comm = comm;
```

```BASH
결과::
   EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7499 ALLEN                SALESMAN                 7698 81/02/20       1600        300         30
      7521 WARD                 SALESMAN                 7698 81/02/22       1250        500         30
      7654 MARTIN               SALESMAN                 7698 81/09/28       1250       1400         30
      7844 TURNER               SALESMAN                 7698 81/09/08       1500          0         30
```

(4) 항상 false인 조건을 사용한 예제

```SQL
drop table sawon purge;

create table sawon
as 
select * from emp 
where 1 = 2;

select * from sawon;
```

```BASH
결과::
선택된 레코드가 없습니다.
```

> 💡 무조건 `FALSE` 를 반환한다. 

(5) is null 연산자

```SQL
select * from emp where comm = null;    -- 엉터리
select * from emp where comm is null;   -- 제대로
```

> 💡 `= NULL` : `SQL`에서는 `NULL`값을 직접적으로 `=` 연산자로 비교할 수 없다.
> 	`IS NULL` : `SQL`에서는 `NULL`값을 비교할 때 `IS NULL`조건을 사용해야 한다.

```BASH
::= NULL;
선택된 레코드가 없습니다.
::IS NULL;
     EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7369 SMITH                CLERK                    7902 80/12/17        800                    20
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7782 CLARK                MANAGER                  7839 81/06/09       2450                    10
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7839 KING                 PRESIDENT                     81/11/17       5000                    10
      7876 ADAMS                CLERK                    7788 83/01/12       1100                    20
      7900 JAMES                CLERK                    7698 81/12/03        950                    30
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
      7934 MILLER               CLERK                    7782 82/01/23       1300                    10
```

```SQL
select * from emp where sal >= 1500 and comm = null;   -- 엉터리
select * from emp where sal >= 1500 and comm is null;  -- 제대로
```


> 💡 마찬가지로, `SQL`에서는 `NULL`값은 `=`연산자로 비교할 수 없다.

```BASH
::= NULL;
선택된 레코드가 없습니다.
:: IS NULL;
     EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7782 CLARK                MANAGER                  7839 81/06/09       2450                    10
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7839 KING                 PRESIDENT                     81/11/17       5000                    10
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
```

(6) 가공한 결과를 사용한 where절

```SQL
--사원이름이 5글자인 사원?
select empno, ename, ename
from EMP;
```

```BASH
 EMPNO ENAME                ENAME
---------- -------------------- --------------------
      7369 SMITH                SMITH
      7499 ALLEN                ALLEN
      7521 WARD                 WARD
      7566 JONES                JONES
      7654 MARTIN               MARTIN
      7698 BLAKE                BLAKE
      7782 CLARK                CLARK
      7788 SCOTT                SCOTT
      7839 KING                 KING
      7844 TURNER               TURNER
      7876 ADAMS                ADAMS
      7900 JAMES                JAMES
      7902 FORD                 FORD
      7934 MILLER               MILLER
```

```SQL
select empno, ename, length(ename)
from emp;
```

```BASH
     EMPNO ENAME                LENGTH(ENAME)
---------- -------------------- -------------
      7369 SMITH                            5
      7499 ALLEN                            5
      7521 WARD                             4
      7566 JONES                            5
      7654 MARTIN                           6
      7698 BLAKE                            5
      7782 CLARK                            5
      7788 SCOTT                            5
      7839 KING                             4
      7844 TURNER                           6
      7876 ADAMS                            5
      7900 JAMES                            5
      7902 FORD                             4
      7934 MILLER                           6
```

```SQL
select empno, ename, length(ename)
from emp
where length(ename) = 5;
```

```BASH
  EMPNO ENAME                LENGTH(ENAME)
---------- -------------------- -------------
      7369 SMITH                            5
      7499 ALLEN                            5
      7566 JONES                            5
      7698 BLAKE                            5
      7782 CLARK                            5
      7788 SCOTT                            5
      7876 ADAMS                            5
      7900 JAMES                            5
```

(7) not 사용하는 예제

```SQL
SELECT *
FROM EMP 
WHERE NOT(DEPTNO=10 AND SAL >= 500);
```

```BASH
 EMPNO ENAME                JOB                       MGR HIREDATE        SAL       COMM     DEPTNO
---------- -------------------- ------------------ ---------- -------- ---------- ---------- ----------
      7369 SMITH                CLERK                    7902 80/12/17        800                    20
      7499 ALLEN                SALESMAN                 7698 81/02/20       1600        300         30
      7521 WARD                 SALESMAN                 7698 81/02/22       1250        500         30
      7566 JONES                MANAGER                  7839 81/04/02       2975                    20
      7654 MARTIN               SALESMAN                 7698 81/09/28       1250       1400         30
      7698 BLAKE                MANAGER                  7839 81/05/01       2850                    30
      7788 SCOTT                ANALYST                  7566 82/12/09       3000                    20
      7844 TURNER               SALESMAN                 7698 81/09/08       1500          0         30
      7876 ADAMS                CLERK                    7788 83/01/12       1100                    20
      7900 JAMES                CLERK                    7698 81/12/03        950                    30
      7902 FORD                 ANALYST                  7566 81/12/03       3000                    20
```

> 💡 부서번호가 10이고 `SAL`이 500 이상이면 포함하지 않겠다는 뜻.

# And 

  https://www.oracletutorial.com/oracle-basics/oracle-and/

  - Null 이해
  
    -> unknown, unavailable, inapplicable, 비어있는, 미결정, ...
    -> 가능하면 null을 허용하지 않는 것이 유리함

    -> ≠ 0
    -> ≠ space
    -> =  null    -> 결과는 Null
   
    -> 산술연산   -> 결과는 Null  -> 극복하기 위해 nvl, nvl2, ...
    -> 비교연산   -> 결과는 Null  -> 극복하기 위해 nvl, nvl2, ...
    -> 논리연산   -> 진리표를 기억할 것
    
  - And 진리표 이해 : F > N > T

      and | True  False Null   
    ------------------------
     True | True  False Null
    False | False False False 
     Null | Null  False Null  

    ex) 키가 180cm이상 이고 성이 김씨?

    if __________ and _________ and _______ then
      실행문;
    end if;

    from 테이블
    where __________ and _________ and _______ 


# Or

  https://www.oracletutorial.com/oracle-basics/oracle-or/

  - Or 진리표 이해 : T > N > F

      or  | True  False Null   
    ------------------------
     True | True  True  True
    False | True  False Null
     Null | True  Null  Null  

    ex) 키가 180cm이상 이거나 성이 김씨?

    if __________ or _________ or _______ then
      실행문;
    end if;

    from 테이블
    where __________ or _________ or _______ 


# FETCH 

  - https://www.oracletutorial.com/oracle-basics/oracle-fetch/
  - https://oracle-base.com/articles/12c/row-limiting-clause-for-top-n-queries-12cr1
  - https://docs.oracle.com/en/database/oracle/oracle-database/21/sqlrf/SELECT.html#GUID-CFA006CA-6FF1-4972-821E-6996142A51C6__BABBADDD

    ** pagenation 

       -- 1 2 3 4 
       SELECT val
       FROM   rownum_order_test
       ORDER BY val
       OFFSET (1 - 1) * 10 ROWS FETCH NEXT 10 ROWS ONLY;


# IN

  select * from emp where mgr = 7698;
  select * from emp where mgr = 7566;
  select * from emp where mgr = 7782;

    ↓↓

  select * from emp where mgr = 7698
  union all
  select * from emp where mgr = 7566
  union all
  select * from emp where mgr = 7782;

    ↓↓

  select * from emp where mgr = 7698 or mgr = 7566 or mgr = 7782;

  select * from emp where mgr in (7698, 7566, 7782);


# BETWEEN

  select * from emp where sal >= 1500 and sal <= 2500;

  select * from emp where sal between 1500 and 2500;

  ** Query Transformation

     https://www.yes24.com/Product/Goods/3796929

     -> 10053 트레이스 : http://wiki.gurubee.net/pages/viewpage.action?pageId=3899776


# LIKE

  * wild card : %(zero or many), _(only one)

  select * from emp where ename like '_____';
  select * from emp where ename like 'S%';

    ----

  drop table t1 purge;

  create table t1 (name varchar2(30));

  insert into t1 values('AAA');
  insert into t1 values('ABA');
  insert into t1 values('ACA');
  insert into t1 values('A_A');

  commit;

  select * from t1 where name like '%A_A%';
  select * from t1 where name like '%A!_A%' escape '!';


# IS NULL 

  -

