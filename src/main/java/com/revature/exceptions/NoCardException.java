package com.revature.exceptions;

public class NoCardException extends RuntimeException {

	private static final long serialVersionUID = 6390259740150601091L;

	public NoCardException() {
		super();
	}

	public NoCardException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoCardException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NoCardException(final String message) {
		super(message);
	}

	public NoCardException(final Throwable cause) {
		super(cause);
	}

}
