package com.appfilm.film.controller;

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

	@Autowired
	private PeliculaValidator peli;

	@Autowired
	private PeliculaDao peliculaDao;

	@RequestMapping(value = "/pelicula_nueva", method = RequestMethod.POST)
	public ResponseEntity<Pelicula> create(@RequestBody Pelicula pelicula)  {
		ResponseEntity<Pelicula> re = null;
		if (pelicula != null) {
			System.out.println("vamos a hacer la prueba");
			if (peli.validate(pelicula) == true) {

				peliculaDao.create(pelicula);

				re = new ResponseEntity<Pelicula>(pelicula, HttpStatus.OK);
				System.err.println("Pelicula creada");

			} else {

				re = new ResponseEntity<Pelicula>(pelicula, HttpStatus.BAD_REQUEST);
				System.err.println("Pelicula no creada");
			}

		}
		return re;

	}

}
