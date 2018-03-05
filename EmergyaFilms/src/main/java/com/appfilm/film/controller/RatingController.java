package com.appfilm.film.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appfilm.film.dao.RatingDao;
import com.appfilm.film.model.Rating;
import com.appfilm.film.validator.RatingValidator;


@RestController
public class RatingController {

	private static final Logger log = Logger.getLogger(RatingController.class);

	@Autowired
	private RatingValidator rtng;

	@Autowired
	private RatingDao ratingDao;

	@RequestMapping(value = "/rating_nuevo", method = RequestMethod.POST)
	public ResponseEntity<Rating> create(@RequestBody Rating rating)  {

		ResponseEntity<Rating> re = null;

		if (rating != null) {
			log.info("vamos a hacer la prueba de Rating");
			try {
				if (rtng.validate(rating)) {

					ratingDao.create(rating);

					re = new ResponseEntity<>(rating, HttpStatus.OK);
					log.info("Rating creado");

				} else {

					re = new ResponseEntity<>(rating, HttpStatus.BAD_REQUEST);
					log.error("Rating no creado");
				}
			} catch (NullPointerException ex) {
				log.info("Error de campos nulos o incorrectos capturado");
				re = new ResponseEntity<>(rating, HttpStatus.BAD_REQUEST);
				rating.setMessageRatingJson("Debe introducir un formato de inserción correcto para los ratings");
			}

		}
		return re;

	}
}
