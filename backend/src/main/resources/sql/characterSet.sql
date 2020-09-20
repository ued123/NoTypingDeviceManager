-- 데이터베이스들 인코딩 타입 확인
SELECT schema_name , default_character_set_name
FROM information_schema.schemata ;

-- DB 인코딩설정
ALTER  DATABASE  ntdev DEFAULT CHARACTER SET utf8 ;

-- 테이블 인코딩 [컬럼 포함] 변경

ALTER TABLE device CONVERT TO CHARACTER SET UTF8;
ALTER TABLE part CONVERT TO CHARACTER SET UTF8;
ALTER TABLE user CONVERT TO CHARACTER SET UTF8;
ALTER TABLE user_device_part_relationship CONVERT TO CHARACTER SET UTF8;
