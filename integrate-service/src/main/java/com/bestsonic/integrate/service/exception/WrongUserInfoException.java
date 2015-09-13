package com.bestsonic.integrate.service.exception;

public class WrongUserInfoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongUserInfoException() {
	}

	public WrongUserInfoException(String message) {
		super(message);
	}

	public WrongUserInfoException(Throwable cause) {
		super(cause);
	}

	public WrongUserInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongUserInfoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
