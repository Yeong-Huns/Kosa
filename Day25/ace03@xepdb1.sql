drop table commute;
drop table annualleaves;

create table commute (
	id		number generated as IDENTITY,
    memberid number,
    dayDate date,
    workStart date,
    worKEnd date,
    primary key (id)
);

create table AnnualLeaves (
	id number generated as IDENTITY,
    dayDate date,
    memberid number,
    primary key (id)
);


select * from commute;
select * from AnnualLeaves;
truncate table commute;
desc commute;
alter table commute ADD workDate date;
insert into commute (memberid,dayDate, workStart, workEnd) values (1, sysDate+4,sysDate+4, sysDate+4);
insert into commute (memberid,dayDate, workStart, workEnd) values (1, sysDate+5,sysDate+5, sysDate+5);
insert into commute (memberid,dayDate, workStart, workEnd) values (1, sysDate+6,sysDate+6, sysDate+6);
insert into commute (memberid,dayDate, workStart, workEnd) values (1, sysDate+7,sysDate+7, sysDate+7);

insert into AnnualLeaves (dayDate ,memberid) values (sysdate,1);
insert into AnnualLeaves (dayDate ,memberid) values (sysdate+1,1);
insert into AnnualLeaves (dayDate ,memberid) values (sysdate+2,1);

select * from commute;
select * from AnnualLeaves;

SELECT COALESCE(c.memberid, al.memberid) AS memberId, 
       COALESCE(al.dayDate, c.dayDate) AS dayDate
FROM commute c
FULL OUTER JOIN AnnualLeaves al ON c.dayDate = al.dayDate AND c.memberid = al.memberid
WHERE (c.memberid = 1 OR al.memberid = 1) and c.dayDate = ""
ORDER BY dayDate ASC;

-- '년-월' 형식으로 받아온다고 가정

-- 해당 년도와 월의 첫 날과 마지막 날을 구한다

-- commute 테이블과 AnnualLeaves 테이블을 조인하여 해당 범위의 기록을 조회한다
SELECT COALESCE(c.memberid, al.memberid) AS memberId, 
       COALESCE(al.dayDate, c.dayDate) AS dayDate
FROM commute c
FULL OUTER JOIN AnnualLeaves al ON c.dayDate = al.dayDate AND c.memberid = al.memberid
WHERE (c.memberid = 1 OR al.memberid = 1) 
AND (
    (c.dayDate >= @start_date AND c.dayDate < @end_date)
    OR (al.dayDate >= @start_date AND al.dayDate < @end_date)
)
ORDER BY dayDate ASC;

set serveroutput on;

DECLARE 
    year_month VARCHAR(7) := '2024-05'; 
    start_date DATE := TO_DATE(year_month || '-01', 'YYYY-MM-DD');
    end_date DATE := ADD_MONTHS(start_date, 1);
BEGIN 
    FOR record IN (
        SELECT COALESCE(c.memberid, al.memberid) AS memberId, 
               COALESCE(al.dayDate, c.dayDate) AS dayDate
        FROM commute c
        FULL OUTER JOIN AnnualLeaves al ON c.dayDate = al.dayDate AND c.memberid = al.memberid
        WHERE (c.memberid = 1 OR al.memberid = 1) 
        AND (
            (c.dayDate >= start_date AND c.dayDate < end_date)
            OR (al.dayDate >= start_date AND al.dayDate < end_date)
        )
        ORDER BY dayDate ASC
    ) LOOP
        -- 결과 출력 혹은 다른 작업 수행
        DBMS_OUTPUT.PUT_LINE('Member ID: ' || record.memberId || ', Day Date: ' || TO_CHAR(record.dayDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/

