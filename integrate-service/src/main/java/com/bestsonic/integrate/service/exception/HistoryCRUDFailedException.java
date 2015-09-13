package com.bestsonic.integrate.service.exception;

public class HistoryCRUDFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HistoryCRUDFailedException() {
	}

	public HistoryCRUDFailedException(String message) {
		super(message);
	}

	public HistoryCRUDFailedException(Throwable cause) {
		super(cause);
	}

	public HistoryCRUDFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public HistoryCRUDFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
