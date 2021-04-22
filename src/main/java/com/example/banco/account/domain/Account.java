package com.example.banco.account.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.example.banco.cliente.domain.Client;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numConta;
	
	private String agencia;
	
	@OneToOne
	@JoinColumn(name = "cliente_id" , nullable = false)
	private Client cliente;
	
	private double saldo;
	
	
	
}
