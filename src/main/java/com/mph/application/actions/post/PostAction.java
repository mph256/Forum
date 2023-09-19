package com.mph.application.actions.post;

import java.util.Map;
import java.util.Set;

import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.PostService;
import com.mph.business.services.interfaces.ThreadService;

import com.mph.domain.beans.Post;
import com.mph.domain.beans.Thread;
import com.mph.domain.beans.User;

import com.mph.domain.util.Util;

public class PostAction extends ActionSupport implements ApplicationAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private long id;

	private String content;

	private Post post;

	private Thread thread;

	private User user;

	private Set<Post> posts;

	private PostService postService;

	private ThreadService threadService;

	private Map<String, Object> session;

	@Override
	public void withApplication(Map<String, Object> application) {

		postService = (PostService) application.get("postService");

		if(postService == null) {

			postService = ServiceFactory.getInstance().getPostService();
			application.put("postService", postService);

		}

		threadService = (ThreadService) application.get("threadService");

		if(threadService == null) {

			threadService = ServiceFactory.getInstance().getThreadService();
			application.put("threadService", threadService);

		}

	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;		
	}
	
	public String createPost() {

		user = (User) session.get("user");

		if(user != null) {

			thread = threadService.getThreadById(id);

			postService.createPost(content, thread, user);

		}

		return SUCCESS;

	}

	public String editPost() {

		user = (User) session.get("user");

		if(user != null) {

			post = postService.getPostById(id);

			postService.editPost(post, content);

		}

		return SUCCESS;

	}

	public String deletePost() {

		user = (User) session.get("user");

		if(user != null) {

			Post post = postService.getPostById(id);

			thread = post.getThread();

			thread.removePost(post);

			threadService.updateThread(thread);

			for(Post post2: thread.getPosts()) {

				User user = post2.getUser();
				user.setProfilePicture(Util.getProfilePictureByLogin(user.getLogin()));

			}

		}

		return SUCCESS;

	}

	public String refreshPosts() {

		thread = threadService.getThreadById(id);

		user = (User) session.get("user");

		return SUCCESS;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}