-- 생성자 Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   위치:        2024-04-29 16:28:39 KST
--   사이트:      Oracle Database 21c
--   유형:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE books (
    book_id           VARCHAR2(20) NOT NULL,
    title             VARCHAR2(50) NOT NULL,
    author_last_name  VARCHAR2(25) NOT NULL,
    author_first_name VARCHAR2(25),
    rating            NUMBER(2)
)
LOGGING;

ALTER TABLE books ADD CONSTRAINT books_pk PRIMARY KEY ( book_id );

CREATE TABLE patrons (
    patron_id      NUMBER NOT NULL,
    last_name      VARCHAR2(25) NOT NULL,
    first_name     VARCHAR2(25),
    street_address VARCHAR2(40),
    city           VARCHAR2(25),
    state          VARCHAR2(2),
    zip            VARCHAR2(10),
    location       mdsys.sdo_geometry
)
LOGGING;

ALTER TABLE patrons ADD CONSTRAINT patrons_pk PRIMARY KEY ( patron_id );

CREATE TABLE transactions (
    transaction_id    NUMBER NOT NULL,
    transaction_date  DATE NOT NULL,
    transaction_type  NUMBER NOT NULL,
    books_book_id     VARCHAR2(20),
    patrons_patron_id NUMBER
)
LOGGING;

ALTER TABLE transactions ADD CONSTRAINT transactions_pk PRIMARY KEY ( transaction_id );

ALTER TABLE transactions
    ADD CONSTRAINT transactions_books_fk FOREIGN KEY ( books_book_id )
        REFERENCES books ( book_id )
    NOT DEFERRABLE;

ALTER TABLE transactions
    ADD CONSTRAINT transactions_patrons_fk FOREIGN KEY ( patrons_patron_id )
        REFERENCES patrons ( patron_id )
    NOT DEFERRABLE;



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             3
-- CREATE INDEX                             0
-- ALTER TABLE                              5
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
