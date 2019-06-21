CREATE TABLE IF NOT EXISTS user_master (
    um_id INT AUTO_INCREMENT,
    um_name VARCHAR(255) NOT NULL,
	um_password VARCHAR(255) NOT NULL,
    PRIMARY KEY (um_id)
)  ENGINE=INNODB;

insert into user_master values(1,'admin','admin');
insert into user_master values(2,'sumilon','sumilon');
insert into user_master values(3,'mani','mani');
insert into user_master values(4,'unimoni','unimoni');
insert into user_master values(5,'test','test');