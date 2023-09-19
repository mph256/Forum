package com.mph.domain.dao.interfaces;

import com.mph.domain.beans.Tag;

public interface TagDao {

	public Tag findByLabel(String label);

}