package com.mph.business.services.exceptions;

public class LabelAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;

	public LabelAlreadyInUseException() {
		super("This label is already in use.");
	}

}