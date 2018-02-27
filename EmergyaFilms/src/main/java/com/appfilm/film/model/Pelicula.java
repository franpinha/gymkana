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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column
	private boolean isAdult;

	@Column(name = "date")
	private String date;

	@Column(name = "genres")
	private String[] genres;

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

}
