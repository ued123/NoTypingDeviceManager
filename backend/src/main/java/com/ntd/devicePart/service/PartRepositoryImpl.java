package com.ntd.devicePart.service;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.Part;
import com.ntd.entity.QPart;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PartRepositoryImpl extends QuerydslRepositorySupport {

    public PartRepositoryImpl() {
	super(Part.class);
    }

    public List<Part> findAllLike (DevicePartContainer devicePartContainer) {
	
	QPart qpart = QPart.part;
	JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
	BooleanBuilder builder = new BooleanBuilder();
	builder.and(qpart.partModel.contains(devicePartContainer.getPart_model()));
	if (!devicePartContainer.getPart_manufactor().isEmpty()) {
	    builder.or(qpart.partManufactor.contains(devicePartContainer.getPart_manufactor()));        
	}
	return queryFactory.selectFrom(qpart).where(builder).fetch();
    }

}
