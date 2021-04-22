package com.example.banco.account.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferenciaDTO {

	private String numContaOrigem;
	private String typeContaOrigem;
	private String numContaDestino;
	private String typeContaDestino;
	private String valor;
}
