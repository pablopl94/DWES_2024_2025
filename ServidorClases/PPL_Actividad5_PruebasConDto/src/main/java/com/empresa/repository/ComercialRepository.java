package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entidades.Comercial;

@Repository
public interface ComercialRepository extends JpaRepository<Comercial, Integer>{
	
}
