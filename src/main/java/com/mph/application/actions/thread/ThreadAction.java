package com.mph.application.actions.thread;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.ThreadService;
import com.mph.business.services.interfaces.TagService;
import com.mph.business.services.interfaces.PostService;

import com.mph.domain.beans.Thread;
import com.mph.domain.beans.User;
import com.mph.domain.beans.Tag;

public class ThreadAction extends ActionSupport implements ApplicationAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(ThreadAction.class);

	private long id;

	private String title;

	private String tags;

	private String content;

	private Thread thread;

	private Set<Thread> threads;

	private ThreadService threadService;

	private TagService tagService;

	private PostService postService;

	private Map<String, Object> session;

	@Override
	public void withApplication(Map<String, Object> application) {

		threadService = (ThreadService) application.get("threadService");

		if(threadService == null) {

			threadService = ServiceFactory.getInstance().getThreadService();
			application.put("threadService", threadService);

		}

		tagService = (TagService) application.get("tagService");

		if(tagService == null) {

			tagService = ServiceFactory.getInstance().getTagService();
			application.put("tagService", tagService);

		}

		postService = (PostService) application.get("postService");

		if(postService == null) {

			postService = ServiceFactory.getInstance().getPostService();
			application.put("postService", postService);

		}

	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;
	}

	@SkipValidation
	public String loadPage() {

		thread = threadService.getThreadById(id);

		return "input2";

	}

	@SkipValidation
	public String searchThread() {

		threads = new LinkedHashSet<>();

		Set<Tag> tagsToSearch = new HashSet<>();

		Arrays.stream(tags.split(" ")).collect(Collectors.toSet())
			.forEach(x -> tagsToSearch.add(tagService.getTagByLabel(x)));

		threads.addAll(threadService.getThreadsByTags(tagsToSearch));

		return SUCCESS;

	}

	public String createThread() {

		User user = (User) session.get("user");

		if(user != null) {

			thread = threadService.createThread(title, user, Arrays.stream(tags.split(" ")).collect(Collectors.toSet()));
			postService.createPost(content, thread, user);

			logger.info("Thread creation: " + thread.getId() + " -> user: " + user.getLogin());

			return SUCCESS;

		}

		return "forbidden";

	}

	@SkipValidation
	public String deleteThread() {

		thread = threadService.getThreadById(id);

		User user = (User) session.get("user");

		if(user != null) {

			threadService.deleteThread(thread);

			logger.info("Thread deletion: " + thread.getId() + " -> user: " + user.getLogin());

			return "success2";

		}

		return "forbidden2";

	}

	@SkipValidation
	public String refreshThreads() {

		threads = threadService.getThreads();

		return SUCCESS;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public Set<Thread> getThreads() {
		return threads;
	}

	public void setThreads(Set<Thread> threads) {
		this.threads = threads;
	}

}