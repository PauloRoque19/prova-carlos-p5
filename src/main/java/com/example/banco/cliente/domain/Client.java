package com.example.banco.cliente.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.example.banco.account.domain.Account;
import com.example.banco.account.domain.AccountCorrente;
import com.example.banco.account.domain.AccountPoupanca;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String nome;
	
	@Column(length = 12)
	private String phone;
	
	@Column
	private String endereco;
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 14, unique = true)
	private String cnpj;
	
	@Column(length = 11 , unique = true)
	private String cpf;
	
	@Column
	private ClientEnum clientType;
	
	
}
