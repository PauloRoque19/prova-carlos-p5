package com.example.banco.resouce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.account.domain.AccountCorrente;
import com.example.banco.account.domain.AccountPoupanca;
import com.example.banco.client.dto.ClientDTO;
import com.example.banco.cliente.domain.Client;
import com.example.banco.repository.AccountCorrenteRepository;
import com.example.banco.repository.AccountPoupancaRepositoty;
import com.example.banco.service.ClientService;


@RestController
@RequestMapping("/client")
public class ClienteResource {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AccountCorrenteRepository acr;
	
	@Autowired
	private AccountPoupancaRepositoty apr;
	
	@PostMapping()
	public ResponseEntity<?>saveAndUpdate(@RequestBody ClientDTO c){
		
			
		return ResponseEntity.ok(clientService.saveAndUpdate(c));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id ){
		return ResponseEntity.ok(clientService.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(clientService.listAll());
	}
	
	@DeleteMapping("/{id}")
	public void removeClient(@PathVariable Long id){
		Client c = clientService.findById(id);
		clientService.deleteClient(id);
		
	}
}
