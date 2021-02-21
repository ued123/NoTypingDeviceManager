package com.ntd.common.exception;

import org.springframework.security.core.Authentication;

public class AuthenticationExceptionImpl extends Exception{

	static final long serialVersionUID = -2729818886357625597L;

	private static final String AUTHENTICATION_EXCEPTION = "403. Account Authentication Fail";

	/**
	 * Spring-Security에서 인증 절차를 성공하면 인자 Authentication에 객체가 생성된다.
	 * 이 객체가 존재하지 않으면 현재 클래스 기준으로 Exception을 일으키도록 처리
	 * @param authentication
	 * @throws AuthenticationExceptionImpl
	 */
	public static void checkAuthentication (Authentication authentication) throws AuthenticationExceptionImpl {
		
		if (authentication == null) {
			throw new AuthenticationExceptionImpl();
		}
	}

	public AuthenticationExceptionImpl () {
		super();
	}

	public AuthenticationExceptionImpl(String msg) {
		super(msg);
	}

	@Override
	public String getMessage() {
		return AUTHENTICATION_EXCEPTION;
	}
	
}
