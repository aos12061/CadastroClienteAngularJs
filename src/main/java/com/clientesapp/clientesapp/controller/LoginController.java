package com.clientesapp.clientesapp.controller;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clientesapp.clientesapp.model.Usuario;
import com.clientesapp.clientesapp.model.UsuarioDAO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	
	Usuario usuario;
	
	@RequestMapping(value="/autenticar", consumes=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws SQLException, ServletException {
		
		if (usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e senha são obrigatórios");
		}
		
		//Consulta no banco
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuAutenticado = dao.buscarPorNome(usuario.getNome().toString());
		
		if (usuAutenticado == null) {
			throw new ServletException("Usuario não encontrado");
		}
		
		if (!usuAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Usuário ou senha inválido");			
		}
		
		//TOKEN
		String token = Jwts.builder()
				.setSubject(usuAutenticado.getNome())
				.signWith(SignatureAlgorithm.HS512, "banana")
				//.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
				.compact();
		
		return new LoginResponse(token);
		
	}

	@RequestMapping(value="/cadastrar", consumes=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public Usuario cadastrar(@RequestBody Usuario usuario) throws SQLException {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.cadastrar(usuario);
		
	}
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
/*
		public String getToken() {
			return token;
		}
*/
	}
	
}
