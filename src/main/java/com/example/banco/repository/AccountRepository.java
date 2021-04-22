package com.example.banco.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.banco.account.domain.Account;
import com.example.banco.account.domain.AccountCorrente;
import com.example.banco.account.domain.AccountPoupanca;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


//	@Query(value = "select * from account_corrente a where a.id = :id" , nativeQuery = true)
//	public AccountCorrente buscarCorrenteId(Long id);
//	
//	@Query(value = "select * from account_poupanca a where a.id = :id" , nativeQuery = true)
//	public AccountPoupanca buscarPoupancaId(Long id);
	
	
}
