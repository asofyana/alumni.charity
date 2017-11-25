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
name varchar(50),
payment_type varchar(20),
file_name varchar(20),
status varchar(20),
created_date datetime,
created_by varchar(50),
updated_date datetime,
updated_by varchar(50)
);

create table t_member_contribution(
id INT NOT NULL AUTO_INCREMENT key,
payment_id int,
user_id int,
month datetime
);

----------
insert into t_role(id, role_code, role_desc) values(1,'ADMIN','Administrator');
insert into t_role(id, role_code, role_desc) values(2,'MEMBER','Member');
insert into t_role(id, role_code, role_desc) values(3,'TREASURY','Treasury');

insert into t_user(email,password,full_name) values('asofyana@yahoo.com','password*1','Andri Sofyana')