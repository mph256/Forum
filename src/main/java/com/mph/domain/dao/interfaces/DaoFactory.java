package com.mph.domain.dao.interfaces;

public interface DaoFactory {

	public RoleDao getRoleDao();

	public UserDao getUserDao();

	public TagDao getTagDao();

	public ThreadDao getThreadDao();

	public PostDao getPostDao();

}