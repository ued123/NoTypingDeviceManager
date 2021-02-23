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

import com.ntd.devicePart.exception.DevicePartException;
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
	 * 컬럼 조건을 통해 장비, 부품 테이블에 질의후 얻은 데이터드을 반환 
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
			logger.info("TEST > getDevicesOrPartsByColumns > deviceParts's size: {}", deviceParts.size());
		} catch (Exception e) {
			logger.info("TEST > getDevicesOrPartsByColumns > ERROR > {}", e.getMessage(), e);
		}

	}


	/**
     * 컬럼의 고유식별자를 통해 장비, 부품 테이블에 질의후 얻은 데이터를, DevicePartContainer에 복사 
	 * @param devicePartContainer
	 * @return
	 */
	@Test
    public void setDeviceOrPartsByIdInDevicePartContainerTest () {
    	try {
    		DevicePartContainer devicePartContainer = new DevicePartContainer();
    		// 장비만 조회
    		devicePartContainer.setDeviceId(3);
    		devicePartManager.setDeviceOrPartsByIdInDevicePartContainer(devicePartContainer);
			logger.info("TEST > setDeviceOrPartsByIdInDevicePartContainer > only device > deviceInfo: {}, parts'size: {}", devicePartContainer.getDeviceModel(), devicePartContainer.getParts().size());
			// 부품만 조회
			devicePartContainer.setDeviceId(0);
			devicePartContainer.setPartId(3);
			devicePartManager.setDeviceOrPartsByIdInDevicePartContainer(devicePartContainer);
			logger.info("TEST > setDeviceOrPartsByIdInDevicePartContainer > only parts > deviceInfo: {}, parts'size: {}", devicePartContainer.getDeviceModel(), devicePartContainer.getParts().size());
			// 장비와 장착된 부품 조회
			devicePartContainer.setDeviceId(1);
			devicePartContainer.setPartId(0);
			devicePartManager.setDeviceOrPartsByIdInDevicePartContainer(devicePartContainer);
			logger.info("TEST > setDeviceOrPartsByIdInDevicePartContainer > device and added parts > deviceInfo: {}, parts'size: {}", devicePartContainer.getDeviceModel(), devicePartContainer.getParts().size());
			
		} catch (Exception e) {
			logger.info("TEST > setDeviceOrPartsByIdInDevicePartContainer > ERROR > {}", e.getMessage(), e);
		}

    }

	/**
     * 부품 테이블에 추가후 devicePartContainer에 저장 테스트
	 * @param devicePartContainer
	 * @return
	 */
	@Test
	public void addPartAfterSetDevicePartContainerTest() {

		try {
			DevicePartContainer devicePartContainer = new DevicePartContainer();
			devicePartContainer.setPartModel("TEST DDR4 222GB");
			devicePartContainer.setPartCategory("R");
			devicePartContainer.setPartManufactor("TEST");
			devicePartManager.addPartAfterSetDevicePartContainer(devicePartContainer);
			// 성공시 table에 추가된 part_id값이 객체에 할당됨
			logger.info("TEST > addPartAfterSetDevicePartContainerTest > partId: {}", devicePartContainer.getPartId());
			// device에 part 부품을 장착시
			devicePartContainer.setDeviceId(1);
			devicePartContainer.setPartId(0);
			devicePartContainer.setPartModel("LGT ODD 52x");
			devicePartContainer.setPartCategory("O");
			devicePartContainer.setPartManufactor("LGT");
			// 성공시 table에 추가된 part_id값이 객체에 할당됨, 장비_부품 관계 테이블에도 데이터 추가
			devicePartManager.addPartAfterSetDevicePartContainer(devicePartContainer);
			logger.info("TEST > addPartAfterSetDevicePartContainerTest > partId: {}", devicePartContainer.getPartId());
		} catch (Exception e) {
			logger.info("TEST > addPartAfterSetDevicePartContainerTest > ERROR > {}", e.getMessage(), e);
		}
	}

	/**
     * 장비 테이블에 추가후 devicePartContainer에 저장 테스트 
	 * @param devicePartContainer
	 * @return
	 */
	@Test
	public void addDeviceAfterSetDevicePartContainerTest() throws DevicePartException {

		try {
			DevicePartContainer devicePartContainer = new DevicePartContainer();
			devicePartContainer.setDeviceCategory("D");
			devicePartContainer.setDeviceInfo("TEST DEVICE INFO");
			devicePartContainer.setDeviceModel("TEST DESKTOP");
			devicePartContainer.setDeviceSerialNumber("TTTT");
			devicePartContainer.setCpuInfo("TEST AMD 123");
			devicePartContainer.setRamInfo("TEST RAM 16GB");
			devicePartContainer.setVolumeInfo("TEST HDD 1TB");
			devicePartManager.addDeviceAfterSetDevicePartContainer(devicePartContainer);
			// 성공시 table에 추가된 device_id값이 객체에 할당됨
			logger.info("TEST > addDeviceAfterSetDevicePartContainerTest > deviceId: {}", devicePartContainer.getDeviceId());
			
		} catch (Exception e) {
			logger.info("TEST > addDeviceAfterSetDevicePartContainerTest > ERROR > {}", e.getMessage(), e);
		}
	}

}
