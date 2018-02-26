package com.appfilm.film.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Pelicula;
import com.appfilm.film.model.Usuario;

@Repository
@Transactional
public class PeliculaDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	/*
     * Almacena la pelicula en la base de datos
     */
    public void create(Pelicula pelicula) {
    	entityManager.persist(pelicula);
    	return;
    }
    
}
