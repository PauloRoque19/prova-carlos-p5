package com.example.banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.client.dto.ClientDTO;
import com.example.banco.cliente.domain.Client;
import com.example.banco.cliente.domain.ClientEnum;
import com.example.banco.repository.AccountCorrenteRepository;
import com.example.banco.repository.AccountPoupancaRepositoty;
import com.example.banco.repository.ClientRepository;


@Service
public class ClientService {

	@Autowired
	private ClientRepository cRepository;
	
	@Autowired
	private AccountCorrenteRepository acr;
	
	@Autowired
	private AccountPoupancaRepositoty apr;
	
	public Client saveAndUpdate(ClientDTO cDTO) {
		
		
		Client c = new Client();
		if(cDTO.getClientType().equals("0")) {
			c.setClientType(ClientEnum.Fisico);
		}else {
			c.setClientType(ClientEnum.Juridico);
		}
		if(cDTO.getId() != null) {
			c.setId(Long.parseLong(cDTO.getId()));
		}
		
		c.setCnpj(cDTO.getCnpj());
		c.setCpf(cDTO.getCpf());
		c.setEmail(cDTO.getEmail());
		c.setEndereco(cDTO.getEndereco());
		c.setNome(cDTO.getNome());
		c.setPhone(cDTO.getPhone());
		
		return cRepository.save(c);
	}
	
	public Client findById(Long id) {
		return cRepository.findById(id).get();
	}
	
	public void deleteClient(Long id) {	
		cRepository.deleteById(id);
	}
	
	public List<Client> listAll() {
		return cRepository.findAll();
	}
}
