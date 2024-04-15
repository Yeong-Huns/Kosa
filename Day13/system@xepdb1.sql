DROP USER ACE03 CASCADE;

CREATE USER ACE03
IDENTIFIED BY me 
QUOTA  100m on users;   

grant create session, create table, create procedure, create view, dba
to ace03;