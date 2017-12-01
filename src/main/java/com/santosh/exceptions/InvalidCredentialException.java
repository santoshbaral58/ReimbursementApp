package com.santosh.exceptions;

public class InvalidCredentialException extends Exception{


	private static final long serialVersionUID = -3232870553220829361L;
	private int statusCode;
	
	
	public InvalidCredentialException(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
}
