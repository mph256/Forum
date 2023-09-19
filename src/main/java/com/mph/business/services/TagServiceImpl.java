package com.mph.business.services;

import com.mph.business.services.interfaces.TagService;

import com.mph.business.services.exceptions.LabelAlreadyInUseException;

import com.mph.domain.dao.interfaces.TagDao;

import com.mph.domain.beans.Tag;

public class TagServiceImpl implements TagService {

	private TagDao tagDao;

	TagServiceImpl(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	@Override
	public void checkNewLabel(String label) throws LabelAlreadyInUseException {

		if(isLabelAlreadyInUse(label))
			throw new LabelAlreadyInUseException();

	}

	@Override
	public Tag getTagByLabel(String label) {
		return tagDao.findByLabel(label);
	}

	private boolean isLabelAlreadyInUse(String label) {

		Tag tag = getTagByLabel(label);

		return (tag != null);

	}

}