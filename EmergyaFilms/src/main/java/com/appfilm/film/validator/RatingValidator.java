package com.appfilm.film.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Rating;



@Repository
public class RatingValidator{

	private static final Logger log = Logger.getLogger(RatingValidator.class);

	private boolean fechaValida=false;
	

	public boolean validarFormatoFechaRtng(Rating rtng) {
		
		Date fecha = rtng.getDate();
		DateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		String fechaFormateada = sdf.format(fecha);
		String corteAnio =  fechaFormateada.substring(0,4);
		int anio = Integer.parseInt(corteAnio);
		String guion1 = fechaFormateada.substring(4,5);
		String guion2 = fechaFormateada.substring(7, 8);
		String corteMes = fechaFormateada.substring(5, 7);
		int mes = Integer.parseInt(corteMes);
		String corteDias = fechaFormateada.substring(8,fechaFormateada.length());
		int dias =  Integer.parseInt(corteDias);
		
		
		if(anio> 2018 || anio <1900 || !guion1.equals("-")|| !guion2.equals("-")||mes>12||mes<1||dias>31||dias <1 ) {
			log.info("El año no es valido");
			fechaValida=false;
		}else {
			fechaValida=true;
		}
		return fechaValida;
	}
	
	public boolean validate(Rating rtng)  {
		validarFormatoFechaRtng(rtng);
		boolean isValid = false;
		String scoreComparator = String.valueOf(rtng.getScore());
		

		try {
			if (rtng.getScore() < 1 || rtng.getScore() > 5 || scoreComparator == null || rtng.getUserId() == null
					|| rtng.getUserId().toString().isEmpty() || rtng.getMovieId() == null
					|| rtng.getMovieId().toString().isEmpty() || rtng.getDate() == null || !fechaValida) {

				
				log.info("No se cumplen los requisitos de inserción de Rating --> "
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());
				rtng.setMessageRatingJson("No se ha insertado el rating por no cumplir los requisitos, revise los campos --> "
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());

			} else {
				log.info("Se han cumplido los requisitos de inserción de Rating --> "
						+ HttpStatus.ACCEPTED.getReasonPhrase());
				isValid = true;
				rtng.setMessageRatingJson("Inserción de rating correcta " + HttpStatus.ACCEPTED.getReasonPhrase());

			}
		}catch(DataIntegrityViolationException ex3) {
			log.info(ex3.getMessage());
			rtng.setMessageRatingJson("Error de MySQL , el usuario o la pelicula no existe en BD " +HttpStatus.BAD_REQUEST);
		}catch (HttpMessageNotReadableException ex) {

			rtng.setMessageRatingJson(ex.getMessage());

		} catch (Exception ex2) {

			log.error("Error en la validacion de campos de Rating --> hay campos " + ex2.getMessage());
			rtng.setMessageRatingJson("hay campos nulos" + ex2.getMessage());
		}

		return isValid;
	}



	
}
