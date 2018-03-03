package com.appfilm.film.validator;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;                                                                                                             
import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Usuario;


@Repository
public class UsuarioValidator {
	
	private static final Logger log = Logger.getLogger(UsuarioValidator.class);

	public boolean validate(Usuario usu) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFormateada = sdf.format(usu.getBirthDate());

		
		boolean isValid = false;
		try {
			if (usu.getName() == null || usu.getName().isEmpty() || usu.getSurname() == null
					|| usu.getSurname().isEmpty() || usu.getBirthDate() == null || fechaFormateada.length() != 10) {

				log.error("No se cumplen los requisitos de inserción de Usuarios --> "
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());
				usu.setMessageUsuarioJson("No se ha insertado el usuario por no cumplir los requisitos" + HttpStatus.BAD_REQUEST.getReasonPhrase() );

			} else {
				log.info("Se han cumplido los requisitos de inserción de Usuarios --> "
						+ HttpStatus.ACCEPTED.getReasonPhrase());
				isValid = true;
				usu.setMessageUsuarioJson("Inserción de usuario correcta " + HttpStatus.ACCEPTED.getReasonPhrase() );
			}
			
		
			
		} catch (Exception ex) {
		
			log.error ("Error en la validacion de campos de Usuarios --> hay campos " + ex.getMessage());
			usu.setMessageUsuarioJson("hay campos nulos" + ex.getMessage());

		}

		return isValid;
	}
}
