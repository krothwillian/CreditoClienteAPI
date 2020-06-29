package com.consulta.credito.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consulta.credito.model.Cliente;
import com.consulta.credito.repository.ClienteRepository;
import com.consulta.credito.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
    ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente consultaCliente(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

	@Override
	public Cliente save(Cliente cliente) {
		String resultado = "Negado"; // Aprovado
		String limite = "";		
		
		// Faz a conta com 30% da renda total
		Float reducao30 = (float) (cliente.getRenda() * 0.3);
		
		// Regra limitada até 5 dependentes
		Integer dependentes = cliente.getDependentes();
		Integer limiteDependente = dependentes == 5 ? 5 : dependentes < 5 ? dependentes : 0;	

		Float rendaFinal = reducao30;
		
		// Taxa - Casado são 2,5% cada dependente
		if ("Casado".equalsIgnoreCase(cliente.getEstadoCivil())) {
			 rendaFinal = reducao30 - (float) ((cliente.getRenda() * 0.025) * limiteDependente);
		} else {
			// os demais são R$200 cada dependente
			rendaFinal = reducao30 - (float) (limiteDependente * 200);
		}
		
		if (cliente.getRenda() <= 500) {
			limite = "Renda baixa";
		} else if (("Divorciado".equalsIgnoreCase(cliente.getEstadoCivil()) && rendaFinal <= 400) || rendaFinal < 100) {
			limite = "Reprovado pela política de crédito";
		} else if (rendaFinal >= 2000) {
			limite = "Superior 2000"; 
			resultado = "Aprovado";
		} else if (rendaFinal >= 1500 && rendaFinal < 2000) {
			limite = "Entre 1500 - 2000";
			resultado = "Aprovado";
		} else if (rendaFinal >= 1000 && rendaFinal < 1500) {
			limite = "Entre 1000 - 1500";
			resultado = "Aprovado";
		} else if (rendaFinal >= 500 && rendaFinal < 1000) {
			limite = "Entre 500 - 1000";
			resultado = "Aprovado";
		} else if (rendaFinal >= 100 && rendaFinal < 500) {
			limite = "Entre 100 - 500";
			resultado = "Aprovado";
		}
				
		cliente.setResultado(resultado);
		cliente.setLimite(limite);
		
		return clienteRepository.save(cliente);
	}
	
	@Override
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void delete(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		clienteRepository.delete(cliente.get());
	}
}
