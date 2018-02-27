package com.appfilm.film.validator;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Usuario;

@Repository
public class UsuarioValidator {

	public boolean validate(Usuario usu) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFormateada = sdf.format(usu.getBirthDate());

		boolean isValid = false;
		try {
			if (usu.getName().equals(null) || usu.getName().isEmpty() || usu.getSurname().equals(null)
					|| usu.getSurname().isEmpty() || usu.getBirthDate().equals(null)
					|| fechaFormateada.length() != 10) {

				System.out.println("No se cumplen los requisitos de inserción de Usuarios --> "
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());
				isValid = false;

			} else {
				System.out.println("Se han cumplido los requisitos de inserción de Usuarios --> "
						+ HttpStatus.ACCEPTED.getReasonPhrase());
				isValid = true;

			}

		} catch (Exception ex) {

			System.err.println("Error en la validacion de campos de Usuarios --> hay campos " + ex.getMessage());

		}

		return isValid;
	}
}
