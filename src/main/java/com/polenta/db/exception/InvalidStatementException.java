package com.polenta.db.exception;

public class InvalidStatementException extends PolentaException {

	private static final long serialVersionUID = -8690157830974582266L;

	public InvalidStatementException() {
	}

	public InvalidStatementException(String message) {
		super(message);
	}
	
}
