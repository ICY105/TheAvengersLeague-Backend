package com.revature.exceptions;

public class NoUserException extends RuntimeException {

	private static final long serialVersionUID = 6390259740150601091L;

	public NoUserException() {
		super();
	}

	public NoUserException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoUserException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NoUserException(final String message) {
		super(message);
	}

	public NoUserException(final Throwable cause) {
		super(cause);
	}

}
