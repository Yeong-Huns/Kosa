SHOW user;


select * from dba_users; -- ������ ������ ���� (�������Ȯ��)

drop user ace02 cascade;

create user ace02
IDENTIFIED by me
QUOTA 100m ON users; 

GRANT CREATE SESSION, CREATE TABLE, CREATE PROCEDURE, CREATE VIEW TO ace02;
