package com.appfilm.film.validator;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.appfilm.film.model.Usuario;

@Repository
public class UsuarioValidator {

	private static final Logger log = Logger.getLogger(UsuarioValidator.class);

	private boolean fechaValida = false;

	/**
	 * 
	 * @param usu
	 * @return boolean Método para validar el formato de fecha a insertar en el
	 *         usuario.
	 */
	public boolean validarFormatoFechaUsu(Usuario usu) {

		Date fecha = usu.getBirthDate();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFormateada = sdf.format(fecha);
		String corteAnio = fechaFormateada.substring(0, 4);
		int anio = Integer.parseInt(corteAnio);
		String guion1 = fechaFormateada.substring(4, 5);
		String guion2 = fechaFormateada.substring(7, 8);
		String corteMes = fechaFormateada.substring(5, 7);
		int mes = Integer.parseInt(corteMes);
		String corteDias = fechaFormateada.substring(8, fechaFormateada.length());
		int dias = Integer.parseInt(corteDias);

		if (anio > 2018 || anio < 1900 || !guion1.equals("-") || !guion2.equals("-") || mes > 12 || mes < 1 || dias > 31
				|| dias < 1) {
			log.info("El año no es valido");
			fechaValida = false;
		} else {
			fechaValida = true;
		}
		return fechaValida;
	}

	/**
	 * 
	 * @param usu
	 * @return boolean Método para comprobar si el usuario a insertar cumple los
	 *         requisitos, si los cumple lanza un HttpStatus.ACCEPTED y si no un
	 *         HttpStatus.BAD_REQUEST
	 */
	public boolean validate(Usuario usu) {

		validarFormatoFechaUsu(usu);

		boolean isValid = false;
		try {
			if (usu.getName() == null || usu.getName().isEmpty() || usu.getSurname() == null
					|| usu.getSurname().isEmpty() || usu.getBirthDate() == null || !fechaValida) {

				log.info("No se cumplen los requisitos de inserción de Usuarios --> "
						+ HttpStatus.BAD_REQUEST.getReasonPhrase());
				usu.setMessageUsuarioJson(
						"No se ha insertado el usuario por no cumplir los requisitos, revise los campos --> "
								+ HttpStatus.BAD_REQUEST.getReasonPhrase());

			} else {
				log.info("Se han cumplido los requisitos de inserción de Usuarios --> "
						+ HttpStatus.ACCEPTED.getReasonPhrase());
				isValid = true;
				usu.setMessageUsuarioJson("Inserción de usuario correcta " + HttpStatus.ACCEPTED.getReasonPhrase());
			}

		} catch (Exception ex) {

			log.info("Error en la validacion de campos de Usuarios --> hay campos " + ex.getMessage());

			log.info("Error en la validacion de campos de Usuarios --> hay campos " + ex.getMessage());

			usu.setMessageUsuarioJson("hay campos nulos" + ex.getMessage());

		}

		return isValid;
	}

}
