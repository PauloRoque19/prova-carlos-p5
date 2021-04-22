package com.example.banco.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.account.domain.Account;
import com.example.banco.account.domain.AccountCorrente;
import com.example.banco.account.domain.AccountPoupanca;
import com.example.banco.account.dto.AccountConverterDTO;
import com.example.banco.account.dto.AccountDTO;
import com.example.banco.account.dto.SaqueDTO;
import com.example.banco.account.dto.TransferenciaDTO;
import com.example.banco.cliente.domain.Client;
import com.example.banco.repository.AccountCorrenteRepository;
import com.example.banco.repository.AccountPoupancaRepositoty;
import com.example.banco.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository apr;
	
	@Autowired
	private AccountPoupancaRepositoty poupRepository;
	
	@Autowired
	private AccountCorrenteRepository corrRepository;
	
	public Account saveOrUpdate(AccountDTO aDto , Client c) {
			if(c == null) {
				throw new RuntimeException("Conta de cliente informada não existe");
			}
		
			if(aDto.getId() == null) {
				if(aDto.getRendimento() != null) {
					AccountPoupanca ap = new AccountPoupanca();
					ap.setAgencia(aDto.getAgencia());
					ap.setNumConta(aDto.getNumConta());
					ap.setRendimento(Double.parseDouble(aDto.getRendimento()));
					ap.setSaldo(Double.parseDouble(aDto.getSaldo()));
					ap.setCliente(c);
					return apr.save(ap);
					
				}else{
					AccountCorrente ac = new AccountCorrente();
					ac.setAgencia(aDto.getAgencia());
					ac.setNumConta(aDto.getNumConta());
					ac.setSaldo(Double.parseDouble(aDto.getSaldo()));
					ac.setCliente(c);
					return apr.save(ac);
				}
			
			}else {
				if(aDto.getRendimento() != null) {
					
					AccountPoupanca ap = this.getAccountPoupancaById(Long.parseLong(aDto.getId()));
					ap.setId(Long.parseLong(aDto.getId()));
					ap.setAgencia(aDto.getAgencia());
					ap.setNumConta(aDto.getNumConta());
					ap.setRendimento(Double.parseDouble(aDto.getRendimento()));
					ap.setSaldo(Double.parseDouble(aDto.getSaldo()));
					ap.setCliente(c);
					return apr.save(ap);
					
				}else{
					
					AccountCorrente ac = this.getAccountCorrenteById(Long.parseLong(aDto.getId()));
					ac.setId(Long.parseLong(aDto.getId()));
					ac.setAgencia(aDto.getAgencia());
					ac.setNumConta(aDto.getNumConta());
					ac.setSaldo(Double.parseDouble(aDto.getSaldo()));
					ac.setCliente(c);
					return apr.save(ac);
				}		
			}
			
		
	}
	
	
	@SuppressWarnings("unused")
	private AccountPoupanca getAccountPoupancaById(Long id) {	
		AccountPoupanca ap = poupRepository.findById(id).get();
		return ap;
	}
	
	@SuppressWarnings("unused")
	private AccountCorrente getAccountCorrenteById(Long id) {
		AccountCorrente ap = corrRepository.findById(id).get();
		return ap;
	}
	
	public Account findAccount(Long id, String type) {
		if(type.equals("p")) {
			return getAccountPoupancaById(id);
		
		}else{
			return getAccountCorrenteById(id);
		}
	}
	
	
	public void removeAccount(Long id, String type) {
		if(type.equals("p")) {
			poupRepository.deleteById(id);
		}else {
			corrRepository.deleteById(id);
		}
	}
	
	
	
	public List<Account> listAllAccount(){
		
		List<Account> listAllAccounts = new ArrayList<>();
			
		List<AccountPoupanca> listAccountPoupanca = poupRepository.findAll();
		List<AccountCorrente> listAccountCorrente = corrRepository.findAll();
		
		listAccountPoupanca.forEach(account -> listAllAccounts.add(account));
		listAccountCorrente.forEach(account -> listAllAccounts.add(account));
			
		return listAllAccounts;
	}
	
	
	public AccountDTO saque(SaqueDTO dto) {
		
		Account ac  = this.findAccount(Long.parseLong(dto.getId()), dto.getTypeConta());
		if(ac == null) {
			throw new RuntimeException("Conta não encontrada");
		}
		
		ac.setSaldo(ac.getSaldo() - Double.parseDouble(dto.getValor()));	
		apr.save(ac);
		
		return AccountConverterDTO.convertToAccountDTO(this.findAccount(Long.parseLong(dto.getId()), dto.getTypeConta()));
	}
	
	@Transactional()
	public String transferencia(TransferenciaDTO dto) {
		Account acOrigem  = findAccount(Long.parseLong(dto.getNumContaOrigem()), dto.getTypeContaOrigem());
		Account acDestino = findAccount(Long.parseLong(dto.getNumContaDestino()), dto.getTypeContaDestino());
		if(acOrigem.getSaldo() > Double.parseDouble(dto.getValor())) {		
			acOrigem.setSaldo(acOrigem.getSaldo() - Double.parseDouble(dto.getValor()));
			apr.save(acOrigem);
			
			acDestino.setSaldo(acDestino.getSaldo() + Double.parseDouble(dto.getValor()));
			apr.save(acDestino);
			return "Operação realizada com Sucesso";
		}else {
			return  "Saldo abaixo do valor a depositar";
		}
		
			
	}
	
	public String jurosAccountPoupanca(Long id) {
		Account ap = findAccount(id, "p");
		System.out.println(ap.getSaldo());
		ap.setSaldo((ap.getSaldo() * 0.2 * 1) / 100 + ap.getSaldo());
		
		String ValorAtual = "Saldo Atual com Juros: " +  String.valueOf(ap.getSaldo());
		
		return ValorAtual;
		
	}
	

}
