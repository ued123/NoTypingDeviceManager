table part 정의서
 
part_id: 부품 key
part_category: 부품 분류 (R:RAM, H:HDD, S:SSD, O:ODD)
part_model: 부품 모델명
part_manufactor: 부품 제조사

table device 정의서

device_id: 장비 key
device_category: 장비 분류 (M:Monitor, L:Labtop, D:DeskTop, S:Server)
device_model: 장비 모델명
device_serial_number: 장비 시리얼 넘버
cpu_info: cpu 정보
ram_info: ram 정보
volume_info: 하드 정보
device_info: 장비 정보 

table user_device_part_relationship 정의서
## device, part 매핑 테이블


table user 정의서

user_state : 유저 상태 [0: 퇴사,중비, 1:정상 일반우저, 2:장비관리자]

testAdmin@test.com 비밀번호: qwer1234

