![](https://media.licdn.com/dms/image/C5612AQH23XNlZhx0_w/article-inline_image-shrink_400_744/0/1520220010136?e=1718841600&v=beta&t=RECywpv0xkueXCC2-VgtKXoePqs_ALzljhVwIO2SNTE)

## 나의 예제



```sql
select e.employee_id, e.first_name||' '||e.last_name as name, d.department_name
from employees e join departments d
on e.department_id = d.department_id
order by 1
fetch first 11 rows only;
```

```bash
결과::
EMPLOYEE_ID NAME                                                                                         DEPARTMENT_NAME
----------- -------------------------------------------------------------------------------------------- ------------------------------------------------------------
        100 Steven King                                                                                  Executive
        101 Neena Kochhar                                                                                Executive
        102 Lex De Haan                                                                                  Executive
        103 Alexander Hunold                                                                             IT
        104 Bruce Ernst                                                                                  IT
        107 Diana Lorentz                                                                                IT
        124 Kevin Mourgos                                                                                Shipping
        141 Trenna Rajs                                                                                  Shipping
        142 Curtis Davies                                                                                Shipping
        143 Randall Matos                                                                                Shipping
        144 Peter Vargas                                                                                 Shipping

```

---

---
 >Single-Row Functions
---

* [Single-Row Functions(Oracle Docs)](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Single-Row-Functions.html#GUID-B93F789D-B486-49FF-B0CD-0C6181C5D85C)

# 분류

  - Character 관련 함수
  - Number    관련 함수
  - Date      관련 함수
  - Convesion 관련 함수
  - General   관련 함수

# Dual

* [Selecting from the DUAL Table(Oracle Docs)](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Selecting-from-the-DUAL-Table.html#GUID-0AB153FC-5238-4E79-8522-C9E2A04AB5E4)

> 📌 `Dual`
> `DUAL` is table 
> automatically created By Oracle Database along with the data dictionary
> `듀얼`은 테이블이다 

```sql
select ltrim ('xyxyxYxy', 'xy')
from dual;
```

```bash
LTRIM(
------
Yxy
```

> 💡 `ltrim`
> `xy` 를 지우는게 아니라 `x` or `y` 를 지운다!!!

```sql
select 45 * 56 from emp;    -- 테이블의 행 수만큼 계산 결과 반복 출력됨
select 45 * 56 from dual;   -- 테이블의 행 수만큼 계산 결과 한번 출력됨
```

```bash
SQL> select 45 * 56 from emp;

     45*56
----------
      2520
      2520
      2520
      2520
      2520
      2520
      2520
      2520
      2520
      2520
      2520
      2520
      2520
      2520

14 행이 선택되었습니다.

---------------------
SQL> select 45 * 56 from dual;

     45*56
----------
      2520
```

> 💡 `emp` 테이블의 행 수 (`14`) 만큼 
> `45 * 56`의 결과가 출력되는 것을 알 수 있다. 

### Case 1

```sql
select '2 x '||level||' = '||level * 2 "2단"
from dual
connect by level <= 9;
```

> 💡 `level` : 1씩 올라감 

```bash
2 x 1 = 2
2 x 2 = 4
2 x 3 = 6
2 x 4 = 8
2 x 5 = 10
2 x 6 = 12
2 x 7 = 14
2 x 8 = 16
2 x 9 = 18
```

### Case 2

```sql
select level, level*2, level*5, level*10 
from dual connect by level <= 10;
```

```bash
 LEVEL    LEVEL*2    LEVEL*5   LEVEL*10
---------- ---------- ---------- ----------
         1          2          5         10
         2          4         10         20
         3          6         15         30
         4          8         20         40
         5         10         25         50
         6         12         30         60
         7         14         35         70
         8         16         40         80
         9         18         45         90
        10         20         50        100
```
  
### 나의 예제

### Case 1

```sql
select '9 x '||level||' = '||9*level as "9단" --> 폭군!!
from dual 
connect by level <= 9;
```

```bash
9단 
----------------------------------------------------------------------------
9 x 1 = 9
9 x 2 = 18
9 x 3 = 27
9 x 4 = 36
9 x 5 = 45
9 x 6 = 54
9 x 7 = 63
9 x 8 = 72
9 x 9 = 81

9 행이 선택되었습니다.
```

### Case 2
```sql
select  
'3 x '||level||' = '||level*3 "3단", 
'5 x '||level||' = '||level*5 "5단", 
'7 x '||level||' = '||level*7 "7단"
from dual 
connect by level < 10;
```

```bash
3단        5단        7단
---------- ---------- ----------
3 x 1 = 3  5 x 1 = 5  7 x 1 = 7
3 x 2 = 6  5 x 2 = 10 7 x 2 = 14
3 x 3 = 9  5 x 3 = 15 7 x 3 = 21
3 x 4 = 12 5 x 4 = 20 7 x 4 = 28
3 x 5 = 15 5 x 5 = 25 7 x 5 = 35
3 x 6 = 18 5 x 6 = 30 7 x 6 = 42
3 x 7 = 21 5 x 7 = 35 7 x 7 = 49
3 x 8 = 24 5 x 8 = 40 7 x 8 = 56
3 x 9 = 27 5 x 9 = 45 7 x 9 = 63

9 행이 선택되었습니다.
```

# Character  : upper, lower, initcap, substr, length, replace, translate, ltrim, rtrim, trim, lpad, rpad, ...
  
## instr
```sql
--instr
select instr('my name is unknown', 'm') from dual;
--m 의 인덱스를 찾는다
select instr('my name is unknown', 'm', 3) from dual;
--m 의 인덱스를 찾는데, 3번 위치부터 찾는다
select instr('my name is unknown', 'm', 1, 2) from dual;
--m 의 인덱스를 1부터 찾는데, 2번째로 일치하는 m의 위치를 출력한다.
```

```bash
INSTR('MYNAMEISUNKNOWN','M')
----------------------------
                           1

INSTR('MYNAMEISUNKNOWN','M',3)
------------------------------
                             6

INSTR('MYNAMEISUNKNOWN','M',1,2)
--------------------------------
                               6
```

> 💡 `instr`(`substring`, `position`, `occurence`)
> `position` : 검색을 시작할 문자열의 문자, 하위 문자열과 비교할 첫 번째 하위 문자열의 
> 문자 위치를 나타내는 0이 아닌 정수이다. `position` 값이 음수면 문자열에서 뒤로 검색
> `occurence` : `occurrence`가 1보다 크면 데이터베이스는 첫 번째 일치 항목에서 반환되지 않지만 일치 번호 `일치`가 발견될 때까지 위에서 설명한 `대로 문자열`의 연속 하위 문자열을 계속 비교한다.

## substr + instr
```sql
drop table t1 purge;
create table t1 (col varchar2(30));
insert into t1 values ('myname@naver.com');

--substr + instr
select substr(col, 1, instr(col, '@') - 1) as ret1,
substr(col, instr(col, '@')+ 1)as ret2 
from t1;
-- col의 맨 처음부터 "@"전까지 as ret1
-- col의 @ 바로 뒤부터 끝까지 as ret2

```

```bash
RET1        RET2
----------- ------------
myname      naver.com                  
```

## ltrim, rtrim, trim
```sql
select ltrim('SSES', 'S') from dual;
select trim(leading 'S' from 'SES') from dual;

select rtrim('SES', 'S') from dual;
select trim(trailing 'S' from 'SES') from dual;

select ltrim(rtrim('SES', 'S'), 'S') from dual;
--SES에서 오른쪽 S 제거, SE에서 왼쪽 S 제거
select trim(both 'S' from 'SES') from dual;

select ltrim('xyxYxy', 'xy')            from dual;   
-- 성공           
select trim(leading 'xy' from 'xyxYxy') from dual;   
-- 에러 : ORA-30001: 트림 설정은 하나 문자만 가지고 있어야 합니다

```

> 💡 `trim property`
> `leading` : 앞에서부터
> `trailing` : 뒤에서부터
> 'both' : 앞뒤로

## replace
```sql
select empno, sal, replace(sal, '0', '*') as ret1, 
lpad(replace(sal, '0', '*'), 6, ' ') as ret2
from emp;
```

```bash
 EMPNO        SAL RET1       RET2
---------- ---------- ---------- ----------
      7369        800 8**           8**
      7499       1600 16**         16**
      7521       1250 125*         125*
      7566       2975 2975         2975
      7654       1250 125*         125*
      7698       2850 285*         285*
      7782       2450 245*         245*
      7788       3000 3***         3***
      7839       5000 5***         5***
      7844       1500 15**         15**
      7876       1100 11**         11**
      7900        950 95*           95*
      7902       3000 3***         3***
      7934       1300 13**         13**
```

> 💡 `lpad` : 왼쪽에 채우기
> `0`을 `*`로 변경하고 , 왼쪽에 공백을 넣은 6자리 포맷을 지정

> 💡 `Line by Line`
> `RET1`: `replace`를 통해 `sal`의 `0` 부분을 `*` 로 변경한 것을 알 수 있다.
> `RET2`: 위와 동일하지만, `lpad` 를 통해 `기본 format`을 6자리로 설정하고, 
> 6자리에서 비는 자리를 `' '` 를 통해 공백으로 채웠다.

```sql
select replace('JAMES', 'MES', 'NE') as ret
from dual;
```

> 💡 `Line by Line`
> `JAMES` 에서 `MES` 부분을 `NE`로 바뀔 것을 쉽게 예상할 수 있다.

```bash
RET
--------
JANE
```
## translate
```sql
select translate('my phone number : 010-1234-5555','0123456789',
                                                   '**********')
from dual;
```

```bash
TRANSLATE('MYPHONENUMBER:010-1234-5555','0123456789','********
--------------------------------------------------------------
my phone number : ***-****-****
```

> 💡`0~9` 사이의 숫자가 있다면, 전부 `*`로 바꾼다.
## rpad를 이용해서 bar chart 그리기
```sql
select empno, sal, sal
from emp;

select empno, sal, ceil(sal/100)
from emp;

select empno, sal, '*', ceil(sal/100)
from emp;

select empno, sal, rpad('*', ceil(sal/100), '*') as bar
from emp;
```

```bash
  EMPNO   SAL BAR
---------- ----- --------------------------------------------------
      7369   800 ********
      7499  1600 ****************
      7521  1250 *************
      7566  2975 ******************************
      7654  1250 *************
      7698  2850 *****************************
      7782  2450 *************************
      7788  3000 ******************************
      7839  5000 **************************************************
      7844  1500 ***************
      7876  1100 ***********
      7900   950 **********
      7902  3000 ******************************
      7934  1300 *************
```

> 💡 `col format`
> `col 컬럼명 format a10` : `a(문자열의 길이)`가 10자리,
> `col 컬럼명 format 9999` : `9(숫자)` 길이를 `9999(4자리)`로 지정

## 문제.숫자로만 이루어진 행 찾기
```sql
drop table t1 purge;
create table t1 (col varchar2(30));

insert into t1 values ('A1023');
insert into t1 values ('98e023');
insert into t1 values ('7821');
insert into t1 values ('1232221');
    
commit;
```

### 해답 1
```sql
select col, ltrim(col, '0123456789') as "isNUll?"
from t1 ;

select col
from t1
where ltrim(col, '0123456789') is null;
```

> 💡 중간에 문자가 있는 `col`은 `ltrim` 으로 전부 지워지지 않는다.


### 해답 2
```sql
select col, upper(col), lower(col) from t1;

select col 
from t1
where upper(col) = lower(col);
```

> 💡 만약 문자가 들어가 있다면,
> 대문자로 변환한 값과 소문자로 변환한 값을 비교하면 같을 수가 없다.!

## 문제.ename 일부를 가리는 두가지 결과를 만드세요
```bash
ENAME   RET1   RET2
-----------------------
SMITH   S*H    S***H
ALLEN   A*N    A***N
WARD    W*F    W**D
JONES   J*S    J**D
...
```
 
```sql
select substr(ename, 1, 1)||'*'||substr(ename, -1, 1) as name
from emp;
```

```bash
NAME
------------------
S*H
A*N
W*D
J*S
M*N
B*E
C*K
S*T
K*G
T*R
A*S
J*S
F*D
M*R

14 행이 선택되었습니다.
```

> 💡 `풀이과정`
> `substr`를 사용하여 `(ename, 1, 1)` : 맨 앞자리 
> `(ename, -1, 1)` : 맨 뒷자리를 구한 후, 
> `맨 앞자리`||`*`||`맨 뒷자리` as `name` 

```plsql
select substr(ename, 1, 1)||rpad('*', length(ename)-2, '*')||substr(ename, -1, 1) as name
from emp;
```

> 💡 예상
> 맨 앞자리 한 글자와 맨 뒷자리 한 글자, 그 사이 공간을 길이 - 2 만큼 `*`로 채움.

```bash
NAME
---------------------------------------------------------------------------
S***H
A***N
W**D
J***S
M****N
B***E
C***K
S***T
K**G
T****R
A***S
J***S
F**D
M****R
```

```plsql
select ename,
substr(ename, 1, 1)||'*'||substr(ename, -1, 1) as ret1,
substr(ename, 1, 1)||translate(substr(lower(ename), 3), 'abcdefghijklmnopqrstuvwxyz','**************************')||
substr(ename, -1, 1) as ret2
from emp;
```

> 💡 3번째 자리부터 소문자 a-z 모두 `*`로 변환(`==length - 2`)
> 맨 앞글자||`*`||맨 뒷글자 


# Number : round, trunc, mod, ceil, floor

```sql
select round(45.995, 2) a,     -- 46.00 or 46
round(45.995, -2) b,    -- 0
round(55445.995, -2) c  -- 55400
from dual;

select trunc(45.995, 2) a,     -- 45.99
trunc(45.995, -2) b,    -- 0
trunc(55445.995, -2) c  -- 55400
from dual;
```

> 💡 `round` : 음수가 나오면 소수점기준 역으로(정수부분으로) 간다.
> `trunc` : 기준아래 버림

```sql
select empno, mod(empno, 2), mod(empno, 3), mod(empno, 5) from emp;

select empno, mod(empno, 2), mod(empno, 3), mod(empno, 5) from emp where mod(empno, 2) = 0;

select empno, mod(empno, 2), mod(empno, 3), mod(empno, 5) from emp where mod(empno, 5) = 0;

select ceil(3.14), floor(3.14)   from dual;

select ceil(-3.14), floor(-3.14) from dual;
```

> 💡 `mod` : 나머지 구하기 

```bash
 EMPNO MOD(EMPNO,2) MOD(EMPNO,3) MOD(EMPNO,5)
---------- ------------ ------------ ------------
      7369            1            1            4
      7499            1            2            4
      7521            1            0            1
      7566            0            0            1
      7654            0            1            4
      7698            0            0            3
      7782            0            0            2
      7788            0            0            3
      7839            1            0            4
      7844            0            2            4
      7876            0            1            1

     EMPNO MOD(EMPNO,2) MOD(EMPNO,3) MOD(EMPNO,5)
---------- ------------ ------------ ------------
      7900            0            1            0
      7902            0            0            2
      7934            0            2            4


     EMPNO MOD(EMPNO,2) MOD(EMPNO,3) MOD(EMPNO,5)
---------- ------------ ------------ ------------
      7566            0            0            1
      7654            0            1            4
      7698            0            0            3
      7782            0            0            2
      7788            0            0            3
      7844            0            2            4
      7876            0            1            1
      7900            0            1            0
      7902            0            0            2
      7934            0            2            4


    EMPNO MOD(EMPNO,2) MOD(EMPNO,3) MOD(EMPNO,5)
---------- ------------ ------------ ------------
      7900            0            1            0



CEIL(3.14) FLOOR(3.14)
---------- -----------
         4           3
CEIL(-3.14) FLOOR(-3.14)
----------- ------------
         -3           -4


```
 
# Date : months_between, add_months, next_day, last_day, ...

## Date 데이터 타입
> 📌 `Date`
	내부적으로 7Byte 숫자 형태로 저장하고 있음 : YYYYMMDDHHMISS 
	화면에 보일 때는 환경 설정에 따라 다양하게 보임

```sql
alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';
select sysdate from dual;  

alter session set nls_language = 'AMERICAN';
alter session set nls_date_format = 'DD-MON-YY';

select sysdate from dual;
```

> 💡 실제 물리적 위치와 상관없이 지역에 맞춰서 시간설정을 할 수 있다.

```bash
alter session set nls_language = 'AMERICAN';
alter session set nls_date_format = 'DD-MON-YY';

alter session set nls_language = 'korean';
alter session set nls_date_format = 'DD-MON-YY';
```

```bash
24-APR-24
----------
24-4월 -24
```

> 💡 `nls ~~` : `globalization support` 

## 상식 수준의 산술 연산 가능
> 📌
> * `Date` - `Date`   = `Number`
> * `Date` + `Number` = `Date`
> * `Date` + `Date`

```sql
select ceil(sysdate - to_date('01-JAN-1972', 'DD-MON-YYYY')) as days
from dual;
-- 내가 얼마를 살았는지(Day)
select ceil((sysdate - to_date('01-JAN-1972', 'DD-MON-YYYY'))/7) as weeks
from dual;
--몇 주를 살았는지

select sysdate, sysdate + 100, sysdate + 1000, sysdate + 10000
from dual;
--100일과 1000일과 10000일 
alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';

select sysdate, 
sysdate + 5/24 + 36/(24*60) + 51/(24*60*60) "5시간 36분 51초 뒤",
sysdate + interval '5:36:51' hour to second "5시간 36분 51초 뒤"
from dual;	
--interval 등장

select sysdate + (sysdate + 1)   
/* 에러 : 00975. 00000 -  "date + date not allowed" */
from dual;

select sysdate + sysdate * 3     
/* 에러 : 00932. 00000 -  "inconsistent datatypes: expected %s got %s" */
from dual;
```

> 💡 상식 수준의 계산을 벗어나면 에러 발생

  - Date 함수
```sql
select empno, hiredate, sysdate, ceil(sysdate - hiredate), ceil(months_between(sysdate, hiredate))
from emp;
 
select sysdate, add_months(sysdate, 3), add_months(sysdate, -3)
from emp;
--3개월 후 

select next_day(sysdate, 'MON')
from dual;
--오늘이 지난 첫번째 월요일
select last_day('11-FEB-23')
from dual;
```


# Conversion : to_char, to_number, t_date, ...
## implict coversion
### to_char : Date -> Character 
> 📌 * [Datetime Format Elements](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Format-Models.html#GUID-49B32A81-0904-433E-B7FE-51606672183A)

```sql
alter session set nls_language = 'AMERICAN';
alter session set nls_date_format = 'DD-MON-YY';

select empno, hiredate, to_char(hiredate, 'YYYY YYYY YYYY')              from emp;
-- 여러번 출력
select empno, hiredate, to_char(hiredate, 'YYYY Q MM WW W DDD DD D Day') from emp;
-- Q : 분기
-- WW : 1년 기준으로 몇 번째 주
-- W : 한달 기준 3주
-- DDD : 1년 기준 며칠 
-- DD : 1주 기준 며칠
select empno, hiredate, to_char(hiredate, 'Year Month Day DAY day') from emp;
-- 첫 글자 대문자 : 첫 글자만 대문자 
-- 전체 대문자 , 전체 소문자 : 동일
select empno, hiredate, to_char(hiredate, 'DD Ddsp Ddth Ddspth Ddthsp') from emp;
--sp : spelling
--th : 17th
--spth : seventeenth 
--thsp : seventeenth 

alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';
select sysdate, to_char(sysdate, 'sssss') from dual;
-- 현재 sysdate 에서 초를 구함
--86400 초를 기준으로 얼마나 흘렀나 

alter session set time_zone = '+09:00';
select localtimestamp, to_char(localtimestamp, 'sssss') from dual;
--local : 내 컴퓨터 
--sysdate : 서버 컴퓨터
-- 둘을 조합하여 특정 지역의 현재시간 확인

select to_char(sysdate, 'YYYY')||'년 '||to_char(sysdate, 'MM')||'월 '||to_char(sysdate, 'DD')||'일' as ret1,
to_char(sysdate, 'YYYY"년" MM"월" DD"일"') as ret2
from dual;

select to_char(hiredate, 'YYYY Month DD HH24:MM:SS') as ret1,
to_char(hiredate, 'fmYYYY Month DD fmHH24:MM:SS') as ret2   /* fm = fill mode */
from emp;
```

> 💡 `Format Element`를 정확히 알고 있느냐
### to_char : Number -> Character
Number Format Elements : https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Format-Models.html#GUID-24E16D8D-25E4-4BD3-A38D-CE1399F2897C

```sql
select sal, 
to_char(sal, '$9,999,999.99') as ret1,
to_char(sal, 'L0,000,000.00') as ret2
from emp;
--9 : 있으면 보여주고 없으면 비워라
--0 : 없으면 0으로 채우고 있으면 보여줘라
--L : nls_territory(국가) 영향을 받음 -> korea:원 america:달러
alter session set nls_territory = 'america';
      
select sal, 
to_char(sal, '$9,999,999.99') as ret1,
to_char(sal, 'L0,000,000.00', 'nls_currency=\ ') as ret2
from emp;
-- nls 설정과 무관하게 강제로 원화 변환
```

### to_number, to_date
```sql
select to_number('3243'), to_date('02/03/04', 'DD/RR/MM')
from dual;
```

> 📌 암시적 변환이 가능하지만, 권장하지 않는다.

# General : nvl, nvl2, decode, ...

## nvl, nvl2, ...
### 함수 기능
```sql
select empno, ename, sal, nvl(comm, 0) + 100        from emp;
--커미션이 null 이면 0을 리턴해라 
select empno, ename, sal, nvl2(comm, comm, 0) + 100 from emp;
--nvl2(검증대상, true면 반환, false면 반환)
select empno, ename, comm, 
nvl(to_char(comm), '없음') as ret1,
nvl2(comm, 'comm 있음', 'comm 없음') as ret2
from emp;
```

> 💡 `nvl`과 `nvl2`의 차이
> `nvl`은 데이터 타입이 일치 해야 한다.
> 반면 `nvl2`는 그런 제약 사항이 없다.(바로 다른 데이터 타입을 리턴 가능하다.)

> 💡 테이블 컬럼 에 `null`을 허용할 때는 신중하게 생각 해야 한다.
> `Not Null`을 `Default`

### 문제.연봉 계산하기

```sql
select empno, ename, sal, comm,
sal*12 + nvl(comm, 0)
from emp;
     
select empno, ename, sal, comm, 
nvl2(comm, sal*12+comm, sal*12) as annual_salary,  
nvl2(comm, 'sal*12+comm', 'sal*12') as gubun
from emp;
```


## decode 함수, simple case 표현식, searched case 표현식

### 문제.부서별 급여 차등 조정
```sql
select empno, sal, '' whatif, deptno
from emp;

select /* decode 함수 */
empno, sal, decode(deptno, 10, sal*1.1, 20, sal*1.2, sal) as whatif, deptno 
from emp;
--부서 번호가 10이면 월급*1.1/ 20이면 월급*1.2 /그 외 -> 월급
```

```bash
  EMPNO   SAL     WHATIF     DEPTNO
---------- ----- ---------- ----------
      7369   800        960         20
      7499  1600       1600         30
      7521  1250       1250         30
      7566  2975       3570         20
      7654  1250       1250         30
      7698  2850       2850         30
      7782  2450       2695         10
      7788  3000       3600         20
      7839  5000       5500         10
      7844  1500       1500         30
      7876  1100       1320         20
      7900   950        950         30
      7902  3000       3600         20
      7934  1300       1430         10

14 행이 선택되었습니다.
```

```sql
select /* simple case 표현식 */
empno, sal, case deptno when 10 then sal*1.1 
when 20 then sal*1.2 
else         sal 
end as whatif, deptno 
from emp;
--case when then 
--case(Deptno) 가 10일 경우 -> 월급*1.1
--case(Deptno) 가 20일 경우 -> 월급*1.2


select /* searched case 표현식 */
empno, sal, case when deptno = 10 then sal*1.1
when deptno = 20 then sal*1.2
else                  sal
end as whatif, deptno 
from emp;
```

> 💡 권장
> `case 표현식`이 좀 더 권장된다.
> 그 중에서도 `simple case`가 권장된다.

### 문제.직무별 연수 위치 나타나게 쿼리 작성하세요
```sql
select empno, ename, job, decode(job, 'ANALYST' , '청계산'
                                          , 'MANAGER' , '청계산'
                                          , 'CLERK'   , '한라산'
                                          , 'SALESMAN', '한라산'
                                          , 'PRESIDENT', '백두산'
                                          , '어딘가') as place
      from emp;
```

```sql
select empno, ename, job, case job when 'ANALYST'   then '청계산'
                                         when 'MANAGER'   then '청계산'
                                         when 'CLERK'     then '한라산'
                                         when 'SALESMAN'  then '한라산'
                                         when 'PRESIDENT' then '백두산'
                                         else                  '어딘가'
                                end as place
      from emp;
```

```sql
select empno, ename, job, case 
when job in ('ANALYST', 'MANAGER') then '청계산'
when job in ('CLERK', 'SALESMAN')  then '한라산'
when job in ('PRESIDENT')          then '백두산'
end as place
from emp;
```

### 문제.아래 기준으로 납부할 세금까지 표시되는 쿼리를 작성하세요
> 💡 `문제`
> 2000미만은 급여의 1%,
> 4000미만은 급여의 2%,
> 4000이상은 급여의 3%


```sql
select empno, sal, trunc(sal/2000) as tax
from emp;

select /* decode 함수 */
empno, sal, decode(trunc(sal/2000), 0, sal*0.01, 1, sal*0.02, sal*0.03) as tax
from emp;

select /* simple case 표현식 */
empno, sal, case trunc(sal/2000) 
when 0 then sal*0.01
when 1 then sal*0.02
else        sal*0.03
end as tax
from emp;

select /* searched case 표현식 */
empno, sal, case 
when sal < 2000 then sal*0.01
when sal < 4000 then sal*0.02
else                 sal*0.03
end as tax
from emp;
```

```bash
     EMPNO   SAL        TAX
---------- ----- ----------
      7369   800          8
      7499  1600         16
      7521  1250       12.5
      7566  2975       59.5
      7654  1250       12.5
      7698  2850         57
      7782  2450         49
      7788  3000         60
      7839  5000        150
      7844  1500         15
      7876  1100         11
      7900   950        9.5
      7902  3000         60
      7934  1300         13
```

## decode + 복수행 함수 패턴 

### 문제.부서별 업무별 급여합
```sql
select sal, sal, sal, sal, deptno
from emp;

select sal, decode(deptno, 10, sal), sal, sal, deptno
from emp;

select sal,case deptno when 10 then sal 
end, sal, sal, deptno
from emp;

select sal, 
decode(deptno, 10, sal) d10,
decode(deptno, 20, sal) d20,
decode(deptno, 30, sal) d30,
deptno
from emp;

select sum(sal) as total, 
sum(decode(deptno, 10, sal)) d10,
sum(decode(deptno, 20, sal)) d20,
sum(decode(deptno, 30, sal)) d30
from emp;

select job,
sum(sal) as total, 
sum(decode(deptno, 10, sal)) d10,
sum(decode(deptno, 20, sal)) d20,
sum(decode(deptno, 30, sal)) d30
from emp
group by job
order by job;

select job,
nvl(sum(sal), 0) as total, 
nvl(sum(decode(deptno, 10, sal)), 0) d10,
nvl(sum(decode(deptno, 20, sal)), 0) d20,
nvl(sum(decode(deptno, 30, sal)), 0) d30
from emp
group by job
order by job;
```

### 예제 연습
### 부서 , 업무별 급여합

```sql
select sal,
decode(deptno, 10, sal) "10",
decode(deptno, 20, sal) "20",
decode(deptno, 30, sal) "30",
deptno
from emp
order by deptno;
```

```bash
 SAL         10         20         30     DEPTNO
----- ---------- ---------- ---------- ----------
 2450       2450                               10
 5000       5000                               10
 1300       1300                               10
 2975                  2975                    20
 3000                  3000                    20
 1100                  1100                    20
  800                   800                    20
 3000                  3000                    20
 1250                             1250         30
 1500                             1500         30
 1600                             1600         30
  950                              950         30
 2850                             2850         30
 1250                             1250         30
```

```sql
select sum(sal) "total",
sum(decode(deptno, 10, sal)) "D_10",
sum(decode(deptno, 20, sal)) "D_20",
sum(decode(deptno, 30, sal)) "D_30"
from emp;
```

```bash
    total       D_10       D_20       D_30
---------- ---------- ---------- ----------
     29025       8750      10875       9400
```

```sql
select job, 
sum(sal) "total",
sum(decode(deptno, 10, sal)) "D_10",
sum(decode(deptno, 20, sal)) "D_20",
sum(decode(deptno, 30, sal)) "D_30"
from emp
group by job
order by job;
```

```bash
JOB                     total       D_10       D_20       D_30
------------------ ---------- ---------- ---------- ----------
ANALYST                  6000                  6000
CLERK                    4150       1300       1900        950
MANAGER                  8275       2450       2975       2850
PRESIDENT                5000       5000
SALESMAN                 5600                             5600
```

```sql
select job, 
nvl(sum(sal), 0) "total",
nvl(sum(decode(deptno, 10, sal)), 0) "D_10",
nvl(sum(decode(deptno, 20, sal)), 0) "D_20",
nvl(sum(decode(deptno, 30, sal)), 0) "D_30"
from emp
group by job
order by job;
```

> 💡 `nvl`을 활용하여 `null` 값을 0으로 바꿔줌

```bash
JOB                     total       D_10       D_20       D_30
------------------ ---------- ---------- ---------- ----------
ANALYST                  6000          0       6000          0
CLERK                    4150       1300       1900        950
MANAGER                  8275       2450       2975       2850
PRESIDENT                5000       5000          0          0
SALESMAN                 5600          0          0       5600
```

### 입사 연도, 업무별 급여합합

```sql
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
```

```bash
JOB                     TOTAL      Y1983      Y1982      Y1981   PREVIOUS
------------------ ---------- ---------- ---------- ---------- ----------
ANALYST                  6000                  3000       3000
CLERK                    4150       1100       1300        950        800
MANAGER                  8275                             8275
PRESIDENT                5000                             5000
SALESMAN                 5600                             5600
```

```sql
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
```

```bash
JOB                     TOTAL      Y1983      Y1982      Y1981   PREVIOUS
------------------ ---------- ---------- ---------- ---------- ----------
ANALYST                  6000          0       3000       3000          0
CLERK                    4150       1100       1300        950        800
MANAGER                  8275          0          0       8275          0
PRESIDENT                5000          0          0       5000          0
SALESMAN                 5600          0          0       5600          0
```

> 💡 `nvl`함수를 사용하여 값이 `Null`인 컬럼의 값을 `0`으로 설정

### 문제.분기별 부서별 입사자수
```sql
select empno, deptno, to_char(hiredate, 'Q') as quarter
from emp;
--Q: 분기
select empno, deptno, quarter, quarter, quarter, quarter
from (select empno, deptno, to_char(hiredate, 'Q') as quarter
from emp);
```

```bash
  EMPNO     DEPTNO QU QU QU QU
---------- ---------- -- -- -- --
      7369         20 4  4  4  4
      7499         30 1  1  1  1
      7521         30 1  1  1  1
      7566         20 2  2  2  2
      7654         30 3  3  3  3
      7698         30 2  2  2  2
      7782         10 2  2  2  2
      7788         20 4  4  4  4
      7839         10 4  4  4  4
      7844         30 3  3  3  3
      7876         20 1  1  1  1
      7900         30 4  4  4  4
      7902         20 4  4  4  4
      7934         10 1  1  1  1
```

```sql
select deptno, decode(quarter, '1', 1) as q1, quarter, quarter, quarter
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp);
```

```bash
   DEPTNO         Q1 QU QU QU
---------- ---------- -- -- --
        20            4  4  4
        30          1 1  1  1
        30          1 1  1  1
        20            2  2  2
        30            3  3  3
        30            2  2  2
        10            2  2  2
        20            4  4  4
        10            4  4  4
        30            3  3  3
        20          1 1  1  1
        30            4  4  4
        20            4  4  4
        10          1 1  1  1
```

```sql
select deptno, 
decode(quarter, '1', 1) as q1, 
decode(quarter, '2', 1) as q2, 
decode(quarter, '3', 1) as q3, 
decode(quarter, '4', 1) as q4
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp);
```

```bash
   DEPTNO         Q1         Q2         Q3         Q4
---------- ---------- ---------- ---------- ----------
        20                                           1
        30          1
        30          1
        20                     1
        30                                1
        30                     1
        10                     1
        20                                           1
        10                                           1
        30                                1
        20          1
        30                                           1
        20                                           1
        10          1

14 행이 선택되었습니다.
```

```sql
select count(decode(quarter, '1', 1)) as q1, 
count(decode(quarter, '2', 1)) as q2, 
count(decode(quarter, '3', 1)) as q3, 
count(decode(quarter, '4', 1)) as q4
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp);
```

```bash
    Q1         Q2         Q3         Q4
---------- ---------- ---------- ----------
         4          3          2          5
```

```sql
select deptno,
count(decode(quarter, '1', 1)) as "1분기", 
count(decode(quarter, '2', 1)) as "2분기", 
count(decode(quarter, '3', 1)) as "3분기", 
count(decode(quarter, '4', 1)) as "4분기"
from (select deptno, to_char(hiredate, 'Q') as quarter
from emp)
group by deptno
order by deptno;
```

```bash
    DEPTNO      1분기      2분기      3분기      4분기
---------- ---------- ---------- ---------- ----------
        10          1          1          0          1
        20          1          1          0          3
        30          2          1          2          1
```

