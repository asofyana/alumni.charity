create table t_user(
id INT NOT NULL AUTO_INCREMENT, 
email varchar(50),
password varchar(50),
salt varchar(64),
full_name varchar(100),
address varchar(250),
city varchar(50),
postal_code varchar(10),
home_phone_number varchar(15),
mobile_number varchar(15),
grade1 varchar(5),
grade2 varchar(5),
grade3 varchar(5),
job_title varchar(100),
office_name varchar(100),
office_address varchar(255),
status varchar(20),
last_access_date datetime,
created_date datetime,
created_by varchar(50),
updated_date datetime,
updated_by varchar(50),
PRIMARY KEY (id)
);

create table t_role(
id INT, 
role_code varchar(20),
role_desc varchar(50)
);

create table t_user_role(
id INT NOT NULL AUTO_INCREMENT key, 
user_id int,
role_id int
);

create table t_payment(
id INT NOT NULL AUTO_INCREMENT key, 
user_id int,
request_id int,
name varchar(50),
payment_type varchar(20),
amount double,
file_name varchar(20),
status varchar(20),
cash_flow varchar(5),
created_date datetime,
created_by varchar(50),
updated_date datetime,
updated_by varchar(50)
);

create table t_member_contribution(
id INT NOT NULL AUTO_INCREMENT key,
payment_id int,
user_id int,
month datetime,
amount double,
created_date datetime,
created_by varchar(50),
updated_date datetime,
updated_by varchar(50)
);

create table t_payment_request(
id INT NOT NULL AUTO_INCREMENT key,
title varchar(50),
description varchar(255),
type varchar(20),
amount double,
status varchar(20),
approval_count int,
approver_list varchar(50),
created_date datetime,
created_by varchar(50),
updated_date datetime,
updated_by varchar(50)
);

--to be dropped
create table t_payment_type(
payment_type varchar(20),
flow_type  varchar(10)
)

create table t_payment_allocation(
id INT NOT NULL AUTO_INCREMENT key,
payment_id int,
allocation_type  varchar(30),
amount double
);

alter table t_payment add cash_flow varchar(5);



----------
insert into t_role(id, role_code, role_desc) values(1,'ADMIN','Administrator');
insert into t_role(id, role_code, role_desc) values(2,'MEMBER','Member');
insert into t_role(id, role_code, role_desc) values(3,'TREASURY','Treasury');
insert into t_role(id, role_code, role_desc) values(4,'APPROVER','Approver');

insert into t_payment_type(payment_type, flow_type) values ('MEMBER_MONTHLY', 'CREDIT');
insert into t_payment_type(payment_type, flow_type) values ('CHARITY_PAYMENT', 'DEBET');


insert into t_user(email,password,full_name) values('asofyana@yahoo.com','password*1','Andri Sofyana')