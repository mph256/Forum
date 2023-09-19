package com.mph.domain.beans;

import java.util.Objects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name="tag")
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(length=30, unique=true, nullable=false, updatable=false)
	private String label;

	public Tag() {
	}

	public Tag(String label) {
		this.label = label;
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

		Tag other = (Tag) obj;

		return id == other.id;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}