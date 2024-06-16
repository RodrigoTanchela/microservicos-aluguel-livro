package com.rodrigo.aluguel.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rodrigo.aluguel.model.Emprestimo;

import jakarta.transaction.Transactional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
	
	@Transactional
    @Modifying
    @Query("UPDATE Emprestimo e SET e.diaDevolucao = :diaDevolucao WHERE e.id = :id")
    void devolucaoEmprestimo(@Param("id") Long id, @Param("diaDevolucao") Date diaDevolucao);
}
