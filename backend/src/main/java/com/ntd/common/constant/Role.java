package com.ntd.common.constant;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
public class Role {

	public static final String ADMIN = "admin";

	public static final String USER = "user";

	public static final String RETIRE = "retire";

	public static final String UNKNOWN = "unknown";

	public static final List<SimpleGrantedAuthority> AdminAuthorities = new ArrayList<>();

	public static final List<SimpleGrantedAuthority> UserAuthorities = new ArrayList<>();

	public static final List<SimpleGrantedAuthority> RetiredAuthorities = new ArrayList<>();

	static {
		// 슈퍼 관리자 권한
		AdminAuthorities.add(new SimpleGrantedAuthority(ADMIN));
		AdminAuthorities.add(new SimpleGrantedAuthority(USER));
		AdminAuthorities.add(new SimpleGrantedAuthority(RETIRE));
		AdminAuthorities.add(new SimpleGrantedAuthority(UNKNOWN));
		// 일반 유저 권한
		UserAuthorities.add(new SimpleGrantedAuthority(USER));
		// 퇴사자 권한
		RetiredAuthorities.add(new SimpleGrantedAuthority(RETIRE));


	}
}
