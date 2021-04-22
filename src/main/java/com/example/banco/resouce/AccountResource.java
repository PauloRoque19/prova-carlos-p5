package com.example.banco.resouce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.account.domain.Account;
import com.example.banco.account.dto.AccountDTO;
import com.example.banco.account.dto.SaqueDTO;
import com.example.banco.account.dto.TransferenciaDTO;
import com.example.banco.cliente.domain.Client;
import com.example.banco.service.AccountService;
import com.example.banco.service.ClientService;

@RequestMapping("/account")
@RestController
public class AccountResource {
	
	@Autowired
	private AccountService acs;
	
	@Autowired
	private ClientService cs;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AccountDTO aDto ) {
		if(aDto == null) {
			throw new RuntimeException("Account incompleta");
		}
		
		Client c = cs.findById(Long.parseLong(aDto.getClienteid()));		
		return ResponseEntity.ok(acs.saveOrUpdate(aDto , c));
				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findAccount(@PathVariable Long id , @RequestParam(required = true , defaultValue = "c") String type){	
		return ResponseEntity.ok(acs.findAccount(id, type));
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> listAllAccount(){
		return ResponseEntity.ok(acs.listAllAccount());
	}
	
	
	@DeleteMapping("/{id}")
	public void removeAccount(@PathVariable Long id ,  @RequestParam(required = true , defaultValue = "c")String type) {
		acs.removeAccount(id, type);
	}
	
	@PostMapping("/sacar")
	public ResponseEntity<?> saque(@RequestBody SaqueDTO sDTO){
		return ResponseEntity.ok(acs.saque(sDTO));
	}
	
	@PostMapping("/transferencia")
	public ResponseEntity<?> transferencia(@RequestBody TransferenciaDTO tDTO){
		return ResponseEntity.ok(acs.transferencia(tDTO));
	}
	
	@PostMapping("/rendimento/{id}")
	public ResponseEntity<?> transferencia(@PathVariable Long id){
		return ResponseEntity.ok(acs.jurosAccountPoupanca(id));
	}
	
}
