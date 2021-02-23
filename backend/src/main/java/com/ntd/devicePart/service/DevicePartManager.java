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

	@Autowired private UserDevicePartRelationshipRepositoryImpl userDevicePartRelationshipRepo;

    @Autowired private DeviceRepositoryImpl deviceRepo;

    @Autowired private PartRepositoryImpl partRepo;

    /**
     * 장비, 부품 테이블에서 컬럼 조건에 따라 데이터 가져오는 함수
     * @param devicePartContainer
     * @return
     */
	public List<DevicePartEntityBinder> getDevicesOrPartsByColumns(DevicePartContainer devicePartContainer)
			throws DevicePartException {
		List<DevicePartEntityBinder> devicePartList = new ArrayList<>();
		try {
			devicePartList = new ArrayList<>();
			// 장비 , 부품 리스트 담기
			List<Part> partList = partRepo.findPartsByColumns(devicePartContainer);
			if (partList != null) {
				devicePartList.addAll(partList);
			}
			List<Device> deviceList = deviceRepo.findDevicesByColumns(devicePartContainer);
			if (deviceList != null) {
				devicePartList.addAll(deviceList);
			}
		} catch (Exception e) {
			throw new DevicePartException(e);
		}

		return devicePartList;
	}

	/**
     * 장비, 부품 테이블에서 컬럼의 고유식별자를 통해 얻은 데이터를 DevicePartContainer에 복사 
	 * @param devicePartContainer
	 * @return
	 */
    public void setDeviceOrPartsByIdInDevicePartContainer (DevicePartContainer devicePartContainer) throws DevicePartException {
    	try {
        	DevicePartEntityBinder devicePartEntityBinder = deviceRepo.findDevicePartsById(devicePartContainer);
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
    	} catch (Exception e) {
    		throw new DevicePartException(e);
    	}
    	
    }

	/**
     * 부품 테이블에 추가 
	 * @param devicePartContainer
	 * @return
	 */
	public void addPartAfterSetDevicePartContainer(DevicePartContainer devicePartContainer) throws DevicePartException {
		DevicePartEntityBinder addedPart = null;
		try {
			// 장비에 매핑되는 부품 추가
			if (devicePartContainer.getDeviceId() > 0) {
				addedPart = userDevicePartRelationshipRepo.addUserDevicePartRelationshipAndPart(devicePartContainer);
			}
			// 일반 부품 정보 추가
			if (addedPart == null) {
				addedPart = partRepo.addPart(devicePartContainer);
			}
		} catch (Exception e) {
			throw new DevicePartException(e);
		}
		devicePartContainer.setPart((Part) addedPart);
	}

	/**
     * 장비 테이블에 추가 
	 * @param devicePartContainer
	 * @return
	 */
	public void addDeviceAfterSetDevicePartContainer(DevicePartContainer devicePartContainer) throws DevicePartException {
		DevicePartEntityBinder addedDevice = null;
		try {
			// 장비 정보 추가
			addedDevice = deviceRepo.addDevice(devicePartContainer);
		} catch (Exception e) {
			throw new DevicePartException(e);
		}
		devicePartContainer.setDevice((Device) addedDevice);
		
	}

	/**
     * 장비 테이블에 추가 
	 * @param devicePartContainer
	 * @return
	 */
	public void modifyDeviceAfterSetDevicePartContainer(DevicePartContainer devicePartContainer) throws DevicePartException {
		DevicePartEntityBinder modifiedDevice = null;
		try {
			// 장비 정보 추가
			modifiedDevice = deviceRepo.modfiyDevice(devicePartContainer);
		} catch (Exception e) {
			throw new DevicePartException(e);
		}
		devicePartContainer.setDevice((Device) modifiedDevice);
		
	}

}
