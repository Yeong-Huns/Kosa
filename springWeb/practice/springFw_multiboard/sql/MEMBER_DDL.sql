DROP TABLE MEMBER;
CREATE TABLE MEMBER (
	USERID VARCHAR2(50) CONSTRAINT PK_MEMBER PRIMARY KEY,
	NAME VARCHAR2(50) NOT NULL,
	PASSWORD VARCHAR2(50) NOT NULL,
	EMAIL VARCHAR2(100) NOT NULL,
	PHONE VARCHAR2(50)
);

ALTER TABLE MEMBER 
 ADD CONSTRAINT UK_MEMBER_EMAIL UNIQUE (EMAIL) 
 USING INDEX;


select * from member;