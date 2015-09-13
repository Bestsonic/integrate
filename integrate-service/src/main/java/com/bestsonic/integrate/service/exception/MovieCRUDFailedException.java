package com.bestsonic.integrate.service.exception;

public class MovieCRUDFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieCRUDFailedException() {
	}

	public MovieCRUDFailedException(String message) {
		super(message);
	}

	public MovieCRUDFailedException(Throwable cause) {
		super(cause);
	}

	public MovieCRUDFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MovieCRUDFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
