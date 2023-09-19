package com.mph.domain.beans;

import java.util.Set;
import java.util.LinkedHashSet;

import java.util.Date;

import java.util.Objects;

import java.io.File;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.OrderBy;

import com.mph.domain.util.Util;

@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(length=30, unique=true, nullable=false)
	private String login;

	@Column(length=320, unique=true, nullable=false)
	private String email;

	@Column(length=30, nullable=false)
	private String password;

	@Transient
	private File profilePicture;

	@Temporal(TemporalType.DATE)
	@Column(name="registration_date", nullable=false, updatable=false)
	private Date registrationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_connection", nullable=false)
	private Date lastConnection;

	@Column(name="is_online", nullable=false)
	private boolean isOnline;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="role_id", nullable=false)
	private Role role;

	@OneToMany(targetEntity=Thread.class, mappedBy="user", orphanRemoval=true, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@OrderBy("publicationDate DESC")
	private Set<Thread> threads;

	@OneToMany(targetEntity=Post.class, mappedBy="user", orphanRemoval=true, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@OrderBy("publicationDate DESC")
	private Set<Post> posts;

	public User() {
	}

	public User(String login, String email, String password) {

		this.login = login;
		this.email = email;
		this.password = password;

		registrationDate = new Date();
		lastConnection = new Date();

		profilePicture = Util.getProfilePictureByLogin(login);

		isOnline = true;

		threads = new LinkedHashSet<Thread>();
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

		User other = (User) obj;

		return id == other.id;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public File getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(File profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Thread> getThreads() {
		return threads;
	}

	public void setThreads(Set<Thread> threads) {
		this.threads = threads;
	}

	public void addThread(Thread thread) {
		threads.add(thread);
	}

	public void removeThread(Thread thread) {
		threads.remove(thread);
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