package com.ntd.devicePart.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.Device;
import com.ntd.entity.QDevice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeviceRepositoryImpl extends QuerydslRepositorySupport {

	private final static Logger logger = LoggerFactory.getLogger(DeviceRepositoryImpl.class);

	public DeviceRepositoryImpl() {
		super(Device.class);
	}

	public void findAllLike(DevicePartContainer devicePartContainer, List<Object> devicePartList) {

		if (!devicePartContainer.isDoSearchDefault() && devicePartContainer.paramsEmptyByDevice()) {
			logger.debug("DEVICE > SEARCH > NOT SUPPORTED WHEN PARAMS EMPTY IN DEVICE");
			return;
		}

		QDevice qdevice = QDevice.device;
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		BooleanBuilder builder = new BooleanBuilder();
		// builder.and(qdevice.deviceModel.contains(devicePartContainer.getDevice_model()));
		builder.and(qdevice.deviceModel.like('%' + devicePartContainer.getDevice_model() + '%'));

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
		devicePartList.addAll(queryFactory.selectFrom(qdevice).where(builder).fetch());

	}


	/**
	 * device_id를 통해 데이터 검색
	 * @param devicePartContainer
	 * @param devicePartList
	 */
	public void findByDeviceId(DevicePartContainer devicePartContainer) {

		QDevice qdevice = QDevice.device;
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qdevice.deviceId.eq(devicePartContainer.getDevice_id()));
		
		// 부품 클릭시 상세정보 호출
		if (devicePartContainer.getDevice_id() > 0) {
			Device device = queryFactory.selectFrom(qdevice).where(builder).fetchOne();
			if (device != null) {
				devicePartContainer.setDevice_id(device.getDeviceId());
				devicePartContainer.setDevice_model(device.getDeviceModel());
				devicePartContainer.setDevice_info(device.getDeviceInfo());
				devicePartContainer.setDevice_serial_number(device.getDeviceSerialNumber());
				devicePartContainer.setDevice_category(device.getDeviceCategory());
				devicePartContainer.setCpu_info(device.getCpuInfo());
				devicePartContainer.setRam_info(device.getRamInfo());
				devicePartContainer.setVolume_info(device.getVolumeInfo());
			}	
		}
		// 장비 클릭시 물려있는 정보 호출
		// TODO devicePart에 있는 key 정보를 바탕으로 호출해야함

	}

}
