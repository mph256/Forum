package com.mph.domain.dao.interfaces;

import java.util.Set;

import com.mph.domain.beans.User;

public interface UserDao {

	public User add(User user);

	public void update(User user);

	public void delete(User user);

	public User findByLogin(String login);

	public User findByEmail(String email);

	public Set<User> findAll();

}