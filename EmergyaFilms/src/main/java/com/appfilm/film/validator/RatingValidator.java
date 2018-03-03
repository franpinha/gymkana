package com.appfilm.film.validator;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Rating;

@Repository
public class RatingValidator {

	private static final Logger log = Logger.getLogger(RatingValidator.class);

	public boolean validate(Rating rtng) {

		boolean isValid = false;
		String scoreComparator = String.valueOf(rtng.getScore());

		try {
			if (rtng.getScore() < 1 || rtng.getScore() > 5 || scoreComparator == null || rtng.getUserId() == null
					|| rtng.getUserId().toString().isEmpty() || rtng.getMovieId() == null
					|| rtng.getMovieId().toString().isEmpty() || rtng.getDate() == null) {

				log.info("No se cumplen los requisitos de inserción de Rating --> "
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());
				rtng.setMessageRatingJson("No se ha insertado el rating por no cumplir los requisitos"
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());

			} else {
				log.info("Se han cumplido los requisitos de inserción de Rating --> "
						+ HttpStatus.ACCEPTED.getReasonPhrase());
				isValid = true;
				rtng.setMessageRatingJson("Inserción de rating correcta " + HttpStatus.ACCEPTED.getReasonPhrase());

			}

		} catch (HttpMessageNotReadableException ex) {

			rtng.setMessageRatingJson(ex.getMessage());

		} catch (Exception ex2) {

			log.error("Error en la validacion de campos de Rating --> hay campos " + ex2.getMessage());
			rtng.setMessageRatingJson("hay campos nulos" + ex2.getMessage());
		}

		return isValid;
	}
}
