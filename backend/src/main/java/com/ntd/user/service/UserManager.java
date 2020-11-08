package com.ntd.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntd.common.constant.Characters;
import com.ntd.user.params.UserContainer;

/**
 * DEVICE-PART 의 요청에 대한 실제로 처리로직을 관리하는 클래스
 * @author HoYa
 *
 */
@Service
public class UserManager {

    @Autowired private UserRepositoryImpl userRepo;

    /**
     * 유저 체크 관련 로직
     * @param resultMap
     * @param userContainer
     */
    public void doLogin (HttpServletRequest request, Map<String, Object> resultMap, UserContainer userContainer) {
    	// 유저 정보가 database에 존재하지 않을떄.
    	if (userRepo.findUsers(request, userContainer).isEmpty()) {
    		resultMap.put(Characters.RESPONSE, "403. Authentication Fail.");
    		return;
    	}
    	resultMap.put(Characters.RESPONSE, "200. Success Authentication.");
		
    }
    
    /**
     * 유저 생성 관련 로직
     * @param resultMap
     * @param userContainer
     */
    public void doRegister (Map<String, Object> resultMap, UserContainer userContainer) {
		

		//  part 리스트 가져오기
		// resultMap.put("devicePart", devicePartContainer);
    }
}
