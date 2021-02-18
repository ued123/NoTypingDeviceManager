package com.ntd.devicePart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntd.common.constant.Characters;
import com.ntd.devicePart.exception.DevicePartException;
import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.Device;
import com.ntd.entity.DevicePartEntityBinder;
import com.ntd.entity.Part;

/**
 * DEVICE-PART 의 요청에 대한 실제로 처리로직을 관리하는 클래스
 * @author HoYa
 *
 */
@Service
public class DevicePartManager {

    @Autowired private DeviceRepositoryImpl deviceRepo;

    @Autowired private PartRepositoryImpl partRepo;

    /**
     * 장비, 부품 테이블에서 컬럼 조건에 따라 데이터 가져오는 함수
     * @param devicePartContainer
     * @return
     */
	public List<DevicePartEntityBinder> getDevicesOrPartsByColumns(DevicePartContainer devicePartContainer) throws DevicePartException {
		List<DevicePartEntityBinder> devicePartList = new ArrayList<>();
		List<Part> partList = null;
		List<Device> deviceList = null;
		// 장비 , 부품 리스트 담기
		partList = partRepo.findPartsByColumns(devicePartContainer);
		if (partList != null) {
			devicePartList.addAll(partList);
		}
		deviceList = deviceRepo.findDevicesByColumns(devicePartContainer);
		if (deviceList != null) {
			devicePartList.addAll(deviceList);
		}
		return devicePartList;
	}

	/**
     * 장비, 부품 테이블에서 컬럼의 고유식별자를 통해 얻은 데이터를 DevicePartContainer에 복사 
	 * @param devicePartContainer
	 * @return
	 */
    public void setDeviceOrPartsByIdInDevicePartContainer (DevicePartContainer devicePartContainer) throws DevicePartException {
    	DevicePartEntityBinder devicePartEntityBinder = null;
    	devicePartEntityBinder = deviceRepo.findDevicePartsById(devicePartContainer);
    	if (devicePartEntityBinder != null) {
			devicePartContainer.setDevice((Device) devicePartEntityBinder);
    		return; 
    	}
    	devicePartEntityBinder = partRepo.findPartByPartId(devicePartContainer);
    	if (devicePartEntityBinder != null) {
    		devicePartContainer.addPart((Part) devicePartEntityBinder);
    		devicePartContainer.setDeviceModel(Characters.BLANK);
    		return; 
    	}
    	
    }
}
