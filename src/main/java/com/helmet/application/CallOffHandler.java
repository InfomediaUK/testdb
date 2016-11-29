package com.helmet.application;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class CallOffHandler {

	private static CallOffHandler callOffHandler;

	protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	private int initial = 0;

	private int subsequent = 5;

	private CallOffTimer callOffTimer;

	public static CallOffHandler getInstance() {
		if (callOffHandler == null) {
			// NOT instantiated yet so create it.
			synchronized (CallOffHandler.class) {
				// Only ONE thread at a time here!
				if (callOffHandler == null) {
					callOffHandler = new CallOffHandler();
				}
			}
		}
		return callOffHandler;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}

	public void setSubsequent(int subsequent) {
		this.subsequent = subsequent;
	}

	public void init() {
		callOffTimer = new CallOffTimer(initial, subsequent);
	}

	public void cancel() {
		callOffTimer.cancel();
	}

}