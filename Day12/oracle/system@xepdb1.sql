SHOW user;


select * from dba_users; -- 관리자 계정만 가능 (만든계정확인)

drop user ace02 cascade;

create user ace02
IDENTIFIED by me
QUOTA 100m ON users; 

GRANT CREATE SESSION, CREATE TABLE, CREATE PROCEDURE, CREATE VIEW TO ace02;
