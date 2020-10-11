package com.ntd.devicePart.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntd.common.constant.Characters;
import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.devicePart.service.DevicePartManager;

/**
 * Device-Part FrontEnd-Backend 통신시 비지니스 로직
 * @author HoYa
 *
 */
@RestController
@RequestMapping("/devicePart")
public class DevicePartController {

    private final static Logger logger = LoggerFactory.getLogger(DevicePartController.class);
    
    @Autowired private DevicePartManager devicePartManager;
    
    @PostMapping (path = "/getList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getList (@RequestBody DevicePartContainer devicePartContainer) {
	Map<String, Object> resultMap = new HashMap<>();
	resultMap.put(Characters.RESULT, Characters.SUCCESS);
	try {
	    devicePartManager.getList(resultMap, devicePartContainer);
	    logger.info("Get DevicePartList");
	} catch (Exception e) {
	    logger.warn("Error Process Getting Device, Part DataInfo : {}",e.getMessage(),e);
	    resultMap.put(Characters.RESULT, Characters.FAIL);
	}
	return resultMap;
    }
    
}
