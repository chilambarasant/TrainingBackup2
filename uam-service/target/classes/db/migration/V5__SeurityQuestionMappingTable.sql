
 create table user_security_question(
 id int not null AUTO_INCREMENT,
 user_id int not null,
 security_questions_id int not null,
 answer varchar(60),
 primary key(id),
 FOREIGN KEY(user_id) REFERENCES user(id),
 FOREIGN KEY(security_questions_id) REFERENCES security_questions(id)
 )