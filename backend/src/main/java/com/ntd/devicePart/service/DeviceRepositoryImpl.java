package com.ntd.devicePart.service;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.Device;
import com.ntd.entity.QDevice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeviceRepositoryImpl extends QuerydslRepositorySupport {

    public DeviceRepositoryImpl() {
	super(Device.class);
    }
    
    public List<Device> findAllLike(DevicePartContainer devicePartContainer) {
	QDevice qdevice = QDevice.device;
	JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
	BooleanBuilder builder = new BooleanBuilder();
	// builder.and(qdevice.deviceModel.contains(devicePartContainer.getDevice_model()));
	builder.and(qdevice.deviceModel.like('%'+devicePartContainer.getDevice_model()+'%'));
	
	// 시리얼 넘버가 존재시 like 질의
	if (!devicePartContainer.getDevice_serial_number().isEmpty()) {
	    builder.and(qdevice.deviceSerialNumber.contains(devicePartContainer.getDevice_serial_number()));
	}
	// cpu정보 존재시like 질의
	if (!devicePartContainer.getCpu_info().isEmpty()) {
	    builder.and(qdevice.cpuInfo.contains(devicePartContainer.getCpu_info()));
	}
	// RAM정보 존재시like 질의
	if (!devicePartContainer.getRam_info().isEmpty()) {
	    builder.and(qdevice.ramInfo.contains(devicePartContainer.getRam_info()));
	}
	// 볼륨정보 존재시like 질의
	if (!devicePartContainer.getVolume_info().isEmpty()) {
	    builder.and(qdevice.volumeInfo.contains(devicePartContainer.getVolume_info()));
	}
	// 장비정보 존재시like 질의
	if (!devicePartContainer.getDevice_info().isEmpty()) {
	    builder.and(qdevice.deviceInfo.contains(devicePartContainer.getDevice_info()));
	}
	
	return queryFactory.selectFrom(qdevice).where(builder).fetch();
	
    }
}
