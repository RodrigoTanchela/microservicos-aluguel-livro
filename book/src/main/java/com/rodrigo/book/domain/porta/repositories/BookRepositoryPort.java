package com.rodrigo.book.domain.porta.repositories;

import java.util.List;

import com.rodrigo.book.domain.Book;

public interface BookRepositoryPort {
	
	public Book buscaId(Long id);
	public List<Book> buscarTodos();
	public Book save(Book book);
	public Book atualizar(Book book);
	public Book deletar(Book book);
	public Book disponibilizar(Long id, boolean disponibilidade);
}
