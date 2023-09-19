package com.mph.business.services.interfaces;

import com.mph.domain.beans.User;

public interface DisconnectionService {

	/**
	 * Disconnects an existing user.
	 *
	 * @param user the connected user to disconnect
	 */
	public void disconnectUser(User user);

}