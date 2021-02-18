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
	
	/**
	 * 테이블: device, 컬럼: deviceModel, deviceSerialNumber, cpuInfo, ramInfo, volumeInfo, deviceInfo
	 * 장비 모델(deviceModel), 장비 고유번호(deviceSerialNumber), 장비 cpu 정보(cpuInfo), 장비 램 정보(ramInfo), 장비 볼륨 정보(volumeInfo), 장비 정보(deviceInfo) 를 통해
	 * 장비 리스트 정보를 얻는 함수
	 * @param devicePartContainer
	 * @return
	 */
	public List<Device> findDevicesByColumns(DevicePartContainer devicePartContainer) {
		List<Device> devices = null;
		if (!devicePartContainer.isDoSearchDefault() && devicePartContainer.paramsEmptyByDevice()) {
			logger.debug("DEVICE > SEARCH > NOT SUPPORTED WHEN PARAMS EMPTY IN DEVICE");
			return devices;
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
		devices = queryFactory.selectFrom(qdevice).where(builder).fetch();
		return devices;
	}

	/**
	 *  테이블: device, 컬럼: deviceId - join 테이블: part
	 *  장비고유식별번호(deviceId)를 통해 장비 정보, 장비와 연결된 부품 정보를 얻는 함수
	 * @param devicePartContainer
	 */
	public Device findDevicePartsById(DevicePartContainer devicePartContainer) {

		Device device = null;
		// device 고유 식별번호가 없으면 데이터 질의하지 않음
		if (devicePartContainer.getDeviceId() <= 0) {
			return device;
		}
		QDevice qdevice = QDevice.device;
		// device , part 같이 join하여질의
		device = from(qdevice).leftJoin(qdevice.parts).where(qdevice.deviceId.eq(devicePartContainer.getDeviceId()))
				.fetchJoin().fetchOne();
		return device;
	}
}
