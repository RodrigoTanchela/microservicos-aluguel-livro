package com.rodrigo.multa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigo.multa.model.Multa;

public interface MultaRepository extends JpaRepository<Multa, Long> {

}
