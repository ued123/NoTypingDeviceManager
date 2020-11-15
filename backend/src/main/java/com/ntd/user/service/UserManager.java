package com.ntd.user.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ntd.common.constant.Characters;
import com.ntd.common.constant.Role;
import com.ntd.entity.User;
import com.ntd.user.params.UserAuth;
import com.ntd.user.params.UserContainer;

/**
 * DEVICE-PART 의 요청에 대한 실제로 처리로직을 관리하는 클래스
 * 
 * @author HoYa
 *
 */
@Service
public class UserManager implements UserDetailsService {

	@Autowired
	private UserRepositoryImpl userRepo;

	/**
	 * 유저 체크 관련 로직
	 * 
	 * @param resultMap
	 * @param userContainer
	 */
	public void doLogin(HttpServletRequest request, Map<String, Object> resultMap, UserContainer userContainer) {
		// 유저 정보가 database에 존재하지 않을떄.
		if (userRepo.findUsers(request.getContextPath(), userContainer).isEmpty()) {
			resultMap.put(Characters.RESPONSE, "403. Authentication Fail.");
			return;
		}
		resultMap.put(Characters.RESPONSE, "200. Success Authentication.");

	}

	/**
	 * 유저 생성 관련 로직
	 * 
	 * @param resultMap
	 * @param userContainer
	 */
	public void doRegister(Map<String, Object> resultMap, UserContainer userContainer) {

		// part 리스트 가져오기
		// resultMap.put("devicePart", devicePartContainer);
	}

	/**
	 * 유저 인증 관련 로직
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserContainer userContainer = new UserContainer();
		userContainer.setUserName(username);
		List<User> userList = userRepo.findUsers("auth", userContainer);
		UserAuth  userAuth = new UserAuth();
		if (userList.isEmpty()) {
			return userAuth;
		}
		User user = userList.get(0);
		// 탈퇴한 계정이 아닐떄 활성화 설정
		if (user.getUserState() != "0") {
			userAuth.setEnabled(true);
			userAuth.setAccountNonExpired(true);
			userAuth.setAccountNonLocked(true);
		}
		userAuth.setUserName(username);
		userAuth.setPassword(user.getPassword());
		userAuth.setAuthorities(getUserRole(Integer.parseInt(user.getUserState())));
		return userAuth;
	}

	/**
	 * 유저 권한 구하기
	 * 
	 * @param userState
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getUserRole(int userState) {
		switch (userState) {
		case 0:
			return Role.RetiredAuthorities;
		case 1:
			return Role.UserAuthorities;
		case 2:
			return Role.AdminAuthorities;
		default:
			return Role.RetiredAuthorities;
		}
	}
}
