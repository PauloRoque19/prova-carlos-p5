package com.example.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banco.account.domain.Account;
import com.example.banco.account.domain.AccountCorrente;

public interface AccountCorrenteRepository extends JpaRepository<AccountCorrente, Long>{


	
}
