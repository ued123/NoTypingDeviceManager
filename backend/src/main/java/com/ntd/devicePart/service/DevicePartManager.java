package com.ntd.devicePart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntd.common.constant.Characters;
import com.ntd.devicePart.params.DevicePartContainer;

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
     * 장비 , 부품 검색 결과를 데이터에 담는 로직
     * @param devicePartContainer 
     * @param resultMap 
     */
    public void getList (Map<String, Object> resultMap, DevicePartContainer devicePartContainer) {
		List<Object> devicePartList = new ArrayList<> ();
		
		partRepo.findAllLike(devicePartContainer, devicePartList);
		deviceRepo.findAllLike(devicePartContainer,devicePartList);
		// devicePartList.addAll(partRepo.findAllLike(devicePartContainer));
		//devicePartList.addAll(deviceRepo.findAllLike(devicePartContainer));
		//  part 리스트 가져오기
		resultMap.put("devicePartList", devicePartList);
    }
    
    /**
     * device Id를 통해서 부품정보 호출
     * @param resultMap
     * @param devicePartContainer
     */
    public void findDevicePartsById (Map<String, Object> resultMap, DevicePartContainer devicePartContainer) {
    	//  part 리스트 가져오기
    	if (devicePartContainer.getDeviceId() > 0) {
    		devicePartContainer.setPartModel(Characters.BLANK);
    		deviceRepo.findDevicePartsById(devicePartContainer);
    	} else if (devicePartContainer.getPartId() > 0) {
    		devicePartContainer.setDeviceModel(Characters.BLANK);
    		partRepo.findByPartId(devicePartContainer);
    	}
    	resultMap.put("devicePartContainer", devicePartContainer);
    }
}
