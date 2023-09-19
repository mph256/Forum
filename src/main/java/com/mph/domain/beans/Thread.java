package com.mph.domain.beans;

import java.util.Set;
import java.util.LinkedHashSet;

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
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OrderBy;

@Entity
@Table(name="thread")
public class Thread implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(length=140, nullable=false)
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="publication_date", nullable=false, updatable=false)
	private Date publicationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable=false)
	private Date lastUpdate;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id", nullable=false, updatable=false)
	private User user;

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(name="thread_tag", joinColumns=@JoinColumn(name="thread_id"), inverseJoinColumns=@JoinColumn(name="tag_id"))
	@OrderBy("label")
	private Set<Tag> tags;

	@OneToMany(targetEntity=Post.class, mappedBy="thread", orphanRemoval=true, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@OrderBy("publicationDate")
	private Set<Post> posts;

	public Thread() {
	}

	public Thread(String title, User user, Set<Tag> tags) {

		this.title = title;
		this.user = user;
		this.tags = tags;

		publicationDate = new Date();
		lastUpdate = new Date();

		posts = new LinkedHashSet<Post>();

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

		Thread other = (Thread) obj;

		return id == other.id;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post post) {
		posts.add(post);
	}

	public void removePost(Post post) {
		posts.remove(post);
	}

}