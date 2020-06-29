package com.consulta.credito.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.consulta.credito.model.Cliente;

public interface ClienteService {
	
	List<Cliente> listarClientes();
	Cliente consultaCliente(@PathVariable(value="cpf") String cpf);
	Cliente save(@RequestBody @Validated Cliente cliente);
	Cliente update(@RequestBody @Validated Cliente cliente);
	void delete(@RequestBody @Validated Long id);

}
