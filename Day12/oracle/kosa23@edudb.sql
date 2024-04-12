purge RECYCLEBIN;

select * from tab;

drop table DEPT purge;

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