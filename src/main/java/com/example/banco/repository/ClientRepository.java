package com.example.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banco.cliente.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
