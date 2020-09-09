CREATE DATABASE ntdev;

use ntdev;

CREATE TABLE part (
part_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
part_category VARCHAR(20) default '',
part_model VARCHAR(50) default '',
part_manufactor VARCHAR(20) default '',
PRIMARY KEY (part_id)
) ENGINE = InnoDB;

CREATE TABLE device (

device_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
device_category VARCHAR(20) default '',
device_model VARCHAR(20) default '',
device_serial_number VARCHAR(20) default '',
cpu_info VARCHAR(20) default '',
ram_info VARCHAR(20) default '',
volume_info VARCHAR(20) default '',
device_info VARCHAR(20) default '',
PRIMARY KEY (device_id)

) ENGINE = InnoDB;

CREATE TABLE user_device_part_relationship (
rel_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
device_id INT(3) unsigned NOT NULL,
part_id INT(3) unsigned NOT NULL,
PRIMARY KEY (rel_id)
) ENGINE = InnoDB;


ALTER TABLE user_device_part_relationship
ADD FOREIGN KEY (device_id) REFERENCES device(device_id);
ALTER TABLE user_device_part_relationship
ADD FOREIGN KEY (part_id) REFERENCES part(part_id);

CREATE TABLE user (

user_id int(3) NOT NULL AUTO_INCREMENT,
user_name VARCHAR(20) default '',
user_organ VARCHAR(20) default '',
user_state int(2) default 1,
password VARCHAR(20) default '',
password_type VARCHAR(10) default '',
PRIMARY KEY (user_id)

) ENGINE = InnoDB;

/*
    state
    0 - 퇴사 계정, 중지 계정
    1 - 정상 일반 유저
    2 - 장비 관리자

 */

/*
    테스트 데이터
 */
INSERT INTO user(user_name, user_state, password)
VALUE ('testAdmin',2,'qwer1234');

INSERT INTO device(device_id, device_category, device_model, device_serial_number, cpu_info, ram_info, volume_info, device_info)
VALUE (1,'C','LG-Gram 9401','04Rm401','intel xeon i5-core','16g','1TB','');

INSERT INTO part(part_id, part_category, part_model, part_manufactor)
VALUE (1,'R','samsung ram 8G','samsung');

INSERT INTO user_device_part_relationship (rel_id, device_id, part_id)
VALUE (1,1,1);