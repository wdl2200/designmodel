package com.cmts.xm.dao.exception;

@SuppressWarnings("serial")
public class NoDefinedGetterException extends Exception {
	
	private String fieldName;
	
	public NoDefinedGetterException(String fieldName){
		super(fieldName + " should have an getter method.");
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
}
