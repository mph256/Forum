package com.mph.business.services.exceptions;

public class EmailAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailAlreadyInUseException() {
		super("This email is already in use. Please choose another.");
	}

}