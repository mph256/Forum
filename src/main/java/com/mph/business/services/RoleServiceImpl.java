package com.mph.business.services;

import com.mph.business.services.interfaces.RoleService;

import com.mph.domain.dao.interfaces.RoleDao;

import com.mph.domain.beans.Role;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Role getRoleById(long id) {
		return roleDao.findById(id);
	}

}