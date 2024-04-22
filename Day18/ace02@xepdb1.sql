-- ACE02 유저로 접속
show user ;
--select tname from tab; 
--select * from time.t1;

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

--LOCK
update members
set name = '탄산수'
where no = 1001;

select * from members;

rollback; --롤백하자마자  오른쪽 LOCK 해제


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