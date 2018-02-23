package com.appfilm.film.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Pelicula {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column
	@Type(type="date")
	private Date anioLanz;
	
	@Column
	private String genero;
}
