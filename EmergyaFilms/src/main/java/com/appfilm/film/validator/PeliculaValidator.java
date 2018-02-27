package com.appfilm.film.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.appfilm.film.model.Pelicula;


@Repository
public class PeliculaValidator {

	public boolean validate(Pelicula peli) {
		boolean isValid = false;
		try {
			if (peli.getTitle().equals(null) || peli.getTitle().isEmpty() || peli.getDate().equals(null)
					|| peli.getDate().isEmpty() || peli.getGenres().length <= 0 || peli.getGenres().length > 3) {

				System.out.println("No se cumplen los requisitos de inserción de Peliculas --> " + HttpStatus.BAD_REQUEST.getReasonPhrase());
				isValid = false;

			} else {
				System.out.println("Se han cumplido los requisitos de inserción de Peliculas --> " + HttpStatus.ACCEPTED.getReasonPhrase());
				isValid = true;

			}

		} catch (Exception ex) {

			System.err.println("Error en la validacion de campos de Peliculas --> hay campos " + ex.getMessage());

		}
		return isValid;
	}
}