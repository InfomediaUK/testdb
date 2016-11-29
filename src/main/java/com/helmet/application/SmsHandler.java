package com.helmet.application;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class SmsHandler {

	private static SmsHandler smsHandler;

	protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	private String username;

	private String password;

	private String account;

	public static SmsHandler getInstance() {
		if (smsHandler == null) {
			// NOT instantiated yet so create it.
			synchronized (SmsHandler.class) {
				// Only ONE thread at a time here!
				if (smsHandler == null) {
					smsHandler = new SmsHandler();
				}
			}
		}
		return smsHandler;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}