package com.mph.application.actions.connection;

import java.util.Map;

import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.ConnectionService;

import com.mph.business.services.exceptions.InvalidLoginException;
import com.mph.business.services.exceptions.InvalidPasswordException;

import com.mph.domain.beans.User;

public class ConnectionAction extends ActionSupport implements ApplicationAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(ConnectionAction.class);

	private String login;

	private String password;

	private ConnectionService connectionService;

	private Map<String, Object> session;

	@Override
	public void withApplication(Map<String, Object> application) {

		connectionService = (ConnectionService) application.get("connectionService");

		if(connectionService == null) {

			connectionService = ServiceFactory.getInstance().getConnectionService();
			application.put("connectionService", connectionService);

		}

	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void validate() {

		try {

			User user = connectionService.checkUser(login, password);

			session.put("user", user);

		} catch (InvalidLoginException e) {
			addFieldError("login", getText("error.login"));
		} catch(InvalidPasswordException e) {
			addFieldError("password", getText("error.password"));
		}

	}

	@Override
	public String execute() {

		User user = (User) session.get("user");

		user = connectionService.connectUser(user);

		logger.info("User connection: " + user.getLogin());

		session.put("user", user);

		return SUCCESS;

	}

	@SkipValidation
	public String loadPage() {

		if(session.get("user") != null)
			return SUCCESS;

		return INPUT;

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}