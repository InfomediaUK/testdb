package com.helmet.api.exceptions;

public class DuplicateDataException extends RuntimeException {

	String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public DuplicateDataException(String field) {
		super();
		this.field = field;
	}
	
	
}
