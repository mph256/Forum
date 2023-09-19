package com.mph.infrastructure.dao;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mph.domain.dao.interfaces.PostDao;

import com.mph.domain.beans.Post;
import com.mph.domain.beans.User;

public class PostDaoImpl implements PostDao {

	PostDaoImpl() {
	}

	@Override
	public Post add(Post post) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			if(!entityManager.contains(post))
				post = entityManager.merge(post);

			entityManager.persist(post);

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

		return post;

	}

	@Override
	public void update(Post post) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			entityManager.merge(post);

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
	public Post findById(long id) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		Post post = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			post = entityManager.find(Post.class, id);

		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return post;

	}

	@Override
	public Set<Post> findByUser(User user) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		List<Post> posts = new ArrayList<Post>();

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			entityManagerFactory = Persistence.createEntityManagerFactory("forum");
			entityManager = entityManagerFactory.createEntityManager();

			posts.addAll(
				entityManager.createQuery("SELECT post FROM Post post WHERE post.user = :user ORDER BY post.publicationDate DESC", Post.class)
					.setParameter("user", user)
					.getResultList()
			);

		} finally {
			if(entityManager != null)
				entityManager.close();
			if(entityManagerFactory != null)
				entityManagerFactory.close();
		}

		return new LinkedHashSet<Post>(posts);

	}

}