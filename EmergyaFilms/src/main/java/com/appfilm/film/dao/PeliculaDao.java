package com.appfilm.film.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Pelicula;

@Repository
@Transactional
public class PeliculaDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @param pelicula
	 * @return void Almacena la pelicula en la base de datos
	 */
	public void create(Pelicula pelicula) {
		entityManager.persist(pelicula);
		return;
	}

	/**
	 * 
	 * @param id
	 * @return Pelicula Buscamos la pelicula mediante su id a través del método
	 *         find().
	 */
	public Pelicula getById(long id) {
		return entityManager.find(Pelicula.class, id);
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return long Método para obtener la pelicula con más rating en un rango de
	 *         tiempo. Mediante una consulta SQL lo obtenemos.
	 */
	public long getByFechaHot(String startDate, String endDate) {
		long idReturn = 0;
		Query query = entityManager.createNativeQuery("SELECT avg(score) as rating,movieId FROM Rating where( date > "
				+ startDate + " and date < " + endDate + ") group by movieId  order by rating desc limit 0,1");

		List<Object[]> peliculas = query.getResultList();

		for (Object[] pelicula : peliculas) {

			// Previa comprobacion sabemos que la posicion[0] es la media y la [1] la peli
			Number id = (Number) pelicula[1];
			idReturn = id.longValue();

		}
		return idReturn;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return long Método para obtener la pelicula con menos rating en un rango de
	 *         tiempo. Mediante una consulta SQL lo obtenemos.
	 */
	public long getByFechaCold(String startDate, String endDate) {
		long idReturn = 0;
		Query query = entityManager.createNativeQuery("SELECT avg(score) as rating,movieId FROM Rating where( date > "
				+ startDate + " and date < " + endDate + ") group by movieId  order by rating asc limit 0,1");
		List<Object[]> peliculas = query.getResultList();

		for (Object[] pelicula : peliculas) {

			Number id = (Number) pelicula[1];
			idReturn = id.longValue();

		}
		return idReturn;
	}

}
