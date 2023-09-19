package com.mph.business.services;

import com.mph.business.services.interfaces.RoleService;
import com.mph.business.services.interfaces.UserService;
import com.mph.business.services.interfaces.RegistrationService;
import com.mph.business.services.interfaces.DisconnectionService;
import com.mph.business.services.interfaces.ConnectionService;
import com.mph.business.services.interfaces.TagService;
import com.mph.business.services.interfaces.ThreadService;
import com.mph.business.services.interfaces.PostService;

import com.mph.domain.dao.interfaces.DaoFactory;

import com.mph.infrastructure.dao.DaoFactoryImpl;

public final class ServiceFactory {

	private static ServiceFactory instance;

	private RoleService roleService;

	private UserService userService;

	private RegistrationService registrationService;

	private DisconnectionService disconnectionService;

	private ConnectionService connectionService;

	private TagService tagService;

	private ThreadService threadService;

	private PostService postService;

	private ServiceFactory() {

		DaoFactory daoFactory = DaoFactoryImpl.getInstance();

		roleService = new RoleServiceImpl(daoFactory.getRoleDao());
		userService = new UserServiceImpl(roleService, daoFactory.getUserDao());
		registrationService = new RegistrationServiceImpl(userService);
		disconnectionService = new DisconnectionServiceImpl(userService);
		connectionService = new ConnectionServiceImpl(userService);
		tagService = new TagServiceImpl(daoFactory.getTagDao());
		threadService = new ThreadServiceImpl(tagService, daoFactory.getThreadDao());
		postService = new PostServiceImpl(daoFactory.getPostDao());

	}

	public static ServiceFactory getInstance() {

		if(instance == null)
			instance = new ServiceFactory();

		return instance;

	}

	public RoleService getRoleService() {
		return roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	public DisconnectionService getDisconnectionService() {
		return disconnectionService;
	}

	public ConnectionService getConnectionService() {
		return connectionService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public ThreadService getThreadService() {
		return threadService;
	}

	public PostService getPostService() {
		return postService;
	}

}