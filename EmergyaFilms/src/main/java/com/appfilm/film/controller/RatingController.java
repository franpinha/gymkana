package com.appfilm.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appfilm.film.dao.RatingDao;

import com.appfilm.film.model.Rating;

@RestController
public class RatingController {
	

	@Autowired
	private RatingDao ratingDao;

	@RequestMapping(value = "/rating_nuevo", method = RequestMethod.POST)
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		


		if (rating != null) {
			
			ratingDao.create(rating);
		
			//System.out.println(pelicula.getGenres()[0]);
			// Logger.getLogger(getClass().getName()).log(
			// Level.INFO, "Usuario creado correctamente");
			// }else {

			// Logger.getLogger(getClass().getName()).log(
			// Level.INFO, "Usuario no creado");
		}

		return new ResponseEntity<Rating>(rating, HttpStatus.OK);
	}


	
}
