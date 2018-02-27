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
import com.appfilm.film.validator.UsuarioValidator;


@RestController
public class UsuarioController {

	@Autowired
	private UsuarioValidator usu;

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping(value = "/usuario_nuevo", method = RequestMethod.POST)
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario)   {
		ResponseEntity<Usuario> re = null;

		if (usuario != null) {
			System.out.println("vamos a hacer la prueba");
			
				if (usu.validate(usuario) == true) {
					usuarioDao.create(usuario);

					re = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
					System.err.println("Usuario creado");

				} else {

					re = new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
					System.err.println("Usuario no creado");
				}
			
		}
		return re;
	}

}
