package com.appfilm.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appfilm.film.dao.UsuarioDao;
import com.appfilm.film.model.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping(value = "/usuario_nuevo", method = RequestMethod.POST)
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {



		if (usuario != null) {
			
			usuarioDao.create(usuario);
			// Logger.getLogger(getClass().getName()).log(
			// Level.INFO, "Usuario creado correctamente");
			// }else {

			// Logger.getLogger(getClass().getName()).log(
			// Level.INFO, "Usuario no creado");
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
}
