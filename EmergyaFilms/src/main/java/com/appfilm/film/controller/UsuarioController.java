package com.appfilm.film.controller;

import org.apache.log4j.Logger;
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
	
	private static final Logger log = Logger.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioValidator usu;

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping(value = "/usuario_nuevo", method = RequestMethod.POST)
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		ResponseEntity<Usuario> re = null;

		if (usuario != null) {
			log.info("vamos a hacer la prueba");
try {
			if (usu.validate(usuario)) {
				usuarioDao.create(usuario);

				re = new ResponseEntity<>(usuario, HttpStatus.OK);
				log.info("Usuario creado");

			} else {

				re = new ResponseEntity<>(usuario, HttpStatus.BAD_REQUEST);
				log.info("Usuario no creado");
			}
			
		}catch(NullPointerException ex) {
			log.info ("Error de campos nulos o incorrectos capturado");
			re = new ResponseEntity<>(usuario, HttpStatus.BAD_REQUEST);
			usuario.setMessageUsuarioJson("Debe introducir un formato de inserción correcto para los usuarios");
		}
		}
		
		return re;
	}

}
