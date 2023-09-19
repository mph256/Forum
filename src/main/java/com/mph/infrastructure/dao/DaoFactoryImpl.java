package com.mph.infrastructure.dao;

import com.mph.domain.dao.interfaces.DaoFactory;

import com.mph.domain.dao.interfaces.RoleDao;
import com.mph.domain.dao.interfaces.UserDao;
import com.mph.domain.dao.interfaces.TagDao;
import com.mph.domain.dao.interfaces.ThreadDao;

public final class DaoFactoryImpl implements DaoFactory {

	private static DaoFactory instance;

	private RoleDao roleDao;

	private UserDao userDao;

	private TagDao tagDao;

	private ThreadDao threadDao;

	public DaoFactoryImpl() {

		roleDao = new RoleDaoImpl();
		userDao = new UserDaoImpl();
		tagDao = new TagDaoImpl();
		threadDao = new ThreadDaoImpl();

	}

	public static DaoFactory getInstance() {

		if(instance == null)
			instance = new DaoFactoryImpl();

		return instance;

	}

	@Override
	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Override
	public UserDao getUserDao() {
		return userDao;
	}

	@Override
	public TagDao getTagDao() {
		return tagDao;
	}

	@Override
	public ThreadDao getThreadDao() {
		return threadDao;
	}

}