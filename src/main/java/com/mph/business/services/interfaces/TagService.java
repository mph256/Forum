package com.mph.business.services.interfaces;

import com.mph.business.services.exceptions.LabelAlreadyInUseException;

import com.mph.domain.beans.Tag;

public interface TagService {

	/** 
	 * Checks the validity of the label entered.
	 * 
	 * @param label the label entered
	 * 
	 * @throws LabelAlreadyInUseException if the entered label is already in use
	 */
	public void checkNewLabel(String label) throws LabelAlreadyInUseException;

	public Tag getTagByLabel(String label);

}