package com.mph.application.listeners;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.DisconnectionService;

import com.mph.domain.beans.User;

public class SessionListener implements HttpSessionListener {

	private static final Logger logger = LogManager.getLogger(SessionListener.class);

	private DisconnectionService disconnectionService = ServiceFactory.getInstance().getDisconnectionService();

	@Override
	public void sessionCreated(HttpSessionEvent event) {

		HttpSession session = event.getSession();

		logger.info("New session: " + session.getId());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {	

		HttpSession session = event.getSession();
		User user = (User) session.getAttribute("user");

		if(user != null) {

			disconnectionService.disconnectUser(user);

			logger.info("User disconnection: " + user.getLogin() + " | End of session: " + session.getId());

		} else
			logger.info("End of session: " + session.getId());

	}

}