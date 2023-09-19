package com.mph.infrastructure.dao;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.mph.domain.dao.interfaces.TagDao;

import com.mph.domain.beans.Tag;

public class TagDaoImpl implements TagDao {

	TagDaoImpl() {
	}

	@Override
	public Tag findByLabel(String label) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		Tag tag = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			tag = entityManager.createQuery("SELECT tag FROM Tag tag WHERE tag.label = :label", Tag.class)
				.setParameter("label", label)
				.getSingleResult();

		} catch(NoResultException e) {
		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return tag;

	}

}