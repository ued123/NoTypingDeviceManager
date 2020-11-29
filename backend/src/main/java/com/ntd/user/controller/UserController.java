package com.ntd.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntd.common.constant.Characters;
import com.ntd.config.security.JwtTokenProvider;
import com.ntd.user.params.UserContainer;
import com.ntd.user.service.UserManager;

/**
 * FrontEnd에서 User 관련 요청 작업 처리 클래스
 * 
 * @author HoYa
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserManager userManager;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;


	@PostMapping(path = "/doAuth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> doAuth(HttpServletRequest request, Authentication authentication) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			if (authentication == null) {
				resultMap.put(Characters.RESPONSE, "403. Failrue Authentication. ");
				throw new Exception ("Invalid Token");
			}
			resultMap.put(Characters.RESPONSE, "200. Success Authentication.");
		} catch (BadCredentialsException e) {
			logger.warn("Invalid User Infomation. : {}", e.getMessage(), e);
			resultMap.put(Characters.RESPONSE, "403. Failrue Authentication. ");
		} catch (Exception e) {
			logger.warn("Error Process Login User, UserInfo : {}", e.getMessage(), e);
			resultMap.put(Characters.RESPONSE, "500. Error Server while login.");
		}

		return resultMap;
	}

	
	@PostMapping(path = "/doLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> doLogin(HttpServletRequest request, @RequestBody UserContainer userContainer) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userContainer.getUserName(), userContainer.getPassword()));
			// SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenProvider.generateToken(authentication);
			resultMap.put(Characters.AUTHORIZATION, jwt);
			resultMap.put(Characters.RESPONSE, "200. Success Authentication.");
		} catch (BadCredentialsException e) {
			logger.warn("Invalid User Infomation. : {}", e.getMessage(), e);
			resultMap.put(Characters.RESPONSE, "403. Failrue Authentication. ");
		} catch (Exception e) {
			logger.warn("Error Process Login User, UserInfo : {}", e.getMessage(), e);
			resultMap.put(Characters.RESPONSE, "500. Error Server while login.");
		}

		return resultMap;
	}

	@PostMapping(path = "/doRegister", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> doRegister(@RequestBody UserContainer userContainer) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
		} catch (Exception e) {
			logger.warn("Error Process Register User, UserInfo : {}", e.getMessage(), e);
			resultMap.put(Characters.RESPONSE, "500. Error Server while register.");
		}
		return resultMap;
	}

}
