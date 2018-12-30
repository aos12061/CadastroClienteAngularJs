package com.clientesapp.clientesapp.controller;

import java.sql.SQLException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clientesapp.clientesapp.model.Usuario;
import com.clientesapp.clientesapp.model.UsuarioDAO;

@RestController
public class UsuarioController {

	@RequestMapping(value="/usuarios", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Usuario cadastrarUsuario(@RequestBody Usuario usuario)  throws SQLException {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.cadastrar(usuario);
	}
	
}
