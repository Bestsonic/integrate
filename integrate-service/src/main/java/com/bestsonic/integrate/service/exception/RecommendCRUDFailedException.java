package com.bestsonic.integrate.service.exception;

public class RecommendCRUDFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecommendCRUDFailedException() {
	}

	public RecommendCRUDFailedException(String message) {
		super(message);
	}

	public RecommendCRUDFailedException(Throwable cause) {
		super(cause);
	}

	public RecommendCRUDFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecommendCRUDFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
