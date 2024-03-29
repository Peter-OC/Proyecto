package com.bootcamp.exceptions;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		this("NOT FOUND");
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super("NOT FOUND", cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}