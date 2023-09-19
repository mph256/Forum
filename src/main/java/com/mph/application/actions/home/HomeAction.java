package com.mph.application.actions.home;

import java.util.Map;
import java.util.Set;

import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import com.mph.business.services.ServiceFactory;

import com.mph.business.services.interfaces.ThreadService;

import com.mph.domain.beans.Thread;

public class HomeAction extends ActionSupport implements ApplicationAware, SessionAware {

	private static final long serialVersionUID = 1L;

	private Set<Thread> threads;

	private ThreadService threadService;

	private Map<String, Object> session;

	@Override
	public void withApplication(Map<String, Object> application) {

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

	@Override
	public void validate() {

		if(!getFieldErrors().isEmpty())
			threads = threadService.getThreads();

	}

	@Override
	public String execute() {

		threads = threadService.getThreads();

		return SUCCESS;

	}

	public Set<Thread> getThreads() {
		return threads;
	}

	public void setThreads(Set<Thread> threads) {
		this.threads = threads;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}