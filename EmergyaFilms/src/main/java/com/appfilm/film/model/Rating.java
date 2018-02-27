package com.appfilm.film.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	@ManyToOne
	@JoinColumn(name="user_id",updatable=false)
	private Usuario user_id;
	
	
	
	@ManyToOne
	@JoinColumn (name="movieId",updatable=false)
	private Pelicula movieId;
	
	@Column
	@Range(min=1,max=5)
	private int score;
	
	@Column
	@CreationTimestamp
	 private Date date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUser_id() {
		return user_id;
	}

	public void setUser_id(Usuario user_id) {
		this.user_id = user_id;
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

	
}