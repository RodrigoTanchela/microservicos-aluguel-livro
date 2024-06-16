package com.rodrigo.book.domain.dto;

import java.util.Date;

public record BookDto(Long id, String titulo, String descricao, Double preco, Boolean digital, Boolean disponivel, Date dataDisponivel) {
	
	
}
