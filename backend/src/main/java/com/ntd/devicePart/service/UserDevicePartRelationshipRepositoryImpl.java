package com.ntd.devicePart.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ntd.devicePart.exception.DevicePartException;
import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.DevicePartEntityBinder;
import com.ntd.entity.Part;
import com.ntd.entity.QUserDevicePartRelationship;
import com.ntd.entity.UserDevicePartRelationship;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * Part Table 관련 쿼리 API 호출 구현 클래스
 * 
 * @author hoya
 *
 */
@Repository
public class UserDevicePartRelationshipRepositoryImpl extends QuerydslRepositorySupport {

	@PersistenceContext
	private EntityManager entityManager;

	public UserDevicePartRelationshipRepositoryImpl() {
		super(UserDevicePartRelationship.class);
	}

	/**
	 * 테이블: user_device_part_relationship, 컬럼: rel_id, device_id, part_id 테이블 컬럼을
	 * 이용해서 존재하는지 질의
	 * 
	 * @param devicePartContainer
	 * @return
	 */
	@Transactional(readOnly = true)
	public UserDevicePartRelationship findUserDevicePartRelationshipByColumns(DevicePartContainer devicePartContainer)  throws Exception {

		JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);
		BooleanBuilder builder = new BooleanBuilder();
		// deviceId 가 존재시 질의 조건 추가
		if (devicePartContainer.getDeviceId() > 0) {
			builder.and(QUserDevicePartRelationship.userDevicePartRelationship.deviceId
					.eq(devicePartContainer.getDeviceId()));
		}
		// partId 가 존재시 질의 조건 추가
		if (devicePartContainer.getPartId() > 0) {
			builder.and(
					QUserDevicePartRelationship.userDevicePartRelationship.partId.eq(devicePartContainer.getPartId()));
		}
		return queryFactory.selectFrom(QUserDevicePartRelationship.userDevicePartRelationship).where(builder).fetchFirst();
	}
	/**
	 * Device 정보와 매핑되는 Prat 정보를 추가시
	 * Device , Part 관계도 테이블에 데이터 추가후
	 * Part테이블에 데이터를 추가하고 싶음
	 * 고려 사항
	 * 1. user_device_part_relationship 테이블에 장비 정보 존재 체크
	 * 2. 부품 테이블에 정보 추가
	 * 3. 장비 정보 존재시, 장비에 매핑되는 부품정보를 새로운 데이터로 추가
	 * 4. 1,2,3 과정중 하나라도 실패시 롤백
	 * DB TABLE: user_device_part_relationship에 데이터 추가
	 * @param devicePartContainer
	 * @return
	 */
	@Transactional(readOnly = false)
	public DevicePartEntityBinder addUserDevicePartRelationshipAndPart(DevicePartContainer devicePartContainer) throws Exception{
		// 1. user_device_part_relationship 테이블에 장비 정보 존재 체크
		if (findUserDevicePartRelationshipByColumns(devicePartContainer) == null) {
			throw new DevicePartException("NOT FOUND deviceId IN TABLE user_device_part_relationship");
		}
		// 2. 부품 테이블에 정보 추가
		 Part part = devicePartContainer.convertPart();
		this.entityManager.persist(part);
		// 3. 장비 정보 존재시, 장비에 매핑되는 부품정보를 새로운 데이터로 추가 (TABLE: user_device_part_relationship)
		UserDevicePartRelationship userDevicePartRelationship = new UserDevicePartRelationship();
		userDevicePartRelationship.setDeviceId(devicePartContainer.getDeviceId());
		userDevicePartRelationship.setPartId(part.getPartId());
		this.entityManager.persist(userDevicePartRelationship);
		this.entityManager.flush();
		return userDevicePartRelationship;
	}

}
