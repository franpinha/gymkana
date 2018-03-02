package com.appfilm.film.validator;


import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Pelicula;
import com.fasterxml.jackson.core.JsonLocation;






@Repository
public class PeliculaValidator {

	JsonLocation js;
	private static final Logger log = Logger.getLogger(PeliculaValidator.class);
	
	public boolean validate(Pelicula peli)  {
		boolean isValid = false;
		try {
			if (peli.getTitle() == null || peli.getTitle().isEmpty() || peli.getDate() == null
					|| peli.getDate().isEmpty() || peli.getGenres().length <= 0 || peli.getGenres().length > 3) {

			
				log.info("No se cumplen los requisitos de inserción de Peliculas --> "
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());
				peli.setMessagePeliculaJson("No se ha insertado la pelicula por no cumplir los requisitos " + HttpStatus.BAD_REQUEST.getReasonPhrase());

			} else {
				log.info("Se han cumplido los requisitos de inserción de Peliculas --> "
						+ HttpStatus.ACCEPTED.getReasonPhrase());
				isValid = true;
				peli.setMessagePeliculaJson("Inserción de pelicula correcta " + HttpStatus.ACCEPTED.getReasonPhrase());
			}
			
		
		
			
		}catch (Exception ex2) {
			
			log.info("Error en la validacion de campos de Peliculas --> hay campos " + ex2.getMessage());
			peli.setMessagePeliculaJson("hay campos nulos" + ex2.getMessage());
		}
		return isValid;
	}
	
}