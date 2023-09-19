package com.mph.infrastructure.dao;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.mph.domain.dao.interfaces.UserDao;

import com.mph.domain.beans.User;

public class UserDaoImpl implements UserDao {

	UserDaoImpl() {
	}

	@Override
	public User add(User user) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			if(!entityManager.contains(user))
				user = entityManager.merge(user);

			entityManager.persist(user);

			entityTransaction.commit();

		} catch(RuntimeException e) {
			entityTransaction.rollback();
			throw e;
		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return user;

	}

	@Override
	public void update(User user) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			entityManager.merge(user);

			entityTransaction.commit();

		} catch(RuntimeException e) {
			entityTransaction.rollback();
			throw e;
		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

	}

	@Override
	public void delete(User user) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			entityManager.remove(entityManager.contains(user)?user:entityManager.merge(user));

			entityTransaction.commit();

		} catch(RuntimeException e)
		{
			entityTransaction.rollback();
			throw e;
		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

	}

	@Override
	public User findByLogin(String login) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		User user = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			user = entityManager.createQuery("SELECT user FROM User user WHERE user.login = :login", User.class)
				.setParameter("login", login)
				.getSingleResult();

		} catch(NoResultException e) {
		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return user;

	}

	@Override
	public User findByEmail(String email) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		User user = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			user = entityManager.createQuery("SELECT user FROM User user WHERE user.email = :email", User.class)
				.setParameter("email", email)
				.getSingleResult();

		} catch(NoResultException e) {
		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return user;

	}

	@Override
	public Set<User> findAll() {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		List<User> users = new ArrayList<>();

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			users.addAll(
				entityManager.createQuery("SELECT user FROM User user ORDER BY user.login", User.class)
					.getResultList()
			);

		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return new LinkedHashSet<User>(users);

	}

}