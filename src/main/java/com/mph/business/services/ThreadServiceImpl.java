package com.mph.business.services;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import java.util.Date;

import com.mph.business.services.interfaces.ThreadService;
import com.mph.business.services.interfaces.TagService;

import com.mph.business.services.exceptions.LabelAlreadyInUseException;

import com.mph.domain.dao.interfaces.ThreadDao;

import com.mph.domain.beans.Thread;
import com.mph.domain.beans.User;
import com.mph.domain.beans.Tag;
import com.mph.domain.beans.Post;

import com.mph.domain.util.Util;

public class ThreadServiceImpl implements ThreadService {

	private TagService tagService;

	private ThreadDao threadDao;

	ThreadServiceImpl(TagService tagService, ThreadDao threadDao) {

		this.tagService = tagService;

		this.threadDao = threadDao;

	}

	@Override
	public Thread createThread(String title, User user, Set<String> labels) {

		Set<Tag> tags = new LinkedHashSet<Tag>();

		for(String label: labels) {

			try {

				tagService.checkNewLabel(label);

				tags.add(new Tag(label));

			} catch(LabelAlreadyInUseException e) {

				Tag tag = tagService.getTagByLabel(label);

				tags.add(tag);

			}

		}

		Thread thread = new Thread(title, user, tags.stream().sorted((x, y) -> x.getLabel().compareTo(y.getLabel())).collect(Collectors.toSet()));

		thread = threadDao.add(thread);

		return thread;

	}

	@Override
	public void updateThread(Thread thread) {

		thread.setLastUpdate(new Date());

		threadDao.update(thread);

	}

	@Override
	public void deleteThread(Thread thread) {
		threadDao.delete(thread);
	}

	@Override
	public Thread getThreadById(long id) {

		Thread thread = threadDao.findById(id);

		for(Post post: thread.getPosts()) {

			User user = post.getUser();
			user.setProfilePicture(Util.getProfilePictureByLogin(user.getLogin()));

		}

		return thread;

	}

	@Override
	public Set<Thread> getThreadsByTags(Set<Tag> tags) {
		return threadDao.findByTags(tags);
	}

	@Override
	public Set<Thread> getThreads() {
		return threadDao.findAll();
	}

}