CREATE TABLE IF NOT EXISTS commodity_master (
    cm_id INT AUTO_INCREMENT,
    cm_name VARCHAR(255) NOT NULL,
	cm_type VARCHAR(255) NOT NULL,
	cm_image VARCHAR(1000) NOT NULL,
    PRIMARY KEY (cm_id)
)  ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS location_master (
    lm_id INT AUTO_INCREMENT,
    lm_code VARCHAR(255) NOT NULL,
	lm_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (lm_id)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS commodity_pricing (
    cp_id INT AUTO_INCREMENT,
    cp_cm_id INT,
	cp_userid INT,
	cp_lm_id INT,
	cp_price FLOAT,
	cp_units VARCHAR(25) NOT NULL,
	cp_status INT,
	cp_createdby INT,
	cp_createdon DATETIME,
    PRIMARY KEY (cp_id),
	FOREIGN KEY(cp_cm_id) REFERENCES commodity_master(cm_id),
	FOREIGN KEY(cp_lm_id) REFERENCES location_master(lm_id)
)  ENGINE=INNODB;
