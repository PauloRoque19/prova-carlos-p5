package com.example.banco.account.dto;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.banco.account.domain.Account;



@Component
public class AccountConverterDTO {

	
	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static Account convertToAccount(AccountDTO dto) {
		return modelMapper().map(dto, Account.class);
	}
	
	public static AccountDTO convertToAccountDTO(Account acc) {
		return modelMapper().map(acc, AccountDTO.class);
	}
	
	public static ResultSaqueDTO convertAccountToResultSaqueDTO(SaqueDTO dto) {
		return modelMapper().map(dto, ResultSaqueDTO.class);
	}
	
	public static List<AccountDTO> conveterListAccount(List<AccountDTO> list) {
		return list
				.stream()
				.map(c -> modelMapper().map(c, AccountDTO.class))
				.collect(Collectors.toList());
	}
	
}
