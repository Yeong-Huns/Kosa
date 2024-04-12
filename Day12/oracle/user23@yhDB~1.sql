purge RECYCLEBIN;

SELECT * FROM user30.emp; --user30 의 emp 테이블에 접근 가능 

SELECT empno, sal, job FROM user30.emp; -- 순서와 상관없음

SELECT sal, sal, sal, sal, sal from user30.emp; -- 실제로 저장된 데이터는 하나지만 컬럼 복제

SELECT SUM(sal), AVG(sal), MAX(sal), MIN(sal) from user30.emp; --실질적인 작동원리는 내가 결정하는게 아님

select * from tab;

DROP TABLE JOB_GRADES;
DROP TABLE EMP;
DROP TABLE DEPT;
DROP TABLE BONUS;
DROP TABLE SALGRADE;
DROP TABLE DUMMY;

drop table employees cascade constraints;
drop table departments cascade constraints;
drop table locations cascade constraints;
drop table regions cascade constraints;
drop table countries cascade constraints;
drop table jobs cascade constraints;
drop table job_history cascade constraints;