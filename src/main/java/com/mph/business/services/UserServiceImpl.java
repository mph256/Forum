package com.mph.business.services;

import java.util.Set;

import java.io.File;

import com.mph.business.services.interfaces.UserService;
import com.mph.business.services.interfaces.RoleService;

import com.mph.business.services.exceptions.LoginAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidLoginException;
import com.mph.business.services.exceptions.EmailAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidPasswordConfirmationException;
import com.mph.business.services.exceptions.InvalidPasswordException;

import com.mph.domain.dao.interfaces.UserDao;

import com.mph.domain.beans.User;

public class UserServiceImpl implements UserService {

	private RoleService roleService;

	private UserDao userDao;

	UserServiceImpl(RoleService roleService, UserDao userDao) {

		this.roleService = roleService;

		this.userDao = userDao;

	}

	@Override
	public User createUser(User user) {

		File profilePicture = user.getProfilePicture();

		user.setRole(roleService.getRoleById(1));

		user = userDao.add(user);

		user.setProfilePicture(profilePicture);

		return user;

	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void updateUser(User user, String email, String password) {

		boolean isNewEmail = !email.equals(user.getEmail());
		boolean isNewPassword = !password.equals(user.getPassword());

		if(isNewEmail)
			user.setEmail(email);

		if(isNewPassword)
			user.setPassword(password);

		userDao.update(user);

	}

	@Override
	public void deleteUser(User user) {

		userDao.delete(user);

		user.getProfilePicture().delete();

	}

	@Override
	public User getUserByLogin(String login) {
		return userDao.findByLogin(login);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public Set<User> getUsers() {
		return userDao.findAll();
	}

	@Override
	public void checkNewLogin(String login) throws LoginAlreadyInUseException {

		if(isLoginAlreadyInUse(login))
			throw new LoginAlreadyInUseException();

	}

	@Override
	public void checkNewEmail(String email) throws EmailAlreadyInUseException {

		if(isEmailAlreadyInUse(email))
			throw new EmailAlreadyInUseException();

	}

	@Override
	public void checkNewPassword(String password, String passwordConfirmation) throws InvalidPasswordConfirmationException {

		if(!password.equals(passwordConfirmation))
			throw new InvalidPasswordConfirmationException();

	}

	@Override
	public void checkLogin(User user) throws InvalidLoginException {

		if(user == null)
			throw new InvalidLoginException();

	}

	@Override
	public void checkPassword(User user, String password) throws InvalidPasswordException {

		if(!password.equals(user.getPassword()))
			throw new InvalidPasswordException();

	}

	private boolean isLoginAlreadyInUse(String login) {

		User user = getUserByLogin(login);

		return (user != null);

	}

	private boolean isEmailAlreadyInUse(String email) {

		User user = getUserByEmail(email);

		return (user != null);

	}

}