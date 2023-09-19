package com.mph.business.services.interfaces;

import java.util.Set;

import com.mph.domain.beans.Post;
import com.mph.domain.beans.Thread;
import com.mph.domain.beans.User;

public interface PostService {

	/**
	 * Creates a new post.
	 *
	 * @param login the content entered
	 * @param email the thread which will contain the post
	 * @param password the user
	 *
	 * @return the new post created
	 */
	public Post createPost(String content, Thread thread, User user);

	/**
	 * Updates an existing post.
	 *
	 * @param post the post to update
	 * @param content the new content of the post
	 */
	public void editPost(Post post, String content);

	public Post getPostById(long id);

	public Set<Post> getPostsByUser(User user);

}