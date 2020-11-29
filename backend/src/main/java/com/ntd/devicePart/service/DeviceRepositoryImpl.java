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
		builder.and(qdevice.deviceModel.like('%' + devicePartContainer.getDeviceModel() + '%'));

		// 시리얼 넘버가 존재시 like 질의
		if (!devicePartContainer.getDeviceSerialNumber().isEmpty()) {
			builder.and(qdevice.deviceSerialNumber.contains(devicePartContainer.getDeviceSerialNumber()));
		}
		// cpu정보 존재시like 질의
		if (!devicePartContainer.getCpuInfo().isEmpty()) {
			builder.and(qdevice.cpuInfo.contains(devicePartContainer.getCpuInfo()));
		}
		// RAM정보 존재시like 질의
		if (!devicePartContainer.getRamInfo().isEmpty()) {
			builder.and(qdevice.ramInfo.contains(devicePartContainer.getRamInfo()));
		}
		// 볼륨정보 존재시like 질의
		if (!devicePartContainer.getVolumeInfo().isEmpty()) {
			builder.and(qdevice.volumeInfo.contains(devicePartContainer.getVolumeInfo()));
		}
		// 장비정보 존재시like 질의
		if (!devicePartContainer.getDeviceInfo().isEmpty()) {
			builder.and(qdevice.deviceInfo.contains(devicePartContainer.getDeviceInfo()));
		}
		devicePartList.addAll(queryFactory.selectFrom(qdevice).where(builder).fetch());

	}


	/**
	 * device_id를 통해 데이터 검색
	 * @param devicePartContainer
	 * @param devicePartList
	 */
	public void findByDeviceId(DevicePartContainer devicePartContainer, DevicePartContainer devicePartResult) {

		QDevice qdevice = QDevice.device;
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qdevice.deviceId.eq(devicePartContainer.getDeviceId()));
		
		// 부품 클릭시 상세정보 호출
		if (devicePartContainer.getDeviceId() > 0) {
			Device device = queryFactory.selectFrom(qdevice).where(builder).fetchOne();
			if (device != null) {
				devicePartResult.setDevice(device);
			}	
		}
	}
	
	public void findDevicePartsById (DevicePartContainer devicePartContainer) {
		QDevice qdevice = QDevice.device;
		// device , part 같이 join하여질의
		Device device = from(qdevice).innerJoin(qdevice.parts).where(qdevice.deviceId.eq(devicePartContainer.getDeviceId())).fetchJoin().fetchOne();
		if (device != null) {
			devicePartContainer.setDevice(device);	
			return;
		}
		// 없을때, device만 질의
		devicePartContainer.setDevice(from(qdevice).where(qdevice.deviceId.eq(devicePartContainer.getDeviceId())).fetchOne());
	}
}
