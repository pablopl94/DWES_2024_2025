package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
