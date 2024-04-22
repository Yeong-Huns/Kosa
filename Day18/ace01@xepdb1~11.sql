SHOW USER;

SELECT * FROM ACE01.MEMBERS;

--
select * from ACE02.members;  -- 권한이 없어서 에러

select * from ACE02.members;  -- DDL 명령과 함께 commit  + 접근 권한 있음 

select * from ACE02.members;  -- 질의 결과 없음 

select * from ACE02.members;  -- 질의 결과 확인됨

COMMIT;

--LOCK

select * from ACE02.members; 

update ACE02.members
set name = '삼다수'
where no = 1001;
--WAITING......

SELECT * FROM ACE02.MEMBERS;

COMMIT;

--DEADLOCK
UPDATE ACE02.MEMBERS
SET NAME = '유연수'
WHERE NO = 1002;

UPDATE ACE02.MEMBERS
SET NAME = '안유진'
WHERE NO = 1001;
--WAITING!


COMMIT;
