package com.rodrigo.book.domain.porta.interfaces;

import java.util.List;

import com.rodrigo.book.domain.dto.BookDto;

public interface BookServicePort {
	
	public BookDto buscaId(Long id);
	public List<BookDto> buscarTodos();
	public BookDto inserir(BookDto bookDto);
	public BookDto atualizar(BookDto bookDto);
	public void deletar(Long id);
	public BookDto disponibilizar(Long id);

}
