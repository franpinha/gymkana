package com.appfilm.film.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

	public Usuario(long id) {
		super();
		this.id = id;
	}

	public Usuario(long id, String name, String surname, Date birthDate, String messageUsuarioJson) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.messageUsuarioJson = messageUsuarioJson;
	}

	public Usuario() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
	private Set<Rating> ratingS = new HashSet<>();

	private String messageUsuarioJson;

	public Set<Rating> getRatingS() {
		return ratingS;
	}

	public void setRatingS(Set<Rating> ratingS) {
		this.ratingS = ratingS;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMessageUsuarioJson() {
		return messageUsuarioJson;
	}

	public void setMessageUsuarioJson(String messageUsuarioJson) {
		this.messageUsuarioJson = messageUsuarioJson;
	}

}