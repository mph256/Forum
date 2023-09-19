package com.mph.business.services.interfaces;

import com.mph.business.services.exceptions.InvalidLoginException;
import com.mph.business.services.exceptions.InvalidPasswordException;

import com.mph.domain.beans.User;

public interface ConnectionService {

	/**
	 * Connects an existing user.
	 * 
	 * <br>
	 * Updates his last connection date.
	 * 
	 * <br>
	 * Argument validity checks must be performed before this method is called.
	 * 
	 * @param user the disconnected user to connect
	 * 
	 * @return the connected user
	 */
	public User connectUser(User user);

	/**
	 * Checks the validity of the login and password entered during a connection attempt.
	 * 
	 * @param login the login entered by the user
	 * @param password the password entered by the user
	 * 
	 * @return the user
	 * 
	 * @throws InvalidLoginException if no user with this login is found
	 * @throws InvalidPasswordException if the entered password does not match the one registered for this login
	 */
	public User checkUser(String login, String password) throws InvalidLoginException, InvalidPasswordException;

}