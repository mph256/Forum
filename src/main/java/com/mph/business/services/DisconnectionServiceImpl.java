package com.mph.business.services;

import com.mph.business.services.interfaces.DisconnectionService;
import com.mph.business.services.interfaces.UserService;

import com.mph.domain.beans.User;

public class DisconnectionServiceImpl implements DisconnectionService {

	private UserService userService;

	DisconnectionServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void disconnectUser(User user) {

		user.setOnline(false);

		userService.updateUser(user);

	}

}