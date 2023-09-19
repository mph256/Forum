package com.mph.infrastructure.dao;

import com.mph.domain.dao.interfaces.DaoFactory;

import com.mph.domain.dao.interfaces.RoleDao;
import com.mph.domain.dao.interfaces.UserDao;
import com.mph.domain.dao.interfaces.TagDao;

public final class DaoFactoryImpl implements DaoFactory {

	private static DaoFactory instance;

	private RoleDao roleDao;

	private UserDao userDao;

	private TagDao tagDao;

	public DaoFactoryImpl() {

		roleDao = new RoleDaoImpl();
		userDao = new UserDaoImpl();
		tagDao = new TagDaoImpl();

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

}