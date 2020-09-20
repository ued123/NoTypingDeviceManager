package com.ntd.devicePart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntd.dao.DeviceRepository;
import com.ntd.dao.PartRepository;
import com.ntd.devicePart.params.DevicePartContainer;

/**
 * DEVICE-PART 의 요청에 대한 실제로 처리로직을 관리하는 클래스
 * @author HoYa
 *
 */
@Service
public class DevicePartManager {

    @Autowired private DeviceRepository deviceRepo;
    
    @Autowired private PartRepository partRepo;
    
    /**
     * 장비 , 부품 검색 결과를 데이터에 담는 로직
     * @param devicePartContainer 
     * @param resultMap 
     */
    public void getList (Map<String, Object> resultMap, DevicePartContainer devicePartContainer) {
	List<Object> devicePartList = new ArrayList<> ();
	devicePartList.addAll(partRepo.findByPartOnSearch(devicePartContainer));
	devicePartList.addAll(deviceRepo.findByDeviceOnSearch(devicePartContainer));
	//  part 리스트 가져오기
	resultMap.put("devicePartList", devicePartList);
    }
}