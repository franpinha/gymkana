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

	/**
	 * @param usuario
	 *            Almacena el usuario en la base de datos
	 */
	public void create(Usuario usuario) {
		entityManager.persist(usuario);
		return;
	}

	/**
	 * 
	 * @param id
	 * @return Usuario Buscamos la pelicula mediante su id a través del método
	 *         find().
	 */
	public Usuario getById(long id) {
		return entityManager.find(Usuario.class, id);
	}

}
