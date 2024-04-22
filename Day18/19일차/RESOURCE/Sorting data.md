
---------------------------
> Section 2. Sorting data <
---------------------------

# order by절 

  https://www.oracletutorial.com/oracle-basics/oracle-order-by/

  (1) 오름차순
```PLSQL
select empno, ename, sal as salary from emp order by sal    asc;
select empno, ename, sal as salary from emp order by salary asc;
select empno, ename, sal as salary from emp order by 3      asc;
```
   

  (2) 내림차순
```PLSQL
select empno, ename, sal as salary from emp order by sal    desc;
select empno, ename, sal as salary from emp order by salary desc;
select empno, ename, sal as salary from emp order by 3      desc;
```
   

  (3) Null이 있을 경우
```PLSQL
select empno, ename, comm from emp order by comm asc;
select empno, ename, comm from emp order by comm asc nulls first;
-- NULL 값이 먼저옴
```

```LUA
--결과
 EMPNO ENAME                      COMM
---------- -------------------- ----------
      7369 SMITH
      7782 CLARK
      7902 FORD
      7900 JAMES
      7876 ADAMS
      7566 JONES
      7698 BLAKE
      7934 MILLER
      7788 SCOTT
      7839 KING
      7844 TURNER                        0

     EMPNO ENAME                      COMM
---------- -------------------- ----------
      7499 ALLEN                       300
      7521 WARD                        500
      7654 MARTIN                     1400
```

```PLSQL
select empno, ename, comm from emp order by comm desc;
select empno, ename, comm from emp order by comm desc nulls last;
```

```LUA
--결과
     EMPNO ENAME                      COMM
---------- -------------------- ----------
      7654 MARTIN                     1400
      7521 WARD                        500
      7499 ALLEN                       300
      7844 TURNER                        0
      7788 SCOTT
      7839 KING
      7876 ADAMS
      7900 JAMES
      7902 FORD
      7934 MILLER
      7698 BLAKE

     EMPNO ENAME                      COMM
---------- -------------------- ----------
      7566 JONES
      7369 SMITH
      7782 CLARK
```
  
> 💡 `ORDER BY` 
> `ORDER BY`는 엄밀히 말하면 `SORT`라곤 할 수 없다. 
> 단지 정렬된 데이터를 원한다는 뜻 -> 오라클이 `SORT`를 할수도, 안할수도 있음!
  

  (4) select list에 없는 컬럼으로 정렬 가능
```PLSQL
select empno, ename, job from emp order by sal desc;
```

  (5) 두 개 이상의 기준으로 정렬
```PLSQL
select empno, ename, sal, deptno from emp order by deptno asc, sal desc;
```
  

  (6) 가공한 결과에 의한 order by

### STEP 1

```PLSQL
select empno, ename, substr(ename, -1, 1) 
from emp;
```

> 💡 `ENAME` 맨 뒤에서 한글자를 자른다.

```LUA
  EMPNO ENAME                SUBSTR(ENAME, -1, 1)
---------- -------------------- --------
      7369 SMITH                H
      7499 ALLEN                N
      7521 WARD                 D
      7566 JONES                S
      7654 MARTIN               N
      7698 BLAKE                E
      7782 CLARK                K
      7788 SCOTT                T
      7839 KING                 G
      7844 TURNER               R
      7876 ADAMS                S
      7900 JAMES                S
      7902 FORD                 D
      7934 MILLER               R
```

### STEP 2

```PLSQL
select empno, ename, substr(ename, -1, 1) 
from emp 
order by substr(ename, -1, 1);
```

> 💡 자른 글자를 기준으로 `ODER BY`한다.

```LUA
EMPNO ENAME                SUBSTR(E
---------- -------------------- --------
      7902 FORD                 D
      7521 WARD                 D
      7698 BLAKE                E
      7839 KING                 G
      7369 SMITH                H
      7782 CLARK                K
      7499 ALLEN                N
      7654 MARTIN               N
      7934 MILLER               R
      7844 TURNER               R
      7566 JONES                S
      7876 ADAMS                S
      7900 JAMES                S
      7788 SCOTT                T
```

### STEP 3

```PLSQL
select empno, ename                       
from emp 
order by substr(ename, -1, 1);
```

> 💡 (`SUBSTR(ENAME, -1, 1)`)을 리스트에 표시하지 않고 정렬 기준으로만 사용한다.

```LUA
  EMPNO ENAME
---------- --------------------
      7902 FORD
      7521 WARD
      7698 BLAKE
      7839 KING
      7369 SMITH
      7782 CLARK
      7499 ALLEN
      7654 MARTIN
      7934 MILLER
      7844 TURNER
      7566 JONES
      7876 ADAMS
      7900 JAMES
      7788 SCOTT
```

### STEP 4

```PLSQL
select empno, hiredate, to_char(hiredate, 'MM') 
from emp;
```

> 💡 `TO_CHAR` : 날짜나 숫자 등의 값을 문자열로 변환하는 함수이다.

```LUA
 EMPNO HIREDATE TO_C
---------- -------- ----
      7369 80/12/17 12
      7499 81/02/20 02
      7521 81/02/22 02
      7566 81/04/02 04
      7654 81/09/28 09
      7698 81/05/01 05
      7782 81/06/09 06
      7788 82/12/09 12
      7839 81/11/17 11
      7844 81/09/08 09
      7876 83/01/12 01
      7900 81/12/03 12
      7902 81/12/03 12
      7934 82/01/23 01
```

### STEP 5

```PLSQL
select empno, hiredate, to_char(hiredate, 'MM') 
from emp 
order by to_char(hiredate, 'MM');
```

> 💡 문자열로 변환한 월을 기준으로 정렬한다.

```LUA
     EMPNO HIREDATE TO_C
---------- -------- ----
      7876 83/01/12 01
      7934 82/01/23 01
      7499 81/02/20 02
      7521 81/02/22 02
      7566 81/04/02 04
      7698 81/05/01 05
      7782 81/06/09 06
      7844 81/09/08 09
      7654 81/09/28 09
      7839 81/11/17 11
      7788 82/12/09 12
      7369 80/12/17 12
      7900 81/12/03 12
      7902 81/12/03 12
```

### STEP 6

```PLSQL
select empno, hiredate                          
from emp 
order by to_char(hiredate, 'MM'), hiredate;
```

> 💡 `SELECT`절에서 `TO_CAHR`를 감춘다.
> 월로 정렬하되, 그 안에서 연도가 빠른 값이 먼저나오게 정렬한다.


```BASH
 EMPNO HIREDATE
---------- --------
      7934 82/01/23
      7876 83/01/12
      7499 81/02/20
      7521 81/02/22
      7566 81/04/02
      7698 81/05/01
      7782 81/06/09
      7844 81/09/08
      7654 81/09/28
      7839 81/11/17
      7369 80/12/17
      7900 81/12/03
      7902 81/12/03
      7788 82/12/09
```
  

