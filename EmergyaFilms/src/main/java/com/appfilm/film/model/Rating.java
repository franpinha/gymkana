package com.appfilm.film.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Range;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "userId", updatable = false)
	private Usuario userId;

	@ManyToOne
	@JoinColumn(name = "movieId", updatable = false)
	private Pelicula movieId;

	@Column
	@Range(min = 1, max = 5)
	private int score;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	private String messageRatingJson;

	
	
	public Rating(long id) {
		super();
		this.id = id;
	}

	public Rating() {
	}

	public Rating(long id, Usuario userId, Pelicula movieId, int score, Date date, String messageRatingJson) {
		super();
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.score = score;
		this.date = date;
		this.messageRatingJson = messageRatingJson;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUserId() {
		return userId;
	}

	public void setUserId(Usuario userId) {
		this.userId = userId;
	}

	public Pelicula getMovieId() {
		return movieId;
	}

	public void setMovieId(Pelicula movieId) {
		this.movieId = movieId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessageRatingJson() {
		return messageRatingJson;
	}

	public void setMessageRatingJson(String messageRatingJson) {
		this.messageRatingJson = messageRatingJson;
	}

}