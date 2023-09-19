package com.mph.domain.dao.interfaces;

import java.util.Set;

import com.mph.domain.beans.Tag;
import com.mph.domain.beans.Thread;

public interface ThreadDao {

	public Thread add(Thread thread);

	public void update(Thread thread);

	public void delete(Thread thread);

	public Thread findById(long id);

	public Set<Thread> findByTags(Set<Tag> tags);

	public Set<Thread> findAll();

}