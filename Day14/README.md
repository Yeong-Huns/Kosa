>[!INFO]- 키워드 
> - [1] AI^[생각하는 것 처럼 보이는 것]
> - [2] 안드로이드 
> - [3] DATA^[자료] 
> - [4] INFORMATION^[정보]
> - [5] DATA MODELING^[챙겨두면 돈이 될 것 같은 데이터를 선별 정리]
> - [6] INFORMATION TECHNOLOGY^[`IT` : DATA를 INFORMATION 으로 바꾸는 것]
> - [7] DATA ANALYSIS^[데이터를 쪼개는 것] 
> - [8] META DATA^[윈도우 레지스트리와 유사하게 ORACLE 스스로 작동을 위하여 사용하는 데이터]
> - [9] CLOUD^[`As a  Service` : 대행서비스. 치료대행서비스->`Hospital`, 요리대행서비스->`Restaurant`]
> - [0] IaaS
> - [1] PaaS
> - [2] SaaS

```SQL
CREATE USER SIDNEY  
IDENTIFIED BY OUT_STANDING1
DEFAULT TABLESPACE USERS -- 디폴트 테이블 지정
QUOTA 10M ON USERS -- 공간 배정
QUOTA 100M ON MYTABS
```

```SQL 
CREATE USER C##COMM_USER -- 공용 유저를 만드는데 하지말자
IDENTIFIED BY COMM_PWD
DEFAULT TABLESPACE EXAMPLE
```
> 📌 굉장히 세팅할게 많고, 배워야 할 것도 많다.

> 📌`GENERATED AS IDENTITY(PRIMARY)`: 자동으로 증가하는 `VALUE`

MySQL : CREATE DATABASE | SCHEMA -> 둘이 동일
Oracle : 둘이 다름 

## 멀티테넌트 
`CDB`: 집주인 DB (컨테이너 DB)
`PDB`: 세입자 DB (플러거블^[USB처럼 쉽게 옮길 수 있음] DB)

>📌 `C##` 나오면 도망쳐라

아이디 / 비밀번호 / 리스너 머신 IP / 리스너 포트번호 / xepdb1 

# PL/SQL 개요
`PL/SQL`: PROCEDURE LANGUAGE -> RUNTIME 

![IMAGE](https://www.oracletutorial.com/wp-content/uploads/2017/11/plsql-architecture.png)

오라클 서버 : `SQL` 엔진 & `PL/SQL` 엔진 두 개의 엔진이 있다. 

실행시간 컴파일 : JAVA의 SQL문 -> 서버 -> (문자열)로 인식 -> 컴파일(SQL로 변환 후 실행)
-> 컴파일 요구가 많을 시 서비스가 지연됨!!

`PL/SQL` : 미리 컴파일을 해두고, 불러와서 사용 (컴파일 과정이 생략)
-> 성능 향상

```SQL
SELECT EMPNO, SAL, SAL FROM EMP;
```

```SQL 
결과::
7369	800	800
7499	1600	1600
7521	1250	1250
7566	2975	2975
```

> 📌 컬럼 복제

```SQL
SELECT EMPNO, SAL, SAL*1.2 FROM EMP;
```

```SQL
결과::
7369	800	960
7499	1600	1920
7521	1250	1500
7566	2975	3570
7654	1250	1500
7698	2850	3420
```
 
> 📌 `SQL`은  `DATA MANIPULATING` 기능이 매우 강하다 


## Advantages of PL/SQL
### Tight Integration with SQL
> 📌 `SEEMLESS` : `SQL` 코드와 `PL/SQL` 구분이 안될 만큼 하나의 언어와 같다.

### High Performance 
>📌 생산성이 향상되고 성능도 향상된다. 

### Portability(이식성)
>📌오라클이 다양한 환경에서 돌아가니 오라클 기반인 `PL/SQL` 도 이식성이 좋다.

### Scalability(확장성)
>📌 확장성이 좋다. -> `CPU`한 개론 할 수 없는 `CPU`를 늘려서 더 많은 일을 처리할 수 있는것 처럼 필요시 추가적으로 기능을 확장하여 더 많은 작업 수행이 가능하다.

## Main Features of PL/SQL

### BLOCK 구조 
```PLSQL
DECLARE 
-- 옵션 [실행에 필요한 여러 요소 선언]; 
BEGIN 
-- 필수[작업을 위해 실제 실행하는 명령어];
EXCEPTION 
-- 옵션 [PL/SQL 수행 도중 발생하는 오류 처리];
END;
/
```

```PLSQL
CREATE OR REPLACE 유형 이름
IS|AS
--선언부 
BEGIN -- 필수
--실행부
EXCEPTION -- 옵션
-- 예외처리
END; 
/
```

### ANONYMOUS BLOCK (익명 블록)
```PLSQL
--DECLARE -> 생략가능
BEGIN 
	DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
END;
/
```

```
결과::
HELLO , WORLD
```

```PLSQL
BEGIN 
	FOR I IN 1..10 LOOP
	DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
	END LOOP;
END;
/
```

```
결과::
HELLO WORLD
HELLO WORLD
HELLO WORLD
HELLO WORLD
HELLO WORLD
HELLO WORLD
HELLO WORLD
HELLO WORLD
HELLO WORLD
HELLO WORLD
```

```PLSQL
DECLARE 
V_NAME EMP.ENAME%TYPE; -- (1)
BEGIN 
SELECT ENAME INTO V_NAME -- (2)
FROM EMP 
WHERE EMPNO = 7788; --(3)
DBMS_OUTPUT.PUT_LINE(V_NAME); --(4)
END;
/
```

```
결과::
SCOTT
```

> 💡 `LINE BY LINE`
> **02행**: `V_NAME` 변수를 만들면서 `EMP` 테이블의 `ENAME`과 동일한 형식으로 지정한다.
> **04행~06행** : `EMPNO`가 7788인 `ENAME`을 가져와서 `V_NAME`에 지정한다.
> **07행** : `V_NAME`을 출력한다.

> 💡 만약 다시 실행하고 싶으면?
> 1. 파일로 저장
> 2. 프로시저로 저장

```PLSQL
CREATE OR REPLACE PROCEDURE PROC1
IS -- 만들거나 대체해라 
V_NAME EMP.ENAME%TYPE;
BEGIN 
SELECT ENAME INTO V_NAME
FROM EMP 
WHERE EMPNO = 7788;
DBMS_OUTPUT.PUT_LINE(V_NAME);
END;
/

-- EXECUTE PROC1; -> 생성한 PROC1 프로시져 실행
```

```SLQ
결과::
SCOTT
```

> 💡 LINE BY LINE
> **01행** : `RPOC1` 프로시저를 (존재하지 않는다면)만들거나 대체하라
> **03행** : `V_NAME` 을 `EMP`테이블의 `ENAME`형식으로 지정한다.
> **05행~07행** : `EMPNO`가 7788인 `ENAME`을 가져와서 `V_NAME`에 지정한다. 
> **08행** : `V_NAME`을 출력한다.
> **12행** : 저장한 프로시저를 실행한다. 
### DATA TYPE(데이터 타입)

| 이름          | 종류                               |
| ----------- | -------------------------------- |
| `SCALAR`    | CHARACTER, NUMBER, DATE, BOOLEAN |
| `COMPOSITE` | `PL/SQL TABLE`, `PL/SQL RECORD`  |
| `REFERENCE` | `REF CURSOR`                     |
| `LOB`       | `CLOB`, `BLOB`, `BFILE`          |

## Lexical Units
>📌 `PL/SQL` 의 어휘단위는 구분 기호, 식별자, 리터럴, 프라그마, 주석 등 작은 개별 구성 요소이다. 

### Delimiters(구분자)
>📌 구분자는 `PL/SQL`에서 특별한 의미를 갖는 문자 또는 문자 조합 

표 2-2 PL/SQL 구분 기호

|구분 기호|의미|
|:--|:--|
|`+`|덧셈 연산자|
|`:=`|할당 연산자|
|`=>`|협회 운영자|
|`%`|속성 표시|
|`'`|문자열 구분 기호|
|`.`|구성요소 표시기|
|`\|`|연결 연산자|
|`/`|나눗셈 연산자|
|`**`|지수 연산자|
|`(`|표현식 또는 목록 구분 기호(시작)|
|`)`|표현식 또는 목록 구분 기호(끝)|
|`:`|호스트 변수 표시기|
|`,`|항목 구분 기호|
|`<<`|라벨 구분 기호(시작)|
|`>>`|라벨 구분자(끝)|
|`/*`|여러 줄 주석 구분 기호(시작)|
|`*/`|여러 줄 주석 구분 기호(끝)|
|`*`|곱셈 연산자|
|`"`|따옴표 붙은 식별자 구분 기호|
|`..`|범위 연산자|
|`=`|관계 연산자(같음)|
|`<>`|관계 연산자(같지 않음)|
|`!=`|관계 연산자(같지 않음)|
|`~=`|관계 연산자(같지 않음)|
|`^=`|관계 연산자(같지 않음)|
|`<`|관계 연산자(보다 작음)|
|`>`|관계 연산자(보다 큼)|
|`<=`|관계 연산자(작거나 같음)|
|`>=`|관계 연산자(크거나 같음)|
|`@`|원격 액세스 표시기|
|`--`|한 줄 주석 표시기|
|`;`|명령문 종결자|
|`-`|빼기 또는 부정 연산자|

### Identifier(식별자)
> 📌 식별자는 다음을 포함하는 `PL/SQL`요소를 명명한다.

- 상수
- 커서
- 예외
- 키워드
- 라벨
- 패키지
- 예약어
- 서브프로그램
- 유형  
- 변수

### Literals(리터럴)
> 📌 `리터럴`은 식별자로 표시되지도 않고 다른 값에서 계산되지도 않는 값이다.


## Declarations
### NOT NULL Constraint
>📌`NOT NULL`스칼라 변수나 상수(또는 합성 변수나 상수의 스칼라 구성요소)에 제약 조건을 부과할 수 있다.
`NOT NULL`조건은 항목에 `null` 값을 할당하는 것을 방지한다. 
암시적으로든 명시적으로든 `NOT NULL`을 지정하는 스칼라 변수 선언에서는 `NOT NULL`변수에 초기 값을 할당해야 한다. 
## PL/SQL Control Statements

## Architecture of PL/SQL

# `PL/SQL` IF Statements 

```PLSQL
DECLARE n_sales NUMBER := 2000000; 
BEGIN IF n_sales > 100000 THEN 
DBMS_OUTPUT.PUT_LINE( 'Sales revenue is greater than 100K ' ); 
END IF; 
END;
```

```PLSQL
DECLARE
  b_profitable BOOLEAN;
  n_sales      NUMBER;
  n_costs      NUMBER;
BEGIN
  b_profitable := false;   
  IF n_sales > n_costs THEN
    b_profitable := true;
  END IF;
END;

```

```PLSQL
IF b_profitable = TRUE THEN
   DBMS_OUTPUT.PUT_LINE( 'This sales deal is profitable' );
END IF;
-- 위와 같이 쓰지마세요 
-- 아래와 같이 사용하세요
IF b_profitable THEN
   DBMS_OUTPUT.PUT_LINE( 'This sales deal is profitable' );
END IF;
```

```PLSQL
DECLARE
  n_sales NUMBER := 300000;
  n_commission NUMBER( 10, 2 ) := 0;
BEGIN
  IF n_sales > 200000 THEN
    n_commission := n_sales * 0.1;
  ELSE
    n_commission := n_sales * 0.05;
  END IF;
END;
```

## ELSIF
```PLSQL
DECLARE
  n_sales NUMBER := 300000;
  n_commission NUMBER( 10, 2 ) := 0;
BEGIN
  IF n_sales > 200000 THEN
    n_commission := n_sales * 0.1;
  ELSIF n_sales <= 200000 AND n_sales > 100000 THEN 
    n_commission := n_sales * 0.05;
  ELSIF n_sales <= 100000 AND n_sales > 50000 THEN 
    n_commission := n_sales * 0.03;
  ELSE
    n_commission := n_sales * 0.02;
  END IF;
END;
```

## Nested IF statement 
```PLSQL
IF condition_1 THEN
    IF condition_2 THEN
        nested_if_statements;
    END IF;
ELSE
    else_statements;
END IF; 
```

# PL/SQL CASE Statement
## Simple PL/SQL CASE statement 
```PLSQL
CASE selector
WHEN selector_value_1 THEN
    statements_1
WHEN selector_value_1 THEN 
    statement_2
...
ELSE
    else_statements
END CASE;

```

```PLSQL
DECLARE
  c_grade CHAR( 1 );
  c_rank  VARCHAR2( 20 );
BEGIN
  c_grade := 'B';
  CASE c_grade
  WHEN 'A' THEN
    c_rank := 'Excellent' ;
  WHEN 'B' THEN
    c_rank := 'Very Good' ;
  WHEN 'C' THEN
    c_rank := 'Good' ;
  WHEN 'D' THEN
    c_rank := 'Fair' ;
  WHEN 'F' THEN
    c_rank := 'Poor' ;
  ELSE
    c_rank := 'No such grade' ;
  END CASE;
  DBMS_OUTPUT.PUT_LINE( c_rank );
END;
```

## Searched CASE statement 
```PLSQL
CASE
WHEN condition_1 THEN statements_1
WHEN condition_2 THEN statements_2
...
WHEN condition_n THEN statements_n
[ ELSE
  else_statements ]
END CASE;]
```

```PLSQL
DECLARE
  n_sales      NUMBER;
  n_commission NUMBER;
BEGIN
  n_sales := 150000;
  CASE
  WHEN n_sales    > 200000 THEN
    n_commission := 0.2;
  WHEN n_sales   >= 100000 AND n_sales < 200000 THEN
    n_commission := 0.15;
  WHEN n_sales   >= 50000 AND n_sales < 100000 THEN
    n_commission := 0.1;
  WHEN n_sales    > 30000 THEN
    n_commission := 0.05;
  ELSE
    n_commission := 0;
  END CASE;

  DBMS_OUTPUT.PUT_LINE( 'Commission is ' || n_commission * 100 || '%'
  );
END;
```

> 📌 `IF` 문처럼 조건이 바로 올 수 있다.

## 팝업 퀴즈 1(CASE문)

```PLSQL
CREATE OR REPLACE PROCEDURE PROC1(PNUM EMP.EMPNO%TYPE) IS
VNUM EMP.EMPNO%TYPE;
VSAL EMP.SAL%TYPE;
BEGIN
SELECT EMPNO, SAL INTO VNUM, VSAL
FROM EMP
WHERE EMPNO = PNUM;

CASE 
WHEN VSAL >= 3000 THEN DBMS_OUTPUT.PUT_LINE('번호 : '||VNUM||' , 월급 : '||VSAL||' 등급 : A');
WHEN VSAL >= 2000 THEN DBMS_OUTPUT.PUT_LINE('번호 : '||VNUM||' , 월급 : '||VSAL||' 등급 : B');
ELSE DBMS_OUTPUT.PUT_LINE('번호 : '||VNUM||' , 월급 : '||VSAL||' 등급 : C');
END CASE;
END;
/
EXECUTE PROC1(7369);
EXECUTE PROC1(7788);
EXECUTE PROC1(7782);
```

```PLSQL
결과::
번호 : 7369 , 월급 : 800 등급 : C
번호 : 7788 , 월급 : 3000 등급 : A
번호 : 7782 , 월급 : 2450 등급 : B
```

> 💡 LINE BY LINE
> **01행** : `EMP`테이블의 `EMPNO`열과 동일타입의 파라미터 `PNUM`을 받는 
> `PROC1` 프로시저를 만들거나 대체해라  
> **02행~03행** : `EMP`테이블의 `EMPNO`, `ENAME`과 동일한 타입의 변수, `VNUM`과 `VSAL`을 선언한다.
> **04행~07행** : 인자로 전달받은 `PNUM` 값을 `EMPNO`에 대입하여 `EMP` 테이블에서 `PNUM`값과 
> 동일한 `EMPNO`를 가지는 `EMPNO`와 `SAL`D을 `VNUM`과 `VSAL`에 넣는다. 
> **09행~13행** : `VSAL`이 3000 이상이면 A등급, 2000 이상이면 B등급, 그 외라면 C 등급을 출력한다

### 내가 만들어 본 예제(CASE문)
```PLSQL
-- CASE 문 사용
CREATE OR REPLACE PROCEDURE MY_PRO1(PNUM EMP.EMPNO%TYPE) IS
VNUM EMP.EMPNO%TYPE;
VNAME EMP.ENAME%TYPE;
VSAL EMP.SAL%TYPE;
BEGIN 
SELECT EMPNO, ENAME, SAL INTO VNUM, VNAME, VSAL
FROM EMP
WHERE EMPNO = PNUM;
CASE 
WHEN VSAL >= 3000 THEN DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 등 급 : A');
WHEN VSAL >= 2000 THEN DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 등 급 : B');
ELSE DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 등 급 : C');
END CASE;
END;
/
EXEC MY_PRO1(7900);
```

```
결과::
사 번 : 7900, 이 름 : JAMES, 등 급 : C
```
## 팝업 퀴즈 1(IF-ELSIF-ELSE문)
```PLSQL
CREATE OR REPLACE PROCEDURE PROC1_1(PNUM EMP.EMPNO%TYPE) IS
VNUM EMP.EMPNO%TYPE;
VSAL EMP.SAL%TYPE;
BEGIN
SELECT EMPNO, SAL INTO VNUM, VSAL
FROM EMP
WHERE EMPNO = PNUM;

IF VSAL >= 3000 THEN DBMS_OUTPUT.PUT_LINE('번호 : '||VNUM||' , 월급 : '||VSAL||' 등급 : A');
ELSIF VSAL >= 2000 THEN DBMS_OUTPUT.PUT_LINE('번호 : '||VNUM||' , 월급 : '||VSAL||' 등급 : B');
ELSE DBMS_OUTPUT.PUT_LINE('번호 : '||VNUM||' , 월급 : '||VSAL||' 등급 : C');
END IF;
END;
/
EXECUTE PROC1_1(7369);
EXECUTE PROC1_1(7788);
EXECUTE PROC1_1(7782);
```

```
결과::
번호 : 7369 , 월급 : 800 등급 : C
번호 : 7788 , 월급 : 3000 등급 : A
번호 : 7782 , 월급 : 2450 등급 : B
```

### 내가 만들어 본 예제(IF-ELSIF-ELSE문)
```PLSQL
--IF-ELSIF-ELSE 사용
CREATE OR REPLACE PROCEDURE MY_PRO2(PNUM EMP.EMPNO%TYPE) IS
VNUM EMP.EMPNO%TYPE;
VNAME EMP.ENAME%TYPE;
VSAL EMP.SAL%TYPE;
BEGIN 
SELECT EMPNO, ENAME, SAL INTO VNUM, VNAME, VSAL
FROM EMP
WHERE EMPNO = PNUM;

IF VSAL >= 3000 THEN DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 등 급 : A');
ELSIF VSAL >= 2000 THEN DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 등 급 : B');
ELSE DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 등 급 : C');
END IF;
END;
/
EXEC MY_PRO2(7900);
```

```
결과::
사 번 : 7900, 이 름 : JAMES, 등 급 : C
```
## 팝업 퀴즈 1(FUNCTION)
```PLSQL
CREATE OR REPLACE FUNCTION FN_SAL_GRADE(PSAL EMP.SAL%TYPE) 
RETURN VARCHAR2 IS
BEGIN
CASE
WHEN PSAL >= 3000 THEN RETURN 'A';
WHEN PSAL >= 2000 THEN RETURN 'B';
ELSE RETURN 'C';
END CASE;
END;
/
CREATE OR REPLACE PROCEDURE PROC1_2(PNUM EMP.EMPNO%TYPE) IS
VNUM EMP.EMPNO%TYPE;
VSAL EMP.SAL%TYPE;
BEGIN
SELECT EMPNO, SAL INTO VNUM, VSAL
FROM EMP
WHERE EMPNO = PNUM;
DBMS_OUTPUT.PUT_LINE('번호 : '||VNUM||' , 월급 : '||VSAL||' 등급 : '||FN_SAL_GRADE(VSAL));
END CASE;
EXCEPTION
WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE(PNUM||'사원은 없습니다!!');
WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('에러');
END;
/
EXECUTE PROC1_2(7369);
EXECUTE PROC1_2(7788);
EXECUTE PROC1_2(7782);

--FUNCTION 을 이용한 방법
```

> 💡 LINE BY LINE
> **01행~09행** : 함수 선언부. EMP.SAL 타입의 파라미터를 전달받고, `CASE`문을 통해 
> 조건에 맞는 `VARCHAR2`를 `RETURN`한다.
> **18행** : 기존에 존재하던 `IF-ELSIF-ELSE`나 `CASE`구문을 삭제하고, 등급을 출력할 때,
> 앞서 선언해 둔 `FN_SAL_GRADE`함수에 `VSAL`을 전달하고 등급을 `RETURN`받는다.

### 내가 만들어 본 예제(FUNCTION)
```PLSQL
--FUNCTION 사용
CREATE OR REPLACE FUNCTION FN_MYFUNCTION1(FSAL EMP.SAL%TYPE) 
RETURN VARCHAR2 IS
BEGIN 
IF FSAL >= 3000 THEN RETURN 'A';
ELSIF FSAL >= 2000 THEN RETURN 'B';
ELSE RETURN 'C';
END IF;
END;
/
CREATE OR REPLACE PROCEDURE MY_PROC3(PNUM EMP.EMPNO%TYPE) IS
VNUM EMP.EMPNO%TYPE;
VNAME EMP.ENAME%TYPE;
VSAL EMP.SAL%TYPE;
BEGIN
SELECT EMPNO, ENAME, SAL INTO VNUM, VNAME, VSAL
FROM EMP
WHERE EMPNO = PNUM;
DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 등 급 : '||FN_MYFUNCTION1(VSAL));
END;
/
EXEC MY_PROC3(7900);
```

```
결과::
사 번 : 7900, 이 름 : JAMES, 등 급 : C
```
## 팝업 퀴즈1(DECODE)
```PLSQL
CREATE OR REPLACE PROCEDURE PROC1_3(PNUM EMP.EMPNO%TYPE) IS 
ENUM EMP.EMPNO%TYPE;
ESAL EMP.SAL%TYPE;
EGRADE VARCHAR2(1);
BEGIN
SELECT EMPNO, SAL, DECODE(TRUNC(SAL/1000), 0, 'C', 1, 'C', 2, 'B', 'A')
INTO ENUM, ESAL, EGRADE
FROM EMP
WHERE EMPNO = PNUM;
DBMS_OUTPUT.PUT_LINE('번호 : '||ENUM||' , 월급 : '||ESAL||' 등급 : '||EGRADE);
END;
/
EXECUTE PROC1_3(7369);
EXECUTE PROC1_3(7788);
EXECUTE PROC1_3(7782);
```


> 💡 LINE BY LINE
> **04행** : `GRADE`를 저장할 `VARCHAR2` 타입 변수 `EGRADE`를 선언한다.
> **06행** : `SAL`값을 `1000`으로 나누고, 나머지를 버린다(`TRUNC`). 
> 해당 값(`TRUNC(SAL/1000)`) 이 0 이면 C, 1 이면 C, 2면 B, 모두 아니면 A를 
> `EGRADE`에 저장한다. 
## 📌 `DECODE 함수` 
> `IF-ELS 구문`과 아주 유사한 기능을 수행한다. 
> 간단한 사용방법은 아래와 같다.
> ![이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99D8F6415CFFC30731)

### 내가 만들어 본 예제(`DECODE`활용)
```PLSQL
--DECODE 사용
CREATE OR REPLACE PROCEDURE MY_PROC4(PNUM EMP.EMPNO%TYPE) IS 
VNUM EMP.EMPNO%TYPE;
VNAME EMP.ENAME%TYPE;
VSAL EMP.SAL%TYPE;
GRADE VARCHAR2(1);
BEGIN 
SELECT EMPNO, ENAME, SAL, DECODE(TRUNC(SAL/1000), 0, 'C', 1, 'C', 2, 'B', 'A')
INTO VNUM, VNAME, VSAL, GRADE
FROM EMP
WHERE EMPNO = PNUM;
DBMS_OUTPUT.PUT_LINE('사 번 : '||VNUM||', 이 름 : '||VNAME||', 연 봉 : '||VSAL||', 등 급 : '||GRADE);
END;
/
EXEC MY_PROC4(7900);
```

```
결과::
사 번 : 7900, 이 름 : JAMES, 연 봉 : 950, 등 급 : C
```
# PL/SQL GOTO Statement 
## PL/SQL GOTO statement example 
```PLSQL
BEGIN
  GOTO second_message;

  <<first_message>> -- 2 SECOND에서 넘어옴
  DBMS_OUTPUT.PUT_LINE( 'Hello' );
  GOTO the_end;

  <<second_message>> -- 1 바로 열로옴 
  DBMS_OUTPUT.PUT_LINE( 'PL/SQL GOTO Demo' );
  GOTO first_message;

  <<the_end>>
  DBMS_OUTPUT.PUT_LINE( 'and good bye...' );

END;

```

```PLSQL
결과::
PL/SQL GOTO Demo
Hello
and good Bye...
```

![IMAGE](https://www.oracletutorial.com/wp-content/uploads/2017/11/PLSQL-GOTO-Example.png)

>  📌`GOTO` 의 태그는 반드시 실행문 위에 있어야한다!
## GOTO statement restrictions
```PLSQL
DECLARE 
  n_sales NUMBER;
  n_tax NUMBER;
BEGIN 
    GOTO inside_if_statement;
    IF n_sales > 0 THEN
      <<inside_if_statement>>
      n_tax  := n_sales * 0.1;
    END IF;
END;

```

>📌 `IF` 문 안에 `GOTO`를 넣지 마라 

```PLSQL
DECLARE
  n_sales      NUMBER;
  n_commission NUMBER;
BEGIN
  n_sales := 120000;
  IF n_sales      > 100000 THEN
    n_commission := 0.2;
    GOTO zero_commission;
  elsif n_sales   > 50000 THEN
    n_commission := 0.15;
  elsif n_sales   > 20000 THEN
    n_commission := 0.1;
  ELSE
    <<zero_commission>>
    n_commission := 0;
  END IF;
END;
```

>📌 `CASE`문 안에 `GOTO` 선언 금지

# PL/SQL LOOP

## EXIT statement 

```PLSQL
LOOP
    EXIT;
END LOOP; 

```

```PLSQL
LOOP
    IF condition THEN
        EXIT;
    END IF;
END LOOP;
```

```PLSQL
DECLARE
  l_counter NUMBER := 0;
BEGIN
  LOOP
    l_counter := l_counter + 1;
    IF l_counter > 3 THEN
      EXIT;
    END IF;
    dbms_output.put_line( 'Inside loop: ' || l_counter )  ;
  END LOOP;
  -- control resumes here after EXIT
  dbms_output.put_line( 'After loop: ' || l_counter );
END;
```

```
결과::
Inside loop: 1
Inside loop: 2
Inside loop: 3
After loop: 4
```

## EXIT WHEN statement 
```PLSQL
DECLARE
  l_counter NUMBER := 0;
BEGIN
  LOOP
    l_counter := l_counter + 1;
    EXIT WHEN l_counter > 3;
    dbms_output.put_line( 'Inside loop: ' || l_counter ) ;
  END LOOP;

  -- control resumes here after EXIT
  dbms_output.put_line( 'After loop: ' || l_counter );
END;
```

> 📌`IF l_COUNTER > 3 THEN EXIT;`을 줄여 쓸 수 있다.
## Constructing nested loops using PL/SQL LOOP statements 
```PLSQL
DECLARE
  l_i NUMBER := 0;
  l_j NUMBER := 0;
BEGIN
  <<outer_loop>>
  LOOP
    l_i := l_i + 1;
    EXIT outer_loop WHEN l_i > 2;    
    dbms_output.put_line('Outer counter ' || l_i);
    -- reset inner counter
    l_j := 0;
      <<inner_loop>> LOOP
      l_j := l_j + 1;
      EXIT inner_loop WHEN l_j > 3;
      dbms_output.put_line(' Inner counter ' || l_j);
    END LOOP inner_loop;
  END LOOP outer_loop;
END;
```

```
결과::
Outer counter 1
 Inner counter 1
 Inner counter 2
 Inner counter 3
Outer counter 2
 Inner counter 1
 Inner counter 2
 Inner counter 3
```

# PL/SQL FOR LOOP 

## PL/SQL FOR LOOP statement  
```PLSQL
FOR index IN lower_bound .. upper_bound
LOOP 
    statements; 
END LOOP;
```

> 📌 `주의`
> `FOR LOOP`문 사용시 반드시 낮은 숫자 -> 높은 숫자 순이여야 한다.
> `FOR I IN 9 .. 1` 이런 형식은 에러발
## PL/SQL FOR LOOP examples

```PLSQL
BEGIN
  FOR l_counter IN 1..5
  LOOP
    DBMS_OUTPUT.PUT_LINE( l_counter );
  END LOOP;
END;

```

```
결과::
1
2
3
4
5

```

```PLSQL
DECLARE
  l_step  PLS_INTEGER := 2;
BEGIN
  FOR l_counter IN 1..5 LOOP
    dbms_output.put_line (l_counter*l_step);
  END LOOP;
END;
```

```
결과::
2
4
6
8
10
```

> ### 📌 `NUMBER`, `PLS_INTEGER`, `BINARY_INTEGER` 등등의 차이?
> 오라클 내에는 `NUMBER` 뿐 아니라 `INTEGER`, `DOUBLE`, `FLOAT`, `DECIMAL`, `LONG`,
> `BINARY_INTEGER`,`PLS_INTEGER` 등과 같은 데이터 타입이 존재한다. 
> 오라클에선 사용자의 편의를 위해 모든 타입을 `NUMBER`로 사용할 수 있다.
> 다만, 오라클 엔진에서 정확한 타입으로 변환하는 과정이 추가되기 때문에 
> 처음부터 데이터 타입을 명시하는 것이 더 빠르다고 한다.

```PLSQL
DECLARE 
L_STEP NUMBER := 2;
BEGIN
FOR I IN 1..5 LOOP
DBMS_OUTPUT.PUT_LINE(L_STEP * I);
END LOOP;
END;
/
```

```
결과::
2
4
6
8
10
```

> 💡 실제로 `PLS_INTEGER` 로 작성된 예제를 `NUMBER`로 변경하여도 동일한 결과가 나오는 것을 확인하였다.
### Simulating `STEP` clause in `FOR LOOP` statement
```PLSQL
BEGIN 
FOR I IN 1..100 LOOP 
IF MOD(I, 2) = 0 THEN DBMS_OUTPUT.PUT_LINE('번호 : '||I);
END IF;
END LOOP;
END;
/
```

### Referencing variable with the same name as the loop index 
```PLSQL
DECLARE
  l_counter PLS_INTEGER := 10;
BEGIN
  FOR l_counter IN 1.. 5 loop
    DBMS_OUTPUT.PUT_LINE (l_counter); -- FOR l_counter
  end loop;
  -- after the loop
  DBMS_OUTPUT.PUT_LINE (l_counter); -- 10
END; 
```

```PLSQL
<<outer>>
DECLARE
  l_counter PLS_INTEGER := 10;
BEGIN
  FOR l_counter IN 1.. 5 loop
    DBMS_OUTPUT.PUT_LINE ('Local counter:' ||  l_counter);
    outer.l_counter := l_counter;
  end loop;
  -- after the loop
  DBMS_OUTPUT.PUT_LINE ('Global counter' || l_counter);
END outer;
```

## FOR LOOP with REVERSE keyword(역순 출력)
```PLSQL
FOR index IN REVERSE lower_bound .. upper_bound
    LOOP 
    statements; 
END LOOP;
```

```PLSQL
BEGIN
  FOR l_counter IN REVERSE 1..3
  LOOP
    DBMS_OUTPUT.PUT_LINE( l_counter );
  END LOOP;
END;
```

# PL/SQL WHILE Loop (`WHILE`문)

## WHILE loop statement 
```PLSQL
DECLARE
  n_counter NUMBER := 1;
BEGIN
  WHILE n_counter <= 5
  LOOP
    DBMS_OUTPUT.PUT_LINE( 'Counter : ' || n_counter );
    n_counter := n_counter + 1;
  END LOOP;
END;
```

```
결과::
Counter : 1
Counter : 2
Counter : 3
Counter : 4
Counter : 5
```

# PL/SQL CONTINUE(`CONTINUE` 구문)

## CONTINUE statement 
```PLSQL
BEGIN
  FOR n_index IN 1 .. 10
  LOOP
    -- skip odd numbers
    IF MOD( n_index, 2 ) = 1 THEN
      CONTINUE;
    END IF;
    DBMS_OUTPUT.PUT_LINE( n_index );
  END LOOP;
END;
```

```
결과::
2
4
6
8
10
```

## CONTINUE WHEN statement 
```PLSQL
IF condition THEN
    CONTINUE;
END IF;
```

```PLSQL
BEGIN
  FOR n_index IN 1 .. 10
  LOOP
    -- skip even numbers
    CONTINUE
  WHEN MOD( n_index, 2 ) = 0;
    DBMS_OUTPUT.PUT_LINE( n_index );
  END LOOP;
END;
```

```
결과::
1
3
5
7
9
```

### FOR LOOP Statement Tries to Change Index Value

```PLSQL
BEGIN
  FOR i IN 1..3 LOOP
    IF i < 3 THEN
      DBMS_OUTPUT.PUT_LINE (TO_CHAR(i));
    ELSE
      i := 2;
    END IF;
  END LOOP;
END;
/
```

> 📌 `FOR` 문의 `I`의 값은 이용은 할 순 있어도, 재할당은 불가능하다! (위 구문은 에러난다.)


### Incorrect Label Placement
```PLSQL
DECLARE
  done  BOOLEAN;
BEGIN
  FOR i IN 1..50 LOOP
    IF done THEN
       GOTO end_loop;
    END IF;
    <<end_loop>>
  END LOOP;
END;
/
```

>📌 `<<end_loop>>`는 실행문 위에 붙어야한다. 위 구문은 에러가 난다.

### NULL Statement as Placeholder During Subprogram Creation
```plsql
CREATE OR REPLACE PROCEDURE award_bonus (
  emp_id NUMBER,
  bonus NUMBER
) AUTHID DEFINER AS
BEGIN    -- Executable part starts here
  NULL;  -- Placeholder
  -- (raises "unreachable code" if warnings enabled)
END award_bonus;
/
```

> 📌 `BEGIN`과  `END` 사이엔 내용이 있어야 한다.  `NULL`이라도 넣지않으면 에러가 난다.
### NULL Statement in ELSE Clause of Simple CASE Statement
```PLSQL
CREATE OR REPLACE PROCEDURE print_grade (
  grade CHAR
) AUTHID DEFINER AS
BEGIN
  CASE grade
    WHEN 'A' THEN DBMS_OUTPUT.PUT_LINE('Excellent');
    WHEN 'B' THEN DBMS_OUTPUT.PUT_LINE('Very Good');
    WHEN 'C' THEN DBMS_OUTPUT.PUT_LINE('Good');
    WHEN 'D' THEN DBMS_OUTPUT.PUT_LINE('Fair');
    WHEN 'F' THEN DBMS_OUTPUT.PUT_LINE('Poor');
    ELSE NULL;
  END CASE;
END;
/
BEGIN
  print_grade('A');
  print_grade('S');
END;
/
```

## 구구단 만들기
### 구구단 예제(1)
```PLSQL
CREATE OR REPLACE PROCEDURE GUGUDAN1(VNUM NUMBER) IS
RS NUMBER := VNUM;
BEGIN
FOR J IN 1..9 LOOP
DBMS_OUTPUT.PUT_LINE(RS||' * '||J||' = '||RS*J);
END LOOP;
END;
/
EXECUTE GUGUDAN1(9);
```

```
결과::
9 * 1 = 9
9 * 2 = 18
9 * 3 = 27
9 * 4 = 36
9 * 5 = 45
9 * 6 = 54
9 * 7 = 63
9 * 8 = 72
9 * 9 = 81
```

> 💡 VNUM 을 전달받아 `FOR LOOP` 문의 1부터 9까지 증가하는 내부 변수 `J`와 곱하여 
> 출력한다.
### 나의 예제(1)
```PLSQL
CREATE OR REPLACE PROCEDURE MY_GUGUDAN1(PNUM PLS_INTEGER) IS
BEGIN
FOR I IN 1..9 LOOP
DBMS_OUTPUT.PUT_LINE(PNUM|| ' * '||I||' = '||PNUM*I);
END LOOP;
END;
/
EXEC MY_GUGUDAN1(4);
```

```
결과::
4 * 1 = 4
4 * 2 = 8
4 * 3 = 12
4 * 4 = 16
4 * 5 = 20
4 * 6 = 24
4 * 7 = 28
4 * 8 = 32
4 * 9 = 36
```

> 💡 생각해보니 `RS NUMBER`라는 변수를 따로 만들지 않아도 충분히 결과를 출력할 수 있을 듯 하여 변수 선언을 생략하였고, 조금 더 빠른 속도를 위해 `PLS_INTEGER`타입으로 변경하였다. 
> 
### 구구단 예제(2)

```PLSQL
CREATE OR REPLACE PROCEDURE GUGUDAN2(VNUM NUMBER, VNUM2 NUMBER) IS
RS NUMBER;
BEGIN
FOR I IN VNUM..VNUM2 LOOP
FOR J IN 1..9 LOOP
RS := I * J;
DBMS_OUTPUT.PUT_LINE(I||' * '||J||' = '||RS);
END LOOP;
DBMS_OUTPUT.PUT_LINE('');
END LOOP;
END;
/
EXECUTE GUGUDAN2(3,7);
```

```
결과::
3 * 1 = 3
3 * 2 = 6
3 * 3 = 9
3 * 4 = 12
3 * 5 = 15
3 * 6 = 18
3 * 7 = 21
3 * 8 = 24
3 * 9 = 27

4 * 1 = 4
4 * 2 = 8
4 * 3 = 12
4 * 4 = 16
4 * 5 = 20
4 * 6 = 24
4 * 7 = 28
4 * 8 = 32
4 * 9 = 36

5 * 1 = 5
5 * 2 = 10
5 * 3 = 15
5 * 4 = 20
5 * 5 = 25
5 * 6 = 30
5 * 7 = 35
5 * 8 = 40
5 * 9 = 45

6 * 1 = 6
6 * 2 = 12
6 * 3 = 18
6 * 4 = 24
6 * 5 = 30
6 * 6 = 36
6 * 7 = 42
6 * 8 = 48
6 * 9 = 54

7 * 1 = 7
7 * 2 = 14
7 * 3 = 21
7 * 4 = 28
7 * 5 = 35
7 * 6 = 42
7 * 7 = 49
7 * 8 = 56
7 * 9 = 63
```

> 💡 여기도 RS가 필요 할까?

### 나의 예제(2)
```PLSQL
CREATE OR REPLACE PROCEDURE MY_GUGUDAN2(PNUM1 PLS_INTEGER, PNUM2 PLS_INTEGER) IS -- IS는 생략 불가능 ㅠ
BEGIN
FOR I IN PNUM1..PNUM2 LOOP
FOR J IN 1..9 LOOP
DBMS_OUTPUT.PUT_LINE(I||' * '||J||' = '||I*J);
END LOOP;
DBMS_OUTPUT.PUT_LINE(''); -- 가시성을 위하여
END LOOP;
END;
/
EXEC MY_GUGUDAN2(4,6);
```

```
결과::
4 * 1 = 4
4 * 2 = 8
4 * 3 = 12
4 * 4 = 16
4 * 5 = 20
4 * 6 = 24
4 * 7 = 28
4 * 8 = 32
4 * 9 = 36

5 * 1 = 5
5 * 2 = 10
5 * 3 = 15
5 * 4 = 20
5 * 5 = 25
5 * 6 = 30
5 * 7 = 35
5 * 8 = 40
5 * 9 = 45

6 * 1 = 6
6 * 2 = 12
6 * 3 = 18
6 * 4 = 24
6 * 5 = 30
6 * 6 = 36
6 * 7 = 42
6 * 8 = 48
6 * 9 = 54
```
### 구구단 예제(3)

```PLSQL
CREATE OR REPLACE PROCEDURE GUGUDAN3(VNUM NUMBER, VNUM2 NUMBER, VNUM3 NUMBER) IS
RS NUMBER;
CUT NUMBER := VNUM3;
BEGIN
FOR I IN VNUM..VNUM2 LOOP
FOR J IN 1..9 LOOP
RS := I * J;
CONTINUE WHEN RS >= CUT;
DBMS_OUTPUT.PUT_LINE(I||' * '||J||' = '||RS);
END LOOP;
DBMS_OUTPUT.PUT_LINE('');
END LOOP;
END;
/
EXECUTE GUGUDAN3(3,7, 40);

```

```
결과::
3 * 1 = 3
3 * 2 = 6
3 * 3 = 9
3 * 4 = 12
3 * 5 = 15
3 * 6 = 18
3 * 7 = 21
3 * 8 = 24
3 * 9 = 27

4 * 1 = 4
4 * 2 = 8
4 * 3 = 12
4 * 4 = 16
4 * 5 = 20
4 * 6 = 24
4 * 7 = 28
4 * 8 = 32
4 * 9 = 36

5 * 1 = 5
5 * 2 = 10
5 * 3 = 15
5 * 4 = 20
5 * 5 = 25
5 * 6 = 30
5 * 7 = 35

6 * 1 = 6
6 * 2 = 12
6 * 3 = 18
6 * 4 = 24
6 * 5 = 30
6 * 6 = 36

7 * 1 = 7
7 * 2 = 14
7 * 3 = 21
7 * 4 = 28
7 * 5 = 35
```

### 나의 예제(3)
```PLSQL
CREATE OR REPLACE PROCEDURE 
MY_GUGUDAN3(PNUM1 PLS_INTEGER,
			PNUM2 PLS_INTEGER, 
			PNUM3 PLS_INTEGER) IS
BEGIN
	FOR I IN PNUM1..PNUM2 LOOP
		FOR J IN 1..9 LOOP
		EXIT WHEN I*J > PNUM3;
		DBMS_OUTPUT.PUT_LINE(I||' * '||J||' = '||I*J);
		END LOOP;
	DBMS_OUTPUT.PUT_LINE('');
	END LOOP;
END;
/
EXEC MY_GUGUDAN3(7,9,50);
```

```
결과::
7 * 1 = 7
7 * 2 = 14
7 * 3 = 21
7 * 4 = 28
7 * 5 = 35
7 * 6 = 42
7 * 7 = 49

8 * 1 = 8
8 * 2 = 16
8 * 3 = 24
8 * 4 = 32
8 * 5 = 40
8 * 6 = 48

9 * 1 = 9
9 * 2 = 18
9 * 3 = 27
9 * 4 = 36
9 * 5 = 45
```
## 같은 이름으로 오버로딩

### 해답 1 `PL/SQL` 패키지
```PLSQL

CREATE OR REPLACE PACKAGE MATH  -- 패키지 스펙이라고 함
IS 
PROCEDURE GUGUDAN(VNUM NUMBER);
PROCEDURE GUGUDAN(VNUM NUMBER, VNUM2 NUMBER);
PROCEDURE GUGUDAN(VNUM NUMBER, VNUM2 NUMBER, VNUM3 NUMBER);
END;
/

CREATE OR REPLACE PACKAGE BODY MATH
IS 
PROCEDURE GUGUDAN(VNUM NUMBER) IS
RS NUMBER := VNUM;
BEGIN
FOR J IN 1..9 LOOP
DBMS_OUTPUT.PUT_LINE(RS||' * '||J||' = '||RS*J);
END LOOP;
END;

PROCEDURE GUGUDAN(VNUM NUMBER, VNUM2 NUMBER) IS
RS NUMBER;
BEGIN
FOR I IN VNUM..VNUM2 LOOP
FOR J IN 1..9 LOOP
RS := I * J;
DBMS_OUTPUT.PUT_LINE(I||' * '||J||' = '||RS);
END LOOP;
DBMS_OUTPUT.PUT_LINE('');
END LOOP;
END;

PROCEDURE GUGUDAN(VNUM NUMBER, VNUM2 NUMBER, VNUM3 NUMBER) IS
RS NUMBER;
CUT NUMBER := VNUM3;
BEGIN
FOR I IN VNUM..VNUM2 LOOP
FOR J IN 1..9 LOOP
RS := I * J;
CONTINUE WHEN RS >= CUT;
DBMS_OUTPUT.PUT_LINE(I||' * '||J||' = '||RS);
END LOOP;
DBMS_OUTPUT.PUT_LINE('');
END LOOP;
END;

END;
/

EXEC MATH.GUGUDAN(3);
EXEC MATH.GUGUDAN(3, 7);
EXEC MATH.GUGUDAN(2, 9, 50);
```

# SELECT INTO 
```PLSQL
--전통적인 방법 - 1
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
VSAL EMP.SAL%TYPE;
BEGIN 
SELECT SAL INTO VSAL
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE(VSAL);
END;
/

--레코드를 이용하는 방법 - 2
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
EMP_REC EMP%ROWTYPE;
BEGIN 
SELECT * INTO EMP_REC
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE(EMP_REC.SAL);
END;
/

--커스텀 레코드를 만드는 방법 - 3 
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
TYPE R1 IS RECORD (
RNAME EMP.ENAME%TYPE, 
RSAL EMP.SAL%TYPE,
RJOB EMP.JOB%TYPE
);
REC R1;
BEGIN 
SELECT ENAME, SAL, JOB INTO REC.RNAME, REC.RSAL, REC.RJOB
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE(REC.RNAME);
DBMS_OUTPUT.PUT_LINE(REC.RSAL);
DBMS_OUTPUT.PUT_LINE(REC.RJOB);
END;
/


--패키지를 이용하는 방법 - 4
CREATE OR REPLACE PACKAGE EMP_TYPES_PACK 
IS
TYPE R1 IS RECORD (
RNAME EMP.ENAME%TYPE, 
RSAL EMP.SAL%TYPE,
RJOB EMP.JOB%TYPE
);
END;
-- 패키지에 만들어둔 RECORD 를 사용
CREATE OR REPLACE PROCEDURE PROC1(ENUM EMP.EMPNO%TYPE)
IS 
REC_EMP EMP_TYPES_PACK.R1
BEGIN 
SELECT ENAME, SAL, JOB INTO REC_EMP
FROM EMP 
WHERE EMPNO = ENUM; 
DBMS_OUTPUT.PUT_LINE('이 름 : '||REC_EMP.RNAME||' , 연 봉 : '||REC_EMP.RSAL||', 직 업 : '||REC_EMP.RJOB);
END;
/
```

```
결과::
Procedure PROC1이(가) 컴파일되었습니다.
3000

Procedure PROC2이(가) 컴파일되었습니다.
3000

Procedure PROC3이(가) 컴파일되었습니다.
SCOTT
3000
ANALYST

Procedure PROC4이(가) 컴파일되었습니다.
이 름 : SCOTT , 연 봉 : 3000, 직 업 : ANALYST
```

