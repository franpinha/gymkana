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



@RestController
public class PeliculaController {
	
	
	@Autowired
	private PeliculaDao peliculaDao;
	
	
	@RequestMapping(value = "/pelicula_nueva", method = RequestMethod.POST)
	public ResponseEntity<Pelicula> create(@RequestBody Pelicula pelicula) {
		


		if (pelicula != null) {
			
			peliculaDao.create(pelicula);
		
			//System.out.println(pelicula.getGenres()[0]);
			// Logger.getLogger(getClass().getName()).log(
			// Level.INFO, "Usuario creado correctamente");
			// }else {

			// Logger.getLogger(getClass().getName()).log(
			// Level.INFO, "Usuario no creado");
		}

		return new ResponseEntity<Pelicula>(pelicula, HttpStatus.OK);
	}


	
}

