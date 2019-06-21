create table user(
 id int NOT NULL AUTO_INCREMENT,
 login_id varchar(15),
 first_name varchar(15),
 last_name varchar(15),
 mobile_no varchar(15),
 password varchar(15),
 created_on Date,
user_type varchar(10) NOT NULL,
 primary key(id),
FOREIGN KEY(user_type) REFERENCES user_type(user_type));