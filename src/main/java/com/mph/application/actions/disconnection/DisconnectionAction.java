package com.mph.application.actions.disconnection;

import java.util.Map;

import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.DisconnectionService;

import com.mph.domain.beans.User;

public class DisconnectionAction extends ActionSupport implements ApplicationAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private DisconnectionService disconnectionService;

	private Map<String, Object> session;

	@Override
	public void withApplication(Map<String, Object> application) {

		disconnectionService = (DisconnectionService) application.get("disconnectionService");

		if(disconnectionService == null) {

			disconnectionService = ServiceFactory.getInstance().getDisconnectionService();
			application.put("disconnectionService", disconnectionService);

		}

	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() {

		User user = (User) session.get("user");

		if(user != null) {

			disconnectionService.disconnectUser(user);

			session.remove("user");

		}

		return SUCCESS;

	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}