package com.ntd.common.exception;

import org.springframework.security.core.Authentication;

public class AuthenticationExceptionImpl extends Exception{

	static final long serialVersionUID = -2729818886357625597L;

	private static final String AUTHENTICATION_EXCEPTION = "403. Account Authentication Fail";

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
