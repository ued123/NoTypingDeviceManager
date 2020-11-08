package com.ntd.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.ntd.entity.QUser;
import com.ntd.entity.User;
import com.ntd.user.params.UserContainer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport {

	private final static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	public UserRepositoryImpl() {
		super(User.class);
	}

	/**
	 * device_id를 통해 데이터 검색
	 * 
	 * @param devicePartContainer
	 * @param devicePartList
	 */
	public List<User> findUsers(HttpServletRequest request, UserContainer userContainer) {
		String urlPath = request.getServletPath();
		QUser quser = QUser.user;
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		BooleanBuilder builder = new BooleanBuilder();
		// 로그인일때 처리
		if (urlPath.contains("doLogin")) {
			builder.and(quser.userName.eq(userContainer.getUserName()))
					.and(quser.password.eq(userContainer.getPassword()));
		}
		// TODO 유저 검색 UI 검색시 질의 만들기
		return queryFactory.selectFrom(quser).where(builder).fetch();
	}

}
