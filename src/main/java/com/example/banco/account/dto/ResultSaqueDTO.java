package com.example.banco.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultSaqueDTO {

	private String numConta;
	
	private String agencia;
	
	private String saldo;
	
	private String saldoAnterior;
	
	private String clienteNome;
	
}
