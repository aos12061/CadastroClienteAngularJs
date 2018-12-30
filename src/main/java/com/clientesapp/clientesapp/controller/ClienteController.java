package com.clientesapp.clientesapp.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clientesapp.clientesapp.model.Cliente;
import com.clientesapp.clientesapp.model.ClienteDAO;

@RestController
@RequestMapping("/admin")
public class ClienteController {

	@RequestMapping(value="/cadcli", method=RequestMethod.POST)
	public Cliente cadastrarCliente(@RequestBody Cliente cliente)  throws SQLException {
		ClienteDAO dao = new ClienteDAO();		
		return dao.salvar(cliente);
	}

	@RequestMapping(value="/clientes", method=RequestMethod.PUT)
	public Cliente alterarCliente(@RequestBody Cliente cliente)  throws SQLException {
		ClienteDAO dao = new ClienteDAO();		
		return dao.alterar(cliente);
	}
	
	@RequestMapping(value="/liscli", method=RequestMethod.GET)
	public List<Cliente> listarClientes() throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		return dao.listar();
	}
	
	@RequestMapping(value="/clientes/{Id}", method=RequestMethod.GET)
	public Cliente buscarClientePorId(@PathVariable("Id") Integer Id) throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		return dao.buscar(Id);		
	}
	
	@RequestMapping(value="/clientes/{Id}", method=RequestMethod.DELETE)
	public void excluirCliente(@PathVariable("Id") Integer Id) throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		dao.remove(Id);
	}

	@RequestMapping(value="/cliente/{Id}", method=RequestMethod.GET)
	public Cliente buscarCliente(@PathVariable("Id") Integer Id) throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		return dao.buscar(Id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDAO> cadCliente(@RequestBody Cliente cliente) throws SQLException {
		ClienteDAO cliDAO = new ClienteDAO();
		cliDAO.salvar(cliente);
		return new ResponseEntity<>(cliDAO, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDAO> lisClientes() throws SQLException {
		ClienteDAO cliDAO = new ClienteDAO();
		cliDAO.listar();
		return new ResponseEntity<>(cliDAO, HttpStatus.OK);
	}
}
