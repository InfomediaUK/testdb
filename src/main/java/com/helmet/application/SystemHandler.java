package com.helmet.application;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class SystemHandler {

	private static SystemHandler systemHandler;

	protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	private String helpPopupEvent;

	public static SystemHandler getInstance() {
		if (systemHandler == null) {
			// NOT instantiated yet so create it.
			synchronized (SystemHandler.class) {
				// Only ONE thread at a time here!
				if (systemHandler == null) {
					systemHandler = new SystemHandler();
				}
			}
		}
		return systemHandler;
	}

	public String getHelpPopupEvent() {
		return helpPopupEvent;
	}

	public void setHelpPopupEvent(String helpPopupEvent) {
		this.helpPopupEvent = helpPopupEvent;
	}
	
}