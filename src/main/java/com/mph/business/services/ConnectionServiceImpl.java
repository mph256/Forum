package com.mph.business.services;

import java.util.Date;

import com.mph.business.services.interfaces.ConnectionService;
import com.mph.business.services.interfaces.UserService;

import com.mph.business.services.exceptions.InvalidLoginException;
import com.mph.business.services.exceptions.InvalidPasswordException;

import com.mph.domain.beans.User;

import com.mph.domain.util.Util;

public class ConnectionServiceImpl implements ConnectionService {

	private UserService userService;

	ConnectionServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User connectUser(User user) {

		updateUser(user);

		return user;

	}

	@Override
	public User checkUser(String login, String password) throws InvalidLoginException, InvalidPasswordException {

		User user = userService.getUserByLogin(login);

		checkLogin(user);
		checkPassword(user, password);

		return user;

	}

	private void checkLogin(User user) throws InvalidLoginException {
		userService.checkLogin(user);
	}

	private void checkPassword(User user, String password) throws InvalidPasswordException {
		userService.checkPassword(user, password);
	}

	private void updateUser(User user) {

		user.setLastConnection(new Date());
		user.setOnline(true);
		user.setProfilePicture(Util.getProfilePictureByLogin(user.getLogin()));

		userService.updateUser(user);

	}

}