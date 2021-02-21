package com.ntd.devicePart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.DevicePartEntityBinder;
import com.ntd.entity.Part;
import com.ntd.entity.QPart;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * Part Table 관련 쿼리 API 호출 구현 클래스
 * @author hoya
 *
 */
@Repository
public class PartRepositoryImpl extends QuerydslRepositorySupport {

	private final static Logger logger = LoggerFactory.getLogger(PartRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	public PartRepositoryImpl() {
		super(Part.class);
	}

	/**
	 * 테이블: part, 컬럼: partModel, partManufactor
	 * 부품모델(partModel), 부품제조사(partManufactor)를 조건으로 하여 부품리스트정보를 얻는 함수
	 * @param devicePartContainer
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Part> findPartsByColumns(DevicePartContainer devicePartContainer) {

		List<Part> parts = null;
		// 기본 검색이 아니거나, 부품부분이 비어있으면 질의하지 않음
		if (!devicePartContainer.isDoSearchDefault() && devicePartContainer.paramsEmptyByPart()) {
			logger.debug("PART > SEARCH > NOT SUPPORTED WHEN PARAMS EMPTY IN PART");
			return parts;
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QPart.part.partModel.contains(devicePartContainer.getPartModel()));
		if (!devicePartContainer.getPartManufactor().isEmpty()) {
			builder.and(QPart.part.partManufactor.contains(devicePartContainer.getPartManufactor()));
		}
		parts = queryFactory.selectFrom(QPart.part).where(builder).fetch();
		return parts;
	}

	/**
	 * 테이블: part, 컬럼: partId
	 * 부품고유식별번호(partId)를 조건으로 하여 부품정보를 얻는 함수
	 * @param devicePartContainer
	 * @param devicePartList
	 */
	@Transactional(readOnly = true)
	public DevicePartEntityBinder findPartByPartId(DevicePartContainer devicePartContainer) {
		
		Part part = null;
		// 부품 고유 번호가 존재하지 않으면 데이터를 찾지 않는다.
		if (devicePartContainer.getPartId() <= 0) {
			return part;
		}
		JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QPart.part.partId.eq(devicePartContainer.getPartId()));
		part = queryFactory.selectFrom(QPart.part).where(builder).fetchOne();
		return part;
	}

	/**
	 * DB TABLE: part에 데이터 추가
	 * @param devicePartContainer
	 * @return
	 */
	@Transactional(readOnly = false)
	public DevicePartEntityBinder addPart(DevicePartContainer devicePartContainer) {
		// frontEnd에서 받은 데이터를 entity로 전달
		Part part = devicePartContainer.convertPart();
		// entity 데이터를 persist 수행시 영속성 컨텍스트에서 관리하며, 
		// commit 후 entity는 DB와 동기화가 된다.
		// 그리고 이 영속성들은 cache에서 관리하게 된다.
		this.entityManager.persist(part);
		this.entityManager.flush();
		return part;
	}

}
