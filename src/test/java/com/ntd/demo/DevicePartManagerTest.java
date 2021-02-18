package com.ntd.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.devicePart.service.DevicePartManager;
import com.ntd.entity.DevicePartEntityBinder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class DevicePartManagerTest {

	private final static Logger logger = LoggerFactory.getLogger(DevicePartManagerTest.class);
	/**
	 * 테스트전 진행절차
	 * 테스트 전 device, part, 테이블에 데이터가 없는경우 아패의 쿼리를 통해 데이터를 만든다.
		INSERT INTO user(user_name, user_state, password) VALUE ('testAdmin',2,'qwer1234');
		INSERT INTO device(device_id, device_category, device_model, device_serial_number, cpu_info, ram_info, volume_info, device_info) VALUE (1,'C','LG-Gram 9401','04Rm401','intel xeon i5-core','16g','1TB','');
		INSERT INTO device(device_id, device_category, device_model, device_serial_number, cpu_info, ram_info, volume_info, device_info) VALUE (2,'M','알파스캔 AOC 24V2Q 프리싱크 75 DP 시력보호 무결점','2zK123','','','','알파스캔');		
		INSERT INTO device(device_id, device_category, device_model, device_serial_number, cpu_info, ram_info, volume_info, device_info) VALUE (3,'D','MSI MAG 메타5 A36R70 \(8GB, M2 256GB\)','3ttaaASK123','','','','MSI');
		INSERT INTO part(part_id, part_category, part_model, part_manufactor) VALUE (1,'R','samsung ram 8G','samsung');
		INSERT INTO user_device_part_relationship (rel_id, device_id, part_id) VALUE (1,1,1);
		INSERT INTO part(part_id, part_category, part_model, part_manufactor) VALUE (2,'H','Western Digital WD RED 5400/64M \(WD40EFRX, 4TB\)','Western Digital');
		INSERT INTO part(part_id, part_category, part_model, part_manufactor) VALUE (3,'S','Western Digital WD BLUE SN550 M.2 NVMe \(500GB\)','Western Digital');
	 */
	@Autowired
	private DevicePartManager devicePartManager;


	/**
	 * 테이블: device, part에서 데이터를 가져온다. 
	 */
	@Test
	public void getDevicesOrPartsByColumnsTest() {
		try {
			DevicePartContainer devicePartContainer = new DevicePartContainer();
			devicePartContainer.setPartModel("s");
			devicePartContainer.setDeviceModel("s");
			devicePartContainer.setDoSearchDefault(true);
			List<DevicePartEntityBinder> deviceParts = devicePartManager
					.getDevicesOrPartsByColumns(devicePartContainer);
			StringBuilder builder = new StringBuilder();
			for (DevicePartEntityBinder devicePart : deviceParts) {
				builder.append(devicePart.obtainDeviceOrPartModel()).append(", ");
			}
			logger.info("TEST > getDevicesOrPartsByColumns > deviceParts's size 4 ? > RESULT: {}, SIZE: {}",
					(deviceParts.size() == 4) ? "YES" : "NO", deviceParts.size());
		} catch (Exception e) {
			logger.info("TEST > getDevicesOrPartsByColumns > ERROR > {}", e.getMessage(), e);
		}

	}

}
