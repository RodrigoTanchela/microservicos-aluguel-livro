package com.rodrigo.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rodrigo.pessoa.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query("SELECT p FROM Pessoa p WHERE p.username = :username")
	Pessoa findByUsername(@Param("username") String username);

}
