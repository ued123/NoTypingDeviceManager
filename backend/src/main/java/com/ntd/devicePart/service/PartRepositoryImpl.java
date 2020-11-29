package com.ntd.devicePart.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.Part;
import com.ntd.entity.QPart;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PartRepositoryImpl extends QuerydslRepositorySupport {

	private final static Logger logger = LoggerFactory.getLogger(PartRepositoryImpl.class);

	public PartRepositoryImpl() {
		super(Part.class);
	}

	public void findAllLike(DevicePartContainer devicePartContainer, List<Object> devicePartList) {

		// 기본검색 수행이 아니고
		if (!devicePartContainer.isDoSearchDefault() && devicePartContainer.paramsEmptyByPart()) {
			logger.debug("PART > SEARCH > NOT SUPPORTED WHEN PARAMS EMPTY IN PART");
			return;
		}

		QPart qpart = QPart.part;
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qpart.partModel.contains(devicePartContainer.getPartModel()));
		if (!devicePartContainer.getPartManufactor().isEmpty()) {
			builder.and(qpart.partManufactor.contains(devicePartContainer.getPartManufactor()));
		}
		devicePartList.addAll(queryFactory.selectFrom(qpart).where(builder).fetch());

	}

	/**
	 * part_id를 통해 데이터 검색
	 * @param devicePartContainer
	 * @param devicePartList
	 */
	public void findByPartId(DevicePartContainer devicePartContainer) {

		QPart qpart = QPart.part;
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qpart.partId.eq(devicePartContainer.getPartId()));
		// 부품 클릭시 상세정보 호출
		if (devicePartContainer.getPartId() > 0) {
			devicePartContainer.addPart(queryFactory.selectFrom(qpart).where(builder).fetchOne());
		}
	}

}
