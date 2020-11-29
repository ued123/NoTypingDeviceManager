CREATE DATABASE ntdev;
ALTER  DATABASE  ntdev DEFAULT CHARACTER SET utf8 ;

use ntdev;

CREATE TABLE part (
part_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
part_category VARCHAR(20) default '',
part_model VARCHAR(255) default '',
part_manufactor VARCHAR(20) default '',
PRIMARY KEY (part_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE device (

device_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
device_category VARCHAR(20) default '',
device_model VARCHAR(255) default '',
device_serial_number VARCHAR(255) default '',
cpu_info VARCHAR(100) default '',
ram_info VARCHAR(100) default '',
volume_info VARCHAR(100) default '',
device_info VARCHAR(100) default '',
PRIMARY KEY (device_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_device_part_relationship (
rel_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
device_id INT(3) unsigned NOT NULL,
part_id INT(3) unsigned NOT NULL,
PRIMARY KEY (rel_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

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

) ENGINE = InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE user MODIFY password VARCHAR(255);

-- sample
INSERT INTO user(user_name, user_state, password)
VALUE ('testAdmin',2,'qwer1234');

INSERT INTO device(device_id, device_category, device_model, device_serial_number, cpu_info, ram_info, volume_info, device_info)
VALUE (1,'C','LG-Gram 9401','04Rm401','intel xeon i5-core','16g','1TB','');

INSERT INTO device(device_id, device_category, device_model, device_serial_number, cpu_info, ram_info, volume_info, device_info)
VALUE (2,'M','알파스캔 AOC 24V2Q 프리싱크 75 DP 시력보호 무결점','2zK123','','','','알파스캔');

INSERT INTO device(device_id, device_category, device_model, device_serial_number, cpu_info, ram_info, volume_info, device_info)
VALUE (3,'D','MSI MAG 메타5 A36R70 \(8GB, M2 256GB\)','3ttaaASK123','','','','MSI');

INSERT INTO part(part_id, part_category, part_model, part_manufactor)
VALUE (1,'R','samsung ram 8G','samsung');

INSERT INTO user_device_part_relationship (rel_id, device_id, part_id)
VALUE (1,1,1);

INSERT INTO part(part_id, part_category, part_model, part_manufactor)
VALUE (2,'H','Western Digital WD RED 5400/64M \(WD40EFRX, 4TB\)','Western Digital');

INSERT INTO part(part_id, part_category, part_model, part_manufactor)
VALUE (3,'S','Western Digital WD BLUE SN550 M.2 NVMe \(500GB\)','Western Digital');


-- 2020 11 -22

-- 유저 이력 테이블 추가

CREATE TABLE user_history (
history_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
user_date VARCHAR(255) default '',
user_history_type VARCHAR(20) default '',
user_history VARCHAR(255) default '',
PRIMARY KEY (history_id)
);

-- 유저 이력, 장비 관계 테이블 추가
CREATE TABLE history_device_relationship (
rel_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
device_id INT(3) unsigned NOT NULL,
history_id INT(3) unsigned NOT NULL,
PRIMARY KEY (rel_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE history_device_relationship ADD FOREIGN KEY (device_id) REFERENCES device(device_id);
ALTER TABLE history_device_relationship ADD FOREIGN KEY (history_id) REFERENCES user_history(history_id);


-- 유저 이력, 부품 관계 테이블 추가
CREATE TABLE history_part_relationship (
rel_id INT(3) unsigned NOT NULL AUTO_INCREMENT,
part_id INT(3) unsigned NOT NULL,
history_id INT(3) unsigned NOT NULL,
PRIMARY KEY (rel_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE history_part_relationship ADD FOREIGN KEY (part_id) REFERENCES part(part_id);
ALTER TABLE history_part_relationship ADD FOREIGN KEY (history_id) REFERENCES user_history(history_id);



-- test case

insert into user_history(`history_id`, `user_date`, `user_history_type`, `user_history`) values (null, '1606024323000', 'DEV', '이승헌 개발 노트북 지급');
insert into history_device_relationship(`rel_id`, `device_id`, `history_id`) values (null, '1', '1');
insert into user_device_part_relationship(`rel_id`, `device_id`, `part_id`) values (null, '1', '1');
insert into user_device_part_relationship(`rel_id`, `device_id`, `part_id`) values (null, '1', '2');



