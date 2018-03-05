package com.appfilm.film.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appfilm.film.dao.PeliculaDao;
import com.appfilm.film.model.Pelicula;
import com.appfilm.film.validator.PeliculaValidator;

@RestController
public class PeliculaController {

	private static final Logger log = Logger.getLogger(PeliculaController.class);
	@Autowired
	private PeliculaValidator peli;

	@Autowired
	private PeliculaDao peliculaDao;

	Pelicula pelicula = new Pelicula();

	@RequestMapping(value = "/pelicula_nueva", method = RequestMethod.POST)
	public ResponseEntity<Pelicula> create(@RequestBody Pelicula pelicula) {
		ResponseEntity<Pelicula> re = null;

		if (pelicula != null) {
			log.info("vamos a hacer la prueba");
			// Logger.log
			try {
				if (peli.validate(pelicula)) {

					peliculaDao.create(pelicula);

					re = new ResponseEntity<>(pelicula, HttpStatus.OK);
					log.info("Pelicula creada");

				} else {

					re = new ResponseEntity<>(pelicula, HttpStatus.BAD_REQUEST);
					log.info("Pelicula no creada");
				}
			} catch (NullPointerException ex) {
				log.info("Error de campos nulos o incorrectos capturado");
				re = new ResponseEntity<>(pelicula, HttpStatus.BAD_REQUEST);
				pelicula.setMessagePeliculaJson("Debe introducir un formato de inserción correcto para las peliculas");
			}
		}

		return re;

	}

}
