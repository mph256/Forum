package com.mph.business.services.interfaces;

import java.util.Set;

import com.mph.business.services.exceptions.LoginAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidLoginException;
import com.mph.business.services.exceptions.EmailAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidPasswordConfirmationException;
import com.mph.business.services.exceptions.InvalidPasswordException;

import com.mph.domain.beans.User;

public interface UserService {

	/**
	 * Creates a new user.
	 *
	 * <br>
	 * Argument validity checks must be performed before this method is called.
	 *
	 * @param user the user
	 *
	 * @return the new user created
	 */
	public User createUser(User user);

	/**
	 * Updates an existing user.
	 *
	 * <br>
	 * The user given in argument must already be up to date.
	 *
	 * @param user the user to update
	 */
	public void updateUser(User user);

	/**
	 * Updates an existing user.
	 * 
	 * <br>
	 * Argument validity checks must be performed before this method is called.
	 * 
	 * @param user the user to update
	 * @param email the new email
	 * @param password the new password
	 */
	public void updateUser(User user, String email, String password);

	/**
	 * Deletes an existing user.
	 *
	 * @param user the user to delete
	 */
	public void deleteUser(User user);

	/**
	 * Checks the validity of the login entered during a registration attempt.
	 * 
	 * @param login the login entered
	 * 
	 * @throws LoginAlreadyInUseException if the entered login is already in use
	 */
	public void checkNewLogin(String login) throws LoginAlreadyInUseException;

	/**
	 * Checks the validity of the email entered during a registration attempt.
	 * 
	 * @param email the email entered
	 * 
	 * @throws EmailAlreadyInUseException if the entered email is already in use
	 */
	public void checkNewEmail(String email) throws EmailAlreadyInUseException;

	/**
	 * Checks the validity of the password entered during a registration attempt.
	 * 
	 * @param password the password entered
	 * @param passwordConfirmation the password confirmation entered
	 * 
	 * @throws InvalidPasswordConfirmationException if the entered passwords do not match
	 */
	public void checkNewPassword(String password, String passwordConfirmation) throws InvalidPasswordConfirmationException;

	/**
	 * Checks the validity of the login entered during a connection attempt.
	 * 
	 * @param user the user
	 * 
	 * @throws InvalidLoginException if no user with this login is found
	 */
	public void checkLogin(User user) throws InvalidLoginException;

	/**
	 * Checks the validity of the password entered during a connection attempt.
	 * 
	 * @param user the user
	 * @param password the password entered by the user
	 * 
	 * @throws InvalidPasswordException if the entered password does not match the one registered for this login
	 */
	public void checkPassword(User user, String password) throws InvalidPasswordException;

	public User getUserByLogin(String login);

	public User getUserByEmail(String email);

	public Set<User> getUsers();

}