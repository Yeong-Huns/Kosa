select * from PRODUCT;


insert into product (prod_code,prod_name, prod_color, prod_qty) values ('NAME1','one', 'r', 1);
insert into product (prod_code,prod_name, prod_color, prod_qty) values ('NAME2','del', 'r', 2);
insert into product (prod_code,prod_name, prod_color, prod_qty) values ('NAME3','three', 'r', 3);
insert into product (prod_code,prod_name, prod_color, prod_qty) values ('NAME4','four', 'r', 4);
insert into product (prod_code,prod_name, prod_color, prod_qty) values ('NAME5','five', 'r', 5);
insert into product (prod_code,prod_name, prod_color, prod_qty) values ('NAME6','siz', 'r', 6);
update product set prod_color = 'ww' where prod_code = 'ddd';
commit;

drop table t1 cascade constraints purge;

  create table t1 
  (empno number generated as identity,
   ename varchar2(10), 
   sal   number(7, 2),
   hiredate date);

  insert into t1(ename, sal, hiredate)
  select ename, sal, hiredate from emp;
  
  select * from t1;
  
  
  
  update PRODUCT set prod_code=NAME8 where prod_name='MARSHA';
  
  create or replace procedure sp_insert_t1
  (p_ename    in t1.ename%type,
   p_sal      in t1.sal%type,
   p_hiredate in t1.hiredate%type)
  is
  begin
    insert into t1(ename, sal, hiredate)
    values (p_ename, p_sal, p_hiredate);

    commit;
  end;
  /
  
  exec sp_insert_t1('DIANA', 2700, sysdate);
  select * from t1 order by empno;
  
  
  
  
  create or replace procedure fetch_members
  (p_name    in varchar2,
   p_age     in number,
   p_cursor out sys_refcursor
  ) 
  as
    sql_query varchar2(500);
  begin
    sql_query := 'select * from member';

    if p_name is not null and p_age != 0 then
      sql_query := sql_query || ' where name = :name and age = :age';
    elsif p_name is not null then
      sql_query := sql_query || ' where name = :name';
    elsif p_age != 0 then
      sql_query := sql_query || ' where age = :age';
    end if;

    open p_cursor for sql_query using p_name, p_age;
  end;
  /

   

  create or replace procedure fetch_members
  (p_name    in varchar2,
   p_age     in number,
   p_cursor out sys_refcursor
  ) 
  as
    sql_query varchar2(500);
  begin
    sql_query := 'select * from member where 1=1';

    if p_name is not null then
      sql_query := sql_query || ' and name = :name';
    end if;

    if p_age != 0 then
      sql_query := sql_query || ' and age = :age';
    end if;

    open p_cursor for sql_query using p_name, p_age;
  end;
  /

  declare
    v_cursor sys_refcursor;
    -- 결과를 받을 변수 선언
    v_id member.id%type;
    v_name member.name%type;
    v_age member.age%type;
    v_height member.height%type;
    v_weight member.weight%type;

  begin
    fetch_members(p_name => 'Peter', p_age => 24, p_cursor => v_cursor);

    loop
      fetch v_cursor into v_id, v_name, v_height, v_weight, v_age;
      exit when v_cursor%notfound;
      dbms_output.put_line('ID: ' || v_id || ' Name: ' || v_name || ' Height: ' || v_height || ' Weight: ' || v_weight || ' Age: ' || v_age);
    end loop;

    close v_cursor;
  end;
  /
  
SELECT * FROM ACCOUNTS;

SET SERVEROUTPUT ON;
  
DROP TABLE ACCOUNTS PURGE;
CREATE TABLE ACCOUNTS(
ANO VARCHAR2(10),
OWNER VARCHAR2(10),
BALANCE NUMBER
);

ALTER TABLE ACCOUNTS -- 테이블 수정하기 : ALTER TABLE (컬럼을 추가 OR 삭제 OR 속성변경) 
ADD CONSTRAINT ACCOUNTS_ANO_PK PRIMARY KEY(ANO);
  
  
CREATE OR REPLACE PACKAGE ACCOUNTS_PACK IS  -- 패키지 생성

PROCEDURE ACCOUNTS_INSERT(
P_ANO ACCOUNTS.ANO%TYPE,
P_OWNER ACCOUNTS.OWNER%TYPE,
P_BALANCE ACCOUNTS.BALANCE%TYPE
);

PROCEDURE ACCOUNTS_PLUS_UPDATE(
P_ANO ACCOUNTS.ANO%TYPE,
P_BALANCE ACCOUNTS.BALANCE%TYPE
);

PROCEDURE ACCOUNTS_MINUS_UPDATE(
P_ANO ACCOUNTS.ANO%TYPE,
P_BALANCE ACCOUNTS.BALANCE%TYPE
);
  
PROCEDURE ACCOUNTS_DELETE(
P_ANO ACCOUNTS.ANO%TYPE
);

PROCEDURE ACCOUNTS_SELECT_ALL(
P_CURSOR OUT SYS_REFCURSOR
);

FUNCTION IS_ACCOUNT_EXISTS(
P_ANO ACCOUNTS.ANO%TYPE
) RETURN NUMBER;
END;
/

CREATE OR REPLACE  PACKAGE BODY ACCOUNTS_PACK IS 

--BALANCE_CHECK 함수
FUNCTION BALANCE_CHECK(
A ACCOUNTS.BALANCE%TYPE
)
RETURN BOOLEAN
IS 
BEGIN 
IF A < 1000 THEN 
RETURN FALSE; 
ELSE 
RETURN TRUE; 
END IF;
END;
--ACOUNTS_INSERT
PROCEDURE ACCOUNTS_INSERT(
P_ANO ACCOUNTS.ANO%TYPE,
P_OWNER ACCOUNTS.OWNER%TYPE,
P_BALANCE ACCOUNTS.BALANCE%TYPE
)
IS
BEGIN 
IF BALANCE_CHECK(P_BALANCE) THEN 
INSERT INTO ACCOUNTS(ANO, OWNER, BALANCE)
VALUES(P_ANO, P_OWNER, p_BALANCE);
END IF;
DBMS_OUTPUT.PUT_LINE('1000원 이상 설정하셔야합니다!');
COMMIT;
END;
--입금
PROCEDURE ACCOUNTS_PLUS_UPDATE (
P_ANO ACCOUNTS.ANO%TYPE,
P_BALANCE ACCOUNTS.BALANCE%TYPE
)
IS
BEGIN 
UPDATE ACCOUNTS
SET BALANCE = P_BALANCE
WHERE ANO = P_ANO;

COMMIT;
END;

--출금
PROCEDURE ACCOUNTS_MINUS_UPDATE(
P_ANO ACCOUNTS.ANO%TYPE,
P_BALANCE ACCOUNTS.BALANCE%TYPE
)
IS
BEGIN 
UPDATE ACCOUNTS
SET BALANCE = P_BALANCE
WHERE ANO = P_ANO;
COMMIT; 
END;

PROCEDURE ACCOUNTS_DELETE(
P_ANO ACCOUNTS.ANO%TYPE
) IS
BEGIN 
DELETE FROM ACCOUNTS 
WHERE ANO = P_ANO;
COMMIT; 
END;

PROCEDURE ACCOUNTS_SELECT_ALL(
P_CURSOR OUT SYS_REFCURSOR
) IS
BEGIN 
OPEN P_CURSOR FOR 
SELECT  * FROM ACCOUNTS;
END;

FUNCTION IS_ACCOUNT_EXISTS(
P_ANO ACCOUNTS.ANO%TYPE
) RETURN NUMBER IS
V_RS NUMBER(1); -- 오라클엔 BOOLEAN 타입  X  TINYINT X ||NUMBER(1) -> 0또는 1 || VARCHAR(1) ->  Y 또는 N
BEGIN 
SELECT -1 INTO V_RS
FROM ACCOUNTS
WHERE ANO=P_ANO;

IF V_RS IS NOT NULL THEN 
RETURN 1;
END IF; 
EXCEPTION
WHEN NO_DATA_FOUND THEN 
RETURN 0;
END;
END;
/


SET VERIFY OFF;
SET SERVEROUTPUT ON;

TRUNCATE TABLE ACCOUNTS; --테이블 날리기
SELECT * FROM ACCOUNTS; --

--데이터 삽입 
EXEC ACCOUNTS_PACK.ACCOUNTS_INSERT('111-111', '나지보', 100); -- 1000원 미만이라 안됨
EXEC ACCOUNTS_PACK.ACCOUNTS_INSERT('111-111', '나자보', 20000);
EXEC ACCOUNTS_PACK.ACCOUNTS_INSERT('222-222', '토레타', 20000);
EXEC ACCOUNTS_PACK.ACCOUNTS_INSERT('333-333', '제임스', 10000);
SELECT * FROM ACCOUNTS; 

--데이터 수정
-- 입급
EXEC ACCOUNTS_PACK.ACCOUNTS_PLUS_UPDATE('222-222', 10000);


-- 출금
EXEC ACCOUNTS_PACK.ACCOUNTS_MINUS_UPDATE('222-222', 10000); 

EXEC ACCOUNTS_PACK.ACCOUNTS_DELETE('111-111');

SELECT * FROM ACCOUNTS;


DECLARE 
V_CURSOR SYS_REFCURSOR;
ACCOUNTS_REC ACCOUNTS%ROWTYPE;
BEGIN 
ACCOUNTS_PACK.ACCOUNTS_SELECT_ALL(V_CURSOR);

LOOP 
FETCH V_CURSOR INTO ACCOUNTS_REC;
EXIT WHEN V_CURSOR%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('-----------');
DBMS_OUTPUT.PUT_LINE(ACCOUNTS_REC.ANO);
DBMS_OUTPUT.PUT_LINE(ACCOUNTS_REC.OWNER);
DBMS_OUTPUT.PUT_LINE(ACCOUNTS_REC.BALANCE);
END LOOP;
CLOSE V_CURSOR;
END;
/

EXEC DBMS_OUTPUT.PUT_LINE(ACCOUNTS_PACK.IS_ACCOUNT_EXISTS('222-222'));

EXEC DBMS_OUTPUT.PUT_LINE(ACCOUNTS_PACK.IS_ACCOUNT_EXISTS('555-555'));

DECLARE
V_ANO ACCOUNTS.ANO%TYPE := null;
V_OWNER ACCOUNTS.OWNER%TYPE := '넘';
V_BALANCE ACCOUNTS.BALANCE%TYPE := 9999;
BEGIN 
ACCOUNTS_PACK.ACCOUNTS_INSERT(V_ANO, V_OWNER, V_BALANCE);
--EXCEPTION 
--WHEN DUP_VAL_ON_INDEX THEN 
ACCOUNTS_PACK.ACCOUNTS_PLUS_UPDATE(V_ANO, V_BALANCE);
END;
/

SELECT * FROM ACCOUNTS;

