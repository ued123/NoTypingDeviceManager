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
		builder.and(qpart.partModel.contains(devicePartContainer.getPart_model()));
		if (!devicePartContainer.getPart_manufactor().isEmpty()) {
			builder.and(qpart.partManufactor.contains(devicePartContainer.getPart_manufactor()));
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
		builder.and(qpart.partId.eq(devicePartContainer.getPart_id()));
		
		// 부품 클릭시 상세정보 호출
		if (devicePartContainer.getPart_id() > 0) {
			Part part = queryFactory.selectFrom(qpart).where(builder).fetchOne();
			if (part != null) {
				devicePartContainer.setPart_id(part.getPartId());
				devicePartContainer.setPart_model(part.getPartModel());
				devicePartContainer.setPart_category(part.getPartCategory());
				devicePartContainer.setPart_manufactor(part.getPartManufactor());
			}	
		}
		// 장비 클릭시 물려있는 정보 호출
		// TODO devicePart에 있는 key 정보를 바탕으로 호출해야함

	}

}
