package com.clientesapp.clientesapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	private final Connection connection;
	private ResultSet rs;
	
	private Usuario usuario;

	public UsuarioDAO() throws SQLException {
		
		this.connection = FabricaDeConexoes.getConnection();
		
	}
	
	public Usuario buscarPorNome(String nome) {
		
		String sql = "SELECT * FROM usuarios WHERE nome = '" + nome + "'";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			usuario = new Usuario();
			
			while ( rs.next() ) {
				usuario.setId(rs.getInt("Id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
		    }

			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	
		return usuario;
		
	}
	
	public Usuario cadastrar(Usuario usuario) {
		
		String sql = "INSERT INTO usuarios (nome, senha) VALUES(?, ?)";
	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// create the mysql insert preparedstatement
			stmt.setString (1, usuario.getNome());
			stmt.setString (2, usuario.getSenha());
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return usuario;
		
	}
	
}
