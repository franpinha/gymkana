package com.appfilm.film.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pelicula {

	public Pelicula(long id) {
		super();
		this.id = id;
	}

	public Pelicula(long id, String title, boolean isAdult, String date, String[] genres, String messagePeliculaJson) {
		super();
		this.id = id;
		this.title = title;
		this.isAdult = isAdult;
		this.date = date;
		this.genres = genres;
		this.messagePeliculaJson = messagePeliculaJson;
	}

	public Pelicula() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column
	private boolean isAdult;

	@Column(name = "date")
	private String date;

	@Column(name = "genres")
	private String[] genres;

	private String messagePeliculaJson;

	@OneToMany(mappedBy = "movieId", fetch = FetchType.EAGER)
	private Set<Rating> ratingS = new HashSet<>();

	public Set<Rating> getRatingS() {
		return ratingS;
	}

	public void setRatingS(Set<Rating> ratingS) {
		this.ratingS = ratingS;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String getMessagePeliculaJson() {
		return messagePeliculaJson;
	}

	public void setMessagePeliculaJson(String messagePeliculaJson) {
		this.messagePeliculaJson = messagePeliculaJson;
	}

}
