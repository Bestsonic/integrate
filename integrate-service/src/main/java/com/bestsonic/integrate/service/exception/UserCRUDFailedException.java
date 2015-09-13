package com.bestsonic.integrate.service.exception;

public class UserCRUDFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserCRUDFailedException() {
	}

	public UserCRUDFailedException(String message) {
		super(message);
	}

	public UserCRUDFailedException(Throwable cause) {
		super(cause);
	}

	public UserCRUDFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserCRUDFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
