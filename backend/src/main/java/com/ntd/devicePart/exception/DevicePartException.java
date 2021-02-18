package com.ntd.devicePart.exception;

public class DevicePartException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595036129516249995L;

	public static final String DEVICEPART_EXCEPTION = "500.1 Error internal Server";

	public DevicePartException(String msg) {
		super(msg);
	}

	@Override
	public String getMessage() {
		return DEVICEPART_EXCEPTION;
	}
	
}
