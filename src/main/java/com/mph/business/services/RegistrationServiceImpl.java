package com.mph.business.services;

import com.mph.business.services.interfaces.RegistrationService;
import com.mph.business.services.interfaces.UserService;

import com.mph.business.services.exceptions.LoginAlreadyInUseException;
import com.mph.business.services.exceptions.EmailAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidPasswordConfirmationException;

import com.mph.domain.beans.User;

public class RegistrationServiceImpl implements RegistrationService {

	private UserService userService;

	RegistrationServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User registerUser(User user){

		user = userService.createUser(user);

		return user;

	}

	@Override
	public User checkUser(String login, String email, String password, String passwordConfirmation) throws LoginAlreadyInUseException, 
		EmailAlreadyInUseException, InvalidPasswordConfirmationException {

		checkLogin(login);
		checkEmail(email);
		checkPassword(password, passwordConfirmation);

		return new User(login, email, password);

	}

	private void checkLogin(String login) throws LoginAlreadyInUseException {
		userService.checkNewLogin(login);
	}

	private void checkEmail(String email) throws EmailAlreadyInUseException {
		userService.checkNewEmail(email);
	}

	private void checkPassword(String password, String passwordConfirmation) throws InvalidPasswordConfirmationException {
		userService.checkNewPassword(password, passwordConfirmation);
	}

}