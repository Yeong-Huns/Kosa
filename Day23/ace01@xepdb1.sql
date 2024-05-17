--2024 04 29

drop table customer_name cascade constraints purge;
drop table customer_tel_number cascade constraints purge;

create table customer_name(
customer_id number GENERATED AS IDENTITY,
first_name varchar2(10),
surname varchar2(10)
);

create index customer_name_customer_id_idx
on customer_name(customer_id);

alter table customer_name
add constraint customer_name_customer_id_pk primary key(customer_id);

alter table customer_name
add constraint customer_name_first_name_nn check(first_name is not null);

create table customer_tel_number(
id number(10) GENERATED as IDENTITY,
customer_id varchar2(10),
telphone_number varchar2(30)
);

create index customer_tel_number_id_idx
on customer_tel_number(id);

create index customer_tel_number_customer_id_idx
on customer_tel_number(customer_id);

alter table customer_tel_number
add constraint customer_tel_number_id primary key(id);

alter table customer_tel_number
add constraint customer_tel_number_fk
foreign key(customer_id) references customer_name(customer_id);

alter table customer_tel_number
add constraint customer_tel_number_fk
foreign key(customer_id) references customer_name(customer_id);

alter table customer_tel_number
add constraint customer_tel_number_nn check(telphone_number is not null);


SELECT * FROM USER_ROLE_PRIVS;


