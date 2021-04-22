package com.example.banco.account.domain;



import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
public class AccountPoupanca extends Account implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double rendimento;
}
