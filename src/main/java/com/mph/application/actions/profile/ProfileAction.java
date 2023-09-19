package com.mph.application.actions.profile;

import java.util.Map;

import java.io.File;

import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.UserService;

import com.mph.business.services.exceptions.EmailAlreadyInUseException;
import com.mph.business.services.exceptions.InvalidPasswordConfirmationException;

import com.mph.domain.beans.User;

import com.mph.domain.util.Util;

public class ProfileAction extends ActionSupport implements ApplicationAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private static final Logger logger= LogManager.getLogger(ProfileAction.class);

	private String email;

	private String password;

	private String passwordConfirmation;

	private File profilePicture;

    private String profilePictureContentType;

    private String profilePictureFileName;

	private UserService userService;

	private Map<String, Object> session;

	@Override
	public void withApplication(Map<String, Object> application) {

		userService = (UserService) application.get("userService");

		if(userService == null) {

			userService = ServiceFactory.getInstance().getUserService();
			application.put("userService", userService);

		}

	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void validate() {

		if(getFieldErrors().isEmpty()) {

			try {

				User user = (User) session.get("user");

				if(!user.getEmail().equals(email))
					userService.checkNewEmail(email);

				if(!user.getPassword().equals(password))
					userService.checkNewPassword(password, passwordConfirmation);

			} catch (EmailAlreadyInUseException e) {
				addFieldError("email", getText("error.exists.email"));
			} catch (InvalidPasswordConfirmationException e) {
				addFieldError("password", getText("error.password.confirmation"));
			}

		}

	}

	@SkipValidation
	public String loadPage() {

		if(session.get("user") != null)
			return SUCCESS;

		return "forbidden";

	}

	public String editAccount() {

		User user = (User) session.get("user");

		if(user != null) {

			if(profilePicture != null) {

				user.getProfilePicture().delete();

				user.setProfilePicture(Util.updateProfilePicture(profilePicture, profilePictureFileName, user.getLogin()));

			}

			userService.updateUser(user, email, password);

			logger.info("User update: " + user.getLogin());

			session.put("user", user);

			return SUCCESS;

		}

		return "forbidden";

	}

	@SkipValidation
	public String deleteAccount() {

		User user = (User) session.get("user");

		if(user != null) {

			userService.deleteUser(user);

			logger.info("User deletion: " + user.getLogin());

			session.remove("user");

			return "success2";

		}

		return "forbidden";

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

	public File getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(File profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getProfilePictureContentType() {
		return profilePictureContentType;
	}

	public void setProfilePictureContentType(String profilePictureContentType) {
		this.profilePictureContentType = profilePictureContentType;
	}

	public String getProfilePictureFileName() {
		return profilePictureFileName;
	}

	public void setProfilePictureFileName(String profilePictureFileName) {
		this.profilePictureFileName = profilePictureFileName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}