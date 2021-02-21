package com.ntd.devicePart.exception;

/**
 * 패키지 기준으로 devicePart 내에 resource들의 
 * Exception을 담당하는 클래ㅐ스
 * @author hoya
 *
 */
public class DevicePartException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595036129516249995L;

	public static final String DEVICEPART_EXCEPTION = "500.1 Error internal Server";

	public DevicePartException() {
		super(DEVICEPART_EXCEPTION);
	}

	public DevicePartException(Throwable e) {
		super(e);
	}
	
	public DevicePartException(String msg) {
		super(msg);
	}

}
