
show user ;

CREATE OR REPLACE PROCEDURE PROC1
IS 
vsal number := 44;
BEGIN 
DBMS_OUTPUT.PUT_LINE(VSAL);
END;
/

set SERVEROUTPUT on;


create table commute (
	id		number generated as IDENTITY,
    memberid number,
    workStart date,
    worKEnd date,
    primary key (id)
);

drop table n1 purge;
create table n1(
    id Number(10),
    name VARCHAR2(20)
);



SELECT * FROM USER_ROLE_PRIVS;



exec proc1;