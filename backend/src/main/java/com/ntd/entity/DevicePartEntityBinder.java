package com.ntd.entity;

/**
 * Entity 역할을 하는 Device, Part 클래스가 데이터를 반환시
 * 묶어주는 공통 인터페이스, database ORM에서도 서로 1:n 관계를 가지고 있다.
 * 필요한 공통함수는 차후에 구현한다.
 * @author hoya
 *
 */
public interface DevicePartEntityBinder {
	public String obtainDeviceOrPartModel();
}
