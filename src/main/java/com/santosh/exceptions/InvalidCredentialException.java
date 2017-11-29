package com.santosh.exceptions;

public class InvalidCredentialException extends Exception{

	private int statusCode;
	
	
	public InvalidCredentialException(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
}
