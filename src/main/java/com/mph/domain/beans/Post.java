package com.mph.domain.beans;

import java.util.Date;

import java.util.Objects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

@Entity
@Table(name="post")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(length=280, nullable=false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="publication_date", nullable=false, updatable=false)
	private Date publicationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable=false)
	private Date lastUpdate;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="thread_id", nullable=false, updatable=false)
	private Thread thread;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id", nullable=false, updatable=false)
	private User user;

	public Post() {
	}

	public Post(String content, Thread thread, User user) {

		this.content = content;
		this.thread = thread;
		this.user = user;

		publicationDate = new Date();
		lastUpdate = new Date();

	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {

		if(this == obj)
			return true;

		if(obj == null)
			return false;

		if(getClass() != obj.getClass())
			return false;

		Post other = (Post) obj;

		return id == other.id;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}