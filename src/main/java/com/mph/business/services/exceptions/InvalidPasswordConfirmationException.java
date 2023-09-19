package com.mph.business.services.exceptions;

public class InvalidPasswordConfirmationException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPasswordConfirmationException() {
		super("Passwords do not match.");
	}

}