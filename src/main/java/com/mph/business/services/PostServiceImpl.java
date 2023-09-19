package com.mph.business.services;

import java.util.Set;

import java.util.Date;

import com.mph.business.services.interfaces.PostService;

import com.mph.domain.dao.interfaces.PostDao;

import com.mph.domain.beans.Post;
import com.mph.domain.beans.Thread;
import com.mph.domain.beans.User;

import com.mph.domain.util.Util;

public class PostServiceImpl implements PostService {

	private PostDao postDao;

	PostServiceImpl(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public Post createPost(String content, Thread thread, User user) {

		thread.setLastUpdate(new Date());

		Post post = new Post(content, thread, user);

		post = postDao.add(post);

		post.getUser().setProfilePicture(Util.getProfilePictureByLogin(user.getLogin()));

		thread.addPost(post);

		return post;

	}

	@Override
	public void editPost(Post post, String content) {

		post.setContent(content);
		post.setLastUpdate(new Date());

		post.getThread().setLastUpdate(new Date());

		postDao.update(post);

	}

	@Override
	public Post getPostById(long id) {

		Post post = postDao.findById(id);

		User user = post.getUser();
		user.setProfilePicture(Util.getProfilePictureByLogin(user.getLogin()));

		return post;

	}

	@Override
	public Set<Post> getPostsByUser(User user) {
		return postDao.findByUser(user);
	}

}