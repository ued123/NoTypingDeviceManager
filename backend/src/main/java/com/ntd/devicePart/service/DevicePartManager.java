package com.ntd.devicePart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 장비/부품 리스트중 선택하여 상세 정보 제공
     * @param resultMap
     * @param devicePartContainer
     */
    public void getDevicePart (Map<String, Object> resultMap, DevicePartContainer devicePartContainer) {
		
		partRepo.findByPartId(devicePartContainer);
		deviceRepo.findByDeviceId(devicePartContainer);
		//  part 리스트 가져오기
		resultMap.put("devicePart", devicePartContainer);
    }
}
