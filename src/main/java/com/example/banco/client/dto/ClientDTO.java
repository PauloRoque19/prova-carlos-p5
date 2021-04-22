package com.example.banco.client.dto;


import lombok.Data;

@Data
public class ClientDTO {
	
	private String id;

	private String nome;

	private String phone;
	
	private String endereco;

	private String email;

	private String cnpj;
	
	private String cpf;
	
	private String clientType;

	
}
