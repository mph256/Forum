package com.mph.application.actions.registration;

import java.util.Map;

import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.RegistrationService;

import com.mph.business.services.exceptions.LoginAlreadyInUseException;
import com.mph.business.services.exceptions.EmailAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidPasswordConfirmationException;

import com.mph.domain.beans.User;

public class RegistrationAction extends ActionSupport implements ApplicationAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(RegistrationAction.class);

	private String login;

	private String email;

	private String password;

	private String passwordConfirmation;

	private RegistrationService registrationService;

	private Map<String, Object> session;

	@Override
	public void withApplication(Map<String, Object> application) {

		registrationService = (RegistrationService) application.get("registrationService");

		if(registrationService == null) {

			registrationService = ServiceFactory.getInstance().getRegistrationService();
			application.put("registrationService", registrationService);

		}

	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session= session;
	}

	@Override
	public void validate() {

		if(getFieldErrors().isEmpty()) {

			try {

				User user = registrationService.checkUser(login, email, password, passwordConfirmation);

				session.put("user", user);

			} catch (LoginAlreadyInUseException e) {
				addFieldError("login", getText("error.exists.login"));
			} catch (EmailAlreadyInUseException e) {
				addFieldError("email", getText("error.exists.email"));
			} catch (InvalidPasswordConfirmationException e) {
				addFieldError("password", getText("error.password.confirmation"));
			}

		}

	}

	@Override
	public String execute() {

		User user = (User) session.get("user");

		user = registrationService.registerUser(user);

		logger.info("User registration: " + user.getLogin());

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}