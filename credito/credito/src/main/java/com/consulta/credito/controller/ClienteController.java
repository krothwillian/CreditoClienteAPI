package com.consulta.credito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.consulta.credito.model.Cliente;
import com.consulta.credito.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/consulta")
@CrossOrigin(origins = "*")
@Api(value="Consulta Clientes - API REST")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@ApiOperation(value="Consultar todos os clientes")
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public List<Cliente> listarClientes(){
		return clienteService.listarClientes();
	}
	
	@ApiOperation(value="Consultar um determinado cliente pelo cpf")
	@RequestMapping(value = "/cliente/{cpf}", method = RequestMethod.GET)
	public Cliente consultaCliente(@PathVariable(value="cpf") String cpf){
		return clienteService.consultaCliente(cpf);
	}
	
	@ApiOperation(value="Salvar um cliente")
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public Cliente save(@RequestBody @Validated Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@ApiOperation(value="Excluir um cliente pelo id")
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value="id") Long id) {
		clienteService.delete(id);
	}
	
	@ApiOperation(value="Atualizar um cliente")
	@RequestMapping(value = "/cliente", method = RequestMethod.PUT)
	public Cliente update(@RequestBody @Validated Cliente cliente) {
		return clienteService.save(cliente);
	}

}