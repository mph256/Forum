package com.mph.business.services.interfaces;

import java.util.Set;

import com.mph.domain.beans.Thread;
import com.mph.domain.beans.User;
import com.mph.domain.beans.Tag;

public interface ThreadService {

	/**
	 * Creates a new thread.
	 * 
	 * <br>
	 * Argument validity checks must be performed before this method is called.
	 * 
	 * @param title the title entered
	 * @param user the user
	 * @param tags the tags entered
	 *
	 * @return the new thread created
	 */
	public Thread createThread(String title, User user, Set<String> tags);

	/**
	 * Updates an existing thread.
	 * 
	 * @param Thread the thread to update
	 */
	public void updateThread(Thread Thread);

	/**
	 * Deletes an existing thread.
	 * 
	 * @param Thread the thread to delete
	 */
	public void deleteThread(Thread Thread);

	public Thread getThreadById(long id);
	
	public Set<Thread> getThreadsByTags(Set<Tag> tags);

	public Set<Thread> getThreads();

}