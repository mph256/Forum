package com.mph.infrastructure.dao;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mph.domain.dao.interfaces.ThreadDao;

import com.mph.domain.beans.Thread;
import com.mph.domain.beans.Tag;

public class ThreadDaoImpl implements ThreadDao {

	ThreadDaoImpl() {
	}

	@Override
	public Thread add(Thread thread) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			if(!entityManager.contains(thread))
				thread = entityManager.merge(thread);

			entityManager.persist(thread);

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

		return thread;

	}

	@Override
	public void update(Thread thread) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			entityManager.merge(thread);

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
	public void delete(Thread thread) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();		
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			entityManager.remove(entityManager.contains(thread)?thread:entityManager.merge(thread));

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
	public Thread findById(long id) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		Thread thread = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			thread = entityManager.find(Thread.class, id);

		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return thread;

	}

	@Override
	public Set<Thread> findByTags(Set<Tag> tags) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		List<Thread> threads = new ArrayList<Thread>();

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			for(Tag tag: tags) {

				threads.addAll(
					entityManager.createQuery("SELECT thread FROM Thread thread WHERE :tag MEMBER OF thread.tags ORDER BY thread.lastUpdate DESC", Thread.class)
						.setParameter("tag", tag)
						.getResultList()
				);

			}

		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return new LinkedHashSet<Thread>(threads);

	}

	@Override
	public Set<Thread> findAll() {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		List<Thread> threads = new ArrayList<Thread>();

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			threads.addAll(
				entityManager.createQuery("SELECT thread FROM Thread thread ORDER BY thread.lastUpdate DESC", Thread.class)
					.getResultList()
			);

		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return new LinkedHashSet<Thread>(threads);

	}

}