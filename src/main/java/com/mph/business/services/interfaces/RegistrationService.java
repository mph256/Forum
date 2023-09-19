package com.mph.business.services.interfaces;

import com.mph.business.services.exceptions.LoginAlreadyInUseException;
import com.mph.business.services.exceptions.EmailAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidPasswordConfirmationException;

import com.mph.domain.beans.User;

public interface RegistrationService {

	/**
	 * Registers a new user.
	 * 
	 * <br>
	 * Argument validity checks must be performed before this method is called.
	 * 
	 * @param the new user to register
	 * 
	 * @return the new registered user
	 */
	public User registerUser(User user);

	/**
	 * Checks the validity of the login, email and password entered during a registration attempt.
	 * 
	 * @param login the login entered
	 * @param email the email entered
	 * @param password the password entered
	 * @param passwordConfirmation the password confirmation entered
	 * 
	 * @return the new user created
	 * 
	 * @throws LoginAlreadyInUseException if the entered login is already in use
	 * @throws EmailAlreadyInUseException if the entered email is already in use
	 * @throws InvalidPasswordConfirmationException if the entered passwords do not match
	 */
	public User checkUser(String login, String email, String password, String passwordConfirmation) throws LoginAlreadyInUseException,
		EmailAlreadyInUseException, InvalidPasswordConfirmationException;

}