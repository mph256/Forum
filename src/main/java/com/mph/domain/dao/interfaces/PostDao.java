package com.mph.domain.dao.interfaces;

import java.util.Set;

import com.mph.domain.beans.Post;
import com.mph.domain.beans.User;

public interface PostDao {

	public Post add(Post post);

	public void update(Post post);

	public Post findById(long id);

	public Set<Post> findByUser(User user);

}