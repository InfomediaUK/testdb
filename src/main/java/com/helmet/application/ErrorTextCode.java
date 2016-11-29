package com.helmet.application;

public class ErrorTextCode {
	private String errorText;

	private int errorCode;

	/**
	 * Empty constructor.
	 */
	public ErrorTextCode() {
	}

	/**
	 * Constructor with errorText supplied.
	 */
	public ErrorTextCode(String errorText) {
		this.errorText = errorText;
	}

	/**
	 * Constructor with errorText and errorCode supplied.
	 */
	public ErrorTextCode(String errorText, int errorCode) {
		this.errorText = errorText;
		this.errorCode = errorCode;
	}

	/**
	 * Return the errorText.
	 */
	public String getErrorText() {
		return errorText;
	}

	/**
	 * Set the errorText.
	 * 
	 * @param errorText
	 *            The new errorText
	 */
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	/**
	 * Return the errorCode.
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Set the errorCode.
	 * 
	 * @param errorCode
	 *            The new errorCode
	 */
	public void seterrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}