purge RECYCLEBIN;

SELECT * FROM user30.emp; --user30 �� emp ���̺� ���� ���� 

SELECT empno, sal, job FROM user30.emp; -- ������ �������

SELECT sal, sal, sal, sal, sal from user30.emp; -- ������ ����� �����ʹ� �ϳ����� �÷� ����

SELECT SUM(sal), AVG(sal), MAX(sal), MIN(sal) from user30.emp; --�������� �۵������� ���� �����ϴ°� �ƴ�

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