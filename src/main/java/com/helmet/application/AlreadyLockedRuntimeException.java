package com.helmet.application;

public class AlreadyLockedRuntimeException extends RuntimeException {

	public AlreadyLockedRuntimeException(String arg) {
		super(arg);
	}

}
