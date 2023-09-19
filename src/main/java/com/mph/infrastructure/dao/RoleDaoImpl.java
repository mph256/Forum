package com.mph.infrastructure.dao;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

import com.mph.domain.dao.interfaces.RoleDao;

import com.mph.domain.beans.Role;

public class RoleDaoImpl implements RoleDao {

	RoleDaoImpl() {
	}

	@Override
	public Role findById(long id) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		Role role = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			role = entityManager.find(Role.class, id);

		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return role;

	}

}