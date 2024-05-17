--EXCEPTION 처리하지 않을 경우
drop table t1 purge;
    create table t1 (no number);

    begin
      insert into t1 values (1000);
      dbms_output.put_line(100/0);
      insert into t1 values (2000);
    end;
    /
    select * from t1;

--EXCEPTION 처리할 경우
begin
insert into t1 values (1000);
dbms_output.put_line(100/0);
insert into t1 values (2000);
exception
when zero_divide then
null;
end;
/

select * from t1;

rollback;
--EXCEPTION 발생과 상관없이 두번째 INSERT 문장 수행 
begin
begin
insert into t1 values (1000);
dbms_output.put_line(100/0);
exception
when zero_divide then
null;
end;

insert into t1 values (2000);
end;
/

select * from t1;

rollback;




SELECT * 
FROM DBA_DATA_FILES;


---


--T1테이블 생성
DROP TABLE T1 PURGE;
CREATE TABLE T1(NO NUMBER NOT NULL);

--
CREATE OR REPLACE PROCEDURE INSERT_T1(A NUMBER)
IS 
BEGIN
INSERT INTO T1 VALUES(A);
END;
/
EXEC INSERT_T1(1000);
EXEC INSERT_T1(NULL);
-- EXCEPTION : CANNOT ISERT NULL INTO

SELECT * FROM T1;
SELECT * FROM "BIN$WNQ7EjJPSmuv0l7ix/PTGQ==$0"; 
--휴지통 내부 테이블 조회
SHOW RECYCLEBIN;

FLASHBACK TABLE "BIN$WNQ7EjJPSmuv0l7ix/PTGQ==$0" TO BEFORE DROP; 
--잘못 DROP 한걸 복구함 


--- DDL 활용 예제

DROP TABLE BREADS PURGE;

CREATE TABLE BREADS       /* 제과제빵 목록 테이블 */
    (NO   NUMBER(2),          /* 번호 */
     NAME VARCHAR2(30 CHAR));  /* 이름 */

COMMENT ON TABLE  BREADS       IS '제과제빵 목록 테이블';
    COMMENT ON COLUMN BREADS.NO    IS '번호';
    COMMENT ON COLUMN BREADS.NAME  IS '이름';
    
SELECT * FROM USER_TAB_COMMENTS;
--테이블 코멘트
SELECT * FROM USER_COL_COMMENTS; 
--컬럼 코멘트


INSERT INTO BREADS VALUES (1, '가토쇼콜라');
INSERT INTO BREADS VALUES (2, '맘모스빵');
INSERT INTO BREADS VALUES (3, '모카번');

COMMIT;

ALTER TABLE BREADS ADD (PRICE NUMBER(10, 2));
--정수 8자리 소수점 2자리 -> 전체 10자리
ALTER TABLE BREADS ADD (BAKER VARCHAR2(30 CHAR));
--30글자 지정

SELECT * FROM BREADS;

UPDATE BREADS
SET  PRICE = 10000, BAKER = 'Dorothy'
WHERE NO = 1;

UPDATE BREADS
SET  PRICE = 15000, BAKER = 'Edward'
WHERE NO = 2;
--UPDATE

SELECT * FROM BREADS;

RENAME BREADS TO T_BREADS;
--테이블 이름 변경

SELECT * FROM T_BREADS;

TRUNCATE TABLE T_BREADS;

SELECT * FROM T_BREADS;



---
drop table books purge;

SELECT * FROM BOOKS;

create table books
(isbn varchar2(10));

insert into books values('1');
insert into books values('2');
insert into books values('3');

savepoint sp1;

insert into books values('4');
insert into books values('5');

savepoint sp2;
--
insert into books values('6');
insert into books values('7');
insert into books values('8');
-- 이 부분이 롤백됨  
rollback to sp2;

insert into books values('9');
insert into books values('10');

commit;

select * from books;


CREATE TABLE MEMBERS
(NO NUMBER(4), NAME VARCHAR2(10CHAR));

select * 
from emp;

select empno, ename, sal, job 
from emp;

select sal, sal, sal, sal /* 컬럼 복제 */
from emp;

select sum(sal), avg(sal), max(sal), min(sal) 
from emp;

select empno, sal, sal*1.1 as whatif 
from emp;

-- 원하는 집합을 묘사하는 방법들
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

---
-- SELECT 문 작성 OR 해석
select deptno, count(*), sum(sal)
from emp
where sal > 1000 
group by deptno 
having sum(sal) <= 30000 
order by sum(sal) desc;

---
--산술연산
select empno, ename, sal
  from emp;

  select empno, ename ||'('|| job||')' as SAWON, sal, sal*1.25 as upsal
  from emp;

  select empno, ename, initcap(ename)
  from emp;

  create or replace function tax(a number) return number
  is
  begin
return a * 0.031;
end;
/

select empno, sal, tax(sal) 
from emp;

---
--LITERAL
select empno, sal, '원'
from emp
where sal >= 1500;


select empno "사번", sal || '원' "급여(\)"
from emp
where sal >= 1500;

SELECT * FROM EMP WHERE EMPNO = EMPNO;
SELECT * FROM EMP WHERE COMM = COMM;


SELECT EMPNO, ENAME, ENAME 
FROM EMP;
---
SELECT EMPNO, ENAME, SUBSTR(ENAME, -1, 1)
FROM EMP;
---



SELECT * 
FROM EMP 
WHERE NOT(SAL >= 500 AND DEPTNO = 10);

SELECT * 
FROM EMP 
WHERE NOT SAL >= 500;

SELECT UNIQUE DEPTNO FROM EMP;
SELECT DISTINCT DEPTNO, JOB FROM EMP;
SET AUTOTRACE ON;
SET AUTOTRACE OFF;


-- PUT NAME TO THE EXCEPTION 
DROP TABLE T1 PURGE;
CREATE TABLE T1(NO NUMBER NOT NULL);

CREATE OR REPLACE PROCEDURE INSERT_T1(A NUMBER)


