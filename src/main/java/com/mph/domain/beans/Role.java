package com.mph.domain.beans;

import java.util.Objects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
@Table(name="role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum RoleEnum {

		USER

	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(length=30, nullable=false)
	private RoleEnum name;

	public Role() {
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

		Role other = (Role) obj;

		return id == other.id;

	}

	public Role(RoleEnum name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

}