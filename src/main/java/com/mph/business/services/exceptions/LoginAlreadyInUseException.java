package com.mph.business.services.exceptions;

public class LoginAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginAlreadyInUseException() {
		super("This login is already in use. Please choose another.");
	}

}