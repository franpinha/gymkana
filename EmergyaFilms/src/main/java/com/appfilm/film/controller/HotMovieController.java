package com.appfilm.film.controller;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.appfilm.film.dao.PeliculaDao;
import com.appfilm.film.model.Pelicula;

@Controller
public class HotMovieController {

	private static final Logger log = Logger.getLogger(HotMovieController.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private PeliculaDao peliDao;




	@RequestMapping(value = "/filtermovie", method = RequestMethod.GET)
	public ResponseEntity<String> consultaMovie(@RequestParam Map<String, String> parametros) {

		ResponseEntity<String> re=null;
	
		String caracter = "'";
		String report = parametros.get("report");
		String startDate = caracter + parametros.get("startDate") + caracter;
		String endDate = caracter + parametros.get("endDate") + caracter;
		Pelicula peli = null;
	
		
		
		try {
		if ( report == null|| report.isEmpty() || startDate.isEmpty()
				 || endDate.isEmpty() || startDate.length() != 12||endDate.length()!=12||!report.equals("hot")||!!report.equals("cold")) {
			log.info("Si no cumplimos las validaciones mostramos error");
			re = new ResponseEntity<>("Error de campo erroneo", HttpStatus.BAD_REQUEST);
		}
			if (parametros.get("report").compareTo("hot")==0) {
				long idReturn = peliDao.getByFechaHot(startDate, endDate);
				peli = peliDao.getById(idReturn);
				re = new ResponseEntity<>(peli.getTitle(), HttpStatus.OK);
				log.info("Devolvemos la pelicula mas votada");
			}
			
				if (parametros.get("report").compareTo("cold")==0) {
					long idReturn2 = peliDao.getByFechaCold(startDate, endDate);
					peli = peliDao.getById(idReturn2);
					re = new ResponseEntity<>(peli.getTitle(), HttpStatus.OK);
					log.info("Devolvemos la pelicula menos votada");
				}

			
		
		}catch(NullPointerException ex) {
			ex.getMessage();
			re = new ResponseEntity<>("Error de campos erroneos o nulos", HttpStatus.BAD_REQUEST);
			
			
		}
		
		return re;
	}
	
	
}
