package com.ntd.devicePart.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntd.common.constant.Characters;
import com.ntd.common.exception.AuthenticationExceptionImpl;
import com.ntd.devicePart.exception.DevicePartException;
import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.devicePart.service.DevicePartManager;

/**
 * Front에서 장비 - 부품 UI 관련 URL 요청시
 * 처리하는 클래스
 * @author hoya
 *
 */
@RestController
@RequestMapping("/devicePart")
public class DevicePartController {

	private final static Logger logger = LoggerFactory.getLogger(DevicePartController.class);

	@Autowired
	private DevicePartManager devicePartManager;

	@PostMapping(path = "/getDevicPartList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> processURLOfDevicePartList(@RequestBody DevicePartContainer devicePartContainer, Authentication authentication) {
		Map<String, Object> requestResultContainer = new HashMap<>();
		String responseMsg = "200. Success OK.";
		try {
			AuthenticationExceptionImpl.checkAuthentication(authentication);
			requestResultContainer.put("devicePartList", devicePartManager.getDevicesOrPartsByColumns(devicePartContainer));
			
		} catch (AuthenticationExceptionImpl | DevicePartException e) {
			logger.warn("ERROR > processURLOfDevicePartList > Exception > {}", e.getMessage(), e);
			responseMsg = e.getMessage();
		} finally {
			requestResultContainer.put(Characters.RESPONSE, responseMsg);
		}
		
		return requestResultContainer;
	}

	@PostMapping(path = "/getDevicePart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> processURLOfDevicePart(@RequestBody DevicePartContainer devicePartContainer, Authentication authentication) {
		Map<String, Object> requestResultContainer = new HashMap<>();
		String responseMsg = "200. Success OK.";
		try {
			AuthenticationExceptionImpl.checkAuthentication(authentication);
			devicePartManager.setDeviceOrPartsByIdInDevicePartContainer(devicePartContainer);
			requestResultContainer.put("devicePartContainer", devicePartContainer);
		} catch (AuthenticationExceptionImpl | DevicePartException e) {
			logger.warn("ERROR > requestResultContainer > Exception > {}", e.getMessage(), e);
			responseMsg = e.getMessage();
		} finally {
			requestResultContainer.put(Characters.RESPONSE, responseMsg);
		}
		
		return requestResultContainer;
	}

	@PostMapping(path = "/addPart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> processURLOfAddPart(@RequestBody DevicePartContainer devicePartContainer, Authentication authentication) {
		Map<String, Object> requestResultContainer = new HashMap<>();
		// 정상 추가 되엇는지 시그널만 확인
		String responseMsg = "200. Success OK.";
		try {
			// 고려사항
			// addPart Url 에서는 부품만 추가 / 장비에 붙은 부품추가를 고려해야한다.
			AuthenticationExceptionImpl.checkAuthentication(authentication);
			devicePartManager.addPartAfterSetDevicePartContainer(devicePartContainer);
			requestResultContainer.put("part", devicePartContainer);
		} catch (AuthenticationExceptionImpl | DevicePartException e) {
			logger.warn("ERROR > requestResultContainer > Exception > {}", e.getMessage(), e);
			responseMsg = e.getMessage();
		} finally {
			requestResultContainer.put(Characters.RESPONSE, responseMsg);
		}

		return requestResultContainer;
	}

	@PostMapping(path = "/addDevice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> processURLOfAddDevice(@RequestBody DevicePartContainer devicePartContainer, Authentication authentication) {
		Map<String, Object> requestResultContainer = new HashMap<>();
		// 정상 추가 되엇는지 시그널만 확인
		String responseMsg = "200. Success OK.";
		try {
			// 고려사항
			// addPart Url 에서는 부품만 추가 / 장비에 붙은 부품추가를 고려해야한다.
			AuthenticationExceptionImpl.checkAuthentication(authentication);
			devicePartManager.addDeviceAfterSetDevicePartContainer(devicePartContainer);
			requestResultContainer.put("device", devicePartContainer);
		} catch (AuthenticationExceptionImpl | DevicePartException e) {
			logger.warn("ERROR > requestResultContainer > Exception > {}", e.getMessage(), e);
			responseMsg = e.getMessage();
		} finally {
			requestResultContainer.put(Characters.RESPONSE, responseMsg);
		}

		return requestResultContainer;
	}

}
