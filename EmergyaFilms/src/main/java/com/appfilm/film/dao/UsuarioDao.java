package com.appfilm.film.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Usuario;


@Repository
@Transactional
public class UsuarioDao {
	
	
	@PersistenceContext
	private EntityManager entityManager;
    
	
	/*
     * Almacena el usuario en la base de datos
     */
    public void create(Usuario usuario) {
    	entityManager.persist(usuario);
    	return;
    }
    
    
    public Usuario getById(long id) {
		return entityManager.find(Usuario.class, id);
	}

}
