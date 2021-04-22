package com.example.banco.cliente.domain;

import lombok.Getter;



@Getter
public enum ClientEnum {

	Fisico(0) , Juridico(1);
	
	private int valor;
	
	ClientEnum(int i) {
		valor = i;
	}

	
	
	
}
