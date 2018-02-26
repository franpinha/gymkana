package com.appfilm.film.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Rating;

@Repository
@Transactional
public class RatingDao {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
     * Almacena el rating en la base de datos
     */
    public void create(Rating rating) {
    	entityManager.persist(rating);
    	return;
    }
}
