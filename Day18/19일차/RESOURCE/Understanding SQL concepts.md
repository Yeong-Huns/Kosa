
 > 📌 SQL(QUERY Language)
 > * `QUERY` : A query is a question, especially one 
 >           that you ask an organization, publication, or expert.
 > * 질의 : 전문가에게 물어봄
 > * 집합적 사고 : SQL, R, NumPy, pandas, ...



> 📌 [sql](https://en.wikipedia.org/wiki/SQL)
> *  History	
> * Standardization history
> * Syntax
> * Procedural extensions

### SQL 분류
| 분류       | 종류                                                                              |
| -------- | ------------------------------------------------------------------------------- |
| `D(S)DL` | CREATE, ALTER, DROP, RENAME, TRUNCATE, COMMENT                                  |
| `DML`    | INSERT, UPDATE, DELETE, MERGE, SELECT                                           |
| `TCL`    | COMMIT, ROLLBACK, SAVEPOINT -> Transaction <br>: DML 문장의 집합 or DDL 하나 or DCL 하나 |
| `DCL`    | GRANT, REVOKE                                                                   |

*  [Graphic Syntax Diagrams](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Graphic-Syntax-Diagrams.html#GUID-D22097D5-1E7A-4A17-862A-F0084732B3CE) 

* parallel_clause
![](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/img/parallel_clause.gif)

* physical_attributes_clause
![](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/img/physical_attributes_clause.gif)

---

  
  - DELETE, TRUNCATE, DROP 비교 (feat. PURGE)

| 종류                  | storage       | rollback |
| ------------------- | ------------- | -------- |
| DELETE FROM EMP;    | 공간 반납 없음      | O        |
| TRUNCATE TABLE EMP; | 최초 공간만 남기고 반납 | X        |
| DROP TABLE EMP;     | 몽땅 반납         | X        |

```plsql
PURGE RECYCLEBIN --
DROP TABLE EMP PURGE;
```

```PLSQL
--휴지통 조회
SHOW RECYCLEBIN;

--휴지통 내부 테이블 조회
SELECT * FROM "BIN$WNQ7EjJPSmuv0l7ix/PTGQ==$0"; 

--잘못 DROP 한걸 복구함 
FLASHBACK TABLE "BIN$WNQ7EjJPSmuv0l7ix/PTGQ==$0" TO BEFORE DROP; 
```

---

## DDL 활용 예제
```PLSQL
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
```


    


# DML, TCL, Read Consistency, Lock, Deadlock 예제

   

## (1) DML, TCL 이해 
 
```PLSQL
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
--여기서부터
insert into books values('6');
insert into books values('7');
insert into books values('8');
-- 이 부분이 롤백됨  
rollback to sp2;

insert into books values('9');
insert into books values('10');

commit;

select * from books;
```

```PLSQL
결과::
1
2
3
4
5
9
10
```

    
## (2) 읽기 일관성(read consistency)
 

> 📌 A 유저 `DELETE` -> A 유저 SELECT -> 삭제된 상태로 SELECT 
> B 유저 SELECT -> 아직 COMMIT 된것도 아니니 삭제된 데이터를 합쳐서 원본처럼 보여줌
> 

### ACE02 
```PLSQL
---
drop table members purge;

--MEMBERS 테이블 생성
create table members
(no number(4),
name varchar2(10 char));

--ACE01 에게 권한부여
grant all
on members
to ACE01;

--
insert into members
values (1001, '석수');

insert into members
values (1002, '장안');
-- 커밋 전 SELECT
select * from members;

commit;
```

### ACE01 
```PLSQL
--
select * from ACE02.members;  -- 권한이 없어서 에러

select * from ACE02.members;  -- DDL 명령과 함께 commit  + 접근 권한 있음 

select * from ACE02.members;  -- 질의 결과 없음 

select * from ACE02.members;  -- 질의 결과 확인됨

COMMIT;
```


## (3) 잠금(LOCK)
### ACE02
```PLSQL
--LOCK
update members
set name = '탄산수'
where no = 1001;

select * from members;

rollback; --롤백하자마자  오른쪽 LOCK 해제
```

### ACE01 
```PLSQL
--LOCK
select * from ACE02.members; 

update ACE02.members
set name = '삼다수'
where no = 1001;
--WAITING......

SELECT * FROM ACE02.MEMBERS;

COMMIT;
```


## (4) 교착 상태(DEADLOCK)
### ACE02 

```PLSQL
-- DEADLOCK
UPDATE MEMBERS
SET NAME = '양배추'
WHERE NO = 1001;

UPDATE MEMBERS
SET NAME = '나이키'
WHERE NO = 1002;
--WAITING!

/*
명령의 45 행에서 시작하는 중 오류 발생 -
UPDATE MEMBERS
SET NAME = '나이키'
WHERE NO = 1002
오류 발생 명령행: 46 열: 5
오류 보고 -
SQL 오류: ORA-00060: 자원 대기중 교착 상태가 검출되었습니다
*/

REVOKE ALL
ON MEMBER
FROM ACE02;
-- REVOKE 로 물려줌
```

### ACE01 
```PLSQL
--DEADLOCK
UPDATE ACE02.MEMBERS
SET NAME = '유연수'
WHERE NO = 1002;

UPDATE ACE02.MEMBERS
SET NAME = '안유진'
WHERE NO = 1001;
--WAITING!

-- REVOKE 후 교착 상태 해제

COMMIT;
```

