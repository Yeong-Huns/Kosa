INSERT INTO BOARD_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION)
VALUES (1, '게시판', '답변형 멀티게시판');

INSERT INTO BOARD_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION)
VALUES (2, '자료실', '파일 업로드 자료실');

INSERT INTO BOARD_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION)
VALUES (3, '갤러리', '이미지 갤러리');

COMMIT;

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (1, 1, '홍길동', 'hong@hong.com', '1234', '방가요', '내용없음', '2015-12-20', 1, 0, 0);

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (2, 1, '이순신', 'lee@lee.com', '1234', '나도', '내용없음', '2015-12-21', 2, 0, 0);

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (3, 1, '홍길동', 'hong@hong.com', '1234', '오랜만이야~ 순신', '그렇지', '2015-12-22', 2, 4, 1);

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (4, 1, '무명씨', 'noname@name.com', '1234', '할루', '재미없음', '2015-12-23', 4, 0, 0);

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (5, 1, '홍길서', 'seo@hong.com', '1234', '나도야 순신', '나도나도', '2015-12-24', 2, 1, 1);

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (6, 1, '조심씨', 'josim@josim.com', '1234', '조심해 길서', '안전하게', '2015-12-25', 2, 2, 2);

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (7, 1, '안전씨', 'an@anjeon.com', '1234', '자나깨나', '불조심', '2015-12-26', 4, 1, 1);

INSERT INTO BOARD (BOARD_ID, CATEGORY_ID, WRITER, EMAIL, PASSWORD, TITLE, CONTENT, WRITE_DATE, MASTER_ID, REPLY_NUMBER, REPLY_STEP)
VALUES (8, 1, '소심씨', 'so@so.com', '1234', '조심해는 잘삐져', '조심씨', '2015-12-27', 2, 3, 3);

COMMIT;

select * from board;